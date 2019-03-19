package cn.pumpkin.angrypandafragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import cn.pumpkin.angrypandafragment.inf.ICallBack;

public abstract class BaseActivity extends AppCompatActivity implements ICallBack {

    private static final String TAG = BaseActivity.class.getName();
    protected FragmentManager manager;
    protected FragmentTransaction transaction;

    protected int keyCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.e(TAG,"onKeyDown : " + keyCode);
        this.keyCode = keyCode;
        doBack(3);
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void doBack(int config) {
        // todo work in your sub class
    }
}
