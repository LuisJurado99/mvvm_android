package developer.unam.practicocoppel.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AbsListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import developer.unam.practicocoppel.adapter.AdapterCharacterMain
import developer.unam.practicocoppel.databinding.ActivityMainBinding
import developer.unam.practicocoppel.factory.MainViewModelFactory
import developer.unam.practicocoppel.repository.MainRepository
import developer.unam.practicocoppel.retrofit.RetrofitInstance
import developer.unam.practicocoppel.viewmodel.MainViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    private val retrofitInstance = RetrofitInstance.getInstance()
    private lateinit var adapter: AdapterCharacterMain

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var isScrolling =false
        val layoutManager= GridLayoutManager(this,2)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvMainActivity.layoutManager = layoutManager
        adapter = AdapterCharacterMain(this@MainActivity)
        binding.rvMainActivity.adapter = adapter

        viewModel =
            ViewModelProvider(this, MainViewModelFactory(MainRepository(retrofitInstance))).get(
                MainViewModel::class.java
            )
        binding.rvMainActivity.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val currentItems = layoutManager.childCount
                val totalItems = layoutManager.itemCount
                val scrollOutItems = layoutManager.findFirstVisibleItemPosition()
                val totalItemsNow= currentItems + scrollOutItems
                if (isScrolling && totalItemsNow == totalItems) {
                    isScrolling = false
                    viewModel.getAllMovies()
                    Log.e("finalScroll","isFinalScroll")
                }
                binding.fabUpdateScrollMainActivity.visibility = if (dy<5 && !isScrolling)
                    View.GONE
                else
                    View.VISIBLE

            }
        })
        binding.fabUpdateScrollMainActivity.setOnClickListener {
            binding.rvMainActivity.scrollToPosition(0)
        }
        viewModel.movieList.observe(this, Observer {
            adapter.insertItems(it.data.results.toMutableList())
        })

        viewModel.errorMessage.observe(this, Observer {
            Log.e(MainActivity::class.java.name, "error $it")
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
        })

        viewModel.getAllMovies()
    }

}