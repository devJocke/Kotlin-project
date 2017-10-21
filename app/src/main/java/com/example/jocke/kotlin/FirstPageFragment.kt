package com.example.jocke.kotlin

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.jocke.kotlin.adapters.TeamAdapter
import com.example.jocke.kotlin.data.person.TeamDTO
import com.example.jocke.kotlin.data.person.TeamService
import com.example.jocke.kotlin.extensions.visible
import kotlinx.android.synthetic.main.fragment_first_page.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by Jocke on 2017-07-02.
 */

class FirstPageFragment : Fragment() {

    public val TAG : String = javaClass.simpleName

    companion object {
        @JvmStatic
        fun newInstance(b: Bundle?): FirstPageFragment {
            val f = FirstPageFragment()
            b?.putString("1", "hej")
            f.arguments = b
            return f
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?):
            View? = inflater?.inflate(R.layout.fragment_first_page, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sortButton.click(textView, display_stuff)
        getAllPersons()
    }

    private fun Button.click(textView: View, imageView: View) {
        val list: ArrayList<View> = ArrayList()
        list.add(textView)
        list.add(imageView)
        setOnClickListener {
            for (v: View in list) {
                v.visible(false)
            }
        }
    }

    private fun getAllPersons() {

        val builder = Retrofit.Builder()
                //Get from localhost on emulator
                .baseUrl("http://10.0.2.2:53836")
                .addConverterFactory(GsonConverterFactory.create())

        val retrofit = builder.build()

        val client = retrofit.create(TeamService::class.java)
        val call= client.allPersons

        call.enqueue(object : Callback<List<TeamDTO>> {
            override fun onResponse(call : Call<List<TeamDTO>>, response: Response<List<TeamDTO>>) {
                val allPersons = response.body()!!

                if(allPersons.isEmpty()){
                    Toast.makeText(context, "No Persons available", Toast.LENGTH_SHORT).show()
                }
                recyclerView.layoutManager = LinearLayoutManager(context)
                recyclerView.adapter = TeamAdapter(allPersons)
            }

            override fun onFailure(call: Call<List<TeamDTO>>, t: Throwable) {
                Toast.makeText(context, "Server error", Toast.LENGTH_SHORT).show()
            }
        })
    }
}

