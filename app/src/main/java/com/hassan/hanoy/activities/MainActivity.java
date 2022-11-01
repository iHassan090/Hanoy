package com.hassan.hanoy.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hassan.hanoy.R;
import com.hassan.hanoy.adapters.ItemAdapter;
import com.hassan.hanoy.interfaces.IRequestListener;
import com.hassan.hanoy.models.Item;
import com.hassan.hanoy.network.VolleyManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import static com.hassan.hanoy.utils.Constants.API_URL;
import static com.hassan.hanoy.utils.Constants.DATA_CRITERIA1;
import static com.hassan.hanoy.utils.Constants.DATA_CRITERIA2;
import static com.hassan.hanoy.utils.Constants.DATA_CRITERIA3;

public class MainActivity extends BasicActivity implements IRequestListener {
    private ArrayList<Item> mItems;
    private RecyclerView mRecyclerView;
    private ProgressDialog mProgressDialog;
    private ConstraintLayout mCLTitles;
    private LinearLayout mLLDate;
    private TextView mTvDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initViews() {
        super.initViews();
        this.mRecyclerView = findViewById(R.id.recycler_view);
        this.mCLTitles = findViewById(R.id.titles);
        this.mLLDate = findViewById(R.id.date_layout);
        this.mTvDate = findViewById(R.id.date);
    }

    @Override
    public void initValues() {
        super.initValues();
        this.mItems = new ArrayList<>();
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Please wait");
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
        VolleyManager.getInstance(this).sendApiCall(API_URL, this);
    }

    @Override
    public void initValuesInViews() {
        super.initValuesInViews();
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
        this.mTvDate.setText(format.format(date));
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            mProgressDialog.dismiss();
            JSONArray data = response.getJSONArray("data");
            for (int i = 0; i < data.length(); i++) {
                JSONObject jsonObject = data.getJSONObject(i);
                if (jsonObject.getString("betType").equalsIgnoreCase(DATA_CRITERIA1)
                        || jsonObject.getString("betType").equalsIgnoreCase(DATA_CRITERIA2)
                        || jsonObject.getString("betType").equalsIgnoreCase(DATA_CRITERIA3)) {
                    Item item = new Item();
                    item.setName(jsonObject.getString("name"));
                    item.setResultBon3(jsonObject.getString("resultBon3"));
                    item.setResultLang2(jsonObject.getString("resultLang2"));
                    mItems.add(item);
                }
            }

            this.mCLTitles.setVisibility(View.VISIBLE);
            this.mLLDate.setVisibility(View.VISIBLE);
            ItemAdapter mAdapter = new ItemAdapter(mItems);
            mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(String error) {
        mProgressDialog.dismiss();
        Toast.makeText(this, "Something went wrong. Please try again later.", Toast.LENGTH_LONG).show();
        finish();
    }
}