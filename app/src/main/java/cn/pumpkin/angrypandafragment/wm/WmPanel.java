package cn.pumpkin.angrypandafragment.wm;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IntDef;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import cn.pumpkin.angrypandafragment.R;

/**
 * @author: zhibao.Liu
 * @version:
 * @date: 2019/1/9 17:42
 * @des:
 * @see {@link }
 */

public class WmPanel {

    @IntDef({LENGTH_SHORT, LENGTH_LONG})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Duration {
    }

    private static int mDelayTimeTpye = 0;
    public static final int LENGTH_SHORT = 0;
    public static final int LENGTH_LONG = 1;
    public static final long SHORT_TIME = 1500;
    public static final long LONG_TIME = 5000;

    private static final int FLAG_DISMISS = 9527;

    private static View mView = null;
    private static WindowManager mWindowManager = null;
    private static Context mContext = null;
    public static Boolean isShown = false;
    private static final int GRAVITY = 0x00000051;
    private String title;

    private static DismissHandler mHandler=new DismissHandler();

    public WmPanel(Context context, String title) {
        //用ApplicationContext防止窗口泄漏
        mContext=context.getApplicationContext();
        this.title = title;
    }
    /**
     * 显示弹出框
     * @param context
     */
    public static WmPanel makeText(final Context context, String content, int type) {
        if (isShown) {
            /**
             * 如果当前正在显示,直接取消当前显示,再重新创建新的显示
             * */
            mHandler.removeMessages(FLAG_DISMISS);
            dismiss();
        }
        WmPanel panel = new WmPanel(context, content);
        mDelayTimeTpye = type;

        return panel;
    }

    public static WmPanel makeText(Context context, int resId, int type) {
        String content = context.getResources().getString(resId);
        if (isShown) {
            /**
             * 如果当前正在显示,直接取消当前显示,再重新创建新的显示
             * */
            mHandler.removeMessages(FLAG_DISMISS);
            dismiss();
        }
        WmPanel toast = new WmPanel(context, content);
        mDelayTimeTpye = type;
        return toast;
    }

    public void show(){
        isShown = true;
        mWindowManager = (WindowManager) mContext .getSystemService(Context.WINDOW_SERVICE);
        mView = setUpView(mContext,title);
        final WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.type = WindowManager.LayoutParams.TYPE_TOAST;
        int flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        params.flags = flags;
        params.format = PixelFormat.TRANSLUCENT;
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        int dxy = mContext.getResources().getDimensionPixelSize(
                R.dimen.panel_y_offset);
        params.y = dxy;
        params.gravity = GRAVITY;
        mWindowManager.addView(mView, params);

        mHandler.removeMessages(FLAG_DISMISS);
        Message msg = mHandler.obtainMessage();
        msg.what = FLAG_DISMISS;
        mHandler.sendMessageDelayed(msg,mDelayTimeTpye == LENGTH_SHORT ? SHORT_TIME : LONG_TIME);
    }

    private static View setUpView(Context context, String text) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.panel_layout, null);
        TextView tview= (TextView) view.findViewById(R.id.title);
        tview.setText(text);
        return view;
    }

    /**
     * 隐藏弹出框
     */
    public static void dismiss() {
        if (isShown && null != mView) {
            mWindowManager.removeView(mView);
            isShown = false;
        }
    }

    private static class DismissHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == FLAG_DISMISS) {
                dismiss();
            }
        }

    };

}
