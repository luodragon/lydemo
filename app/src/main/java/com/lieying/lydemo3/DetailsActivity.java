package com.lieying.lydemo3;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.lieying.lydemo3.bean.DataBean;
import com.lieying.lydemo3.bean.ProductDetail;
import com.lieying.lydemo3.config.Config;
import com.lieying.lydemo3.net.Response;
import com.lieying.lydemo3.net.SchedulerProvider;
import com.lieying.lydemo3.repository.MainRepository;
import com.lieying.lydemo3.ui.home.HomeFragment;
import com.lieying.lydemo3.ui.notifications.NotificationsFragment;
import com.lieying.lydemo3.ui.productshow.ProductShowFragment;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;


import java.util.ArrayList;

import io.reactivex.functions.Consumer;

@Route(path = "/lydemo3/DetailsActivity")
public class DetailsActivity extends AppCompatActivity {
    com.youth.banner.Banner banner;
    TextView tvProductName, tvMonthSails,tvPriceNow,tvPriceOld;
    TabLayout mTabLayout;
    ViewPager2 mViewPager;

    @Autowired()
    int productId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ARouter.getInstance().inject(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        banner = findViewById(R.id.banner);
        tvProductName = findViewById(R.id.tv_product_name);
        tvMonthSails = findViewById(R.id.tv_month_sales);
        tvPriceNow = findViewById(R.id.tv_price_now);
        tvPriceOld = findViewById(R.id.tv_price_old);

        getData();
        setupViewpage();
    }
    public void useBanner(ArrayList<DataBean> dataBeans) {
        banner.setAdapter(new BannerImageAdapter<DataBean>(dataBeans) {
            @Override
            public void onBindView(BannerImageHolder holder, DataBean data, int position, int size) {
                //图片加载自己实现
                Glide.with(holder.itemView)
                        .load(dataBeans.get(position).imageUrl)
                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                        .into(holder.imageView);
            }
        })
                .addBannerLifecycleObserver(this)//添加生命周期观察者
                .setIndicator(new CircleIndicator(this));
        //更多使用方法仔细阅读文档，或者查看demo
    }

    private void getData(){
        MainRepository mainRepository = new MainRepository();
        Log.e("productId",productId+"");
        mainRepository.getProductDetails(productId).compose(SchedulerProvider.getInstance().applySchedulers()).subscribe(new Consumer<Response<ProductDetail>>() {
            @Override
            public void accept(Response<ProductDetail> productDetailResponse) throws Exception {
                ProductDetail detail = productDetailResponse.getData();
                if(null!=detail) {
                    Log.e("repsonseUrl", detail.getMaxImage());
                    tvProductName.setText(detail.getProductName());
                    tvMonthSails.setText("月销售: " + detail.getSaleNum());
                    tvPriceNow.setText("￥"+detail.getPrice());
                    tvPriceOld.setText("￥"+detail.getRecomPrice());
                    ArrayList<DataBean> arr = getDataBeanArr(detail.getMaxImage());
                    useBanner(arr);
                }


            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                throwable.getStackTrace();
            }
        });
    }
    private ArrayList<DataBean> getDataBeanArr(String s){
        ArrayList<DataBean> arr = new ArrayList<DataBean>();
        String[] arrS = s.split(",");
        arr.clear();
        if(arrS.length>0){
            for(int i=0;i<arrS.length;i++){

                String url = Config.prefixUrl + arrS[i];
                Log.e("url","url = " + url);
                DataBean bean = new DataBean(url,null,1);
                arr.add(bean);
            }
        }
        return arr;
    }
    private void setupViewpage(){
        mTabLayout = findViewById(R.id.tl_product_show_comment);
        mViewPager = findViewById(R.id.mViewPager);
        mViewPager.setAdapter(new FragmentStateAdapter(this) {
            int count = 2;
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                Fragment fragment;
                switch (position){
                    default:
                    case 0:
                        fragment = new ProductShowFragment();
                     break;
                    case 1:
                        fragment = new NotificationsFragment();
                        break;
                }

                return fragment;
            }

            @Override
            public int getItemCount() {
                return count;
            }
        });
        final String[] tabTexts = {"商品介绍", "商品评价"};
        TabLayoutMediator mediator = new TabLayoutMediator(mTabLayout, mViewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(tabTexts[position]);
            }
        }
        );
        mediator.attach();
    }
}