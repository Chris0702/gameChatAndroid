package magic.com.gamechat.tool;

import android.util.Log;

import magic.com.gamechat.define.Constants;
import magic.com.gamechat.role.SystemInfo;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;


/**
 * Created by Chris.Wu on 2016/11/21.
 */

public class StringProcess {

//    public static String addProjectNameToUrl(String url,String projectName)
//    {
//        String result= url+"/"+projectName;
//        return result;
//    }
//
//    public static String getChangePageURL(String controllerType) {
//        String result = "{" + Constants.DOUBLE_QUOTES + "url" + Constants.DOUBLE_QUOTES + ":" + controllerType + "}";
//        return result;
//    }
//
//    public static String getCookieString(String serverToken) {
//        return "WDT="+serverToken;
//    }
//
//
//    public static String getAlarmSummaryLogSocketMsg(String projectName, String start, String count, String filters, String sort) {
//        String result = "{" +
//                Constants.DOUBLE_QUOTES + "projectName1" + Constants.DOUBLE_QUOTES + ":" + Constants.DOUBLE_QUOTES + projectName + Constants.DOUBLE_QUOTES + "," +
//                Constants.DOUBLE_QUOTES + "Widget" + Constants.DOUBLE_QUOTES + ":" + Constants.DOUBLE_QUOTES + "alarmSummaryAndCount" + Constants.DOUBLE_QUOTES + "," +
//                Constants.DOUBLE_QUOTES + "Widgetlid" + Constants.DOUBLE_QUOTES + ":" + Constants.DOUBLE_QUOTES + "ALARM_SUMMARY_WIDGETLID" + Constants.DOUBLE_QUOTES + "," +
//                Constants.DOUBLE_QUOTES + "widPushinterval" + Constants.DOUBLE_QUOTES + ":" + Constants.DOUBLE_QUOTES + "0" + Constants.DOUBLE_QUOTES + "," +
//                Constants.DOUBLE_QUOTES + "count" + Constants.DOUBLE_QUOTES + ":" + Constants.DOUBLE_QUOTES + count + Constants.DOUBLE_QUOTES + "," +
//                Constants.DOUBLE_QUOTES + "start" + Constants.DOUBLE_QUOTES + ":" + Constants.DOUBLE_QUOTES + start + Constants.DOUBLE_QUOTES +
//                "}";
//        return result;
//    }
//
//    public static String getAlarmLogSocketMsg(String projectName,String start,String count,String nodeName) {
//        String result = "{" +
//                Constants.DOUBLE_QUOTES + "projectName1" + Constants.DOUBLE_QUOTES + ":" + Constants.DOUBLE_QUOTES + projectName + Constants.DOUBLE_QUOTES + "," +
//                Constants.DOUBLE_QUOTES + "Widget" + Constants.DOUBLE_QUOTES + ":" + Constants.DOUBLE_QUOTES + "alarmLogAndCount" + Constants.DOUBLE_QUOTES + "," +
//                Constants.DOUBLE_QUOTES + "Widgetlid" + Constants.DOUBLE_QUOTES + ":" + Constants.DOUBLE_QUOTES + "ALARM_WIDGETLID" + Constants.DOUBLE_QUOTES + "," +
//                Constants.DOUBLE_QUOTES + "widPushinterval" + Constants.DOUBLE_QUOTES + ":" + Constants.DOUBLE_QUOTES + "0" + Constants.DOUBLE_QUOTES + "," +
//                Constants.DOUBLE_QUOTES + "count" + Constants.DOUBLE_QUOTES + ":" + Constants.DOUBLE_QUOTES + count + Constants.DOUBLE_QUOTES + "," +
//                Constants.DOUBLE_QUOTES + "nodeName" + Constants.DOUBLE_QUOTES + ":" + Constants.DOUBLE_QUOTES + nodeName + Constants.DOUBLE_QUOTES + "," +
//                Constants.DOUBLE_QUOTES + "start" + Constants.DOUBLE_QUOTES + ":" + Constants.DOUBLE_QUOTES + start + Constants.DOUBLE_QUOTES +
//                "}";
//        return result;
//    }
//
//    public static String getActionLogSocketMsg(String projectName,String start,String count,String nodeName) {
//        String result = "{" +
//                Constants.DOUBLE_QUOTES + "projectName1" + Constants.DOUBLE_QUOTES + ":" + Constants.DOUBLE_QUOTES + projectName + Constants.DOUBLE_QUOTES + "," +
//                Constants.DOUBLE_QUOTES + "Widget" + Constants.DOUBLE_QUOTES + ":" + Constants.DOUBLE_QUOTES + "alarmLogAndCount" + Constants.DOUBLE_QUOTES + "," +
//                Constants.DOUBLE_QUOTES + "Widgetlid" + Constants.DOUBLE_QUOTES + ":" + Constants.DOUBLE_QUOTES + "ALARM_WIDGETLID" + Constants.DOUBLE_QUOTES + "," +
//                Constants.DOUBLE_QUOTES + "widPushinterval" + Constants.DOUBLE_QUOTES + ":" + Constants.DOUBLE_QUOTES + "0" + Constants.DOUBLE_QUOTES + "," +
//                Constants.DOUBLE_QUOTES + "count" + Constants.DOUBLE_QUOTES + ":" + Constants.DOUBLE_QUOTES + count + Constants.DOUBLE_QUOTES + "," +
//                Constants.DOUBLE_QUOTES + "nodeName" + Constants.DOUBLE_QUOTES + ":" + Constants.DOUBLE_QUOTES + nodeName + Constants.DOUBLE_QUOTES + "," +
//                Constants.DOUBLE_QUOTES + "start" + Constants.DOUBLE_QUOTES + ":" + Constants.DOUBLE_QUOTES + start + Constants.DOUBLE_QUOTES +
//                "}";
//        return result;
//    }
//
//    public static String getQueryUserWhereStringByPK(User user) {
//        return Constants.USER_USERNAME_SQL + "='" + user.getUsername()
//                + "' AND " + Constants.USER_PASSWORD_SQL + "='" + user.getPassword()
//                + "' AND " + Constants.USER_PROJECT_NAME_SQL + "='" + user.getProjectName()
//                + "' AND " + Constants.USER_SERVER_IP_SQL + "='" + user.getServerIP() + "' ";
//    }
//
    public static String getQuerySystemInfoWhereStringByPK(SystemInfo systemInfo) {
        return Constants.ID_SQL + "='" + systemInfo.getId() + "'";
    }
//
//    public static String getQueryProjectWhereStringByPK(Project project) {
//        return Constants.PROJECT_IP_SQL + "='" + project.getIP()
//                + "' AND " + Constants.PROJECT_NAME_SQL + "='" + project.getProjectName() + "' ";
//    }
//
//    public static String getQueryServerWhereStringByPK(Server server) {
//        return Constants.SERVER_IP_SQL + "='" + server.getIP() + "' ";
//    }
//
//
//    public static String getJavascriptFunctionStringBy1WayArray(Object[] array, String arrayName, String functionName) {
//        String arg = getJsonStringBy1WayArray(arrayName, array);
////        String result = Constants.JAVASCRIPT + ":" + Constants.JAVASCRIPT_PARAMETER_FOR_ANDROID + "." + functionName + "('" + arg + "')";
//        String result = getJavascriptFunctionString(arg, functionName);
//        return result;
//    }
//
//    public static String getJavascriptFunctionStringBy1WayJsonArray(JSONObject[] jsonArray, String arrayName, String functionName) {
//        String arg = "{"
//                + Constants.DOUBLE_QUOTES + arrayName + Constants.DOUBLE_QUOTES + ":"
//                + getJsonArray2StringForJavascript(jsonArray)
//                + "}";
////        String result = Constants.JAVASCRIPT + ":" + Constants.JAVASCRIPT_PARAMETER_FOR_ANDROID + "." + functionName + "('" + arg + "')";
//        String result = getJavascriptFunctionString(arg, functionName);
//        return result;
//    }
//
//    public static String getJavascriptFunctionStringBy1WayStringArrayString(String stringArray ,String stringName, String functionName) {
//        String arg ="{"+Constants.DOUBLE_QUOTES+stringName+Constants.DOUBLE_QUOTES+":"+stringArray+"}";
//        String result = Constants.JAVASCRIPT + ":" + Constants.JAVASCRIPT_PARAMETER_FOR_ANDROID + "." + functionName + "('" + arg + "')";
//        return result;
//    }
//
//    public static String getJavascriptFunctionStringByString(String stringName,String stringValue, String functionName) {
//        String arg="{"+Constants.DOUBLE_QUOTES+stringName+Constants.DOUBLE_QUOTES+":"+Constants.DOUBLE_QUOTES+stringValue+Constants.DOUBLE_QUOTES+"}";
//        String result = Constants.JAVASCRIPT + ":" + Constants.JAVASCRIPT_PARAMETER_FOR_ANDROID + "." + functionName + "('" + arg + "')";
//        return result;
//    }
//
//    public static String getJavascriptFunctionString(String arg, String functionName) {
//        String result = Constants.JAVASCRIPT + ":" + Constants.JAVASCRIPT_PARAMETER_FOR_ANDROID + "." + functionName + "('" + arg + "')";
//        return result;
//    }
//
//
//
//    public static String getJavascriptFunctionStringByJson(JSONObject jsonObject, String jsonName, String functionName) {
//        String arg = "{" + Constants.DOUBLE_QUOTES + jsonName + Constants.DOUBLE_QUOTES + ":" + getJson2StringForJavascript(jsonObject) + "}";
//        String result = getJavascriptFunctionString(arg, functionName);
//        return result;
//    }
//
//    public static String getJsonStringBy1WayArray(String name, Object[] array) {
//        String result = "{" + Constants.DOUBLE_QUOTES + name + Constants.DOUBLE_QUOTES + ":[";
//        for (int i = 0; i < array.length; i++) {
//            if (i != 0)
//                result = result + ",";
//            result = result + Constants.DOUBLE_QUOTES + array[i].toString() + Constants.DOUBLE_QUOTES;
//        }
//        result = result + "]}";
//        return result;
//    }
//
//    public static String getJsonArray2StringForJavascript(JSONObject[] jsonArray) {
//        String result = "[";
//        for (int i = 0; i < jsonArray.length; i++) {
//            if (i < jsonArray.length - 1)
//                result = result + getJson2StringForJavascript(jsonArray[i]) + ",";
//            else
//                result = result + getJson2StringForJavascript(jsonArray[i]);
//        }
//        result = result + "]";
//        return result;
//    }
//
//    public static String getJson2StringForJavascript(JSONObject jsonObject) {
//        String result = "{";
//        Iterator<?> keys = jsonObject.keys();
//        while (keys.hasNext()) {
//            String key = (String) keys.next();
//            if (keys.hasNext()) {
//                try {
//                    result = result + Constants.DOUBLE_QUOTES + key + Constants.DOUBLE_QUOTES + ":" + Constants.DOUBLE_QUOTES + jsonObject.getString(key) + Constants.DOUBLE_QUOTES + ",";
//                } catch (JSONException e) {
//                }
//            } else {
//                try {
//                    result = result + Constants.DOUBLE_QUOTES + key + Constants.DOUBLE_QUOTES + ":" + Constants.DOUBLE_QUOTES + jsonObject.getString(key) + Constants.DOUBLE_QUOTES;
//                } catch (JSONException e) {
//                }
//            }
//        }
//        result = result + "}";
//        return result;
//    }
//
//    public static void updateRestApiPath()
//    {
//       Constants.READ_PROJECT_LIST_REST_API = Constants.SERVER_URL+ getLocalRestAPI(Constants.READ_PROJECT_LIST_REST_API);
//       Constants.GET_WEBACCESS_PROJECT_LIST_API = Constants.SERVER_URL + getLocalRestAPI(Constants.GET_WEBACCESS_PROJECT_LIST_API);
//       Constants.GET_DASHBOARD_TOKEN_REST_API = Constants.SERVER_URL + getLocalRestAPI(Constants.GET_DASHBOARD_TOKEN_REST_API);
//       Constants.SAVE_MOBILE_INFO_REST_API = Constants.SERVER_URL + getLocalRestAPI(Constants.SAVE_MOBILE_INFO_REST_API);
//       Constants.GET_NODE_LIST_REST_API = Constants.SERVER_URL + getLocalRestAPI(Constants.GET_NODE_LIST_REST_API);
//       Constants.ALARM_ACK_ALL_REST_API = Constants.SERVER_URL + getLocalRestAPI(Constants.ALARM_ACK_ALL_REST_API);
//    }
//
//    public static String getLocalRestAPI(String api)
//    {
//        return api.substring(api.indexOf("/WADashboard"),api.length());
//    }

//    public static JSONObject getJsonStringBy1WayArray2(String name, String[] array)
//    {
//        JSONObject jsonObject=new JSONObject();
//        try {
//            jsonObject.put(name,array);
//        }catch (JSONException e){}
//        return jsonObject;
//    }


}
