package magic.com.gamechat.fragment;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import magic.com.gamechat.R;
import magic.com.gamechat.tool.Factory;
import magic.com.gamechat.tool.Model;

/**
 * Created by DX on 2016/12/13.
 */
public class SearchFriendFragment extends ControlFragment {

    private Button constellationSearchButton;
    private Button accountSearchButton;
    private Button interestSearchButton;
    private Button friendsListButton;
    private Button logoutButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View returnView = inflater.inflate(R.layout.search_friend_fragment, container, false);
        createObj(returnView);
        setListener();
        return returnView;
    }

    @Override
    public void onResume() {
        super.onResume();
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    return true;
                }
                return false;
            }
        });
    }


    private void createObj(View view) {
        factory = new Factory();
        controlActivity = getActivity();
        constellationSearchButton = (Button) view.findViewById(R.id.constellation_search_button);
        accountSearchButton = (Button) view.findViewById(R.id.account_search_button);
        interestSearchButton = (Button) view.findViewById(R.id.interest_search_button);
        friendsListButton = (Button) view.findViewById(R.id.friends_list_button);
        logoutButton=(Button) view.findViewById(R.id.logout_button);
    }

    private void setListener() {
        buttonClick();
    }

    private void buttonClick() {
        constellationSearchButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                controlModel.toastString("星座搜尋", controlActivity);
                ChatTextFragment chatTextFragment = factory.createChatTextFragment();
                controlModel.changeFragment(getFragmentManager(), R.id.content_main, chatTextFragment);
            }
        });
        accountSearchButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                controlModel.toastString("帳號搜尋", controlActivity);
                ChatTextFragment chatTextFragment = factory.createChatTextFragment();
                controlModel.changeFragment(getFragmentManager(), R.id.content_main, chatTextFragment);
            }
        });
        interestSearchButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                controlModel.toastString("興趣搜尋", controlActivity);
                ChatTextFragment chatTextFragment = factory.createChatTextFragment();
                controlModel.changeFragment(getFragmentManager(), R.id.content_main, chatTextFragment);
            }
        });
        friendsListButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                controlModel.toastString("好友列表", controlActivity);
            }
        });
        logoutButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                LoginFragment loginFragment = factory.createLoginFragment();
                controlModel.toastString("好友列表", controlActivity);
                controlModel.setLogoutToDB();
                controlModel.changeFragment(getFragmentManager(), R.id.content_main, loginFragment);
            }
        });
    }

    public void setControlModel(Model model) {
        controlModel = model;
    }
}
