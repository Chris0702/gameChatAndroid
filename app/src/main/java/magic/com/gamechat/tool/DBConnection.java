package magic.com.gamechat.tool;

import java.util.ArrayList;
import java.util.List;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import magic.com.gamechat.define.Constants;
import magic.com.gamechat.role.SystemInfo;

public class DBConnection extends SQLiteOpenHelper {

    SQLiteDatabase readDatabase = null;
    SQLiteDatabase writeDatabase = null;

    public DBConnection(Context ctx) {
        super(ctx, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        Factory factory = new Factory();
        Log.d("debug", "開始建置資料庫");
//        Log.d("debug", "1");
//        db.execSQL(Constants.USER_TABLE_CREATE_SQL);
//        Log.d("debug", "2");
        db.execSQL(Constants.SYSTEM_INFO_TABLE_CREATE_SQL);
//        Log.d("debug", "3");
//        db.execSQL(Constants.SERVER_TABLE_CREATE_SQL);
//        Log.d("debug", "4");
//        db.execSQL(Constants.PROJECT_TABLE_CREATE_SQL);
//        Log.d("debug", "5");
        Log.d("debug", "資料庫建置完成");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
    }

    public void DBInit() {
        readDatabase = this.getReadableDatabase();
        writeDatabase = this.getWritableDatabase();
    }

    public void closeDB() {
        if (!readDatabase.equals(null))
            readDatabase.close();
        if (!writeDatabase.equals(null))
            writeDatabase.close();
    }

        public long insertSystemInfo(SystemInfo systemInfo) {
//        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.ID_SQL, 0);
        values.put(Constants.SYSTEM_INFO_FIREBASE_TOKEN_SQL, systemInfo.getFirebaseToken());
        values.put(Constants.SYSTEM_INFO_IS_LOGIN_NAME_SQL, String.valueOf(systemInfo.getIsLogin()));
        values.put(Constants.SYSTEM_INFO_LAST_USERNAME_SQL, systemInfo.getLastUsername());
        values.put(Constants.SYSTEM_INFO_LAST_PASSWORD_SQL, systemInfo.getLastPassword());
        long count = writeDatabase.insert(Constants.TABLE_SYSTEM_INFO_SQL, null, values);
//        db.close();
        return count;
    }

    public List<SystemInfo> getSystemInfoList() {
        Factory factory = new Factory();
        List<SystemInfo> SystemInfoList = new ArrayList<SystemInfo>();
//        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = readDatabase.query(Constants.TABLE_SYSTEM_INFO_SQL, null, null, null, null, null, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); cursor.moveToNext(), i++) {
                SystemInfoList.add(factory.createSystemInfo());
                for (int j = 0; j < cursor.getColumnCount(); j++)
                    SystemInfoList.get(i).setAttribute(j, cursor.getString(j));
            }
        }
        cursor.close();
//        db.close();
        return SystemInfoList;
    }

    public int updateSystemInfoByPK(SystemInfo systemInfo) {
//        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.ID_SQL, 0);
        values.put(Constants.SYSTEM_INFO_FIREBASE_TOKEN_SQL, systemInfo.getFirebaseToken());
        values.put(Constants.SYSTEM_INFO_IS_LOGIN_NAME_SQL, String.valueOf(systemInfo.getIsLogin()));
        values.put(Constants.SYSTEM_INFO_LAST_USERNAME_SQL, systemInfo.getLastUsername());
        values.put(Constants.SYSTEM_INFO_LAST_PASSWORD_SQL, systemInfo.getLastPassword());
        String whereClause = StringProcess.getQuerySystemInfoWhereStringByPK(systemInfo);
        Log.d("debug", "whereClause   " + whereClause);
        int count = writeDatabase.update(Constants.TABLE_SYSTEM_INFO_SQL, values, whereClause, null);
//        db.close();
        return count;
    }

    public int deleteSystemInfo(SystemInfo systemInfo) {
//        SQLiteDatabase db = getWritableDatabase();
        String whereClause = StringProcess.getQuerySystemInfoWhereStringByPK(systemInfo);
        int count = writeDatabase.delete(Constants.TABLE_SYSTEM_INFO_SQL, whereClause, null);
//        db.close();
        return count;
    }


