package com.lieying.lydemo3.ui.nearly;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.lieying.lydemo3.R;
import com.lieying.lydemo3.ui.focus.FocusFragment;
import com.lieying.lydemo3.ui.home.HomeViewModel;
import com.lieying.lydemo3.ui.recommend.RecommendFragment;
import com.lieying.lydemo3.ui.samecity.SameCityFragment;

public class NearlyFragment extends Fragment {

    private NearlyViewModel mViewModel;
    private ViewPager2 mViewpage;
    private TabLayout mTablayout;

    public static NearlyFragment newInstance(){
        return new NearlyFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel =
                ViewModelProviders.of(this).get(NearlyViewModel.class);
        View root = inflater.inflate(R.layout.nearly_fragment, container, false);

        mViewpage = root.findViewById(R.id.view_pager);
        mTablayout = root.findViewById(R.id.tabLayout);
        mViewpage.setAdapter(new FragmentStateAdapter(this) {
            private int count = 2;

            @NonNull
            @Override
            public Fragment createFragment(int position) {
                Fragment fragment;
                switch (position) {
                    default:
                    case 0:
                        fragment = new IndexFragment();
                        break;
                    case 1:
                        fragment = new RecommendFragment();
                        break;
                }
                return fragment;
            }

            @Override
            public int getItemCount() {
                return count;
            }
        });
        final String[] tabTexts = {"同城","动态"};
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
