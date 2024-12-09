package ru.otus.hw6.user.domain;

public class UserProfile {
    private long userId;
    private String role;

    public UserProfile(int userId, String role) {
        this.userId = userId;
        this.role = role;
    }

    public long getUserId() {
        return userId;
    }

    public String getRole() {
        return role;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
