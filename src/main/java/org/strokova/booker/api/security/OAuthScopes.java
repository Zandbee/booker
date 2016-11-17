package org.strokova.booker.api.security;

/**
 * 17.11.2016.
 */
public enum OAuthScopes {
    READ("read"),
    WRITE("write"),
    TRUST("trust");

    private String name;

    OAuthScopes(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
