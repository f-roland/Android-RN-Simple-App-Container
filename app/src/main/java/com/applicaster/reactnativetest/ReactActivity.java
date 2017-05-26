package com.applicaster.reactnativetest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactInstanceManagerBuilder;
import com.facebook.react.ReactRootView;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.shell.MainReactPackage;

/**
 * Created by francois on 26/05/2017.
 */

public class ReactActivity extends Activity implements DefaultHardwareBackBtnHandler {
    private ReactInstanceManager reactInstanceManager;

    @Override
    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (reactInstanceManager != null) {
            reactInstanceManager.onHostPause(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (reactInstanceManager != null) {
            reactInstanceManager.onHostResume(this, this);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ReactRootView reactRootView = new ReactRootView(this);

        ReactInstanceManagerBuilder reactManagerBuilder = ReactInstanceManager.builder()
                .setApplication(getApplication())
                .addPackage(new MainReactPackage())
                .addPackage(new BridgePackage())
                .setInitialLifecycleState(LifecycleState.RESUMED);

        reactManagerBuilder
                .setUseDeveloperSupport(true)
                .setBundleAssetName("index.android.bundle");

        reactInstanceManager = reactManagerBuilder.build();
        reactRootView.startReactApplication(reactInstanceManager, "RNRoot", getInitialRNProps());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        reactInstanceManager.onActivityResult(this, requestCode, resultCode, data);
    }

    private Bundle getInitialRNProps() {
        Bundle bundle = new Bundle();
        bundle.putString("foo", "bar");

        return bundle;
    }
}
