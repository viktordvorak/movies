package cz.dvorakv.constant;

public enum UserRole {

    ADMIN,
    USER;

    public static UserRole[] getValues() {
        return new UserRole[] {ADMIN, USER};
    }

}
