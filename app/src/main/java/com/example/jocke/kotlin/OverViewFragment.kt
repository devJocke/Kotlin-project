package com.example.jocke.kotlin

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.*
import com.example.jocke.kotlin.R.drawable.crest_lindsdal
import com.example.jocke.kotlin.adapters.TeamAdapter
import com.example.jocke.kotlin.data.dal.TeamMember
import com.example.jocke.kotlin.extensions.inflate
import com.kwabenaberko.openweathermaplib.Units
import com.kwabenaberko.openweathermaplib.implementation.OpenWeatherMapHelper
import com.kwabenaberko.openweathermaplib.models.currentweather.CurrentWeather
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.drawer_layout_overview.*
import kotlinx.android.synthetic.main.fragment_overview.*
import kotlinx.android.synthetic.main.next_game_overview.*
import kotlinx.android.synthetic.main.toolbar_overview.*
import java.util.*

/**
 * Created by Jocke on 2017-07-02.
 */

class OverViewFragment : Fragment(), View.OnClickListener,ItemListDialogFragment.Listener {

    override fun onItemClicked(position: Int) {

    }

    var mActionMode: ActionMode? = null

    private val TAG: String = javaClass.simpleName
    val allPersons = ArrayList<TeamMember>()


    companion object {
        fun newInstance(): OverViewFragment = OverViewFragment()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        view?.isSelected = true
//        testSettingsFragment()
        ItemListDialogFragment.newInstance(30).show(activity.supportFragmentManager, "dialog")
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? = container?.inflate(R.layout.fragment_overview)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getWeather()
        game_line_up_image_button.setOnClickListener({ addPerson() })
        next_game_location_image_button.setOnClickListener { getAllPersons() }

        Picasso.with(context).load(crest_lindsdal).fit().into(club_crest_image_view)
        (activity as MainActivity).setupDrawer(toolbar, drawer_layout, main_navigation_container)
        showNotification()
//        testPlurals()
//        showCamera( Uri.parse("http://www.google.com"))
    }

    private fun addPerson() {
        allPersons.add(TeamMember.TeamBuilder("Jocke", "Pocke", 22).setThirdName("a").build())
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_overview, menu)
    }

    private val mActionModeCallback = object : ActionMode.Callback {

        // Called when the action mode is created; startActionMode() was called
        override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
            // Inflate a menu resource providing context menu items
            mode.menuInflater.inflate(R.menu.menu_navigation_drawer_overview, menu)
            return true
        }

        // Called each time the action mode is shown. Always called after onCreateActionMode, but
        // may be called multiple times if the mode is invalidated.
        override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean {
            return false // Return false if nothing is done
        }

        // Called when the user selects a contextual menu item
        override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {
            return when (item.itemId) {
                R.id.open_drawer -> {
                    mode.finish()
                    true
                }
                else -> false
            }
        }

        // Called when the user exits the action mode
        override fun onDestroyActionMode(mode: ActionMode) {
            mActionMode = null
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.history_item -> {
                // Start the CAB using the ActionMode.Callback defined above
                mActionMode = activity.startActionMode(mActionModeCallback)
                true
            }
            else -> false
        }
    }

    override fun onClick(view: View?) {
        (activity as MainActivity).startTeamLineupFragment()
    }

    private fun openMaps() {}

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

    private fun getAllPersons() {
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

//        allPersons.add(Team(1L, "För", "Efter", "Tredje")) 
        allPersons.add(TeamMember.TeamBuilder("Jocke", "Pocke", 22).build())
        allPersons.add(TeamMember.TeamBuilder("Steffe", "Stagg", 26).setThirdName("steffelito").build())

        val teamAdapter = TeamAdapter(allPersons)

//        Log.d("tag", allPersons.get(0).thirdName)

        val teamStatus = TeamStatus(allPersons)

//        Log.d("tag", teamStatus.averageAge.toString())

        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.adapter = teamAdapter
//            }
//
//            override fun onFailure(call: Call<List<Team>>, t: Throwable) {
//                Toast.makeText(context, "Server error", Toast.LENGTH_SHORT).show()
//            }
//        })
    }

    private fun testIntent() {
//        val intent = Intent(context.packageName + ".CUSTOM_VIEW")
//        startActivity(intent)
    }

    private fun testPlurals() {
        Log.d("TAG", resources.getQuantityString(R.plurals.plurals, 1, 2))

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        var bitmap = data.getParcelableExtra<Bitmap>("data")
        Log.d("TAG", bitmap.toString())
    }

    private fun showCamera(photoUri: Uri) {
        var intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
        startActivityForResult(intent, 1)
    }

    override fun onPause() {
        super.onPause()
        //RESET VALUES TO BE SAVED TO DB HERE FROM EDITTEXTS ETC
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
    }

    fun showNotification(){
        NewMessageNotification.notify(context, "DummyText", 10)
    }

    /**
     * Prepare the Fragment host's standard options menu to be displayed. This is called right before the menu is shown, every time it is shown.
     */
    override fun onPrepareOptionsMenu(menu: Menu?) {
        super.onPrepareOptionsMenu(menu)
    }

    private fun testSettingsFragment(){
        startActivity(Intent(context, SettingsActivity::class.java))
    }
}

