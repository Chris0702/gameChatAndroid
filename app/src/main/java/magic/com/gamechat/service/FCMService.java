package magic.com.gamechat.service;

import android.app.Service;
import android.os.Vibrator;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


import java.util.List;

import magic.com.gamechat.define.Constants;

/**
 * Created by Chris.Wu on 2016/11/9.
 */

public class FCMService extends FirebaseMessagingService {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Vibrator mVibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
        Log.d("debug", "onMessageReceived:" + remoteMessage.getFrom());
        mVibrator.vibrate(Constants.ONE_SECOND_TIME);
//        Factory factory = new Factory();
//        Model controlModel=factory.createModel(getApplication());

//        DBConnection dbHelper = new DBConnection(getApplication());
//        dbHelper.insertUser(new User("asd","zzzz"));
//                List<User> test = dbHelper.getUserList();
//        for (int i = 0; i < test.size(); i++) {
//            Log.d("debug", "id  " + test.get(i).getId());
//            Log.d("debug", "account  " + test.get(i).getAccount());
//            Log.d("debug", "password   " + test.get(i).getPassword());
//
//        }
    }
}
