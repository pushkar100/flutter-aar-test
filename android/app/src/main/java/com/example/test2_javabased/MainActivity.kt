package com.example.test2_javabased

import androidx.appcompat.app.AppCompatDelegate
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler
import io.flutter.embedding.android.FlutterFragmentActivity
import io.flutter.embedding.engine.FlutterEngine

class MainActivity: FlutterFragmentActivity(), DefaultHardwareBackBtnHandler {
    private val delegate = AppCompatDelegate.create(this, null)

    init {
        addOnContextAvailableListener {
            delegate.installViewFactory()
        }
    }

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        flutterEngine.platformViewsController
            .registry
            .registerViewFactory("<platform-view-type>", ReactFragmentsViewFactory())
    }

    override fun invokeDefaultOnBackPressed() {
        TODO("Not yet implemented")
    }

}