package com.example.free_games

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.iterator
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.free_games.Forum.Forum
import com.example.free_games.HomeRecyclerView.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var BottomNavigationMenu: BottomNavigationView
    //private lateinit var Register:

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        BottomNavigationMenu = findViewById(R.id.bnv)
        //this.title = mainActivityTitle

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.content_area, RecyclerView())
                .commitAllowingStateLoss()
        }

        BottomNavigationMenu.setOnNavigationItemSelectedListener(
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.id_home -> {
                        for (menuitem in BottomNavigationMenu.menu.iterator()) {
                            menuitem.isEnabled = true
                        }
                        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
//                        val menu = BottomNavigationMenu.menu.findItem(R.id.id_home)
//                        menu?.isEnabled = false

                        supportFragmentManager.beginTransaction()
                            .replace(R.id.content_area, RecyclerView())
                            .commit()

                    }
                    R.id.id_profile -> {
                        for (menuitem in BottomNavigationMenu.menu.iterator()) {
                            menuitem.isEnabled = true
                        }
                        val menu = BottomNavigationMenu.menu.findItem(R.id.id_profile)
                        menu?.isEnabled = false

                        supportFragmentManager.beginTransaction()
                            .replace(R.id.content_area, LoginFragment())
                            .addToBackStack(null)
                            .commit()
                    }

                    R.id.id_list -> {
                        val intent = Intent(this@MainActivity, Forum::class.java)
                        startActivity(intent)
                    }
                }
                true
            })

        supportFragmentManager.addOnBackStackChangedListener {
            if (supportFragmentManager.backStackEntryCount == 0) {
                for (menuitem in BottomNavigationMenu.menu.iterator()) {
                    menuitem.isEnabled = true
                }
            }
        }
    }
}

























//        BottomNavigationMenu.setOnNavigationItemReselectedListener { it ->
//            when (it.itemId) {
//                R.id.id_home -> {
//                    for(menuitem in BottomNavigationMenu.menu.iterator())
//                    {
//                        menuitem.isEnabled = true
//                    }
//                    val menu = BottomNavigationMenu.menu.findItem(R.id.id_home)
//                    menu?.isEnabled = false
//
//                    CurrentFragment = RecyclerView()
//                    supportFragmentManager.beginTransaction()
//                        .replace(R.id.content_area, CurrentFragment)
//                        .commit()
//
//                }
//                R.id.id_profile -> {
//                    for(menuitem in BottomNavigationMenu.menu.iterator())
//                    {
//                        menuitem.isEnabled = true
//                    }
//                    val menu = BottomNavigationMenu.menu.findItem(R.id.id_profile)
//                    menu?.isEnabled = false
//                    CurrentFragment = LoginFragment()
//                    supportFragmentManager.beginTransaction()
//                        .add(R.id.content_area, CurrentFragment)
//                        .addToBackStack(null)
//                        .commit()
//                }
//
//                R.id.id_list -> {
//                    val intent = Intent(this@MainActivity, Forum::class.java)
//                    startActivity(intent)
//                }
//            }
//        }
//
//        //BottomNavigationMenu.setOnNavigationItemSelectedListener(navListener)
//    }
//}

//    val navListener = BottomNavigationView.OnNavigationItemReselectedListener {
//        when(it.itemId){
//            R.id.id_home -> {
//                CurrentFragment = RecyclerView()
//            }
//            R.id.id_profile -> {
//                CurrentFragment = LoginFragment()
//            }
//        }
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.content_area, CurrentFragment)
//            .commit()

