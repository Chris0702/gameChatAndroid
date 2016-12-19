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
    private EditText inputAccount;
    private EditText inputPassword;
    private Button loginOrSignInButton;
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
        inputAccount = (EditText) view.findViewById(R.id.input_username);
        inputPassword = (EditText) view.findViewById(R.id.input_password);
        loginOrSignInButton = (Button) view.findViewById(R.id.login_or_signIn_button);
        username = controlModel.getLastUsername();
        password = controlModel.getLastPassword();
    }

    private void setListener() {
        buttonClick();
    }

    private void autoLogin() {
        if (controlModel.isLogin()) {
            controlModel.toastString("現在開始嘗試自動登入", controlActivity);
            loginRequest();
        } else {
            controlModel.toastString("請手動登入", controlActivity);
        }
    }

    private void buttonClick() {
        loginOrSignInButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                username = inputAccount.getText().toString();
                password = inputPassword.getText().toString();
                controlModel.toastString("帳號   " + username + "   密碼  " + password, controlActivity);
                if (controlModel.isEngOrNum(username) && controlModel.isEngOrNum(password)) {
                    loginRequest();
                } else {
                    controlModel.toastString(Constants. USERNAME_PASSWORD_ENGLISH_NUMBER_ERROR_ACCOUNT, controlActivity);
                }
            }
        });
    }

    private void loginRequest() {
        if (!username.equals(Constants.ENPTY_STRING) && !password.equals(Constants.ENPTY_STRING)) {
            httpClient.login(username, password, this);
        } else {
            controlModel.toastString(Constants.INPUT_ERROR_ACCOUNT, controlActivity);
        }
    }

    public void loginResponse(String receiveMessage) {
        Log.d("debug", " loginResponse    receiveMessage    " + receiveMessage);
        if (controlModel.getHttpResult(receiveMessage)) {
            controlModel.toastString("登入成功", controlActivity);
            loginSuccess();
        } else {
            resolveLoginErrorResString(receiveMessage);
        }
    }

    private void loginSuccess() {
        SearchFriendFragment searchFriendFragment = factory.createSearchFriendFragment();
        controlModel.saveLoginAccount(username, password);
        controlModel.changeFragment(getFragmentManager(), R.id.content_main, searchFriendFragment);
    }

    private void resolveLoginErrorResString(String receiveMessage) {
        String resString = controlModel.getJSONProtString(Constants.RES_STRING_REST_API, receiveMessage);
        if (resString.equals(Constants.INPUT_ERROR_ACCOUNT_RES_STRING)) {
            controlModel.toastString(Constants.INPUT_ERROR_ACCOUNT, controlActivity);
        } else if (resString.equals(Constants.USERNAME_ERROR_ACCOUNT_RES_STRING)) {
            controlModel.toastString(Constants.USERNAME_ERROR_ACCOUNT, controlActivity);
        } else if (resString.equals(Constants.PASSWORD_ERROR_ACCOUNT_RES_STRING)) {
            controlModel.toastString(Constants.PASSWORD_ERROR_ACCOUNT, controlActivity);
        }
    }
}
