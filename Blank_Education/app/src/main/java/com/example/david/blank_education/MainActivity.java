package com.example.david.blank_education;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bigkoo.convenientbanner.CBViewHolderCreator;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

import java.util.Arrays;
import java.util.List;

import cn.bmob.v3.Bmob;


public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{

    String[] images = new String[5];
    private List<String> networkImages;
    private RadioGroup radioGroup;
    private RadioButton education, life, me;
    private ResideMenu  resideMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if( savedInstanceState == null )
            changeFragment(new EducateFragment());
        Bmob.initialize(this, "2937a4aa0da04e31a3f2a9135ea87d2c");

        init();
    }

    private void init()
    {
        initImageLoader();
        initImages();
        networkImages = Arrays.asList(images);
        ConvenientBanner convenientBanner = (ConvenientBanner)findViewById(R.id.convenientBanner);
        convenientBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();
            }
        }, networkImages).setPageIndicator(new int[]{R.drawable.pic_on, R.drawable.pic_dison})
                .setPageTransformer(ConvenientBanner.Transformer.DefaultTransformer);

        initNext();

    }

    private void initImageLoader(){
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.button_main_education_dison)
                .cacheInMemory(true).cacheOnDisk(true).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                this).defaultDisplayImageOptions(defaultOptions)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(config);
    }

    private void initImages()
    {
        images[0] = "http://pic25.nipic.com/20121120/8989765_154157612161_2.jpg";
        images[1] = "http://pic1a.nipic.com/2008-10-31/20081031174853985_2.jpg";
        images[2] = "http://pica.nipic.com/2008-06-14/2008614113421778_2.jpg";
        images[3] = "http://cpic2.edushi.com/cn/hz/zh-chs/LocalInfo/Companys/352982/Windows/Photo2011090715234192602.jpg";
        images[4] = "http://www.jianke.com/bbs/data/attachment/forum/201405/15/115435y9ew9tt5wtaj55n5.jpg";
    }

    private void initNext()
    {
        radioGroup = (RadioGroup)findViewById(R.id.radiogroup_main_bottom);
        radioGroup.setOnCheckedChangeListener(this);
        education = (RadioButton)findViewById(R.id.radiobutton_main_educate);

        resideMenu = new ResideMenu(this);
        resideMenu.setBackground(R.drawable.meu_background);
        resideMenu.attachToActivity(this);

        FrameLayout frameLayout = (FrameLayout)findViewById(R.id.convenientBannerContainer);
        resideMenu.addIgnoredView(frameLayout);

        // create menu items;
        String titles[] = { "Home", "Profile", "Calendar", "Settings" };
        int icon[] = { R.drawable.button_main_education_dison, R.drawable.button_main_education_dison, R.drawable.button_main_education_dison, R.drawable.button_main_education_dison };

        for (int i = 0; i < titles.length; i++){
            ResideMenuItem item = new ResideMenuItem(this, icon[i], titles[i]);
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            resideMenu.addMenuItem(item, ResideMenu.DIRECTION_LEFT);
            resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);
        }

    }

    public void onCheckedChanged(RadioGroup group, int checkId)
    {
        switch(checkId)
        {
            case R.id.radiobutton_main_educate:
                changeFragment(new EducateFragment());
                break;
            case R.id.radiobutton_main_life:
                changeFragment(new LifeFragment());
                break;
            case R.id.radiobutton_main_me:
                changeFragment(new MeFragment());
                break;
            default:
                break;

        }
    }

    private void changeFragment(Fragment targetFragment){

        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.anim_fragment_in, R.anim.anim_fragment_out)
                .replace(R.id.fragment_container, targetFragment, "fragment")
                .setTransitionStyle(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }



}


