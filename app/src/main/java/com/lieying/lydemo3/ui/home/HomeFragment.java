package com.lieying.lydemo3.ui.home;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.lieying.lydemo3.R;
import com.lieying.lydemo3.ui.focus.FocusFragment;
import com.lieying.lydemo3.ui.nearly.NearlyFragment;
import com.lieying.lydemo3.ui.notifications.NotificationsFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.lieying.lydemo3.ui.recommend.RecommendFragment;
import com.lieying.lydemo3.ui.samecity.SameCityFragment;

import java.lang.reflect.Field;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private ViewPager2 mViewpage;
    private TabLayout mTablayout;

    public  static HomeFragment newInstance(){
        return new HomeFragment();
    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        mViewpage = root.findViewById(R.id.view_pager);
        mTablayout = root.findViewById(R.id.tabLayout);
        mViewpage.setAdapter(new FragmentStateAdapter(this) {
            private int count = 3;

            @NonNull
            @Override
            public Fragment createFragment(int position) {
                Fragment fragment;
                switch (position) {
                    default:
                    case 0:
                        fragment = new FocusFragment();
                        break;
                    case 1:
                        fragment = new RecommendFragment();
                        break;
                    case 2:
                        fragment = new SameCityFragment();
                        break;
                }
                return fragment;
            }

            @Override
            public int getItemCount() {
                return count;
            }
        });
        final String[] tabTexts = {"关注", "推荐", "同城"};
        TabLayoutMediator mediator = new TabLayoutMediator(mTablayout, mViewpage, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(tabTexts[position]);
            }
        }
        );
        mediator.attach();
        return root;
    }
}