package com.example.jocke.kotlin

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.*
import com.example.jocke.kotlin.R.drawable.crest_lindsdal
import com.example.jocke.kotlin.R.menu.menu_overview
import com.kwabenaberko.openweathermaplib.Units
import com.kwabenaberko.openweathermaplib.implementation.OpenWeatherMapHelper
import com.kwabenaberko.openweathermaplib.models.currentweather.CurrentWeather
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

        getWeather()
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
        val weatherMapHelper = OpenWeatherMapHelper()
//        BuildConfig.WEATHER_API_KEY
        weatherMapHelper.setApiKey(getString(R.string.WEATHER_API_KEY))
        weatherMapHelper.setUnits(Units.METRIC)

        weatherMapHelper.getCurrentWeatherByCityName("Kalmar", object : OpenWeatherMapHelper.CurrentWeatherCallback {
            override fun onSuccess(currentWeather: CurrentWeather) {
                weather_description_text_view.text = currentWeather.weatherArray[0].description.toString().capitalize()
                weather_wind_text_view.append(" " + currentWeather.wind.speed.toString().capitalize())
                weather_temperature_text_view.append(" " + currentWeather.main.tempMax)
            }

            override fun onFailure(throwable: Throwable) {
                Log.v(TAG, throwable.message)
            }
        })
        return ""
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
//        call.enqueue(object : Callback<List<Team>> {
//            override fun onResponse(call: Call<List<Team>>, response: Response<List<Team>>) {
//                val allPersons = response.body()!!
//
//                if (allPersons.isEmpty()) {
//                    Toast.makeText(context, "No Persons available", Toast.LENGTH_SHORT).show()
//                }
//                recycler_view.layoutManager = LinearLayoutManager(context)
//                recycler_view.adapter = TeamAdapter(allPersons)
//            }
//
//            override fun onFailure(call: Call<List<Team>>, t: Throwable) {
//                Toast.makeText(context, "Server error", Toast.LENGTH_SHORT).show()
//            }
//        })
//    }
}

