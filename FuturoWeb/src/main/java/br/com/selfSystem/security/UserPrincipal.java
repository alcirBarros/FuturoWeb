package br.com.selfSystem.security;

import java.security.Principal;

public class UserPrincipal implements Principal {

    private final String name;

    public UserPrincipal(String name) {
        super();
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
