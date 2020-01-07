package com.hoangviet.demoui.ui.demoretrofit;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import com.hoangviet.demoui.BuildConfig;
import com.hoangviet.demoui.R;
import com.hoangviet.demoui.model.Items;
import com.hoangviet.demoui.model.MyPojo;
import com.hoangviet.demoui.model.Question;
import com.hoangviet.demoui.remote.RetrofitClient;
import com.hoangviet.demoui.remote.ServiceGenerator;
import com.hoangviet.demoui.ui.crop.CropActivity;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DemoRetrofitActivity extends AppCompatActivity {

    private static final String TAG = "DemoRetrofitActivity";
    private static final int REQUEST_CODE_CAMERA = 12345;
    private static final int REQUEST_TAKE_PHOTO_CAMERA = 995;
    private RetrofitClient stackOverFlowAPI;
    private Button btnCamera;
    private String currentPhotoPath;
    private File photoFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_retrofit);
        final List<Question> questionList = new ArrayList<>();
        stackOverFlowAPI = ServiceGenerator.getStackOverFlowAPI(RetrofitClient.class);

        Call<MyPojo> call = stackOverFlowAPI.getQuestion("desc", "activity", "Android", "stackoverflow");

        call.enqueue(new Callback<MyPojo>() {
            @Override
            public void onResponse(Call<MyPojo> call, Response<MyPojo> response) {
                if (response.isSuccessful()) {
                    for (int i = 0; i < Objects.requireNonNull(response.body()).getItems().length; i++) {
                        Items item = response.body().getItems()[i];
                        final String title = item.getTitle();
                        final String link = item.getLink();
                        questionList.add(new Question(title, link));
                        Log.d(TAG, "onResponse: " + title + "___________" + link);
                    }
                }
            }

            @Override
            public void onFailure(Call<MyPojo> call, Throwable t) {

            }
        });
        btnCamera = findViewById(R.id.btnCamera);
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(DemoRetrofitActivity.this, new String[]{Manifest.permission.CAMERA}, REQUEST_CODE_CAMERA);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_CAMERA) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                dispatchTakePictureIntent();
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            try {
                photoFile = createImageFile();
                Uri photoURI = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO_CAMERA);
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_TAKE_PHOTO_CAMERA) {
            if (resultCode == RESULT_OK) {
                Uri uri = Uri.fromFile(photoFile);
                cropPhoto(uri);
            }
        }
    }

    private void cropPhoto(Uri uri) {
        Intent intent = new Intent(DemoRetrofitActivity.this, CropActivity.class);
        intent.setData(uri);
        startActivity(intent);
    }
}
