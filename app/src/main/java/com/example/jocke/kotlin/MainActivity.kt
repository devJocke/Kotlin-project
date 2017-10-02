package com.example.jocke.kotlin

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import kotlinx.android.synthetic.main.drawer_layout.*
import kotlinx.android.synthetic.main.first_page_drawer.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val TAG: String = this::class.java.simpleName
    lateinit var drawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(customToolbar)
        setupDrawer()
    }

    private fun setupDrawer() {
        drawerToggle = ActionBarDrawerToggle(this, main_drawer_container, customToolbar, R.string.open, R.string.close)
        main_drawer_container.addDrawerListener(drawerToggle)
        main_navigation_container.setNavigationItemSelectedListener(this)
        drawerToggle.syncState()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.login -> {
                Log.d(TAG, "Login")
            }
            R.id.logout -> {
            }
            R.id.firstname -> {
            }
            R.id.lastname -> {
            }
            R.id.email -> {
            }
            R.id.open_drawer -> {
            }
            R.id.close_drawer -> {
                main_drawer_container.closeDrawer(GravityCompat.START)
            }
        }
        return true
    }
}
