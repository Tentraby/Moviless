package com.example.testandroid.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.testandroid.R
import com.example.testandroid.databinding.ActivityMainBinding
import com.example.testandroid.ui.popular.PopularFragment
import com.example.testandroid.ui.upcoming.UpcomingFragment
import com.example.testandroid.ui.viewest.ViewestFragment

import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val upcoming = UpcomingFragment()
        val popular = PopularFragment()
        val viewest = ViewestFragment()
        //mandamos a llamar al boton boton de navegacion que creamos al principio
        val bottomNavigationView = binding.navigationBoton
        //al fragmento dentro del layout
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
        //al navController (control de navegacion) del mismo navHostFragment
        val navController = navHostFragment.navController


        //sincronizamos nuestro la vista de botones de navegacion (bottom navigation view - en el layout) para que escuche todos los items que precionamos
        bottomNavigationView.setupWithNavController(navController)


        //configuracion del headvar
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.homeFragment,R.id.homeTopRated,R.id.homeUpcoming)
        )
        setupActionBarWithNavController(navController,appBarConfiguration)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
        private fun setCurrentFragment(fragment: Fragment)=
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.homeFragment,fragment)
                commit()
            }

}
