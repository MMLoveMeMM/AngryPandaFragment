package cn.pumpkin.angrypandafragment.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import cn.pumpkin.angrypandafragment.R;
import cn.pumpkin.angrypandafragment.eventbus.KeyCodeEvent;
import cn.pumpkin.angrypandafragment.inf.ICallBack;
import de.greenrobot.event.Subscribe;

/**
 * @author: zhibao.Liu
 * @version:
 * @date: 2019/3/19 16:20
 * @des:
 * @see {@link }
 */

public class NumberFragment extends Fragment {

    private Button btn;
    private TextView editText;
    private ICallBack mICallBack;
    private int keyCode;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mICallBack = (ICallBack) getActivity();

        Bundle bundle = getArguments();
        keyCode = bundle.getInt("key");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.number_fragment, null);
        editText = (TextView) root.findViewById(R.id.hello);
        editText.setText("current key code : " + keyCode);
        btn = (Button) root.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mICallBack.doBack(2);
            }
        });

        return root;

    }

    @Subscribe
    public void listener(KeyCodeEvent eventMessage){
        if(editText!=null) {
            editText.setText("current key code : " + eventMessage.getKeyCode());
        }
    }

}
