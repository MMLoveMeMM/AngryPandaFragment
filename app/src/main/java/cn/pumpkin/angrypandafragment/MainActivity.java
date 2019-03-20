package cn.pumpkin.angrypandafragment;

import android.os.Bundle;

import cn.pumpkin.angrypandafragment.fragment.DetailsFragment;
import cn.pumpkin.angrypandafragment.fragment.HomeFragment;
import cn.pumpkin.angrypandafragment.fragment.NumberFragment;

public class MainActivity extends BaseActivity {

    private DetailsFragment detailsFragment = new DetailsFragment();
    private HomeFragment homeFragment = new HomeFragment();
    private NumberFragment numberFragment = new NumberFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        detailsFragment = new DetailsFragment();
        homeFragment = new HomeFragment();
        numberFragment = new NumberFragment();

        eventBus.register(numberFragment);

        transaction.add(R.id.container, homeFragment, "home");
        transaction.commit();

    }

    @Override
    public void doBack(int config) {
        /**
         * 把其他fragment置为null,不然fragment里面还有上一次的结果值
         * 如果不想保留上一次的结果信息
         * 比如HomeFragment 一直使用全局的homeFragment,不置null,那么里面count的值还会是上一次onDestroy之前的值,
         * count内存中的值是跟随homeFragment的
         */
        transaction = manager.beginTransaction();
        if (config == 1) {
            transaction.replace(R.id.container, detailsFragment);
        } else if (config == 2) {
            transaction.replace(R.id.container, homeFragment);
        }else if(config == 3){
            Bundle bundle=new Bundle();
            bundle.putInt("key",keyCode);
            numberFragment.setArguments(bundle);
            transaction.replace(R.id.container, numberFragment);
        }else if(config == 5){
            /**
             * 目前系统太高,不能随便制作窗口,除非系统权限
             */
            // WmPanel.makeText(App.getContext(),"this is panel !",WmPanel.LENGTH_LONG).show();
        }
        transaction.commit();
    }

}
