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
import cn.pumpkin.angrypandafragment.inf.ICallBack;

/**
 * @author: zhibao.Liu
 * @version:
 * @date: 2019/3/19 11:52
 * @des:
 * @see {@link }
 */

public class DetailsFragment extends Fragment {

    private Button btn;
    private ICallBack mICallBack;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mICallBack= (ICallBack) getActivity();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.details_fragment, null);
        TextView editText = (TextView) root.findViewById(R.id.hello);
        btn = (Button)root.findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mICallBack.doBack(2);
            }
        });
        return root;
    }
}
