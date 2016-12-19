package magic.com.gamechat.tool;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Toast;

import magic.com.gamechat.define.Constants;
import magic.com.gamechat.fragment.ControlFragment;
import magic.com.gamechat.role.SystemInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Chris.Wu on 2016/10/20.
 */
public class Model implements Serializable {
    Factory factory;
    DBConnection dbHelper;
    Activity controlActivity;
    HttpClient httpClient;
    Application controlApplication;

    public Model() {
        controlActivity = null;
        controlApplication = null;
        createObj();
    }

    public Model(Activity activity) {
        controlActivity = activity;
        controlApplication = null;
        createObj();
    }

    public Model(Application application) {
        controlActivity = null;
        controlApplication = application;
        createObj();
    }

    public void toastString(final String str, final Activity activity) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(activity, str, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, Constants.BITMAP_COMPRESS_RATIO, baos);
        return baos.toByteArray();
    }

    private void createObj() {
        factory = new Factory();
        httpClient = factory.createHttpClient();
        if (controlActivity != null) {
            dbHelper = factory.createDBConnection(controlActivity);
            dbHelper.DBInit();
            createSystemInfo();

        } else if (controlApplication != null) {
            dbHelper = factory.createDBConnection(controlApplication);
            dbHelper.DBInit();
            createSystemInfo();
        } else {
            dbHelper = null;
        }
    }

    public void closeDB() {
        if (dbHelper != null) {
            dbHelper.close();
        }
    }

    private void createSystemInfo() {
        SystemInfo systemInfo = getSystemInfo();
        if (systemInfo == null) {
            systemInfo = factory.createSystemInfo();
            dbHelper.insertSystemInfo(systemInfo);
        }
    }

    public SystemInfo getSystemInfo() {
        if (dbHelper != null) {
            List<SystemInfo> systemInfoList = dbHelper.getSystemInfoList();
            Log.d("debug", "systemInfoList.size()    " + systemInfoList.size());
            if (systemInfoList.size() == 0) {
                return null;
            } else {
                SystemInfo systemInfo = systemInfoList.get(0);
                return systemInfo;
            }
        } else {
            return null;
        }
    }

    public boolean isLogin() {
        SystemInfo systemInfo = getSystemInfo();
        if (systemInfo != null) {
            return systemInfo.getIsLogin();
        } else {
            return false;
        }

    }

    public void setLogoutToDB() {
        SystemInfo systemInfo = getSystemInfo();
        if (systemInfo != null) {
            systemInfo.setAttribute(2, Constants.FALSE_STRING);
            dbHelper.updateSystemInfoByPK(systemInfo);
        }
    }

    //
//    public void saveFirebaseToken(String token) {
//        SystemInfo systemInfo = getSystemInfo();
//        if (systemInfo != null) {
//            systemInfo.setAttribute(1, token);
//            dbHelper.updateSystemInfoByPK(systemInfo);
//        }
//    }
//
    public void saveLoginAccount(String username, String password) {
        if (username == null)
            username = Constants.ENPTY_STRING;
        if (password == null)
            password = Constants.ENPTY_STRING;
        SystemInfo systemInfo = getSystemInfo();
        if (systemInfo != null) {
            systemInfo.setAttribute(2, Constants.TRUE_STRING);
            systemInfo.setAttribute(3, username);
            systemInfo.setAttribute(4, password);
            dbHelper.updateSystemInfoByPK(systemInfo);
            logSystemInfoDB();
        }
    }

    public boolean isEngOrNum(String str) {
        if (str.matches("[a-zA-Z0-9|\\.]*")) {
            return true;
        } else {
            return false;
        }
    }

    //
