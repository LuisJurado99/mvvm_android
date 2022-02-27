package developer.unam.practicocoppel.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import developer.unam.practicocoppel.adapter.AdapterCharacterMain
import developer.unam.practicocoppel.databinding.MainFragmentBinding
import developer.unam.practicocoppel.factory.MainViewFactory
import developer.unam.practicocoppel.repository.FragmentMainRepository
import developer.unam.practicocoppel.retrofit.RetrofitInstance
import developer.unam.practicocoppel.retrofit.character.Result
import developer.unam.practicocoppel.view.MainActivity
import developer.unam.practicocoppel.viewmodel.MainViewModelFragment

class MainFragment : Fragment(),AdapterCharacterMain.onClickAdapterCharacter {

    private var _binding:MainFragmentBinding? =null
    private val binding get() = _binding!!
    private var model = activityViewModels<MainViewModelFragment>()

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModelFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(LayoutInflater.from(context),container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this,MainViewFactory(FragmentMainRepository(RetrofitInstance.getInstance())))[MainViewModelFragment::class.java]
        var isScrolling = false
        var offset =0
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.rvMainActivity.layoutManager = layoutManager
        val adapter = AdapterCharacterMain(requireContext())
        binding.rvMainActivity.adapter = adapter

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

                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItems: IntArray? = layoutManager.findFirstVisibleItemPositions(null)
                val pastVisibleItems =
                    if (firstVisibleItems != null && firstVisibleItems.isNotEmpty()) {
                        firstVisibleItems[0]
                    } else 0

                if (isScrolling) {
                    if (visibleItemCount + pastVisibleItems >= totalItemCount) {
                        isScrolling = false
                        offset += 100

                        viewModel.getAllMovies(offset = offset)
                    }
                }
                binding.fabUpdateScrollMainActivity.visibility = if (dy < 5 && !isScrolling)
                    View.GONE
                else
                    View.VISIBLE

            }
        })
        binding.fabUpdateScrollMainActivity.setOnClickListener {
            binding.fabUpdateScrollMainActivity.visibility = View.GONE
            binding.rvMainActivity.scrollToPosition(0)
        }

        viewModel.movieList.observe(viewLifecycleOwner, Observer {
            adapter.insertItems(it.data.results.toMutableList())
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            Log.e(MainActivity::class.java.name, "error $it")
            Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
        })
        viewModel.getAllMovies()
    }

    override fun clickCharacterAdapter(character: Result) {
        findNavController().navigate(MainFragmentDirections.actionMainFragmentToDetailsHeroFragment(character))
    }

}
