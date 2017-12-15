package com.study.dwika.kplchat.data.network;

/**
 * Created by A.I on 02/12/2017.
 */

public class APIEndPoint {

    //Base url ketik di sini
    public static final String BASE_URL = "http://10.107.255.221/chat-api/api/";

    // address url selanjutnya ketik di sini
    public static final String LOGIN = BASE_URL + "authenticate";
    public static final String REGISTER = "register";
    public static final String AUTHENTICATED_USER = BASE_URL + "authenticated_user";

}
