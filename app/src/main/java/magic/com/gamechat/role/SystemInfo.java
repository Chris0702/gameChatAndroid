package magic.com.gamechat.role;

import magic.com.gamechat.define.Constants;

import java.util.List;

/**
 * Created by Chris.Wu on 2016/11/11.
 */

public class SystemInfo {
    private int id;
    private String firebaseToken;
    private String lastUsername;
    private String lastPassword;
    private boolean isLogin;

    public SystemInfo() {
        id = 0;
        firebaseToken = Constants.ENPTY_STRING;
        lastProtInit();
    }

    public SystemInfo(String firebaseToken) {
        this.firebaseToken = firebaseToken;
        id = 0;
        lastProtInit();
    }

    public SystemInfo(int id, String firebaseToken) {
        this.id = id;
        this.firebaseToken = firebaseToken;
        lastProtInit();
    }

    private void lastProtInit() {
        lastUsername = Constants.ENPTY_STRING;
        lastPassword = Constants.ENPTY_STRING;
    }

    public int getId() {
        return id;
    }

    public String getFirebaseToken() {
        return firebaseToken;
    }

    public String getLastUsername() {
        return lastUsername;
    }

    public String getLastPassword() {
        return lastPassword;
    }

    public boolean getIsLogin()
    {
        return  isLogin;
    }

    public void setAttribute(int type, String attribute) {
        switch (type) {
            case 0:
                this.id = Integer.parseInt(attribute);
                break;
            case 1:
                this.firebaseToken = attribute;
                break;
            case 2:
                this.isLogin = Boolean.parseBoolean(attribute);
                break;
            case 3:
                this.lastUsername = attribute;
                break;
            case 4:
                this.lastPassword = attribute;
                break;

            default:
        }
    }
}
