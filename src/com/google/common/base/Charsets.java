package com.google.common.base;

import java.nio.charset.Charset;

public final class Charsets {
    public static final Charset ISO_8859_1;
    public static final Charset US_ASCII;
    public static final Charset UTF_16;
    public static final Charset UTF_16BE;
    public static final Charset UTF_16LE;
    public static final Charset UTF_8;

    private Charsets() {
    }

    static {
        US_ASCII = Charset.forName(StringEncodings.US_ASCII);
        ISO_8859_1 = Charset.forName("ISO-8859-1");
        UTF_8 = Charset.forName(StringEncodings.UTF8);
        UTF_16BE = Charset.forName("UTF-16BE");
        UTF_16LE = Charset.forName("UTF-16LE");
        UTF_16 = Charset.forName("UTF-16");
    }
}
