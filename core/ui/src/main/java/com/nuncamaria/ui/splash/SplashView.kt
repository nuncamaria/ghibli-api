package com.nuncamaria.ui.splash

import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen

const val ANIMATION_DURATION = 500L
const val SCALE_FROM = 1f
const val SCALE_TO = 0.4f

fun SplashScreen.splashViewConfig() {

    setOnExitAnimationListener { screen ->
        val zoomX = ObjectAnimator.ofFloat(
            screen.iconView,
            View.SCALE_X,
            SCALE_FROM,
            SCALE_TO,
        )

        zoomX.interpolator = OvershootInterpolator()
        zoomX.duration = ANIMATION_DURATION
        zoomX.doOnEnd { screen.remove() }

        val zoomY = ObjectAnimator.ofFloat(
            screen.iconView,
            View.SCALE_Y,
            SCALE_FROM,
            SCALE_TO,
        )

        zoomY.interpolator = OvershootInterpolator()
        zoomY.duration = ANIMATION_DURATION
        zoomY.doOnEnd { screen.remove() }

        zoomX.start()
        zoomY.start()
    }
}