package magic.com.gamechat.fragment;

import android.app.Activity;
import android.app.Fragment;

import magic.com.gamechat.tool.Factory;
import magic.com.gamechat.tool.Model;

/**
 * Created by DX on 2016/12/10.
 */
public class ControlFragment extends Fragment {
    Factory factory;
    Activity controlActivity;
    Model controlModel;

    public void setControlModel(Model model) {
        controlModel.toastString("ControlFragment  setControlModel(Model model) ",getActivity());
    }

    public Model getControlModel() {
        return controlModel;
    }

}
