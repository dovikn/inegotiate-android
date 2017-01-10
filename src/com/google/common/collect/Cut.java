package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Booleans;
import java.io.Serializable;
import java.util.NoSuchElementException;
import javax.annotation.Nullable;
import org.codehaus.jackson.impl.JsonWriteContext;

@GwtCompatible
abstract class Cut<C extends Comparable> implements Comparable<Cut<C>>, Serializable {
    private static final long serialVersionUID = 0;
    final C endpoint;

    /* renamed from: com.google.common.collect.Cut.1 */
    static /* synthetic */ class C04431 {
        static final /* synthetic */ int[] $SwitchMap$com$google$common$collect$BoundType;

        static {
            $SwitchMap$com$google$common$collect$BoundType = new int[BoundType.values().length];
            try {
                $SwitchMap$com$google$common$collect$BoundType[BoundType.CLOSED.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$common$collect$BoundType[BoundType.OPEN.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    private static final class AboveAll extends Cut<Comparable<?>> {
        private static final AboveAll INSTANCE;
        private static final long serialVersionUID = 0;

        static {
            INSTANCE = new AboveAll();
        }

        private AboveAll() {
            super(null);
        }

        Comparable<?> endpoint() {
            throw new IllegalStateException("range unbounded on this side");
        }

        boolean isLessThan(Comparable<?> comparable) {
            return false;
        }

        BoundType typeAsLowerBound() {
            throw new AssertionError("this statement should be unreachable");
        }

        BoundType typeAsUpperBound() {
            throw new IllegalStateException();
        }

        Cut<Comparable<?>> withLowerBoundType(BoundType boundType, DiscreteDomain<Comparable<?>> discreteDomain) {
            throw new AssertionError("this statement should be unreachable");
        }

        Cut<Comparable<?>> withUpperBoundType(BoundType boundType, DiscreteDomain<Comparable<?>> discreteDomain) {
            throw new IllegalStateException();
        }

        void describeAsLowerBound(StringBuilder sb) {
            throw new AssertionError();
        }

        void describeAsUpperBound(StringBuilder sb) {
            sb.append("+\u221e)");
        }

        Comparable<?> leastValueAbove(DiscreteDomain<Comparable<?>> discreteDomain) {
            throw new AssertionError();
        }

        Comparable<?> greatestValueBelow(DiscreteDomain<Comparable<?>> domain) {
            return domain.maxValue();
        }

        public int compareTo(Cut<Comparable<?>> o) {
            return o == this ? 0 : 1;
        }

        private Object readResolve() {
            return INSTANCE;
        }
    }

    private static final class AboveValue<C extends Comparable> extends Cut<C> {
        private static final long serialVersionUID = 0;

        public /* bridge */ /* synthetic */ int compareTo(Object x0) {
            return super.compareTo((Cut) x0);
        }

        AboveValue(C endpoint) {
            super((Comparable) Preconditions.checkNotNull(endpoint));
        }

        boolean isLessThan(C value) {
            return Range.compareOrThrow(this.endpoint, value) < 0;
        }

        BoundType typeAsLowerBound() {
            return BoundType.OPEN;
        }

        BoundType typeAsUpperBound() {
            return BoundType.CLOSED;
        }

        Cut<C> withLowerBoundType(BoundType boundType, DiscreteDomain<C> domain) {
            switch (C04431.$SwitchMap$com$google$common$collect$BoundType[boundType.ordinal()]) {
                case JsonWriteContext.STATUS_OK_AFTER_COMMA /*1*/:
                    C next = domain.next(this.endpoint);
                    return next == null ? Cut.belowAll() : Cut.belowValue(next);
                case JsonWriteContext.STATUS_OK_AFTER_COLON /*2*/:
                    return this;
                default:
                    throw new AssertionError();
            }
        }

        Cut<C> withUpperBoundType(BoundType boundType, DiscreteDomain<C> domain) {
            switch (C04431.$SwitchMap$com$google$common$collect$BoundType[boundType.ordinal()]) {
                case JsonWriteContext.STATUS_OK_AFTER_COMMA /*1*/:
                    return this;
                case JsonWriteContext.STATUS_OK_AFTER_COLON /*2*/:
                    C next = domain.next(this.endpoint);
                    return next == null ? Cut.aboveAll() : Cut.belowValue(next);
                default:
                    throw new AssertionError();
            }
        }

        void describeAsLowerBound(StringBuilder sb) {
            sb.append('(').append(this.endpoint);
        }

        void describeAsUpperBound(StringBuilder sb) {
            sb.append(this.endpoint).append(']');
        }

        C leastValueAbove(DiscreteDomain<C> domain) {
            return domain.next(this.endpoint);
        }

        C greatestValueBelow(DiscreteDomain<C> discreteDomain) {
            return this.endpoint;
        }

        Cut<C> canonical(DiscreteDomain<C> domain) {
            C next = leastValueAbove(domain);
            return next != null ? Cut.belowValue(next) : Cut.aboveAll();
        }

        public int hashCode() {
            return this.endpoint.hashCode() ^ -1;
        }
    }

    private static final class BelowAll extends Cut<Comparable<?>> {
        private static final BelowAll INSTANCE;
        private static final long serialVersionUID = 0;

        static {
            INSTANCE = new BelowAll();
        }

        private BelowAll() {
            super(null);
        }

        Comparable<?> endpoint() {
            throw new IllegalStateException("range unbounded on this side");
        }

        boolean isLessThan(Comparable<?> comparable) {
            return true;
        }

        BoundType typeAsLowerBound() {
            throw new IllegalStateException();
        }

        BoundType typeAsUpperBound() {
            throw new AssertionError("this statement should be unreachable");
        }

        Cut<Comparable<?>> withLowerBoundType(BoundType boundType, DiscreteDomain<Comparable<?>> discreteDomain) {
            throw new IllegalStateException();
        }

        Cut<Comparable<?>> withUpperBoundType(BoundType boundType, DiscreteDomain<Comparable<?>> discreteDomain) {
            throw new AssertionError("this statement should be unreachable");
        }

        void describeAsLowerBound(StringBuilder sb) {
            sb.append("(-\u221e");
        }

        void describeAsUpperBound(StringBuilder sb) {
            throw new AssertionError();
        }

        Comparable<?> leastValueAbove(DiscreteDomain<Comparable<?>> domain) {
            return domain.minValue();
        }

        Comparable<?> greatestValueBelow(DiscreteDomain<Comparable<?>> discreteDomain) {
            throw new AssertionError();
        }

        Cut<Comparable<?>> canonical(DiscreteDomain<Comparable<?>> domain) {
            Cut<Comparable<?>> belowValue;
            try {
                belowValue = Cut.belowValue(domain.minValue());
            } catch (NoSuchElementException e) {
            }
            return belowValue;
        }

        public int compareTo(Cut<Comparable<?>> o) {
            return o == this ? 0 : -1;
        }

        private Object readResolve() {
            return INSTANCE;
        }
    }

    private static final class BelowValue<C extends Comparable> extends Cut<C> {
        private static final long serialVersionUID = 0;

        public /* bridge */ /* synthetic */ int compareTo(Object x0) {
            return super.compareTo((Cut) x0);
        }

        BelowValue(C endpoint) {
            super((Comparable) Preconditions.checkNotNull(endpoint));
        }

        boolean isLessThan(C value) {
            return Range.compareOrThrow(this.endpoint, value) <= 0;
        }

        BoundType typeAsLowerBound() {
            return BoundType.CLOSED;
        }

        BoundType typeAsUpperBound() {
            return BoundType.OPEN;
        }

        Cut<C> withLowerBoundType(BoundType boundType, DiscreteDomain<C> domain) {
            switch (C04431.$SwitchMap$com$google$common$collect$BoundType[boundType.ordinal()]) {
                case JsonWriteContext.STATUS_OK_AFTER_COMMA /*1*/:
                    return this;
                case JsonWriteContext.STATUS_OK_AFTER_COLON /*2*/:
                    Cut belowAll;
                    C previous = domain.previous(this.endpoint);
                    if (previous == null) {
                        belowAll = Cut.belowAll();
                    } else {
                        belowAll = new AboveValue(previous);
                    }
                    return belowAll;
                default:
                    throw new AssertionError();
            }
        }

        Cut<C> withUpperBoundType(BoundType boundType, DiscreteDomain<C> domain) {
            switch (C04431.$SwitchMap$com$google$common$collect$BoundType[boundType.ordinal()]) {
                case JsonWriteContext.STATUS_OK_AFTER_COMMA /*1*/:
                    C previous = domain.previous(this.endpoint);
                    return previous == null ? Cut.aboveAll() : new AboveValue(previous);
                case JsonWriteContext.STATUS_OK_AFTER_COLON /*2*/:
                    return this;
                default:
                    throw new AssertionError();
            }
        }

        void describeAsLowerBound(StringBuilder sb) {
            sb.append('[').append(this.endpoint);
        }

        void describeAsUpperBound(StringBuilder sb) {
            sb.append(this.endpoint).append(')');
        }

        C leastValueAbove(DiscreteDomain<C> discreteDomain) {
            return this.endpoint;
        }

        C greatestValueBelow(DiscreteDomain<C> domain) {
            return domain.previous(this.endpoint);
        }

        public int hashCode() {
            return this.endpoint.hashCode();
        }
    }

    abstract void describeAsLowerBound(StringBuilder stringBuilder);

    abstract void describeAsUpperBound(StringBuilder stringBuilder);

    abstract C greatestValueBelow(DiscreteDomain<C> discreteDomain);

    abstract boolean isLessThan(C c);

    abstract C leastValueAbove(DiscreteDomain<C> discreteDomain);

    abstract BoundType typeAsLowerBound();

    abstract BoundType typeAsUpperBound();

    abstract Cut<C> withLowerBoundType(BoundType boundType, DiscreteDomain<C> discreteDomain);

    abstract Cut<C> withUpperBoundType(BoundType boundType, DiscreteDomain<C> discreteDomain);

    Cut(@Nullable C endpoint) {
        this.endpoint = endpoint;
    }

    Cut<C> canonical(DiscreteDomain<C> discreteDomain) {
        return this;
    }

    public int compareTo(Cut<C> that) {
        if (that == belowAll()) {
            return 1;
        }
        if (that == aboveAll()) {
            return -1;
        }
        int result = Range.compareOrThrow(this.endpoint, that.endpoint);
        return result == 0 ? Booleans.compare(this instanceof AboveValue, that instanceof AboveValue) : result;
    }

    C endpoint() {
        return this.endpoint;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Cut)) {
            return false;
        }
        try {
            if (compareTo((Cut) obj) == 0) {
                return true;
            }
            return false;
        } catch (ClassCastException e) {
            return false;
        }
    }

    static <C extends Comparable> Cut<C> belowAll() {
        return BelowAll.INSTANCE;
    }

    static <C extends Comparable> Cut<C> aboveAll() {
        return AboveAll.INSTANCE;
    }

    static <C extends Comparable> Cut<C> belowValue(C endpoint) {
        return new BelowValue(endpoint);
    }

    static <C extends Comparable> Cut<C> aboveValue(C endpoint) {
        return new AboveValue(endpoint);
    }
}
