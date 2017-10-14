package com.example.jocke.kotlin.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.jocke.kotlin.R
import com.example.jocke.kotlin.data.dal.Person
import com.example.jocke.kotlin.extensions.inflate
import kotlinx.android.synthetic.main.row_string_data.view.*

/**
 * Created by Jocke on 2017-07-09.
 */
open class UserAdapter(val persons: ArrayList<Person>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.ViewHolder {
        return ViewHolder(parent.inflate(R.layout.row_string_data))
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindUser(persons[position])
        holder.itemView.firstname.animate().setDuration(1000).alpha(1f).start()
    }

    override fun getItemCount(): Int {
        return persons.size
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindUser(person: Person) {
            with(person) {
                itemView.firstname.text = person.firstname
                itemView.lastname.text = person.lastname
                itemView.thirdname.text = person.thirdname
            }
        }
    }
}
