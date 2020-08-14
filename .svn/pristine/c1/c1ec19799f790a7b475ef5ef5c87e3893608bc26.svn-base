package com.lieying.lydemo3.ui.samecity;

import androidx.lifecycle.ViewModelProviders;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.lieying.lydemo3.OnViewPagerListener;
import com.lieying.lydemo3.R;
import com.lieying.lydemo3.adapter.ChatDialogAdapter;
import com.lieying.lydemo3.adapter.GvHomeAdapter;
import com.lieying.lydemo3.customview.FullWindowVideoView;
import com.lieying.lydemo3.manager.MyLayoutManager;
import com.lieying.lydemo3.ui.focus.FocusFragment;
import com.lieying.lydemo3.ui.focus.FocusViewModel;
import com.lieying.lydemo3.ui.recommend.RecommendFragment;
import com.lieying.lydemo3.ui.recommend.RecommendViewModel;

import java.util.ArrayList;

public class SameCityFragment extends Fragment {

    private SameCityViewModel mViewModel;
    BottomSheetDialog mChatDialog;
    BottomSheetBehavior mDialogBehavior;
    ArrayList<Integer> data = new ArrayList<Integer>();
    RecyclerView mRecyclerView,mChatRecyclerView;
    SameCityFragment.MyAdapter mAdapter;
    ChatDialogAdapter mChatAdapter;
    View view;
    TextView tv_close_dialog;
    MyLayoutManager myLayoutManager;

    public static SameCityFragment newInstance() {
        return new SameCityFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.same_city_fragment,container,false);
        mRecyclerView = view.findViewById(R.id.rv_samecity);
        initView();
        initListener();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SameCityViewModel.class);
        // TODO: Use the ViewModel
    }

    private void initView() {
        if(myLayoutManager==null) {
            myLayoutManager = new MyLayoutManager(getActivity(), OrientationHelper.VERTICAL, false);
        }
        mRecyclerView.setLayoutManager(myLayoutManager);

        mAdapter = new SameCityFragment.MyAdapter(getActivity());

        mRecyclerView.setAdapter(mAdapter);
    }
    private void initListener() {
        myLayoutManager.setOnViewPagerListener(new OnViewPagerListener() {
            @Override
            public void onInitComplete() {

            }

            @Override
            public void onPageRelease(boolean isNext, int position) {
                //           Log.e(TAG, "释放位置:" + position + " 下一页:" + isNext);
                int index = 0;
                if (isNext) {
                    index = 0;
                } else {
                    index = 1;
                }
                releaseVideo(index);
            }

            @Override
            public void onPageSelected(int position, boolean bottom) {
                //               Log.e(TAG, "选择位置:" + position + " 下一页:" + bottom);

                playVideo(0);
            }
        });
    }
    private void releaseVideo(int index) {
        View itemView = mRecyclerView.getChildAt(index);
        if(itemView!=null) {
            final ImageView imgThumb = itemView.findViewById(R.id.img_thumb);
            final VideoView videoView = itemView.findViewById(R.id.video_view);
            imgThumb.animate().alpha(1).start();
            videoView.stopPlayback();
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void  playVideo(int position) {
        View itemView = mRecyclerView.getChildAt(position);
        if(itemView!=null) {
            final ImageView imgPlay = itemView.findViewById(R.id.img_play);
            final ImageView imgThumb = itemView.findViewById(R.id.img_thumb);
            final FullWindowVideoView videoView = itemView.findViewById(R.id.video_view);
            final MediaPlayer[] mediaPlayer = new MediaPlayer[1];
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {

                }
            });
            videoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                @Override
                public boolean onInfo(MediaPlayer mp, int what, int extra) {
                    mediaPlayer[0] = mp;
                    mp.setLooping(true);
                    imgThumb.animate().alpha(0).setDuration(200).start();
                    return false;
                }
            });

            videoView.start();
            imgPlay.setOnClickListener(new View.OnClickListener() {
                boolean isPlaying = true;

                @Override
                public void onClick(View v) {
                    if (videoView.isPlaying()) {
                        imgPlay.animate().alpha(0.7f).start();
                        videoView.pause();
                        isPlaying = false;
                    } else {
                        imgPlay.animate().alpha(0f).start();
                        videoView.start();
                        isPlaying = true;
                    }
                }
            });
        }
    }

    class MyAdapter extends RecyclerView.Adapter<SameCityFragment.MyAdapter.ViewHolder> {
        private int[] imgs = {R.mipmap.img_video_1, R.mipmap.img_video_2, R.mipmap.img_video_3, R.mipmap.img_video_4, R.mipmap.img_video_5, R.mipmap.img_video_6, R.mipmap.img_video_7, R.mipmap.img_video_8};
        private int[] videos = {R.raw.video_1, R.raw.video_2, R.raw.video_3, R.raw.video_4, R.raw.video_5, R.raw.video_6, R.raw.video_7, R.raw.video_8};
        private int index = 0;
        private Context mContext;
        View view;

        public MyAdapter(Context context) {
            this.mContext = context;
        }


        @Override
        public SameCityFragment.MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_samecity_rv_item, parent, false);


            return new SameCityFragment.MyAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(SameCityFragment.MyAdapter.ViewHolder holder, int position) {
            holder.img_thumb.setImageResource(imgs[index]);
            holder.videoView.setVideoURI(Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + videos[index]));
            index++;
//            holder.videoView.setVideoURI(Uri.parse("https://lieyingshop.oss-cn-beijing.aliyuncs.com/user-dir-prefix/video_"+index+".mp4"));

            if (index >= 7) {
                index = 0;
            }

            holder.iv_chat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showSheetDialog1();
                    mChatDialog.show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return 88;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView img_thumb;
            VideoView videoView;
            ImageView img_play;
            ImageView iv_chat;
//            RelativeLayout rootView;

            public ViewHolder(View itemView) {
                super(itemView);
                img_thumb = itemView.findViewById(R.id.img_thumb);
                videoView = itemView.findViewById(R.id.video_view);
                img_play = itemView.findViewById(R.id.img_play);
                iv_chat = itemView.findViewById(R.id.iv_chat);

            }
        }
    }
    private void showSheetDialog1() {
        View view = View.inflate(getActivity(), R.layout.layout_chat_dialog, null);
        tv_close_dialog = view.findViewById(R.id.tv_close);
        mChatRecyclerView = view.findViewById(R.id.rv_chat);
        mChatRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mChatAdapter = new ChatDialogAdapter(data,getActivity());
        mChatRecyclerView.setAdapter(mChatAdapter);

        mChatDialog = new BottomSheetDialog(getActivity(), R.style.AppBottomSheetDialogTheme);
        mChatDialog.setContentView(view);
        mDialogBehavior = BottomSheetBehavior.from((View) view.getParent());
        mDialogBehavior.setPeekHeight(getPeekHeight());
        tv_close_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mChatDialog.dismiss();
            }
        });
    }
    /**
     * 弹窗高度，默认为屏幕高度的四分之三
     * 子类可重写该方法返回peekHeight
     *
     * @return height
     */
    protected int getPeekHeight() {
        int peekHeight = getActivity().getResources().getDisplayMetrics().heightPixels;
        //设置弹窗高度为屏幕高度的3/4
        return peekHeight - peekHeight / 3;
    }

    @Override
    public void onResume() {
        super.onResume();
        initView();
        initListener();
    }
}
