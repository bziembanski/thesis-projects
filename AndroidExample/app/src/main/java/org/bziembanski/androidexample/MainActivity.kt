package org.bziembanski.androidexample

import android.os.Bundle
import android.transition.Slide
import android.transition.TransitionManager
import android.util.Log
import android.view.Gravity
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import org.bziembanski.androidexample.databinding.ActivityMainBinding
import org.bziembanski.androidexample.screens.details.DetailsFragment

class MainActivity : AppCompatActivity() {
  private lateinit var binding: ActivityMainBinding
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
    setSupportActionBar(binding.toolbar)

    val navController = binding
      .navHostFragmentContentMain
      .getFragment<NavHostFragment>()
      .navController

    binding.bottomNavigation.apply {
      setupWithNavController(navController)
      visibility = View.VISIBLE
    }
    val appBarConfiguration = AppBarConfiguration(navController.graph)
    binding.toolbar.setupWithNavController(navController, appBarConfiguration)

    supportFragmentManager.registerFragmentLifecycleCallbacks(object :
      FragmentManager.FragmentLifecycleCallbacks() {
      override fun onFragmentViewCreated(
        fm: FragmentManager,
        f: Fragment,
        v: View,
        savedInstanceState: Bundle?
      ) {
        TransitionManager.beginDelayedTransition(
          binding.root,
          Slide(Gravity.BOTTOM).excludeTarget(
            R.id.nav_host_fragment_content_main,
            true
          )
        )
        Log.d("MainActivity", f.toString())
        when (f) {
          is DetailsFragment -> binding.bottomNavigation.visibility = View.GONE
          else -> binding.bottomNavigation.visibility = View.VISIBLE
        }
      }
    }, true)
  }
}