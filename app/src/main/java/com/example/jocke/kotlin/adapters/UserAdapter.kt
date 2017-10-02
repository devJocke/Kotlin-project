package com.example.jocke.kotlin.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.jocke.kotlin.R
import com.example.jocke.kotlin.User
import com.example.jocke.kotlin.extensions.inflate
import kotlinx.android.synthetic.main.row_string_data.view.*

/**
 * Created by Jocke on 2017-07-09.
 */
open class UserAdapter(val users: ArrayList<User>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.ViewHolder {
        return ViewHolder(parent.inflate(R.layout.row_string_data))
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindUser(users[position])
        holder.itemView.firstname.animate().setDuration(1000).alpha(1f).start()
    }

    override fun getItemCount(): Int {
        return users.size
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindUser(user: User) {
            with(user) {
                itemView.firstname.text = user.firstname
                itemView.lastname.text = user.lastname
                itemView.thirdname.text = user.thirdname
            }
        }
    }
}
