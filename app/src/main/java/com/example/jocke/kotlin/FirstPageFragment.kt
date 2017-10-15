package com.example.jocke.kotlin

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.jocke.kotlin.adapters.UserAdapter
import com.example.jocke.kotlin.data.dal.Person
import com.example.jocke.kotlin.extensions.setUrl
import com.example.jocke.kotlin.extensions.toast
import com.example.jocke.kotlin.extensions.visible
import kotlinx.android.synthetic.main.fragment_first_page.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory


/**
 * Created by Jocke on 2017-07-02.
 */

class FirstPageFragment : Fragment() {

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
        setTextViewText()
        xd()
    }



    private fun setTextViewText() {
        textView.text = "TextView"

        val persons: ArrayList<Person> = ArrayList()
        val user1 = Person("Hejsan", "asd", "wqe")
        val user2 = Person("Joakim", "Nilsson", "Robin")
        val user3 = Person("Hejsan", "asd", "Robin")
        val user4 = Person("Hejsan", "asd", "Robin")
        persons.add(user1)
        persons.add(user2)
        persons.add(user3)
        persons.add(user4)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = UserAdapter(persons)

        display_stuff.setUrl(R.drawable.x)

        sortButton.click(textView, display_stuff)
        checkVisibility(textView)

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


    private fun checkVisibility(textView: TextView) {
        when (textView.visibility) {
            View.VISIBLE -> toast(context, "Visible")
            View.INVISIBLE -> toast(context, "Invisible")
            else -> toast(context, "Gone")
        }
    }

    private fun xd(){
//        .networkModule(new NetworkModule "https://apirix-inspection-stage.azurewebsites.net")
        Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()


//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .baseUrl("localhost")
    }
}

