package com.exlab.common;

import java.util.HashMap;
import java.util.Map;

public class DataForApi {

    /**
     * Register New User with Name, Email, Password etc.
     * {
     * "name": "aFm",
     * "email": "dev@krafttechexlab.com",
     * "password": "123467",
     * "about": "About Me",
     * "terms": "10"
     * }
     */

    public static Map<String, Object> registerUserBody(String name,String email,String password, String about, String terms) {
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("name",name);
        requestMap.put("email",email);
        requestMap.put("password",password);
        requestMap.put("about",about);
        requestMap.put("terms",terms);
        return requestMap;
    }
}
