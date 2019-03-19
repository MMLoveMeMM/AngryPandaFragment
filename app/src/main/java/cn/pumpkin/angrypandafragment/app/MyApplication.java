package cn.pumpkin.angrypandafragment.app;

import android.app.Application;

/**
 * @author: zhibao.Liu
 * @version:
 * @date: 2019/3/19 17:18
 * @des:
 * @see {@link }
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        App.setContext(getApplicationContext());

    }
}