//    public void saveFunctionList(String actionLog, String alarmLog, String alarmSummary, String trend, String dashboard, String tagsInfo, String gMap) {
//        SystemInfo systemInfo = getSystemInfo();
//        if (systemInfo != null) {
//            systemInfo.setAttribute(8, actionLog);
//            systemInfo.setAttribute(9, alarmLog);
//            systemInfo.setAttribute(10, alarmSummary);
//            systemInfo.setAttribute(11, trend);
//            systemInfo.setAttribute(12, dashboard);
//            systemInfo.setAttribute(13, tagsInfo);
//            systemInfo.setAttribute(14, gMap);
//            dbHelper.updateSystemInfoByPK(systemInfo);
//        }
//    }
//
//    public JSONObject getFunctionListJson() {
//        JSONObject jsonObject = new JSONObject();
//        try {
//            SystemInfo systemInfo = getSystemInfo();
//            jsonObject.put(Constants.ACTION_LOG_HOME, systemInfo.getActionLogPage());
//            jsonObject.put(Constants.ALARM_LOG_HOME, systemInfo.getAlarmLogPage());
//            jsonObject.put(Constants.ALARM_SUMMARY_LOG_HOME, systemInfo.getAlarmSummaryPage());
//            jsonObject.put(Constants.TREND_HOME, systemInfo.getTrendPage());
//            jsonObject.put(Constants.DASHBOARD_HOME, systemInfo.getDashboardPage());
//            jsonObject.put(Constants.TAGS_INFO_HOME, systemInfo.getTagsInfoPage());
//            jsonObject.put(Constants.G_MAP_HOME, systemInfo.getGMapPage());
//        } catch (JSONException e) {
//            jsonObject = null;
//        }
//        return jsonObject;
//    }
//
//    public void saveLoginProject(Project saveProject) {
//        if (dbHelper != null) {
//            dbHelper.insertProject(saveProject);
//        }
//    }
//
//    public String getLastIP() {
//        if (dbHelper != null) {
//            List<SystemInfo> systemInfoList = dbHelper.getSystemInfoList();
//            if (systemInfoList.size() == 0) {
//                return Constants.ENPTY_STRING;
//            } else {
//                SystemInfo systemInfo = systemInfoList.get(0);
//                return systemInfo.getLastIP();
//            }
//        } else {
//            return Constants.ENPTY_STRING;
//        }
//    }
//
    public String getLastUsername() {
        if (dbHelper != null) {
            List<SystemInfo> systemInfoList = dbHelper.getSystemInfoList();
            if (systemInfoList.size() == 0) {
                return Constants.ENPTY_STRING;
            } else {
                SystemInfo systemInfo = systemInfoList.get(0);
                return systemInfo.getLastUsername();
            }
        } else {
            return Constants.ENPTY_STRING;
        }
    }

    public String getLastPassword() {
        if (dbHelper != null) {
            List<SystemInfo> systemInfoList = dbHelper.getSystemInfoList();
            if (systemInfoList.size() == 0) {
                return Constants.ENPTY_STRING;
            } else {
                SystemInfo systemInfo = systemInfoList.get(0);
                return systemInfo.getLastPassword();
            }
        } else {
            return Constants.ENPTY_STRING;
        }
    }

    //
