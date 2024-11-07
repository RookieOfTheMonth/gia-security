package iisi.example.gia.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class SecurityUtil {
    public static User getLoginUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
