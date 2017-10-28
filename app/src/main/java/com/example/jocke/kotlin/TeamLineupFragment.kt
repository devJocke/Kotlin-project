package com.example.jocke.kotlin

import android.graphics.Point
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import kotlinx.android.synthetic.main.fragment_team_lineup.*






class TeamLineupFragment : Fragment(), View.OnTouchListener {

    private val TAG: String = javaClass.simpleName

    var xDelta = 0
    var yDelta = 0
    var deviceWidth = 0
    var deviceHeight = 0



    companion object {
        fun newInstance(bundle: Bundle?): TeamLineupFragment = TeamLineupFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater?.inflate(R.layout.fragment_team_lineup, container, false)!!

        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = (activity as MainActivity)
        activity.attachSupportBar(toolbar, true)

        knapp.setOnTouchListener(this)

        getDeviceDimensions()
    }

    private fun getDeviceDimensions() {
        val display = activity.windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        deviceWidth = size.x
        deviceHeight = size.y

    }

    private fun isViewContains(view: View, viewRelativeXPosition: Float, viewRelativeYPosition: Float): Boolean {
        val l = IntArray(2)
        view.getLocationOnScreen(l)
        val x = l[0]
        val y = l[1]
        val w = view.width
        val h = view.height

        return !(viewRelativeXPosition < x -150 || viewRelativeXPosition > x + w  || viewRelativeYPosition < y || viewRelativeYPosition > y + h )
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
//                if (relativePositionX > -150 && relativePositionX < deviceWidth
//                        && relativePositionY < deviceHeight - (navigationHeight - 150) && relativePositionY > -(navigationHeight - 150)) {

                    if(isViewContains(football_field_image_view, relativePositionX, relativePositionY)) {
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
                (activity as MainActivity).onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
