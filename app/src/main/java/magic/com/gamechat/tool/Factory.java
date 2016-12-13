package magic.com.gamechat.tool;

import android.app.Activity;
import android.app.Application;
import android.webkit.WebView;

import magic.com.gamechat.fragment.ChatTextFragment;
import magic.com.gamechat.fragment.LoginFragment;
import magic.com.gamechat.fragment.SearchFriendFragment;
import magic.com.gamechat.role.SystemInfo;


/**
 * Created by Chris.Wu on 2016/10/20.
 */
public class Factory {

    public LoginFragment createLoginFragment()
    {
        return new LoginFragment();
    }

    public ChatTextFragment createChatTextFragment()
    {
        return new ChatTextFragment();
    }

    public SearchFriendFragment createSearchFriendFragment()
    {
        return new SearchFriendFragment();
    }
//    public User createUser() {
//        return new User();
//    }
//
//    public User createUser(String username,String password,String projectName,String serverToken,String serverIP) {
//        return new User(username,password,projectName,serverToken,serverIP);
//    }
//
//    public Server createServer() {
//        return new Server();
//    }
//
//    public Server createServer(String ip, String firebaseTokenEnable) {
//        return new Server(ip, firebaseTokenEnable);
//    }
//
//
//    public Project createProject() {
//        return new Project();
//    }
//
//    public Project createProject(String ip, String projectName) {
//        return new Project(ip, projectName);
//    }
//
    public SystemInfo createSystemInfo() {
        return new SystemInfo();
    }

    public SystemInfo createSystemInfo(String firebaseToken ) {
        return new SystemInfo(firebaseToken);
    }

    public SystemInfo createSystemInfo(int id,String firebaseToken) {
        return new SystemInfo(id,firebaseToken);
    }

    public DBConnection createDBConnection(Activity activity) {
        return new DBConnection(activity);
    }

    public DBConnection createDBConnection(Application application) {
        return new DBConnection(application);
    }


    public Model createModel(Activity activity) {
        return new Model(activity);
    }

    public Model createModel(Application application) {
        return new Model(application);
    }

    public Model createModel() {
        return new Model();
    }

    public SocketIO createSocketIO(String nameSpace) {
        return new SocketIO(nameSpace);
    }

    public HttpClient createHttpClient() {
        return new HttpClient();
    }

    public HttpClient createHttpClient(String serverToken) {
        return new HttpClient(serverToken);
    }
}
