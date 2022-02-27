package developer.unam.practicocoppel.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import developer.unam.practicocoppel.R
import developer.unam.practicocoppel.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mNavController:NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var isScrolling = false
        binding = ActivityMainBinding.inflate(layoutInflater)
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        setContentView(binding.root)
        setSupportActionBar(binding.tbActivity)
        mNavController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this,mNavController)

    }

    override fun onSupportNavigateUp(): Boolean {
        mNavController.popBackStack()
        return super.onSupportNavigateUp()
    }

}
