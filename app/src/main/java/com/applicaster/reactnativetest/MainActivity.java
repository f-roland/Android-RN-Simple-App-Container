package com.applicaster.reactnativetest;

import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_CODE = 1;
    static final int OVERLAY_PERMISSION_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            askForOverlayPermission();
        } else {
            launchReactView();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_CANCELED) {
            finish();
        }
        if (requestCode == OVERLAY_PERMISSION_CODE) {
            launchReactView();
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void askForOverlayPermission() {
        if (!Settings.canDrawOverlays(this)) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, REQUEST_CODE);
        } else {
            launchReactView();
        }
    }

    private void launchReactView() {
        Intent intent = new Intent(this, ReactActivity.class);
        intent.putExtra("dev_mode", true);
        startActivityForResult(intent, REQUEST_CODE);
    }
}
