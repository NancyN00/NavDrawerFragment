package com.example.navigationdrawer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    lateinit var toggle : ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawerLayout)
        val navView : NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {

           it.isChecked = true
            when(it.itemId){
                //if user taps on home icon, it calls an item(replace) then pass an object(homefragment)

                R.id.nav_home-> replaceFragment(HomeFragment(), it.title.toString())
                R.id.nav_message-> replaceFragment(MessageFragment(), it.title.toString())
                R.id.nav_sync-> Toast.makeText(applicationContext, "Clicked Sync", Toast.LENGTH_SHORT).show()
                R.id.nav_delete-> Toast.makeText(applicationContext, "Clicked Delete", Toast.LENGTH_SHORT).show()
                R.id.nav_settings-> replaceFragment(SettingFragment(), it.title.toString())
                R.id.nav_login-> Toast.makeText(applicationContext, "Clicked Login", Toast.LENGTH_SHORT).show()
                R.id.nav_share-> Toast.makeText(applicationContext, "Clicked Share", Toast.LENGTH_SHORT).show()
                R.id.nav_rate-> Toast.makeText(applicationContext, "Clicked Rate Us", Toast.LENGTH_SHORT).show()
            }

            true
        }
    }

    ////whenever a user taps on any item, we replace frame layout with any of the fragment

    private fun replaceFragment(fragment: Fragment, title : String){

        //get data from the fragment manager

        val fragmentManager  = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout,fragment)
        fragmentTransaction.commit()
        //whenever user taps on the drawer it should get close
        drawerLayout.close()
        setTitle(title)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}