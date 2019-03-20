package cn.pumpkin.angrypandafragment.eventbus;

/**
 * @author: zhibao.Liu
 * @version:
 * @date: 2019/3/20 09:22
 * @des: 发送key键值
 * @see {@link }
 */

public class KeyCodeEvent {
    private int keyCode;

    public KeyCodeEvent(int key){
        this.keyCode = key;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }
}
