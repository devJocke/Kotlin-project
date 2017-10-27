package com.example.jocke.kotlin

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import kotlinx.android.synthetic.main.fragment_team_lineup.*

class TeamLineupFragment : Fragment() {


    companion object {
        fun newInstance(bundle: Bundle?): TeamLineupFragment = TeamLineupFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater?.inflate(R.layout.fragment_team_lineup, container, false)

        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = (activity as MainActivity)
        activity.attachSupportBar(toolbar, true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_team_lineup, menu)
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
