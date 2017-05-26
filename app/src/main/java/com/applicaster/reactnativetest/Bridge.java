package com.applicaster.reactnativetest;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

/**
 * Created by francois on 26/05/2017.
 */

public class Bridge extends ReactContextBaseJavaModule {
    @Override
    public String getName() {
        return "ZPReactNativeBridgeListener";
    }

    public Bridge(ReactApplicationContext reactContext) { super(reactContext);}
}
