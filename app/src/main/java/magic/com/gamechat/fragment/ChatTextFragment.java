package magic.com.gamechat.fragment;

import android.app.Fragment;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;

import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import magic.com.gamechat.R;
import magic.com.gamechat.define.Constants;
import magic.com.gamechat.tool.Factory;
import magic.com.gamechat.tool.Model;
import magic.com.gamechat.tool.SocketIO;

/**
 * Created by DX on 2016/12/10.
 */
public class ChatTextFragment extends ControlFragment {

    private Factory factory;
    private LoginFragment loginFragment;
    private Button sendMessageButton;
    private Button sendImageButton;
    private Button logoutButton;
    private EditText input;
    private LinearLayout chatContent;
    private ScrollView chatContentScroll;
    private SocketIO socketIO;
    private Socket socket;
    private TextView systemMessage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View returnView = inflater.inflate(R.layout.chat_text_fragment, container, false);
        init(returnView);
        socketRun();
        buttonClick();
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
                    SearchFriendFragment searchFriendFragment = factory.createSearchFriendFragment();
                    controlModel.changeFragment(getFragmentManager(), R.id.content_main, searchFriendFragment);
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        Toast.makeText(getActivity(), "onDestroyView()", Toast.LENGTH_SHORT).show();
        socket.disconnect();
    }

    public void setControlModel(Model model) {
        controlModel = model;
    }

    private void buttonClick() {
        sendMessageButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                String message = input.getText().toString();
                if (!message.equals("")) {
                    socket.emit(Constants.CHAT_MESSAGE_EVENT_SOCKET, message);
                    input.setText("");
                    LayoutInflater inflater = getActivity().getLayoutInflater(); //調用Activity的getLayoutInflater()
                    View sendMessageView = LayoutInflater.from(getActivity()).inflate(R.layout.chat_send_message_view, null);
                    LinearLayout sendMessageContent = (LinearLayout) sendMessageView.findViewById(R.id.sendMessageContent);
                    TextView sendMessageViewText = new TextView(sendMessageView.getContext());
                    ImageView sendMessageViewIcon = (ImageView) sendMessageView.findViewById(R.id.sendMessageIcon);
                    Handler handler = new Handler();
                    sendMessageViewIcon.setImageResource(R.drawable.female_icon);
                    sendMessageViewText.setTextColor(Color.parseColor(Constants.FEMALE_TEXT_COLOR));
                    sendMessageViewText.setBackgroundColor(Color.parseColor(Constants.FEMALE_BACKGROUND_COLOR));
                    sendMessageViewText.setText(message);
                    sendMessageContent.addView(sendMessageViewText);
                    sendMessageViewText.setTextSize(Constants.MESSAGE_TEXT_SIZE);
                    chatContent.addView(sendMessageView);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            chatContentScroll.fullScroll(ScrollView.FOCUS_DOWN);
                        }
                    });

                }
            }
        });

        sendImageButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                System.out.println("sendmessageImg");
                //開啟相簿相片集，須由startActivityForResult且帶入requestCode進行呼叫，原因為點選相片後返回程式呼叫onActivityResult
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, Constants.PHOTO);
            }
        });


        logoutButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                controlModel.setLogoutToDB();
                controlModel.changeFragment(getFragmentManager(), R.id.content_main, loginFragment);
            }
        });

    }

    private void init(View view) {
        objectInit(view);
    }

    private void socketRun() {
        String findType = "boyFindGirl";//getArguments().getString("findType");
        //Toast.makeText(getActivity(), findType, Toast.LENGTH_SHORT).show();
        socket.emit(Constants.FIND_EVENT, findType);
        socket.on(Constants.CHAT_MESSAGE_EVENT_SOCKET, onChatMessage);
        socket.on(Constants.CHAT_IMAGE_EVENT_SOCKET, onChatImage);
        socket.on(Constants.SYSTEM_EVENT_SOCKET, onSystemMessage);
//        socket.on(Constants.SYSTEM_EVENT, onChatMessage);
    }

    private void objectInit(View view) {
        factory = new Factory();
        loginFragment = factory.createLoginFragment();
        logoutButton = (Button) view.findViewById(R.id.logout_button);
        sendMessageButton = (Button) view.findViewById(R.id.sendMessageButton);
        sendImageButton = (Button) view.findViewById(R.id.sendImageButton);
        input = (EditText) view.findViewById(R.id.inputChatMessage);
        chatContent = (LinearLayout) view.findViewById(R.id.chatMessageContentLayout);
        chatContentScroll = (ScrollView) view.findViewById(R.id.chatMessageScrollLayout);
        socketIO = factory.createSocketIO(Constants.CHAT_NAMESPACE_SOCKET);
        socket = socketIO.getSocket();
        systemMessage = (TextView) view.findViewById(R.id.systemMessage);
    }

    private Emitter.Listener onChatImage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    LayoutInflater inflater = getActivity().getLayoutInflater(); //調用Activity的getLayoutInflater()
                    View receiveMessageView = LayoutInflater.from(getActivity()).inflate(R.layout.chat_receive_message_view, null);
                    LinearLayout receiveMessageContent = (LinearLayout) receiveMessageView.findViewById(R.id.receiveMessageContent);
                    ImageView receiveMessageViewIcon = (ImageView) receiveMessageView.findViewById(R.id.receiveMessageIcon);
                    ImageView receiveMessageViewImage = new ImageView(receiveMessageView.getContext());
                    Handler handler = new Handler();
                    receiveMessageViewIcon.setImageResource(R.drawable.female_icon);
