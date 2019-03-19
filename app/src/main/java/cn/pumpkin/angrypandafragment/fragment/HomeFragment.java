package cn.pumpkin.angrypandafragment.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.atomic.AtomicBoolean;

import cn.pumpkin.angrypandafragment.BlackActivity;
import cn.pumpkin.angrypandafragment.R;
import cn.pumpkin.angrypandafragment.app.App;
import cn.pumpkin.angrypandafragment.inf.ICallBack;
import cn.pumpkin.angrypandafragment.wm.WmPanel;

/**
 * @author: zhibao.Liu
 * @version:
 * @date: 2019/3/19 10:55
 * @des:
 * @see {@link }
 */

public class HomeFragment extends Fragment {

    private Button btn;
    private Button qie;
    private Button show;
    private TextView editText;
    private ICallBack mICallBack;

    private AtomicBoolean isInterrupt = new AtomicBoolean(false);
    private Thread mThread;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("HomeFragment", "onCreate");
        Log.e("HomeFragment", "count : " + count);
        isInterrupt.set(false);
        mThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!isInterrupt.get()) {
                    mExHandler.sendEmptyMessage(0);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        mThread.setName("hello");
        mThread.start();

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("HomeFragment", "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("HomeFragment", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("HomeFragment", "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("HomeFragment", "onStop");
        isInterrupt.set(true);
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("HomeFragment", "onDestroy");
    }

    private ExHandler mExHandler = new ExHandler();
    private int count = 0;

    private class ExHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            editText.setText("cnt : " + count++);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("HomeFragment", "onAttach");
        mICallBack = (ICallBack) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.home_fragment, null);
        editText = (TextView) root.findViewById(R.id.hello);
        btn = (Button) root.findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mICallBack.doBack(1);

            }
        });

        qie = (Button)root.findViewById(R.id.qie);
        qie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), BlackActivity.class);
                startActivity(intent);
            }
        });

        show = (Button)root.findViewById(R.id.show);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mICallBack.doBack(5);
            }
        });

        return root;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e("HomeFragment", "onSaveInstanceState");
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.e("HomeFragment", "onViewStateRestored");
    }
}
