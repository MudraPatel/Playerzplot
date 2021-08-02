package com.example.playerzpot

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.playerzpot.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {

    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        setSupportActionBar(viewBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false);
        supportActionBar?.setHomeButtonEnabled(false);
        val navController = findNavController(this, R.id.my_nav_host_fragment)
        navController.addOnDestinationChangedListener(this)
        NavigationUI.setupWithNavController( viewBinding.bottomNavView, navController)

    }

    private fun showBottomNav() {
        viewBinding.bottomNavView.visibility = View.VISIBLE
    }

    private fun hideBottomNav() {
        viewBinding.bottomNavView.visibility = View.GONE
    }

    private fun visibleBack(){
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setHomeButtonEnabled(true);
        viewBinding.ivFavorite.isVisible = true
        viewBinding.ivSpeaker.isVisible = true
        viewBinding.ivMenu.isVisible = false
        viewBinding.searchView.isVisible = false
    }

    private fun hideBack(){
        supportActionBar?.setDisplayHomeAsUpEnabled(false);
        supportActionBar?.setHomeButtonEnabled(false);
        viewBinding.ivFavorite.isVisible = false
        viewBinding.ivSpeaker.isVisible = false
        viewBinding.ivMenu.isVisible = true
        viewBinding.searchView.isVisible = true
    }
    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        when(destination.id){
            R.id.discoverFragment -> {
                hideBack()
                showBottomNav()
            }
            R.id.videoFragment -> {
                hideBack()
                showBottomNav()
            }
            R.id.detailFragment -> {
                visibleBack()
                hideBottomNav()
            }
            else -> {
                hideBack()
                hideBottomNav()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
