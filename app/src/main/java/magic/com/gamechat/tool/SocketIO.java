package magic.com.gamechat.tool;

import android.util.Log;
import android.widget.Toast;
import java.net.URISyntaxException;
import io.socket.client.IO;
import io.socket.client.Socket;

import magic.com.gamechat.define.Constants;
/**
 * Created by Chris.Wu on 2016/10/21.
 */


public class SocketIO {
    private Socket socket;

    public SocketIO(String nameSpace) {
        try {
            Log.d("debug", "socket connect action");
            Log.d("debug", "Constants.SERVER_URL "+Constants.SERVER_URL);
            IO.Options opts = new IO.Options();
            opts.reconnection = true;
            opts.reconnectionDelay = Constants.ONE_SECOND_TIME;
            socket = IO.socket(Constants.SERVER_URL + nameSpace, opts);
            socket.connect();
            Log.d("debug", "socket connect ok");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void disconnect() {
        socket.disconnect();
    }
}
