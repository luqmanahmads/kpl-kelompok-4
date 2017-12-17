package com.study.dwika.kplchat.data.network;

/**
 * Created by A.I on 02/12/2017.
 */

public class APIEndPoint {

    //Base url ketik di sini
    //public static final String BASE_URL = "http://classified5.me/chat-api/api/";
    public static final String BASE_URL = "http://a08f0a7b.ngrok.io/chat-api/api/";

    // address url selanjutnya ketik di sini
    public static final String LOGIN = BASE_URL + "authenticate";
    public static final String AUTHENTICATED_USER = BASE_URL + "authenticated_user";
    public static final String SEARCH_USER_BY_EMAIL = BASE_URL + "search_by_email";
    public static final String ADD_FRIEND = BASE_URL + "friends/add/";
}
