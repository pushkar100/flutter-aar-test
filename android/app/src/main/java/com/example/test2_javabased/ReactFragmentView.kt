package com.example.test2_javabased

import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import android.view.View
import androidx.core.view.doOnAttach
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentContainerView
import com.facebook.react.ReactFragment
import io.flutter.plugin.common.StandardMessageCodec
import io.flutter.plugin.platform.PlatformView
import io.flutter.plugin.platform.PlatformViewFactory
import java.util.Random
import kotlin.math.abs

class ReactFragmentView(
    private val context: Context,
    id: Int,
    creationParams: Map<String?, Any?>?,
) : PlatformView {
    private val view: FragmentContainerView

    init {
        val launchOptions = Bundle()
        val fragment =
            ReactFragment.Builder().setComponentName("AndroidSdk").setLaunchOptions(launchOptions)
                .setFabricEnabled(false).build()
        val viewId = abs(Random().nextInt())
        val view = FragmentContainerView(context)
        view.id = viewId
        view.doOnAttach {
            val activity = it.context.getFragmentActivityOrThrow()
            activity.supportFragmentManager.findFragmentByTag("flutter_fragment")
                ?.let { flutterFragment: Fragment ->
                    flutterFragment.childFragmentManager.beginTransaction()
                        .replace(it.id, fragment).commit();
                }
        }

        this.view = view
    }

    override fun getView(): View = view

    override fun dispose() {}

    private fun Context.getFragmentActivityOrThrow(): FragmentActivity {
        if (this is FragmentActivity) {
            return this
        }

        var currentContext = this
        while (currentContext is ContextWrapper) {
            if (currentContext is FragmentActivity) {
                return currentContext
            }
            currentContext = currentContext.baseContext
        }

        throw IllegalStateException("Unable to find activity")
    }
}

class ReactFragmentsViewFactory : PlatformViewFactory(StandardMessageCodec.INSTANCE) {
    override fun create(context: Context?, viewId: Int, args: Any?): PlatformView {
        val creationParams = args as Map<String?, Any?>?
        return ReactFragmentView(context!!, viewId, creationParams)
    }
}