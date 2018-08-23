package com.zhou.jingfutong;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.zhou.jingfutong.jmodel.utils.UserConfigUtils;
import com.zhou.jingfutong.ui.BaseFragmentActivity;
import com.zhou.jingfutong.ui.fragments.MainGetOrderFragment;
import com.zhou.jingfutong.ui.fragments.MainHomeFragment;
import com.zhou.jingfutong.ui.fragments.MainMessageFragment;
import com.zhou.jingfutong.ui.fragments.MainOrderFragment;
import com.zhou.jingfutong.ui.fragments.MainSheBeiFragment;
import com.zhou.jingfutong.ui.fragments.MainShopperMessageFragment;
import com.zhou.jingfutong.ui.fragments.MainShopperOrderFragment;
import com.zhou.jingfutong.ui.fragments.MainShopperUserFragment;
import com.zhou.jingfutong.ui.fragments.MainUserFragment;
import com.zhou.jingfutong.ui.jinterfaces.MainActivityCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class MainActivity extends BaseFragmentActivity implements RadioGroup.OnCheckedChangeListener, MainActivityCallback {

    @Bind(R.id.rg_main)
    RadioGroup rgMain;
    @Bind(R.id.rg_main_one)
    RadioGroup rgMainOne;

    @Bind(R.id.rb_main)
    RadioButton rb_main;
    @Bind(R.id.rb_main_one1)
    RadioButton rg_mainone;
    @Bind(R.id.Vp_container)
    ViewPager container;


    private MainHomeFragment homeFragment;
    private MainOrderFragment orderFragment;
    private MainMessageFragment messageFragment;
    private MainUserFragment userFragment;
    private MainGetOrderFragment getOrderFragment;
    private MainShopperOrderFragment shopperOrderFragment;
    private MainSheBeiFragment sheBeiFragment;
    private MainShopperMessageFragment shopperMessageFragment;
    private MainShopperUserFragment shopperUserFragment;
    private List<Fragment> fragmentList = new ArrayList<>();
    private UserConfigUtils userConfigUtils;

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initData() {
        userConfigUtils = new UserConfigUtils(this);
        homeFragment = new MainHomeFragment();              //初始化第一个界面
        orderFragment = new MainOrderFragment();              //初始化第二个界面
        messageFragment = new MainMessageFragment();           //初始化第三个界面
        userFragment = new MainUserFragment();                 //初始化第四个界面
        getOrderFragment = new MainGetOrderFragment();        //初始化第五个界面
        shopperOrderFragment = new MainShopperOrderFragment();    //初始化第六个界面
        sheBeiFragment = new MainSheBeiFragment();                //初始化第七个界面
        shopperMessageFragment = new MainShopperMessageFragment();//初始化第八个界面
        shopperUserFragment = new MainShopperUserFragment();      //初始化第九个界面

        fragmentList.add(homeFragment);      //将第一个界面添加到集合中
        fragmentList.add(orderFragment);      //将第二个界面添加到集合中
        fragmentList.add(messageFragment);      //将第三个界面添加到集合中
        fragmentList.add(userFragment);      //将第四个界面添加到集合中
        fragmentList.add(getOrderFragment);      //将第五个界面添加到集合中
        fragmentList.add(shopperOrderFragment);      //将第六个界面添加到集合中
        fragmentList.add(sheBeiFragment);      //将第七个界面添加到集合中
        fragmentList.add(shopperMessageFragment);      //将第八个界面添加到集合中
        fragmentList.add(shopperUserFragment);      //将第九个界面添加到集合中
        //创建adapter
        GuideAdapter adapter = new GuideAdapter(getSupportFragmentManager());
        //设置viewpager缓存页数,默认的缓存一页,因为页面共有9页,
        //所以设置缓存4页,这样所以page在滑动过程中不会重新创建
        container.setOffscreenPageLimit(4);
        container.setAdapter(adapter);

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_main:
                container.setCurrentItem(0);
                break;
            case R.id.rb_show:
                container.setCurrentItem(1);
                break;
            case R.id.rb_nine:
                container.setCurrentItem(2);
                break;
            case R.id.rb_catory:
                container.setCurrentItem(3);
                break;
            case R.id.rb_main_one1:
                container.setCurrentItem(4);
                break;
            case R.id.rb_show_one:
                container.setCurrentItem(5);
                break;
            case R.id.rb_nine_one:
                container.setCurrentItem(6);
                break;
            case R.id.rb_catory_one:
                container.setCurrentItem(7);
                break;
            case R.id.rb_user_one:
                container.setCurrentItem(8);
                break;
        }
    }

    @Override
    public void doMain() {
        isState();
    }

    class GuideAdapter extends FragmentPagerAdapter {
        public GuideAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }

    @Override
    public void init() {
        rgMain.setOnCheckedChangeListener(this);
        rgMainOne.setOnCheckedChangeListener(this);
        isState();

    }

    public void isState() {
        String states = userConfigUtils.getState();
        if (states.equals(userConfigUtils.state_user) || states.isEmpty()) {
            container.setCurrentItem(0);
            rgMain.setVisibility(View.VISIBLE);
            rgMainOne.setVisibility(View.GONE);
            rb_main.setChecked(true);
        } else if (states.equals(userConfigUtils.state_shopper)) {
            container.setCurrentItem(5);
            rgMain.setVisibility(View.GONE);
            rgMainOne.setVisibility(View.VISIBLE);
            rg_mainone.setChecked(true);
        }
    }


}
