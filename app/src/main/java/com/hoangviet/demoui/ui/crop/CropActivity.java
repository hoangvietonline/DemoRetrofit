package com.hoangviet.demoui.ui.crop;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.hoangviet.demoui.R;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;

public class CropActivity extends AppCompatActivity {
    private CropImageView mImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop);
        setToolbar();
        mImg = findViewById(R.id.img);
        initImage();
        mImg.setOnCropImageCompleteListener((view, result) -> {
            Intent intent = new Intent(CropActivity.this, ShowActivity.class);
            intent.setData(result.getUri());
            startActivity(intent);
        });
    }

    private void initImage() {
        Uri mUri = getIntent().getData();
        mImg.setImageUriAsync(mUri);
    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                mImg.saveCroppedImageAsync(Uri.fromFile(
                        new File(CropActivity.this.getFilesDir(), "crop_photo")), Bitmap.CompressFormat.JPEG, 100);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.runFinalization();
        Runtime.getRuntime().gc();
        System.gc();
    }
}
