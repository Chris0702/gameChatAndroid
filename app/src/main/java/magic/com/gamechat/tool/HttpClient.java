package magic.com.gamechat.tool;

import android.util.Log;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import magic.com.gamechat.define.Constants;
import magic.com.gamechat.fragment.LoginFragment;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Chris.Wu on 2016/11/10.
 */

public class HttpClient {
    private OkHttpClient okHttpClient;
    private String serverToken;

    public HttpClient() {
        serverToken = Constants.ENPTY_STRING;
        createOkHttpClient();
    }

    public HttpClient(String serverToken) {
        okHttpClient = new OkHttpClient();
        this.serverToken = serverToken;
        createOkHttpClient();
    }

    public void setServerToken(String serverToken) {
        this.serverToken = serverToken;
    }

    public String getServerToken() {
        return serverToken;
    }

    public void createOkHttpClient() {
        okHttpClient = new OkHttpClient.Builder()
                .cookieJar(new CookieJar() {
                    private final HashMap<HttpUrl, List<Cookie>> cookieStore = new HashMap<>();

                    @Override
                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
//                        String cookiesString=cookies.toString();
//                        if(cookiesString.indexOf(Constants.SERVER_TOKEN_TITLE)>0) {
//                            cookiesString = cookiesString.substring(cookiesString.indexOf(Constants.SERVER_TOKEN_TITLE) + 4, cookiesString.indexOf(Constants.SEMICOLON));
//                            serverToken = cookiesString;
//                        }
//                        Log.d("okhttp", " :::   "+ cookiesString);
                    }

                    @Override
                    public List<Cookie> loadForRequest(HttpUrl url) {
                        List<Cookie> cookies = cookieStore.get(url.host());
                        return cookies != null ? cookies : new ArrayList<Cookie>();
                    }
                })
                .build();
    }

    public OkHttpClient getClient() {
        return okHttpClient;
    }

    public Call getCall(Request request) {
        return okHttpClient.newCall(request);
    }

    public void login(final String username, final String password, final LoginFragment loginFragment) {
//        RequestBody requestBody = new FormBody.Builder()
//                .add(Constants.USERNAME, username)
//                .add(Constants.PASSWORD, password)
//                .build();
        Request request = new Request.Builder()
                .url(Constants.USER_LOGIN_REST_API)
                .addHeader(Constants.USERNAME, username)
                .addHeader(Constants.PASSWORD, password)
//                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("http", "http rest api  login  fail         ");
                loginFragment.getActivity().runOnUiThread(new Runnable() {
                    //  @Override
                    public void run() {
                        loginFragment.getControlModel().toastString(Constants.INTERNET_ERROR);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.code()==200) {
                    String receiveMessage = response.body().string();
                    Log.d("http", "http rest api  login  success  " + receiveMessage);
                    loginFragment.loginResponse(receiveMessage);
                }
                else
                {
                    loginFragment.getActivity().runOnUiThread(new Runnable() {
                        //  @Override
                        public void run() {
                            loginFragment.getControlModel().toastString(Constants.INTERNET_ERROR);
                        }
                    });
                }
            }
        });
    }

