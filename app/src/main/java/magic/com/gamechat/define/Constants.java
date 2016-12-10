package magic.com.gamechat.define;



/**
 * Created by Chris.Wu on 2016/10/21.
 */
public class Constants {
    //    public static final String SERVER_URL = "http://192.168.0.10:3000";
    public static String SERVER_URL = "http://172.18.2.42:81";
//public static final String SERVER_URL = "http://172.18.2.42:3000";
//public static final String SERVER_URL = "http://54.249.42.33:81";

    //socket
    public static final String CHAT_NAMESPACE_SOCKET = "/chat";
    public static final String CHAT_MESSAGE_EVENT_SOCKET = "chatMessage";
    public static final String CHAT_IMAGE_EVENT_SOCKET = "chatImage";
    public static final String SYSTEM_EVENT_SOCKET = "systemMessage";


    //chat fragment
    public static final String FIND_EVENT = "findType";
    public static final String MALE_BACKGROUND_COLOR = "#b3a5f6";
    public static final String MALE_TEXT_COLOR = "#ff0000";
    public static final String FEMALE_BACKGROUND_COLOR = "#43ea5f";
    public static final String FEMALE_TEXT_COLOR = "#0000ff";
    public static final int MESSAGE_TEXT_SIZE = 25;
    public static final int PHOTO = 99;
    public static final int BITMAP_COMPRESS_RATIO = 50;

    //sqlite
    public static final String DATABASE_NAME = "gameChatDB";
    public static final int DATABASE_VERSION = 1;
    public static final String ID_SQL = "id";
    //table user
    public static final String TABLE_USER_SQL = "User";
    public static final String USER_ID_SQL = "UserId";
    public static final String USER_USERNAME_SQL = "username";
    public static final String USER_PASSWORD_SQL = "password";
    public static final String USER_PROJECT_NAME_SQL = "projectName";
    public static final String USER_SERVER_TOKEN_SQL = "serverToken";
    public static final String USER_SERVER_IP_SQL = "ip";
    public static final String USER_TABLE_CREATE_SQL = "CREATE TABLE "
            + TABLE_USER_SQL + " ( "
            + USER_USERNAME_SQL + " text not null ," + USER_PASSWORD_SQL + " text not null ,"
            + USER_PROJECT_NAME_SQL + " text not null ,"+ USER_SERVER_TOKEN_SQL + " text not null ,"
            + USER_SERVER_IP_SQL + " text not null ,"
            + "CONSTRAINT "+USER_ID_SQL+" PRIMARY KEY ("+USER_USERNAME_SQL+","+USER_PROJECT_NAME_SQL+","+USER_SERVER_IP_SQL+")); ";

    //table system info
    public static final String TABLE_SYSTEM_INFO_SQL = "SystemInfo";
    public static final String SYSTEM_INFO_FIREBASE_TOKEN_SQL = "firebaseToken";
    public static final String SYSTEM_INFO_LAST_IP_SQL = "lastIP";
    public static final String SYSTEM_INFO_LAST_USERNAME_SQL = "lastUsername";
    public static final String SYSTEM_INFO_LAST_PASSWORD_SQL = "lastPassword";
    public static final String SYSTEM_INFO_LAST_PROJECT_NAME_SQL = "lastProjectName";
    public static final String SYSTEM_INFO_IS_LOGIN_NAME_SQL = "isLogin";


    public static final String SYSTEM_INFO_TABLE_CREATE_SQL = "CREATE TABLE "
            + TABLE_SYSTEM_INFO_SQL + " ( " + ID_SQL + "  INTEGER primary key autoincrement, "
            + SYSTEM_INFO_FIREBASE_TOKEN_SQL + " text not null,"
            + SYSTEM_INFO_IS_LOGIN_NAME_SQL + " text not null,"
            + SYSTEM_INFO_LAST_USERNAME_SQL + " text not null,"
            + SYSTEM_INFO_LAST_PASSWORD_SQL + " text not null"
            +"); ";

    //table server
    public static final String TABLE_SERVER_SQL = "Server";
    public static final String SERVER_IP_SQL = "ip";
    public static final String SERVER_FIREBASE_TOKEN_ENABLE_SQL = "firebaseTokenEnable";
    public static final String SERVER_TABLE_CREATE_SQL = "CREATE TABLE "
            + TABLE_SERVER_SQL + " ( "
            + SERVER_IP_SQL + " text not null ,"
            + SERVER_FIREBASE_TOKEN_ENABLE_SQL + " text not null ,"
            +" PRIMARY KEY ("+SERVER_IP_SQL+")); ";