//                    receiveMessageContent.setBackgroundColor(Color.GRAY);
//                    receiveMessageViewImage.setBackgroundColor(Color.BLUE);
                    byte[] byteArray = (byte[]) args[0];
                    Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

//                    receiveMessageContent.getLayoutParams().height = receiveMessageContent.getLayoutParams().width * bmp.getHeight() / bmp.getWidth();
                    receiveMessageViewImage.setImageBitmap(bmp);
                    receiveMessageViewImage.setScaleType(ImageView.ScaleType.MATRIX);
                    receiveMessageContent.addView(receiveMessageViewImage);


                    chatContent.addView(receiveMessageView);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            chatContentScroll.fullScroll(ScrollView.FOCUS_DOWN);
                        }
                    });
                }
            });
        }
    };

    private Emitter.Listener onChatMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    String receiveChatText = "" + args[0];
                    LayoutInflater inflater = getActivity().getLayoutInflater(); //調用Activity的getLayoutInflater()
                    View receiveMessageView = LayoutInflater.from(getActivity()).inflate(R.layout.chat_receive_message_view, null);
                    LinearLayout receiveMessageContent = (LinearLayout) receiveMessageView.findViewById(R.id.receiveMessageContent);
                    TextView receiveMessageViewText = new TextView(receiveMessageView.getContext());
                    ImageView receiveMessageViewIcon = (ImageView) receiveMessageView.findViewById(R.id.receiveMessageIcon);
                    Handler handler = new Handler();
                    receiveMessageViewIcon.setImageResource(R.drawable.female_icon);
                    receiveMessageViewText.setTextColor(Color.parseColor(Constants.FEMALE_TEXT_COLOR));
                    receiveMessageViewText.setBackgroundColor(Color.parseColor(Constants.FEMALE_BACKGROUND_COLOR));
                    receiveMessageViewText.setText(receiveChatText);
                    receiveMessageContent.addView(receiveMessageViewText);
                    receiveMessageContent.setBackgroundColor(Color.GRAY);
                    receiveMessageViewText.setTextSize(Constants.MESSAGE_TEXT_SIZE);
                    chatContent.addView(receiveMessageView);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            chatContentScroll.fullScroll(ScrollView.FOCUS_DOWN);
                        }
                    });
                }
            });
        }
    };

    private Emitter.Listener onSystemMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    String receiveSystemText = " " + args[0];
//                    Toast.makeText(getActivity(),receiveSystemText, Toast.LENGTH_SHORT).show();
                    systemMessage.setText(receiveSystemText);
                }
            });
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //藉由requestCode判斷是否為開啟相機或開啟相簿而呼叫的，且data不為null
        if (requestCode == Constants.PHOTO && data != null) {
            //取得照片路徑uri
            Uri uri = data.getData();
            ContentResolver cr = getActivity().getContentResolver();
            try {
                //讀取照片，型態為Bitmap
                Bitmap bmp = BitmapFactory.decodeStream(cr.openInputStream(uri));
                LayoutInflater inflater = getActivity().getLayoutInflater(); //調用Activity的getLayoutInflater()
                View sendMessageView = LayoutInflater.from(getActivity()).inflate(R.layout.chat_send_message_view, null);
                LinearLayout sendMessageContent = (LinearLayout) sendMessageView.findViewById(R.id.sendMessageContent);
                ImageView sendMessageViewImage = new ImageView(sendMessageView.getContext());
                ImageView sendMessageViewIcon = (ImageView) sendMessageView.findViewById(R.id.sendMessageIcon);
                Handler handlerView = new Handler();
                Handler handlerSocket = new Handler();

                sendMessageViewIcon.setImageResource(R.drawable.female_icon);
                sendMessageViewImage.setImageBitmap(bmp);
                sendMessageViewImage.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                sendMessageContent.getLayoutParams().height = sendMessageContent.getLayoutParams().width * bmp.getHeight() / bmp.getWidth();
                sendMessageContent.addView(sendMessageViewImage);
                chatContent.addView(sendMessageView);
                socket.emit(Constants.CHAT_IMAGE_EVENT_SOCKET, controlModel.Bitmap2Bytes(bmp));
                handlerView.post(new Runnable() {
                    @Override
                    public void run() {
                        chatContentScroll.fullScroll(ScrollView.FOCUS_DOWN);
                    }
                });
            } catch (FileNotFoundException e) {
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
