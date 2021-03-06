package com.google.common.primitives;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.gdata.util.common.base.StringUtil;
import java.math.BigInteger;
import java.util.Comparator;

@GwtCompatible
@Beta
public final class UnsignedLongs {
    public static final long MAX_VALUE = -1;
    private static final int[] maxSafeDigits;
    private static final long[] maxValueDivs;
    private static final int[] maxValueMods;

    enum LexicographicalComparator implements Comparator<long[]> {
        INSTANCE;

        public int compare(long[] left, long[] right) {
            int minLength = Math.min(left.length, right.length);
            for (int i = 0; i < minLength; i++) {
                if (left[i] != right[i]) {
                    return UnsignedLongs.compare(left[i], right[i]);
                }
            }
            return left.length - right.length;
        }
    }

    private UnsignedLongs() {
    }

    private static long flip(long a) {
        return Long.MIN_VALUE ^ a;
    }

    public static int compare(long a, long b) {
        return Longs.compare(flip(a), flip(b));
    }

    public static long min(long... array) {
        boolean z;
        if (array.length > 0) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z);
        long min = flip(array[0]);
        for (int i = 1; i < array.length; i++) {
            long next = flip(array[i]);
            if (next < min) {
                min = next;
            }
        }
        return flip(min);
    }

    public static long max(long... array) {
        boolean z;
        if (array.length > 0) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z);
        long max = flip(array[0]);
        for (int i = 1; i < array.length; i++) {
            long next = flip(array[i]);
            if (next > max) {
                max = next;
            }
        }
        return flip(max);
    }

    public static String join(String separator, long... array) {
        Preconditions.checkNotNull(separator);
        if (array.length == 0) {
            return StringUtil.EMPTY_STRING;
        }
        StringBuilder builder = new StringBuilder(array.length * 5);
        builder.append(array[0]);
        for (int i = 1; i < array.length; i++) {
            builder.append(separator).append(toString(array[i]));
        }
        return builder.toString();
    }

    public static Comparator<long[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    public static long divide(long dividend, long divisor) {
        int i = 1;
        if (divisor < 0) {
            if (compare(dividend, divisor) < 0) {
                return 0;
            }
            return 1;
        } else if (dividend >= 0) {
            return dividend / divisor;
        } else {
            long quotient = ((dividend >>> 1) / divisor) << 1;
            if (compare(dividend - (quotient * divisor), divisor) < 0) {
                i = 0;
            }
            return ((long) i) + quotient;
        }
    }

    public static long remainder(long dividend, long divisor) {
        if (divisor < 0) {
            if (compare(dividend, divisor) < 0) {
                return dividend;
            }
            return dividend - divisor;
        } else if (dividend >= 0) {
            return dividend % divisor;
        } else {
            long rem = dividend - ((((dividend >>> 1) / divisor) << 1) * divisor);
            if (compare(rem, divisor) < 0) {
                divisor = 0;
            }
            return rem - divisor;
        }
    }

    public static long parseUnsignedLong(String s) {
        return parseUnsignedLong(s, 10);
    }

    public static long parseUnsignedLong(String s, int radix) {
        Preconditions.checkNotNull(s);
        if (s.length() == 0) {
            throw new NumberFormatException("empty string");
        } else if (radix < 2 || radix > 36) {
            throw new NumberFormatException("illegal radix:" + radix);
        } else {
            int max_safe_pos = maxSafeDigits[radix] - 1;
            long value = 0;
            int pos = 0;
            while (pos < s.length()) {
                int digit = Character.digit(s.charAt(pos), radix);
                if (digit == -1) {
                    throw new NumberFormatException(s);
                } else if (pos <= max_safe_pos || !overflowInParse(value, digit, radix)) {
                    value = (((long) radix) * value) + ((long) digit);
                    pos++;
                } else {
                    throw new NumberFormatException("Too large for unsigned long: " + s);
                }
            }
            return value;
        }
    }

    private static boolean overflowInParse(long current, int digit, int radix) {
        if (current < 0) {
            return true;
        }
        if (current < maxValueDivs[radix]) {
            return false;
        }
        if (current > maxValueDivs[radix] || digit > maxValueMods[radix]) {
            return true;
        }
        return false;
    }

    public static String toString(long x) {
        return toString(x, 10);
    }

    public static String toString(long x, int radix) {
        boolean z = radix >= 2 && radix <= 36;
        Preconditions.checkArgument(z, "radix (%s) must be between Character.MIN_RADIX and Character.MAX_RADIX", Integer.valueOf(radix));
        if (x == 0) {
            return "0";
        }
        char[] buf = new char[64];
        int i = buf.length;
        if (x < 0) {
            long top = x >>> 32;
            long bot = (4294967295L & x) + ((top % ((long) radix)) << 32);
            top /= (long) radix;
            while (true) {
                if (bot <= 0 && top <= 0) {
                    break;
                }
                i--;
                buf[i] = Character.forDigit((int) (bot % ((long) radix)), radix);
                bot = (bot / ((long) radix)) + ((top % ((long) radix)) << 32);
                top /= (long) radix;
            }
        } else {
            while (x > 0) {
                i--;
                buf[i] = Character.forDigit((int) (x % ((long) radix)), radix);
                x /= (long) radix;
            }
        }
        return new String(buf, i, buf.length - i);
    }

    static {
        maxValueDivs = new long[37];
        maxValueMods = new int[37];
        maxSafeDigits = new int[37];
        BigInteger overflow = new BigInteger("10000000000000000", 16);
        for (int i = 2; i <= 36; i++) {
            maxValueDivs[i] = divide(MAX_VALUE, (long) i);
            maxValueMods[i] = (int) remainder(MAX_VALUE, (long) i);
            maxSafeDigits[i] = overflow.toString(i).length() - 1;
        }
    }
}
