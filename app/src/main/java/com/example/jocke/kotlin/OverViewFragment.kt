package com.example.jocke.kotlin

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jocke.kotlin.R.drawable.lif
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_overview.*
import kotlinx.android.synthetic.main.overview_drawer_layout.*


/**
 * Created by Jocke on 2017-07-02.
 */

class OverViewFragment : Fragment() {

    public val TAG: String = javaClass.simpleName

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater?.inflate(R.layout.fragment_overview, container, false)
        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Picasso.with(context).load(lif).fit().into(club_crest_image_view)
        (activity as MainActivity).setupDrawer(toolbar, drawer_layout, main_navigation_container)
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

