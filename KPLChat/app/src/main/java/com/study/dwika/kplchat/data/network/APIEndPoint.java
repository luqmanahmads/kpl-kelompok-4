package com.study.dwika.kplchat.data.network;

/**
 * Created by A.I on 15/12/2017.
 */

public class ApiEndPoint {

    //Base url ketik di sini
    public static final String BASE_URL = "http://10.107.255.221/chat-api/api/";

    // address url selanjutnya ketik di sini
    public static final String LOGIN = BASE_URL + "authenticate";
    public static final String GET_FRIEND = BASE_URL + "friends";
}