//    public void alarmAck()
//    {
//
//    }
//
//    public void alarmAckAllList(final String projectName, final AlarmSummaryLogController alarmSummaryLogController)
//    {
//        Log.d("debug", "Constants.ALARM_ACK_ALL_REST_API         "+Constants.ALARM_ACK_ALL_REST_API);
//        Request request = new Request.Builder()
//                .url(StringProcess.addProjectNameToUrl( Constants.ALARM_ACK_ALL_REST_API,projectName ))
//                .addHeader(Constants.COOKIE, StringProcess.getCookieString(serverToken))
//                .build();
//        Call call = okHttpClient.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.d("http", "http rest api alarmAckAllList  fail         ");
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                Log.d("http", "http rest api  alarmAckAllList  success  ");
//                String receiveMessage = response.body().string();
//                alarmSummaryLogController.alarmAckAllListResponse(receiveMessage);
//            }
//        });
//    }
//
//    public  void getWebAccessProjectList( final LoginController LoginController)
//    {
//        Log.d("debug", "http  rest api getWebAccessProjectList  ");
//        Request request=new Request.Builder()
//                .url(Constants.GET_WEBACCESS_PROJECT_LIST_API)
//                .build();
//        Call call = okHttpClient.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.d("http", "http  rest api get webAccess project list  fail  ");
////                LoginController.inputConnectIPResponse(Constants.HTTP_FAIL,ip,remember);
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                Log.d("http", "http rest api get webAccess project list   success  ");
//                String receiveMessage = response.body().string();
//                Log.d("http", receiveMessage);
//                LoginController.getProjectListByIPResponse(receiveMessage);
////                LoginController.inputConnectIPResponse(receiveMessage,ip,remember);
//            }
//        });
//    }
//
//    public  void inputAccount(final String projectName,final String username, final String password ,final String remember, final LoginController LoginController)
//    {
//        Request request = new Request.Builder()
//                .url(Constants.GET_DASHBOARD_TOKEN_REST_API)
//                .addHeader(Constants.TOKEN, getServerToken())
//                .addHeader(Constants.PROJECT_NAME_1, projectName)
//                .addHeader(Constants.USERNAME, username)
//                .addHeader(Constants.PASSWORD, password)
//                .addHeader(Constants.TYPE, Constants.ENPTY_STRING)
//                .build();
//        Call call = okHttpClient.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.d("http", "http rest api inputAccount  fail  ");
//                LoginController.inputAccountResponse( projectName, username,  password , remember,Constants.HTTP_FAIL);
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                Log.d("http", "http rest api inputAccount  success  ");
//                String receiveMessage = response.body().string();
//                Log.d("http", "receiveMessage     " + receiveMessage);
//                LoginController.inputAccountResponse( projectName, username,  password , remember,receiveMessage);
//
//            }
//        });
//    }
//
//    public  void sendMobileInfoToServer(final String projectName, final String username,final String password,final String firebaseToken,final LoginController LoginController)
//    {
//        RequestBody requestBody = new FormBody.Builder()
//                .add(Constants.FIREBASE_TOKEN,firebaseToken)
//                .add(Constants.PROJECT,projectName)
//                .add(Constants.USERNAME,username)
//                .add(Constants.PASSWORD,password)
//                .build();
//        Request request = new Request.Builder()
//                .url(Constants.SAVE_MOBILE_INFO_REST_API)
//                .post(requestBody)
//                .addHeader(Constants.COOKIE, StringProcess.getCookieString(serverToken))
//                .build();
//        Call call = okHttpClient.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.d("http", "http rest api  sendMobileInfoToServer  fail         ");
//                LoginController.sendMobileInfoToServerResponse(Constants.HTTP_FAIL);
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                Log.d("http", "http rest api  sendMobileInfoToServer  success  ");
//                String receiveMessage = response.body().string();
//                LoginController.sendMobileInfoToServerResponse(receiveMessage);
//            }
//        });
//    }
//
//    public  void getNodeList(final String projectName,final Controller controller)
//    {
//        Log.d("debug", "Constants.GET_NODE_LIST_REST_APIl         "+Constants.GET_NODE_LIST_REST_API);
//        Request request = new Request.Builder()
//                .url(StringProcess.addProjectNameToUrl( Constants.GET_NODE_LIST_REST_API,projectName ))
//                .addHeader(Constants.COOKIE, StringProcess.getCookieString(serverToken))
//                .build();
//        Call call = okHttpClient.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.d("http", "http rest api getNodeList  fail         ");
//                controller.getNodeListResponse(Constants.HTTP_FAIL);
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                Log.d("http", "http rest api  getNodeList  success  ");
//                String receiveMessage = response.body().string();
//                controller.getNodeListResponse(receiveMessage);
//            }
//        });
//    }
}
