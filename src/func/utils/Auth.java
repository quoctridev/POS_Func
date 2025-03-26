package func.utils;

import func.entity.UserEntity;

public class Auth {

    public static UserEntity user = null;

    public static void clear() {
        Auth.user = null;
    }

    public static boolean isLogin() {
        return Auth.user != null;
    }

    public static int isRole() {
        switch (user.getRole()) {
            case "admin":
                return 1;
            case "cashier":
                return 2;
            case "chef":
                return 3;
            default:
                return 0;
        }
    }
}
