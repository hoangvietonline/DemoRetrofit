package com.hoangviet.demoui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import com.hoangviet.demoui.databinding.ActivityMainBinding;
import com.hoangviet.demoui.model.User;
import com.hoangviet.demoui.withviewmodel.CountNumberActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        User user = new User();
        user.setFirstName("Nguyễn Đình");
        user.setLastName("Hoàng Việt");
        binding.setUser(user);
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void onClickChange(View view) {
        Intent intent = new Intent(MainActivity.this, CountNumberActivity.class);
        startActivity(intent);
    }

    public void onClickMaster(View view) {
        Intent intent = new Intent(MainActivity.this, ShareDataActivity.class);
        startActivity(intent);
    }
}
