package com.example.jocke.kotlin

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.FragmentManager
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MenuItem
import com.facebook.stetho.Stetho


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val TAG: String = this::class.java.simpleName

    private lateinit var mFragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Stetho.initializeWithDefaults(this)

        mFragmentManager = supportFragmentManager

        if (mFragmentManager.findFragmentById(R.id.overview_fragment_container) == null) {
            val overViewFragment = OverViewFragment.newInstance(null)
            mFragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                    .replace(R.id.main_activity_framelayout, overViewFragment)
                    .addToBackStack("")
                    .commit()
        }
    }


    override fun onBackPressed() {
        if (mFragmentManager.backStackEntryCount > 0) {
            Log.i(TAG, "Popping from back stack")
            mFragmentManager.popBackStack()
        } else {
            Log.i(TAG, "Nothing to pop from back stack, calling super")

            finish()
            super.onBackPressed()
        }
    }

    fun setupDrawer(toolbar: Toolbar, drawerLayout: DrawerLayout, navView: NavigationView) {
        setSupportActionBar(toolbar)

        val drawerToggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.title_open, R.string.title_close)
        drawerLayout.addDrawerListener(drawerToggle)
        navView.setNavigationItemSelectedListener(this)
        drawerToggle.syncState()
    }

    fun attachSupportBar(toolbar: Toolbar?, displayHomeAsUp: Boolean) {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(displayHomeAsUp)
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
            }
        }
        //TODO REMOVE
        startActivity(Intent(this, SplashActivity::class.java))
        finish()
        return true
    }

    fun startTeamLineupFragment(bundle: Bundle?) {
        if (mFragmentManager.findFragmentByTag("teamLineupFragment") == null) {
            val teamLineupFragment = TeamLineupFragment.newInstance(bundle)
            mFragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                    .replace(R.id.main_activity_framelayout, teamLineupFragment)
                    .addToBackStack("teamLineupFragment")
                    .commit()
        }
    }

}

