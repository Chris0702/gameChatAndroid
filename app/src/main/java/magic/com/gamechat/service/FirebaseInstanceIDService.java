package magic.com.gamechat.service;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import magic.com.gamechat.tool.Factory;
import magic.com.gamechat.tool.Model;
import okhttp3.OkHttpClient;

/**
 * Created by Chris.Wu on 2016/11/9.
 */

public class FirebaseInstanceIDService extends FirebaseInstanceIdService {

    Factory factory;
    Model controlModel;

    @Override
    public void onCreate() {
        super.onCreate();
        createObj();
    }

    @Override
    public void onTokenRefresh() {
        Log.d("debug", "onTokenRefresh");
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d("debug", "Token:" + token);
        saveFirebaseToken(token);
//        registerToken(token);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        controlModel.closeDB();
    }

    private void createObj() {
        factory = new Factory();
        controlModel = factory.createModel(getApplication());
    }

    private void saveFirebaseToken(String token) {

//        controlModel.saveFirebaseToken(token);
    }
//    private void registerToken(String token){
//        Factory factory=new Factory();
//        Model controlModel=factory.createModel();
//        controlModel.registerTokenHttp(token);
//    }
}
