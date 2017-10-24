package com.example.jocke.kotlin

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.*
import com.example.jocke.kotlin.R.drawable.crest_lindsdal
import com.example.jocke.kotlin.R.menu.menu_overview
import com.kwabenaberko.openweathermaplib.implementation.OpenWeatherMapHelper
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.drawer_layout_overview.*
import kotlinx.android.synthetic.main.fragment_overview.*
import kotlinx.android.synthetic.main.next_game_overview.*
import kotlinx.android.synthetic.main.toolbar_overview.*


/**
 * Created by Jocke on 2017-07-02.
 */

class OverViewFragment : Fragment(), View.OnClickListener {

    private val TAG: String = javaClass.simpleName


    companion object {
        fun newInstance(bundle: Bundle?): OverViewFragment = OverViewFragment()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater?.inflate(R.layout.fragment_overview, container, false)

        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        line_up_image_button.setOnClickListener(this)

        Picasso.with(context).load(crest_lindsdal).fit().into(club_crest_image_view)
        (activity as MainActivity).setupDrawer(toolbar, drawer_layout, main_navigation_container)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(menu_overview, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        Log.d(TAG, item?.itemId.toString())
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(p0: View?) {

        (activity as MainActivity).startTeamLineupFragment(null)
    }

    private fun getWeather(): String {
        val helper = OpenWeatherMapHelper()
        return "";
    }

//    private fun getAllPersons() {
//
//        val builder = Retrofit.Builder()
//                //Get from localhost on emulator
//                .baseUrl("http://10.0.2.2:53836")
//                .addConverterFactory(GsonConverterFactory.create())
//
//        val retrofit = builder.build()
//
//        val client = retrofit.create(TeamService::class.java)
//        val call = client.allPersons
//
//        call.enqueue(object : Callback<List<TeamDTO>> {
//            override fun onResponse(call: Call<List<TeamDTO>>, response: Response<List<TeamDTO>>) {
//                val allPersons = response.body()!!
//
//                if (allPersons.isEmpty()) {
//                    Toast.makeText(context, "No Persons available", Toast.LENGTH_SHORT).show()
//                }
//                recyclerView.layoutManager = LinearLayoutManager(context)
//                recyclerView.adapter = TeamAdapter(allPersons)
//            }
//
//            override fun onFailure(call: Call<List<TeamDTO>>, t: Throwable) {
//                Toast.makeText(context, "Server error", Toast.LENGTH_SHORT).show()
//            }
//        })
//    }
}

