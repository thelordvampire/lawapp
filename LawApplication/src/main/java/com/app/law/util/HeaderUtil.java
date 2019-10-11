package com.app.law.util;

import com.app.law.entity.User;
import com.app.law.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class HeaderUtil {

    @Autowired
    private static IUserService IUserService;

    public static HttpHeaders createNotAuthenHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("My-Authorized", "You not logined");
        return headers;
    }

    public HttpHeaders createNotAdminPermissionHeader() {
        User user = IUserService.getLoginedUser();
        HttpHeaders headers = createNotAuthenHeader();
        if(headers == null) {
            headers = new HttpHeaders();
//            if(user.getRole().getId() != Constant.UserRole.ADMIN) {
//                headers.add("My-Authorized", "You not have admin permission");
//                return headers;
//            }
        }
        return headers;
    }
}
