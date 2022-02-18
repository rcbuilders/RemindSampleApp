package com.remind.sampleapp.swipe_finish_example

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.remind.sampleapp.BaseActivity
import com.remind.sampleapp.R
import com.remind.sampleapp.databinding.ActivitySwipeFinishExampleBinding
import kotlin.math.abs

class SwipeFinishExampleActivity :
    BaseActivity<ActivitySwipeFinishExampleBinding>(R.layout.activity_swipe_finish_example) {

    override fun initView() {
        super.initView()
        setOnSwipeListener()
    }

}

fun Activity.setOnSwipeListener(vararg views: View) {
    val onSwipeTouchListener =
        OnSwipeTouchListener(this, object : OnSwipeCallback {
            override fun onLeft() {}
            override fun onRight() {
                finish()
                overridePendingTransition(R.anim.anim_enter_no_change, R.anim.anim_exit_slide_right)
            }
        })
    views.forEach { view ->
        if (view is RecyclerView) {
            view.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
                override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean =
                    onSwipeTouchListener.getGestureDetector().onTouchEvent(e)

                override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}
                override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}

            })
        } else {
            view.setOnTouchListener(onSwipeTouchListener)
        }
    }
    window.decorView.rootView.setOnTouchListener(onSwipeTouchListener)
}

interface OnSwipeCallback {
    fun onLeft()
    fun onRight()
}

class OnSwipeTouchListener(context: Context, callback: OnSwipeCallback) : View.OnTouchListener {

    private val gestureDetector = GestureDetector(context, OnSwipeGestureListener(callback))

    fun getGestureDetector() = gestureDetector

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        return gestureDetector.onTouchEvent(event)
    }

}

class OnSwipeGestureListener(val callback: OnSwipeCallback) :
    GestureDetector.SimpleOnGestureListener() {

    companion object {
        private const val SWIPE_THRESHOLD = 350
        private const val SWIPE_VELOCITY_THRESHOLD = 350
    }

    override fun onDown(e: MotionEvent?): Boolean = false

    override fun onFling(
        e1: MotionEvent?,
        e2: MotionEvent?,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        try {
            if (e2 == null || e1 == null) return false
            val diffX = e2.x - e1.x
            if (abs(diffX) > SWIPE_THRESHOLD && abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                if (diffX > 0) {
                    callback.onRight()
                } else {
                    callback.onLeft()
                }
                return true
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }
}