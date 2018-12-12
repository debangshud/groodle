package org.acacia.sample.springboot.auth.server.config;

public enum AuthorizationGrantType {

    REFRESH_TOKEN("refresh_token"),
    AUTHORIZATION_CODE("authorization_code"),
    IMPLICIT("implicit"),
    PASSWORD("password"),
    CLIENT_CREDENTIAL("client_credential");

    private final String type;

    AuthorizationGrantType(String type) {
        this.type = type;
    }

    public boolean equalsName(String otherType) {
        return type.equals(otherType);
    }

    @Override
    public String toString() {
        return this.type;
    }
}
