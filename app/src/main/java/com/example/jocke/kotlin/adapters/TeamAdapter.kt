package com.example.jocke.kotlin.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.jocke.kotlin.R
import com.example.jocke.kotlin.data.dal.Team
import com.example.jocke.kotlin.extensions.inflate
import kotlinx.android.synthetic.main.row_team_data.view.*

/**
 * Created by Jocke on 2017-07-09.
 */
open class TeamAdapter(private val teams: List<Team>) : RecyclerView.Adapter<TeamAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamAdapter.ViewHolder = ViewHolder(parent.inflate(R.layout.row_team_data))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindPerson(teams[position])
        holder.itemView.firstname.animate().setDuration(1000).alpha(1f).start()
    }

    override fun getItemCount(): Int = teams.size


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindPerson(team: Team) {
            with(team) {
                itemView.firstname.text = team.firstName
                itemView.lastname.text = team.lastName
                itemView.thirdname.text = team.thirdName
            }
        }
    }
}
