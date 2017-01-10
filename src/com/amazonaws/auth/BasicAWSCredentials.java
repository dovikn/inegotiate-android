package com.amazonaws.auth;

public class BasicAWSCredentials implements AWSCredentials {
    private final String accessKey;
    private final String secretKey;

    public BasicAWSCredentials(String str, String str2) {
        if (str == null) {
            throw new IllegalArgumentException("Access key cannot be null.");
        } else if (str2 == null) {
            throw new IllegalArgumentException("Secret key cannot be null.");
        } else {
            this.accessKey = str;
            this.secretKey = str2;
        }
    }

    public String getAWSAccessKeyId() {
        return this.accessKey;
    }

    public String getAWSSecretKey() {
        return this.secretKey;
    }
}
