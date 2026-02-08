package user;

import utils.PropertyReader;

public class UserFactory {

    public static User withAdminPermission() {
        return new User(PropertyReader.getProperty("sausedemo.admin_user"),
                PropertyReader.getProperty("sausedemo.password"));
    }

    public static User withLockedPermission() {
        return new User(PropertyReader.getProperty("sausedemo.locked_user"),
                PropertyReader.getProperty("sausedemo.password"));
    }
}
