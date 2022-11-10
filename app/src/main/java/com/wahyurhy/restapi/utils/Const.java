package com.wahyurhy.restapi.utils;

import java.util.HashMap;
import java.util.Map;

public class Const {
    public static Map<String, String> API_KEY;
    public static Map<String, String> LANGUAGE;
    static {
        API_KEY = new HashMap<>();
        API_KEY.put("api_key", "f3aa39c23e3c246fc689906fcddb40b5");

        LANGUAGE = new HashMap<>();
        LANGUAGE.put("language", "en");
    }

}
