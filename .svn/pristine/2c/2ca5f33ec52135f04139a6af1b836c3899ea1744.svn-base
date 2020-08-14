package com.lieying.lydemo3;

import android.os.Bundle;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import com.lieying.lydemo3.adapter.GvZbSelectAdapter;

import java.util.ArrayList;


public class RegisterSuccessActivity extends AppCompatActivity {
    GridView mGridView;
    GvZbSelectAdapter mGvZbSelectAdapter;
    ArrayList<Integer> data = new ArrayList<Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_success);
        getSupportActionBar().hide();
        mGridView = findViewById(R.id.gv_zhubo_select);
        mock();
        mGvZbSelectAdapter = new GvZbSelectAdapter(data,this);
        mGridView.setAdapter(mGvZbSelectAdapter);
    }

    public void mock(){
        for(int i =0 ;i < 9;i++){
            data.add(i);
        }
    }
}