    //table project
    public static final String TABLE_PROJECT_SQL = "Project";
    public static final String PROJECT_ID_SQL = "ProjectId";
    public static final String PROJECT_IP_SQL = "ip";
    public static final String PROJECT_NAME_SQL = "projectName";
    public static final String PROJECT_TABLE_CREATE_SQL = "CREATE TABLE "
            + TABLE_PROJECT_SQL + " ( "
            + PROJECT_IP_SQL + " text not null ,"
            + PROJECT_NAME_SQL + " text not null ,"
            + "CONSTRAINT "+PROJECT_ID_SQL+" PRIMARY KEY ("+PROJECT_IP_SQL+","+PROJECT_NAME_SQL+")); ";

    //util
    public static final String OPEN_BRACE = "{";
    public static final String CLOSE_BRACE = "}";
    public static final String TOKEN = "token";
    public static final String RESULT_REST_API = "resStatus";
    public static final String RES_STRING_REST_API = "resString";
    public static final String ZERO = "0";
    public static final String ONE = "1";
    public static final String HTTP = "http:";
    public static final String HTTPS = "https:";
    public static final String SLASH = "//";
    public static final String ENPTY_STRING = "";
    public static final String IP = "ip";
    public static final String USERNAME = "username";
    public static final String ACCOUNT = "account";
    public static final String PASSWORD = "password";
    public static final String REMEMBER = "remember";
    public static final String TYPE = "type";
    public static final String PROJECT_NAME = "projectName";
    public static final String PROJECT_NAME_1 = "projectName1";
    public static final String PROJECT = "project";
    public static final String TRUE_STRING = "true";
    public static final String FALSE_STRING = "false";
    public static final String FIREBASE_TOKEN = "firebaseToken";
    public static final String DOUBLE_QUOTES="\"";
    public static final String JAVASCRIPT="javascript";
    public static final String IP_LIST="ipList";
    public static final String PROJECT_LIST="projectList";
    public static final String ACCOUNT_LIST="accountList";
    public static final String FUNCTION_LIST="funcList";
    public static final String HTTP_FAIL="{"+DOUBLE_QUOTES+RESULT_REST_API+DOUBLE_QUOTES+":"+ONE+"}";
    public static final String URL="url";
    public static final String START="start";
    public static final String COUNT="count";
    public static final String FILTERS="filters";
    public static final String SORT="sort";
    public static final String COOKIE="Cookie";
    public static final String STATUS = "status";
    public static final String SERVER_TOKEN_TITLE="WDT=";
    public static final String SEMICOLON=";";
    public static final String NODE_LISTS="nodeLists";
    public static final String NODE_NAME="nodeName";
    public static final String TAGS_LIST="tagsList";
    public static final String CONTROL_MODEL="controlModel";

    //time
    public static final int ONE_SECOND_TIME=1000;
    public static final int SCROLL_DELAY_TIME=250;




    //



    //rest api
    public static String READ_PROJECT_LIST_REST_API = SERVER_URL + "/WADashboard/api/dashboard/v1/mainframes/readProjectList";
    public static String GET_WEBACCESS_PROJECT_LIST_API = SERVER_URL + "/WADashboard/api/dashboard/v6/waConfig/getWebAccessProjectList";
//    public static final String READ_PROJECT_LIST_REST_API = SERVER_URL + "/cookie";
    public static String GET_DASHBOARD_TOKEN_REST_API = SERVER_URL + "/WADashboard/api/dashboard/v6/auth/getToken";
    public static String SAVE_MOBILE_INFO_REST_API = SERVER_URL +"/WADashboard/api/dashboard/v1/mobile/saveMobileInfo";
    public static String GET_NODE_LIST_REST_API = SERVER_URL +"/WADashboard/api/dashboard/v1/tagAjaxAction/getNodeList";
    public static String ALARM_ACK_ALL_REST_API = SERVER_URL +"/WADashboard/api/dashboard/v1/tagAjaxAction/alarmAckAll";
    ///WADashboard/api/dashboard/v1/mainframes/readProjectList






}
