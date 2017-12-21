package com.example.jocke.kotlin

import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import android.widget.FrameLayout
import android.widget.ImageView
import com.example.jocke.kotlin.extensions.inflate
import kotlinx.android.synthetic.main.fragment_team_lineup.*


class TeamLineupFragment : Fragment(), View.OnTouchListener {

    private val TAG: String = javaClass.simpleName

    private lateinit var mMainActivity: MainActivity
    private lateinit var mContainer: ViewGroup
    private lateinit var mPlayers: ArrayList<ImageView>
    private var xDelta = 0
    private var yDelta = 0
    private var deviceWidth = 0
    private var deviceHeight = 0

    companion object {
        fun newInstance(): TeamLineupFragment = TeamLineupFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        hideSystemUI()
        setHasOptionsMenu(true)
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = container?.inflate(R.layout.fragment_team_lineup)

        mContainer = container!!

        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mMainActivity.attachSupportBar(toolbar, true)

        mPlayers = ArrayList()

        for (i in 0..10) {
            val imageView = ImageView(context)
            imageView.setImageResource(R.drawable.ic_00_black_person)
            mContainer.addView(imageView)
            val layoutParams = FrameLayout.LayoutParams(84, 84)
            mPlayers.add(imageView)
            layoutParams.marginStart = 70 * (i + 2)
            layoutParams.gravity = Gravity.CENTER_VERTICAL
            imageView.layoutParams = layoutParams
            imageView.setOnTouchListener(this)
        }
        getDeviceDimensions()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is GetParentActivity) {
            mMainActivity = context.getMainActivity() as MainActivity
        } else {
            throw NotImplementedError("Context does not implement GetParentActivity")
        }
    }

    override fun onPause() {
        super.onPause()
        for (i in mPlayers) {
            mContainer.removeView(i)
        }
    }

    // This snippet hides the system bars.
    private fun hideSystemUI() {
        // Set the IMMERSIVE flag.
        // Set the content to appear under the system bars so that the content
        // doesn't resize when the system bars hide and show.

        mMainActivity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_FULLSCREEN or
                View.SYSTEM_UI_FLAG_IMMERSIVE
    }


    private fun getDeviceDimensions() {
        val display = mMainActivity.windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        deviceWidth = size.x
        deviceHeight = size.y

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_team_lineup, menu)
    }

    override fun onTouch(view: View, event: MotionEvent): Boolean {
        // Absolute coordinates
        val X = event.rawX.toInt()
        val Y = event.rawY.toInt()

        when (event.action and MotionEvent.ACTION_MASK) {
            MotionEvent.ACTION_DOWN -> {
                //Relative coordinates
                xDelta = (X - view.x).toInt()
                yDelta = (Y - view.y).toInt()
            }
            MotionEvent.ACTION_UP -> {
                view.performClick()
            }
            MotionEvent.ACTION_MOVE -> {
                val relativePositionX = (X - xDelta).toFloat()
                val relativePositionY = (Y - yDelta).toFloat()

                if (isViewContains(football_field_image_view, relativePositionX, relativePositionY)) {
                    view.x = (X - xDelta).toFloat()
                    view.y = (Y - yDelta).toFloat()
                    view.invalidate()
                }
//                }
            }
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.location_item -> {
                //TODO Launch google maps
            }
            android.R.id.home -> {
                mMainActivity.onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun isViewContains(view: View, viewRelativeXPosition: Float, viewRelativeYPosition: Float): Boolean {
        val l = IntArray(2)
        view.getLocationOnScreen(l)
        val x = l[0]
        val y = l[1]
        val w = view.width
        val h = view.height

        return !(viewRelativeXPosition < x - 150 || viewRelativeXPosition > x + w || viewRelativeYPosition < y || viewRelativeYPosition > y + h)
    }

    override fun onResume() {
        super.onResume()
        uiChangeListener()
    }

    private fun uiChangeListener() {
        val decorView = mMainActivity.window.decorView

        decorView.setOnSystemUiVisibilityChangeListener { visibility ->
            if (visibility and View.SYSTEM_UI_FLAG_FULLSCREEN == 0) {
                decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
            }
        }
    }
}
