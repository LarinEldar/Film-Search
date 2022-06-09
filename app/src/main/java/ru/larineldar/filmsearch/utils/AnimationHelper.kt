package ru.larineldar.filmsearch.utils

import android.app.Activity
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.AccelerateDecelerateInterpolator
import java.util.concurrent.Executors
import kotlin.math.hypot
import kotlin.math.roundToInt

object AnimationHelper {
    private const val menuItems = 4;

    fun performFragmentCircularRevealAnimation(rootView: View, activity: Activity, position: Int){
        Executors.newSingleThreadExecutor().execute {
            while(true){
                if(rootView.isAttachedToWindow){
                    activity.runOnUiThread {
                        val step = rootView.width  * (2 * position - 1) / (menuItems * 2)

                        val x: Int = step
                        val y: Int = rootView.y.roundToInt() + rootView.height

                        val radius = hypot(rootView.width.toFloat(), rootView.height.toFloat())

                        ViewAnimationUtils.createCircularReveal(rootView, x, y, 0f, radius)
                            .apply {
                                duration = 500
                                interpolator = AccelerateDecelerateInterpolator()
                                start()
                            }

                        rootView.visibility = View.VISIBLE
                    }

                    return@execute
                }
            }
        }
    }
}