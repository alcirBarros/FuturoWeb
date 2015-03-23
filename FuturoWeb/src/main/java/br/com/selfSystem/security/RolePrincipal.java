package br.com.selfSystem.security;

import java.security.Principal;

public class RolePrincipal implements Principal {

    private final String name;

    public RolePrincipal(String name) {
        super();
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
