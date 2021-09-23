package com.ecommerce.domain.user.domain.vo;

public enum UserRole {
    USER("ROLE_USER", "일반 사용자"),
    STUDENT("ROLE_STUDENT", "학생"),
    ARTIST("ROLE_ARTIST", "예술가"),
    ADMIN("ROLE_ADMIN", "관리자");

    private final String key;
    private final String title;

    UserRole(final String key, final String title) {
        this.key = key;
        this.title = title;
    }

    public String getKey() {
        return key;
    }

    public String getTitle() {
        return title;
    }

}
