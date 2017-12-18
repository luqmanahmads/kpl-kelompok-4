package com.study.dwika.kplchat.data.network;

/**
 * Created by A.I on 15/12/2017.
 */

public class ApiEndPoint {

    //Base url ketik di sini
    public static final String BASE_URL = "http://classified5.me/chat-api/api/";
//    public static final String BASE_URL = "http://35.198.231.167/chat-api/api/";
//    public static final String BASE_URL = "http://10.107.255.221/chat-api/api/";
//    public static final String BASE_URL = "http://a08f0a7b.ngrok.io/chat-api/api/";
//    public static final String BASE_URL = "http://da02ca44.ngrok.io/chat-api/api/";
//    public static final String BASE_URL = "http://10.151.13.21/chat-api/api/";
//    public static final String BASE_URL = "http://da02ca44.ngrok.io/chat-api/api/";

    // address url selanjutnya ketik di sini
    public static final String LOGIN = BASE_URL + "authenticate";

    public static final String REGISTER = "register";
    public static final String AUTHENTICATED_USER = BASE_URL + "authenticated_user";
    public static final String SEARCH_USER_BY_EMAIL = BASE_URL + "user/find?email={email}";
    public static final String ADD_FRIEND = BASE_URL + "friends/add/{id}";
    public static final String CONVERSATION_DETAIL = BASE_URL + "conversation/{id}";
    public static final String GET_FRIEND = BASE_URL + "friends";
    public static final String SEND_CHAT = BASE_URL + "conversation/{id}";
    public static final String GET_CHAT = BASE_URL + "conversation";
    public static final String AVAILABLE_FRIENDS_TO_ADD = BASE_URL + "conversation/{convId}/participant/add";
    public static final String ADD_TO_CONVERSATION = BASE_URL + "conversation/{convId}/participant/add/{userId}";

}