//    public String getLastProjectName() {
//        if (dbHelper != null) {
//            logSystemInfoDB();
//            List<SystemInfo> systemInfoList = dbHelper.getSystemInfoList();
//            if (systemInfoList.size() == 0) {
//                return Constants.ENPTY_STRING;
//            } else {
//                SystemInfo systemInfo = systemInfoList.get(0);
//                return systemInfo.getLastProjectName();
//            }
//        } else {
//            return Constants.ENPTY_STRING;
//        }
//    }
//
//    public String getLastServerToken() {
//        if (dbHelper != null) {
//            List<SystemInfo> systemInfoList = dbHelper.getSystemInfoList();
//            if (systemInfoList.size() == 0) {
//                return Constants.ENPTY_STRING;
//            } else {
//                SystemInfo systemInfo = systemInfoList.get(0);
//                return systemInfo.getLastServerToken();
//            }
//        } else {
//            return Constants.ENPTY_STRING;
//        }
//    }
//
//    public String getFirebaseToken() {
//        if (dbHelper != null) {
//            List<SystemInfo> systemInfoList = dbHelper.getSystemInfoList();
//            if (systemInfoList.size() == 0) {
//                return Constants.ENPTY_STRING;
//            } else {
//                return systemInfoList.get(0).getFirebaseToken();
//            }
//        } else {
//            return Constants.ENPTY_STRING;
//        }
//    }
//
//    public User getUserFromDB(String username, String password, String projectName, String serverIP) {
//        User returnUser = factory.createUser();
//        if (dbHelper != null) {
//            List<User> user = dbHelper.getUserList();
//            if (user.size() > 0) {
//                for (int i = 0; i < user.size(); i++) {
//                    if (user.get(i).getServerIP().equals(serverIP) && user.get(i).getUsername().equals(username) && user.get(i).getPassword().equals(password) && user.get(i).getProjectName().equals(projectName)) {
//                        returnUser = user.get(i);
//                    }
//                }
//
//                return returnUser;
//            } else {
//                return returnUser;
//            }
//        } else {
//
//            return returnUser;
//        }
//    }
//
//    public void saveLoginIP(Server server) {
//        if (dbHelper != null) {
//            dbHelper.insertServer(server);
////            logServerDB();
//        }
//    }
//
//    public void saveLoginUser(User user) {
//        if (dbHelper != null) {
//            dbHelper.insertUser(user);
////            logUserDB();
//        }
//    }
//
//    public void updateUserDB(User user) {
//        if (dbHelper != null) {
//            dbHelper.updateUserByPK(user);
////            logUserDB();
//        }
//    }
//
//    public String[] getIpHistoryList() {
//        if (dbHelper != null) {
//            List<Server> server = dbHelper.getServerList();
//            if (server.size() > 0) {
//                String[] ipList = new String[server.size()];
//                for (int i = 0; i < server.size(); i++) {
//                    ipList[i] = server.get(i).getIP();
//                }
//                return ipList;
//            } else {
//                String[] ipList = new String[0];
//                return ipList;
//            }
//        } else {
//            String[] ipList = new String[0];
//            return ipList;
//        }
//    }
//
//    public JSONObject[] getUserAccountHistoryArray() {
//        JSONObject[] userAccountHistoryArray;
//        if (dbHelper != null) {
//            List<User> user = dbHelper.getUserList();
//            if (user.size() > 0) {
//                userAccountHistoryArray = new JSONObject[user.size()];
//                for (int i = 0; i < user.size(); i++) {
//                    try {
//                        userAccountHistoryArray[i] = new JSONObject();
//                        userAccountHistoryArray[i].put(Constants.USERNAME, user.get(i).getUsername());
//                        userAccountHistoryArray[i].put(Constants.PASSWORD, user.get(i).getPassword());
//                        userAccountHistoryArray[i].put(Constants.PROJECT_NAME, user.get(i).getProjectName());
//                        userAccountHistoryArray[i].put(Constants.IP, user.get(i).getServerIP());
//                    } catch (JSONException e) {
//                    }
//                }
//                return userAccountHistoryArray;
//            } else {
//                userAccountHistoryArray = new JSONObject[0];
//                return userAccountHistoryArray;
//            }
//        } else {
//            userAccountHistoryArray = new JSONObject[0];
//            return userAccountHistoryArray;
//        }
//    }
//
//    public String[] getProjectArray() {
//        if (dbHelper != null) {
//            List<Project> project = dbHelper.getProjectList();
//            if (project.size() > 0) {
//                String[] projectArray = new String[project.size()];
//                for (int i = 0; i < project.size(); i++) {
//                    projectArray[i] = project.get(i).getProjectName();
//                }
//                return projectArray;
//            } else {
//                String[] projectArray = new String[0];
//                return projectArray;
//            }
//        } else {
//            String[] projectArray = new String[0];
//            return projectArray;
//        }
//    }
//
//    public void logServerDB() {
//        List<Server> server = dbHelper.getServerList();
//        Log.d("debug", "server list size  " + server.size());
//        for (int i = 0; i < server.size(); i++) {
//            Log.d("debug", "ip  " + server.get(i).getIP());
//            Log.d("debug", "firebaseTokenEnable   " + server.get(i).getFirebaseTokenEnable());
//
//        }
//    }
//
//    public void logUserDB() {
//        List<User> User = dbHelper.getUserList();
//        Log.d("debug", "User list size  " + User.size());
//        for (int i = 0; i < User.size(); i++) {
//            Log.d("debug", "username  " + User.get(i).getUsername());
//            Log.d("debug", "password   " + User.get(i).getPassword());
//            Log.d("debug", "projectName   " + User.get(i).getProjectName());
//            Log.d("debug", "serverToken   " + User.get(i).getServerToken());
//            Log.d("debug", "ip   " + User.get(i).getServerIP());
//
//        }
//    }
//
    public void logSystemInfoDB() {
        List<SystemInfo> SystemInfo = dbHelper.getSystemInfoList();
        Log.d("debug", "SystemInfo list size  " + SystemInfo.size());
        for (int i = 0; i < SystemInfo.size(); i++) {
            Log.d("debug", "id  " + SystemInfo.get(i).getId());
            Log.d("debug", "firebase token  " + SystemInfo.get(i).getFirebaseToken());
            Log.d("debug", "last username  " + SystemInfo.get(i).getLastUsername());
            Log.d("debug", "last password  " + SystemInfo.get(i).getLastPassword());
            Log.d("debug", "isLogin  " + SystemInfo.get(i).getIsLogin());
        }
    }


    public boolean getHttpResult(String receiveMessage) {
        try {
            JSONObject jsonObject = getJsonObject(receiveMessage);
            if (jsonObject == null) {
                return false;
            } else {
                String result = jsonObject.getString(Constants.RESULT_REST_API);
                if (result.equals(Constants.ZERO)) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (JSONException e) {
            return false;
        }
    }

    public String getJSONProtString(String protName, String JSONString) {
        JSONObject jsonObject = getJsonObject(JSONString);
        return getJSONProtString(protName, jsonObject);
    }

    public JSONObject getJsonObject(String JSONString) {
        JSONObject jsonObject = null;
        try {
            if (JSONString.indexOf(Constants.OPEN_BRACE) >= 0) {
                JSONString = JSONString.substring(JSONString.indexOf(Constants.OPEN_BRACE), JSONString.lastIndexOf(Constants.CLOSE_BRACE) + 1);
                jsonObject = new JSONObject(JSONString);
            }
        } catch (JSONException e) {
        }
        return jsonObject;
    }

    public String getJSONProtString(String protName, JSONObject jsonObject) {
        String result = Constants.ENPTY_STRING;
        if (jsonObject != null)
            try {
                result = jsonObject.getString(protName);
            } catch (JSONException e) {
            }
        return result;
    }

    public String getProperIp(String ip) {
        String front = Constants.ENPTY_STRING;
        boolean addFront = true;
        if (ip.indexOf(Constants.HTTP) == 0) {
            addFront = false;
        }
        if (ip.indexOf(Constants.HTTPS) == 0) {
            addFront = false;
        }
        if (addFront) {
            ip = Constants.HTTP + Constants.SLASH + ip;
        }
        return ip;
    }

    public void changeFragment(FragmentManager fragmentManager, int id, ControlFragment controlFragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        controlFragment.setControlModel(this);
        transaction.replace(id, controlFragment);
        transaction.commit();
    }
}
