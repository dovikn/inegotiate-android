package com.amazonaws.javax.xml.stream.xerces.util;

import com.amazonaws.javax.xml.stream.xerces.xni.XMLString;

public class XMLStringBuffer extends XMLString {
    public static final int DEFAULT_SIZE = 32;

    public XMLStringBuffer() {
        this((int) DEFAULT_SIZE);
    }

    public XMLStringBuffer(int size) {
        this.ch = new char[size];
    }

    public XMLStringBuffer(char c) {
        this(1);
        append(c);
    }

    public XMLStringBuffer(String s) {
        this(s.length());
        append(s);
    }

    public XMLStringBuffer(char[] ch, int offset, int length) {
        this(length);
        append(ch, offset, length);
    }

    public XMLStringBuffer(XMLString s) {
        this(s.length);
        append(s);
    }

    public void clear() {
        this.offset = 0;
        this.length = 0;
    }

    public void append(char c) {
        if (this.length + 1 > this.ch.length) {
            int newLength = this.ch.length * 2;
            if (newLength < this.ch.length + DEFAULT_SIZE) {
                newLength = this.ch.length + DEFAULT_SIZE;
            }
            char[] tmp = new char[newLength];
            System.arraycopy(this.ch, 0, tmp, 0, this.length);
            this.ch = tmp;
        }
        this.ch[this.length] = c;
        this.length++;
    }

    public void append(String s) {
        int length = s.length();
        if (this.length + length > this.ch.length) {
            int newLength = this.ch.length * 2;
            if (newLength < (this.ch.length + length) + DEFAULT_SIZE) {
                newLength = (this.ch.length + length) + DEFAULT_SIZE;
            }
            char[] newch = new char[newLength];
            System.arraycopy(this.ch, 0, newch, 0, this.length);
            this.ch = newch;
        }
        s.getChars(0, length, this.ch, this.length);
        this.length += length;
    }

    public void append(char[] ch, int offset, int length) {
        if (this.length + length > this.ch.length) {
            int newLength = this.ch.length * 2;
            if (newLength < (this.ch.length + length) + DEFAULT_SIZE) {
                newLength = (this.ch.length + length) + DEFAULT_SIZE;
            }
            char[] newch = new char[newLength];
            System.arraycopy(this.ch, 0, newch, 0, this.length);
            this.ch = newch;
        }
        if (ch != null && length > 0) {
            System.arraycopy(ch, offset, this.ch, this.length, length);
            this.length += length;
        }
    }

    public void append(XMLString s) {
        append(s.ch, s.offset, s.length);
    }
}
