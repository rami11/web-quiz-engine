package com.rsn.webquizengine.security;

public enum ApplicationUserPermission {
    USER_READ("user:read"),
    USER_WRITE("user:write"),
    QUIZ_READ("quiz:read"),
    QUIZ_WRITE("quiz:write");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }
}
