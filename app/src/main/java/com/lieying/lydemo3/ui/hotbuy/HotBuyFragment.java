package com.lieying.lydemo3.ui.hotbuy;

import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.tabs.TabLayout;
import com.lieying.lydemo3.R;
import com.lieying.lydemo3.adapter.GvHotbuyAdapter;
import com.lieying.lydemo3.adapter.HotbuyRvAdapter;
import com.lieying.lydemo3.bean.Data;
import com.lieying.lydemo3.bean.NewSpellActiveData;
import com.lieying.lydemo3.bean.NewSpellActiveProduct;
import com.lieying.lydemo3.bean.Product;
import com.lieying.lydemo3.net.Response;
import com.lieying.lydemo3.net.SchedulerProvider;

import java.util.ArrayList;

import io.reactivex.functions.Consumer;
import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

@Route(path="/hotbuy/HotBuyFragment")
public class HotBuyFragment extends Fragment {

    private HotBuyViewModel mHotBuyViewModel;
    TabLayout mTablayout;
    ArrayList<Integer> data = new ArrayList<Integer>();
    MaterialProgressBar pb;
    boolean isSpellActiveComplete = false;
    boolean isSpellComplete = false;

    View view;
    GridView mGridView;
    RecyclerView mRecycleView;
    ImageView headerPic;
    ViewPager2 vp_hotbuy;
    public static HotBuyFragment newInstance() {
        return new HotBuyFragment();
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mHotBuyViewModel = ViewModelProviders.of(this).get(HotBuyViewModel.class);
        view = inflater.inflate(R.layout.hot_buy_fragment, container, false);
        pb = view.findViewById(R.id.pb_hotbuy);
        setupSeachView();

        addTabData();

        headerPic = view.findViewById(R.id.imageView);

    //    mock();
        Glide.with(getActivity()).load(R.mipmap.zhubo).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(headerPic);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }

    private void addTabData() {
        mTablayout =view.findViewById(R.id.tablayout);
        mHotBuyViewModel.getSpellLiveData().compose(SchedulerProvider.getInstance().applySchedulers()).subscribe(new Consumer<Response<Data>>() {
            @Override
            public void accept(Response<Data> dataResponse) throws Exception {
                pb.setVisibility(View.VISIBLE);
                Log.e("data111",dataResponse.getData().toString());
                if(dataResponse.getSuccess()){
                    for(int i=0;i<dataResponse.getData().getSpellGroups().size();i++){
                        TabLayout.Tab tab = mTablayout.newTab();
                        tab.setText(dataResponse.getData().getSpellGroups().get(i).getGroupName());
                        mTablayout.addTab(tab);
                      setupRecycleview(dataResponse.getData().getProducts().getList());
                    }
                }
                isSpellActiveComplete = true;
                if(isSpellComplete&&isSpellActiveComplete) {
                    pb.setVisibility(View.GONE);
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                throwable.getStackTrace();
            }
        });

        mHotBuyViewModel.getSpellActiveData().compose(SchedulerProvider.getInstance().applySchedulers()).subscribe(new Consumer<Response<NewSpellActiveData>>() {
            @Override
            public void accept(Response<NewSpellActiveData> newSpellActiveDataResponse) throws Exception {
                pb.setVisibility(View.VISIBLE);
                Log.e("response1",newSpellActiveDataResponse.toString());
                if(newSpellActiveDataResponse.getSuccess()){
                    Log.e("response",newSpellActiveDataResponse.getData().toString());
                    setupGridView(newSpellActiveDataResponse.getData().getResultList());
                }
                isSpellComplete = true;
                if(isSpellComplete&&isSpellActiveComplete) {
                    pb.setVisibility(View.GONE);
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                throwable.getStackTrace();
            }
        });

//        String[] tabContentArray = {"热门", "推选", "女妆", "美妆","母婴","护肤"};
//        for (int i = 0; i < tabContentArray.length; i++) {
//            TabLayout.Tab tab = mTablayout.newTab();
//            tab.setText(tabContentArray[i]);
//            mTablayout.addTab(tab);
//        }

    }

    private void setupGridView(ArrayList<NewSpellActiveProduct>  data){
        mGridView = view.findViewById(R.id.gv_hotbuy);
        GvHotbuyAdapter adapter = new GvHotbuyAdapter(data,getActivity());
        mGridView.setAdapter(adapter);
    }
    private void setupRecycleview(ArrayList<Product> data){
        mRecycleView = view.findViewById(R.id.rv_hotbuy);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
       layoutManager.setSmoothScrollbarEnabled(true);
        mRecycleView.setLayoutManager(layoutManager);
        HotbuyRvAdapter adapter = new HotbuyRvAdapter(data,getActivity());
        mRecycleView.setAdapter(adapter);
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setNestedScrollingEnabled(false);
    }

    public void mock(){
        for(int i =0 ;i < 3;i++){
            data.add(i);
        }
    }

    private void setupSeachView(){
        SearchView searchView = (SearchView)view.findViewById(R.id.sv_search);
//设置输入字体颜色
        if(searchView == null) { return;}
        SearchView.SearchAutoComplete textView = ( SearchView.SearchAutoComplete) searchView.findViewById(R.id.search_src_text);
        textView.setTextSize(12);//字体、提示字体大小
    }
}
