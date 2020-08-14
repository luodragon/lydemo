package com.lieying.lydemo3;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

public class PersonalCenterActivity extends AppCompatActivity {
    TextView tv_userlevel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        getSupportActionBar().hide();
        initView();
        initListener();

    }
    private void initView(){
        tv_userlevel = (TextView)findViewById(R.id.tv_user_level);
    }
    private  void initListener(){
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.tv_user_level:
                        Intent intent = new Intent(PersonalCenterActivity.this,UserLevelActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        break;
                }
            }
        };
        tv_userlevel.setOnClickListener(listener);
    }

}