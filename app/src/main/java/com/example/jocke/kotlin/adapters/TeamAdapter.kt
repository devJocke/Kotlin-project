package com.example.jocke.kotlin.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.jocke.kotlin.R
import com.example.jocke.kotlin.data.person.TeamDTO
import com.example.jocke.kotlin.extensions.inflate
import kotlinx.android.synthetic.main.row_string_data.view.*

/**
 * Created by Jocke on 2017-07-09.
 */
open class TeamAdapter(private val teams: List<TeamDTO>) : RecyclerView.Adapter<TeamAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamAdapter.ViewHolder {
        return ViewHolder(parent.inflate(R.layout.row_string_data))
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindPerson(teams[position])
        holder.itemView.firstname.animate().setDuration(1000).alpha(1f).start()
    }

    override fun getItemCount(): Int = teams.size


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindPerson(team: TeamDTO) {
            with(team) {
                itemView.firstname.text = team.firstName
                itemView.lastname.text = team.lastName
                itemView.thirdname.text = team.thirdName
            }
        }
    }
}
