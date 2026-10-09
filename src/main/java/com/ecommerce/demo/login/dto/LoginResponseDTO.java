package com.ecommerce.demo.login.dto;

public class LoginResponseDTO {

    private String token;
    private UserData user;

    public LoginResponseDTO(String token, String username, String role) {
        this.token = token;
        this.user = new UserData(username, role);
    }

    public String getToken() { return token; }
    public UserData getUser() { return user; }

    public static class UserData {
        private String username;
        private String role;

        public UserData(String username, String role) {
            this.username = username;
            this.role = role;
        }

        public String getUsername() { return username; }
        public String getRole() { return role; }
    }
}