//
//    public long insertUser(User user) {
////        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(Constants.USER_USERNAME_SQL, user.getUsername());
//        values.put(Constants.USER_PASSWORD_SQL, user.getPassword());
//        values.put(Constants.USER_PROJECT_NAME_SQL, user.getProjectName());
//        values.put(Constants.USER_SERVER_TOKEN_SQL, user.getServerToken());
//        values.put(Constants.USER_SERVER_IP_SQL, user.getServerIP());
//        long count = writeDatabase.insert(Constants.TABLE_USER_SQL, null, values);
////        db.close();
//        return count;
//    }
//
//
//    public List<User> getUserList() {
//        Factory factory = new Factory();
//        List<User> UserList = new ArrayList<User>();
////        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = readDatabase.query(Constants.TABLE_USER_SQL, null, null, null, null, null, null);
//        if (cursor.getCount() > 0) {
//            cursor.moveToFirst();
//            for (int i = 0; i < cursor.getCount(); cursor.moveToNext(), i++) {
//                UserList.add(factory.createUser());
//                for (int j = 0; j < cursor.getColumnCount(); j++)
//                    UserList.get(i).setAttribute(j, cursor.getString(j));
//            }
//        }
//        cursor.close();
////        db.close();
//        return UserList;
//    }
//
//    public int updateUserByPK(User user) {
////        SQLiteDatabase db = getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(Constants.USER_USERNAME_SQL, user.getUsername());
//        values.put(Constants.USER_PASSWORD_SQL, user.getPassword());
//        values.put(Constants.USER_PROJECT_NAME_SQL, user.getProjectName());
//        values.put(Constants.USER_SERVER_TOKEN_SQL, user.getServerToken());
//        values.put(Constants.USER_SERVER_IP_SQL, user.getServerIP());
//        String whereClause = StringProcess.getQueryUserWhereStringByPK(user);
//        int count = writeDatabase.update(Constants.TABLE_USER_SQL, values, whereClause, null);
////        db.close();
//        return count;
//    }
//
//    public int deleteUserByPK(User user) {
////        SQLiteDatabase db = getWritableDatabase();
//        String whereClause = StringProcess.getQueryUserWhereStringByPK(user);
//        int count = writeDatabase.delete(Constants.TABLE_USER_SQL, whereClause, null);
////        db.close();
//        return count;
//    }
//
//
//    public long insertServer(Server server) {
////        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(Constants.SERVER_IP_SQL, server.getIP());
//        values.put(Constants.SERVER_FIREBASE_TOKEN_ENABLE_SQL, server.getFirebaseTokenEnable());
//        long count = writeDatabase.insert(Constants.TABLE_SERVER_SQL, null, values);
////        db.close();
//        return count;
//    }
//
//
//    public List<Server> getServerList() {
//        Factory factory = new Factory();
//        List<Server> ServerList = new ArrayList<Server>();
////        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = readDatabase.query(Constants.TABLE_SERVER_SQL, null, null, null, null, null, null);
//        if (cursor.getCount() > 0) {
//            cursor.moveToFirst();
//            for (int i = 0; i < cursor.getCount(); cursor.moveToNext(), i++) {
//                ServerList.add(factory.createServer());
//                for (int j = 0; j < cursor.getColumnCount(); j++)
//                    ServerList.get(i).setAttribute(j, cursor.getString(j));
//            }
//        }
//        cursor.close();
////        db.close();
//        return ServerList;
//    }
//
//    public int updateServerByPK(Server server) {
////        SQLiteDatabase db = getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(Constants.SERVER_IP_SQL, server.getIP());
//        values.put(Constants.SERVER_FIREBASE_TOKEN_ENABLE_SQL, server.getFirebaseTokenEnable());
//        String whereClause = StringProcess.getQueryServerWhereStringByPK(server);
//        int count = writeDatabase.update(Constants.TABLE_SERVER_SQL, values, whereClause, null);
////        db.close();
//        return count;
//    }
//
//    public int deleteServerByPK(Server server) {
////        SQLiteDatabase db = getWritableDatabase();
//        String whereClause = StringProcess.getQueryServerWhereStringByPK(server);
//        int count = writeDatabase.delete(Constants.TABLE_SERVER_SQL, whereClause, null);
////        db.close();
//        return count;
//    }
//
//    public long insertProject(Project project) {
////        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(Constants.PROJECT_IP_SQL, project.getIP());
//        values.put(Constants.PROJECT_NAME_SQL, project.getProjectName());
//        long count = writeDatabase.insert(Constants.TABLE_PROJECT_SQL, null, values);
////        db.close();
//        return count;
//    }
//
//    public List<Project> getProjectList() {
//        Factory factory = new Factory();
//        List<Project> ServerList = new ArrayList<Project>();
////        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = readDatabase.query(Constants.TABLE_PROJECT_SQL, null, null, null, null, null, null);
//        if (cursor.getCount() > 0) {
//            cursor.moveToFirst();
//            for (int i = 0; i < cursor.getCount(); cursor.moveToNext(), i++) {
//                ServerList.add(factory.createProject());
//                for (int j = 0; j < cursor.getColumnCount(); j++)
//                    ServerList.get(i).setAttribute(j, cursor.getString(j));
//            }
//        }
//        cursor.close();
////        db.close();
//        return ServerList;
//    }
//
//    public int updateProjectByPK(Project project) {
////        SQLiteDatabase db = getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(Constants.PROJECT_NAME_SQL, project.getProjectName());
//        values.put(Constants.PROJECT_IP_SQL, project.getIP());
//        String whereClause = StringProcess.getQueryProjectWhereStringByPK(project);
//        int count = writeDatabase.update(Constants.TABLE_PROJECT_SQL, values, whereClause, null);
////        db.close();
//        return count;
//    }
//
//    public int deleteProject(Project project) {
////        SQLiteDatabase db = getWritableDatabase();
//        String whereClause = StringProcess.getQueryProjectWhereStringByPK(project);
//        int count = writeDatabase.delete(Constants.TABLE_PROJECT_SQL, whereClause, null);
////        db.close();
//        return count;
//    }
//
//
//    public long insertSystemInfo(SystemInfo systemInfo) {
////        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(Constants.ID_SQL, 0);
//        values.put(Constants.SYSTEM_INFO_FIREBASE_TOKEN_SQL, systemInfo.getFirebaseToken());
//        values.put(Constants.SYSTEM_INFO_IS_LOGIN_NAME_SQL, String.valueOf(systemInfo.getIsLogin()));
//        values.put(Constants.SYSTEM_INFO_LAST_USERNAME_SQL, systemInfo.getLastUsername());
//        values.put(Constants.SYSTEM_INFO_LAST_PASSWORD_SQL, systemInfo.getLastPassword());
//        values.put(Constants.SYSTEM_INFO_LAST_IP_SQL, systemInfo.getLastIP());
//        values.put(Constants.SYSTEM_INFO_LAST_PROJECT_NAME_SQL, systemInfo.getLastProjectName());
//        values.put(Constants.SYSTEM_INFO_LAST_SERVER_TOKEN_SQL, systemInfo.getLastServerToken());
//        values.put(Constants.SYSTEM_INFO_ACTION_LOG_PAGE_NAME_SQL, String.valueOf(systemInfo.getActionLogPage()));
//        values.put(Constants.SYSTEM_INFO_ALARM_LOG_PAGE_NAME_SQL, String.valueOf(systemInfo.getAlarmLogPage()));
//        values.put(Constants.SYSTEM_INFO_ALARM_SUMMARY_LOG_PAGE_NAME_SQL, String.valueOf(systemInfo.getAlarmSummaryPage()));
//        values.put(Constants.SYSTEM_INFO_TREND_PAGE_NAME_SQL, String.valueOf(systemInfo.getTrendPage()));
//        values.put(Constants.SYSTEM_INFO_DASHBOARD_PAGE_NAME_SQL, String.valueOf(systemInfo.getDashboardPage()));
//        values.put(Constants.SYSTEM_INFO_G_MAP_PAGE_NAME_SQL, String.valueOf(systemInfo.getGMapPage()));
//        values.put(Constants.SYSTEM_INFO_TAGS_INFO_PAGE_NAME_SQL, String.valueOf(systemInfo.getTagsInfoPage()));
//        long count = writeDatabase.insert(Constants.TABLE_SYSTEM_INFO_SQL, null, values);
////        db.close();
//        return count;
//    }
//
//    public List<SystemInfo> getSystemInfoList() {
//        Factory factory = new Factory();
//        List<SystemInfo> SystemInfoList = new ArrayList<SystemInfo>();
////        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = readDatabase.query(Constants.TABLE_SYSTEM_INFO_SQL, null, null, null, null, null, null);
//        if (cursor.getCount() > 0) {
//            cursor.moveToFirst();
//            for (int i = 0; i < cursor.getCount(); cursor.moveToNext(), i++) {
//                SystemInfoList.add(factory.createSystemInfo());
//                for (int j = 0; j < cursor.getColumnCount(); j++)
//                    SystemInfoList.get(i).setAttribute(j, cursor.getString(j));
//            }
//        }
//        cursor.close();
////        db.close();
//        return SystemInfoList;
//    }
//
//    public int updateSystemInfoByPK(SystemInfo systemInfo) {
////        SQLiteDatabase db = getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(Constants.ID_SQL, 0);
//        values.put(Constants.SYSTEM_INFO_FIREBASE_TOKEN_SQL, systemInfo.getFirebaseToken());
//        values.put(Constants.SYSTEM_INFO_IS_LOGIN_NAME_SQL, String.valueOf(systemInfo.getIsLogin()));
//        values.put(Constants.SYSTEM_INFO_LAST_USERNAME_SQL, systemInfo.getLastUsername());
//        values.put(Constants.SYSTEM_INFO_LAST_PASSWORD_SQL, systemInfo.getLastPassword());
//        values.put(Constants.SYSTEM_INFO_LAST_IP_SQL, systemInfo.getLastIP());
//        values.put(Constants.SYSTEM_INFO_LAST_PROJECT_NAME_SQL, systemInfo.getLastProjectName());
//        values.put(Constants.SYSTEM_INFO_LAST_SERVER_TOKEN_SQL, systemInfo.getLastServerToken());
//        values.put(Constants.SYSTEM_INFO_ACTION_LOG_PAGE_NAME_SQL, String.valueOf(systemInfo.getActionLogPage()));
//        values.put(Constants.SYSTEM_INFO_ALARM_LOG_PAGE_NAME_SQL, String.valueOf(systemInfo.getAlarmLogPage()));
//        values.put(Constants.SYSTEM_INFO_ALARM_SUMMARY_LOG_PAGE_NAME_SQL, String.valueOf(systemInfo.getAlarmSummaryPage()));
//        values.put(Constants.SYSTEM_INFO_TREND_PAGE_NAME_SQL, String.valueOf(systemInfo.getTrendPage()));
//        values.put(Constants.SYSTEM_INFO_DASHBOARD_PAGE_NAME_SQL, String.valueOf(systemInfo.getDashboardPage()));
//        values.put(Constants.SYSTEM_INFO_G_MAP_PAGE_NAME_SQL, String.valueOf(systemInfo.getGMapPage()));
//        values.put(Constants.SYSTEM_INFO_TAGS_INFO_PAGE_NAME_SQL, String.valueOf(systemInfo.getTagsInfoPage()));
//        String whereClause = StringProcess.getQuerySystemInfoWhereStringByPK(systemInfo);
//        Log.d("debug", "whereClause   " + whereClause);
//        int count = writeDatabase.update(Constants.TABLE_SYSTEM_INFO_SQL, values, whereClause, null);
////        db.close();
//        return count;
//    }
//
//    public int deleteSystemInfo(SystemInfo systemInfo) {
////        SQLiteDatabase db = getWritableDatabase();
//        String whereClause = StringProcess.getQuerySystemInfoWhereStringByPK(systemInfo);
//        int count = writeDatabase.delete(Constants.TABLE_SYSTEM_INFO_SQL, whereClause, null);
////        db.close();
//        return count;
//    }


//example
    //        dbHelper.insertUser(factory.createUser("rsdsdt", "xxxxx"));
//        dbHelper.insertUser(factory.createUser("rSS", "sssssss"));
//        dbHelper.insertUser(factory.createUser("XXXXt", "eeeeeee"));
//        dbHelper.updateUserById(factory.createUser(12,"xcvx","fewf"));
//      dbHelper.deleteUser(10);
//        List<User> test = dbHelper.getUserList();
//        for (int i = 0; i < test.size(); i++) {
//            Log.d("debug", "id  " + test.get(i).getId());
//            Log.d("debug", "account  " + test.get(i).getAccount());
//            Log.d("debug", "password   " + test.get(i).getPassword());
//
//        }

//    public static boolean isTableExist(String inputTable) {
//        // TODO Auto-generated method stub
//        for (String table : EXIST_TABLE) {
//            if (table.equals(inputTable))
//                return true;
//        }
//        return false;
//    }
}