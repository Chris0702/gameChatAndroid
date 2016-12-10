package magic.com.gamechat.role;


import magic.com.gamechat.define.Constants;

public class User {
    private String username;
    private String password;
    private String projectName;
    private String serverToken;
    private String serverIP;

    public User() {
        this.username = Constants.ENPTY_STRING;
        this.password= Constants.ENPTY_STRING;
        this.projectName= Constants.ENPTY_STRING;
        this.serverToken= Constants.ENPTY_STRING;
        this.serverIP= Constants.ENPTY_STRING;
    }

    public User(String username, String password,String project,String serverToken,String serverIP) {
        this.username = username;
        this.password = password;
        this.projectName=project;
        this.serverToken=serverToken;
        this.serverIP=serverIP;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getServerToken()
    {
        return  serverToken;
    }

    public String getServerIP()
    {
        return  serverIP;
    }

    public void setAttribute(int type, String attribute) {
        switch (type) {
            case 0:
                this.username = attribute;
                break;
            case 1:
                this.password = attribute;
                break;
            case 2:
                this.projectName = attribute;
                break;
            case 3:
                this.serverToken = attribute;
                break;
            case 4:
                this.serverIP = attribute;
                break;
            default:
        }
    }

}