package cn.pumpkin.angrypandafragment.app;

import android.content.Context;

/**
 * @author: zhibao.Liu
 * @version:
 * @date: 2019/3/19 17:19
 * @des:
 * @see {@link }
 */

public class App {

    private static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    public static void setContext(Context mContext) {
        App.mContext = mContext;
    }
}
