package com.demo.bookmarklocation.ui

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.ListFragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.demo.bookmarklocation.R
import com.demo.bookmarklocation.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var curFragment: Fragment
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        goToFragment(LocationListFragment.newInstance())


    }


    private fun goToFragment(fragment: Fragment?) {

        //val someFragment = LocationListFragment.newInstance();
        val transaction = supportFragmentManager?.beginTransaction();
        transaction?.replace(
            R.id.framlayout,
            fragment!!
        ) // give your fragment container id in first parameter
        transaction?.addToBackStack(null)
        transaction?.commit();
    }
}