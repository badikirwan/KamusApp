package com.badikirwan.dicoding.kamusapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.badikirwan.dicoding.kamusapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    public static final String ITEM_KATA = "ITEM_KATA";
    public static final String ITEM_ARTI = "ITEM_ARTI";

    @BindView(R.id.tv_kata)
    TextView mKata;

    @BindView(R.id.tv_arti)
    TextView mArti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        mKata.setText(getIntent().getStringExtra(ITEM_KATA));
        mArti.setText(getIntent().getStringExtra(ITEM_ARTI));
    }
}
