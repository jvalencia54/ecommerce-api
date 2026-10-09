package com.ecommerce.demo.login.enums;

public enum Role {
    ADMIN(1, "ROLE_ADMIN"),
    CLIENTE(2, "ROLE_CLIENTE");

    private final int id;
    private final String authority;

    Role(int id, String authority) {
        this.id = id;
        this.authority = authority;
    }

    public int getId() {
        return id;
    }

    public String getAuthority() {
        return authority;
    }

    public static Role fromId(int id) {
        for (Role role : Role.values()) {
            if (role.getId() == id) {
                return role;
            }
        }
        throw new IllegalArgumentException("ID de rol no válido: " + id);
    }
}
