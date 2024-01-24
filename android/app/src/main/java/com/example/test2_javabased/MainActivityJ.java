package com.example.test2_javabased;

import androidx.annotation.NonNull;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;

public class MainActivityJ extends FlutterActivity {
    @Override
    public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
        flutterEngine
                .getPlatformViewsController()
                .getRegistry()
                //.registerViewFactory("reactFragments", new NativeViewFactory())
                .registerViewFactory("<platform-view-type>", new ReactFragmentsViewFactory());
    }
}

