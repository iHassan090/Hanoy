package com.hassan.hanoy.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.hassan.hanoy.R;
import com.hassan.hanoy.preferences.PreferenceManager;

public class RateUsDialog extends Dialog implements View.OnClickListener {
    private final Context context;
    private ImageView mImageView;

    public RateUsDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_rate_us);
        mImageView = findViewById(R.id.gif_view);
        Glide.with(context).load(R.raw.thank_you).into(mImageView);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(this);

        ImageView close = findViewById(R.id.close);
        close.setOnClickListener(this);

        int width = (int) (context.getResources().getDisplayMetrics().widthPixels * 0.80);
        int height = (int) (context.getResources().getDisplayMetrics().heightPixels * 0.80);

        getWindow().setLayout(width, height);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button) {
            PreferenceManager.getInstance(context).saveShowRateUs(false);
            final String appPackageName = context.getPackageName();
            try {
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
            } catch (android.content.ActivityNotFoundException anfe) {
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
            }
        }
        dismiss();
    }
}
