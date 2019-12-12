package com.hsh.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtil {

    public static String getCurrentDate() {
        Date date = new Date();
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

}
