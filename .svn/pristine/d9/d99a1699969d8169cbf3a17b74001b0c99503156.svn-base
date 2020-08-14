package com.lieying.lydemo3.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

@SuppressLint("AppCompatCustomView")
public class FontIconView extends TextView {
    public FontIconView(Context context) {
        super(context);
        init(context);
    }

    public FontIconView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FontIconView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        Typeface font = Typeface.createFromAsset(context.getAssets(),"iconfont.ttf");
        this.setTypeface(font);
    }
}
