package com.hoangviet.demoui.withviewmodel;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.hoangviet.demoui.R;

public class CountNumberActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView scoreA;
    private TextView scoreB;
    private CountNumberViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_number);
        viewModel = ViewModelProviders.of(this).get(CountNumberViewModel.class);
        registerLiveDataListenner();
        initView();
    }

    private void registerLiveDataListenner() {
        viewModel.getmScoreTeamA().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                scoreA.setText(String.valueOf(integer));
            }
        });
        viewModel.getmScoreTeamB().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                scoreB.setText(String.valueOf(integer));
            }
        });
    }

    private void initView() {
        scoreA = findViewById(R.id.scoreA);
        scoreB = findViewById(R.id.scoreB);
        findViewById(R.id.btnAddScoreA).setOnClickListener(this);
        findViewById(R.id.btnAddScoreB).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAddScoreA:
                viewModel.setmScoreTeamA(3);
                break;
            case R.id.btnAddScoreB:
                viewModel.setmScoreTeamB(3);
                break;
        }
    }
}
