package com.example.jetpacknavigationcomponent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import androidx.recyclerview.widget.DiffUtil
import com.example.jetpacknavigationcomponent.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //Setting up toolbar XML as actionBar
        setSupportActionBar(toolbar)

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.homeFragment , R.id.settingsFragment, R.id.notificationsFragment),
        //passdrawer layout object to see hamburger icon
        binding.drawerLayout
        )

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        setupActionBarWithNavController(navController , appBarConfiguration)

        binding.bottomNavView.setupWithNavController(navController)
        binding.navDrawer.setupWithNavController(navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        //navigateUp() will take us to previous destination
        //Now <- button will also work on Action bar
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
        //passing appBarConfiguration object to show drawer layout on click hamburger icon
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu , menu)
         return super.onCreateOptionsMenu(menu)

    }

    //handle events on menu in this function
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return  when(item.itemId){
            R.id.item_about_app ->{
                //Handle onclick on menu item_abou_app icon
             val action = MainNavGraphDirections.actionGlobalAboutFragment()
             navController.navigate(action)
                return true
            }
            else ->
                //item.onNavDestinationSelected(navController) will take us to setting fragment
                //From menu setting icon click
             item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)

        }


    }

    override fun onDestroy() {
        super.onDestroy()

    }
}