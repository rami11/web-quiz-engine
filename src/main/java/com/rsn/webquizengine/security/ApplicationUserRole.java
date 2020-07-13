package com.rsn.webquizengine.security;

import java.util.Set;

import static com.rsn.webquizengine.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    ADMIN(Set.of()),
    USER(Set.of(QUIZ_READ, QUIZ_WRITE, USER_READ, USER_WRITE));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }
}
