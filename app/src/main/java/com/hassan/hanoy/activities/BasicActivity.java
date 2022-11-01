package com.hassan.hanoy.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BasicActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        initViews();
        initValues();
        initValuesInViews();
        setOnViewClickListener();
    }

    public void initViews() {

    }

    public void initValues() {

    }

    public void initValuesInViews() {

    }

    public void setOnViewClickListener() {

    }
}
