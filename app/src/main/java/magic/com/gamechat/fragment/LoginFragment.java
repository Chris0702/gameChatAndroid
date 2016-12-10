package magic.com.gamechat.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;

import magic.com.gamechat.R;
import magic.com.gamechat.define.Constants;
import magic.com.gamechat.tool.Factory;
import magic.com.gamechat.tool.HttpClient;
import magic.com.gamechat.tool.Model;

/**
 * Created by DX on 2016/12/10.
 */
public class LoginFragment extends ControlFragment {
    HttpClient httpClient;
    ChatFragment chatFragment;
    private EditText inputAccount;
    private EditText inputPassword;
    private Button loginOrLogout;
    String username;
    String password;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View returnView = inflater.inflate(R.layout.login_fragment, container, false);
        createObj(returnView);
        setListener();
        autoLogin();
        return returnView;
    }

    public void setControlModel(Model model) {
        controlModel = model;
    }

    private void createObj(View view) {
        factory = new Factory();
        controlActivity = getActivity();
        httpClient = factory.createHttpClient();
        chatFragment=factory.createChatFragment();
        inputAccount = (EditText) view.findViewById(R.id.input_username);
        inputPassword = (EditText) view.findViewById(R.id.input_password);
        loginOrLogout = (Button) view.findViewById(R.id.login_or_login_button);
        username=controlModel.getLastUsername();
        password=controlModel.getLastPassword();
    }

    private void setListener() {
        buttonClick();
    }

    private void autoLogin()
    {
        if(controlModel.isLogin())
        {
            controlModel.toastString("現在開始嘗試自動登入");
            loginSuccess();
        }
        else
        {
            controlModel.toastString("請手動登入");
        }
    }

    private void buttonClick() {
        loginOrLogout.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                username = inputAccount.getText().toString();
                 password = inputPassword.getText().toString();



                controlModel.toastString("帳號   " + username + "   密碼  " + password);
//                controlModel.logSystemInfoDB();
                loginSuccess();
            }
        });
    }

    private void loginRequest(String account, String password) {

    }

    public void loginResponse(String receiveMessage) {

    }

    private void loginSuccess()
    {
        Log.d("debug","username    " +username      );
        Log.d("debug","password    " +password      );
        controlModel.saveLoginAccount(username,password);
        controlModel.changeFragment(getFragmentManager(), R.id.content_main, chatFragment);

    }

}
