package developer.unam.practicocoppel.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import developer.unam.practicocoppel.R
import developer.unam.practicocoppel.viewmodel.DetailsHeroViewModel

class DetailsHeroFragment : Fragment() {

    companion object {
        fun newInstance() = DetailsHeroFragment()
    }

    private lateinit var viewModel: DetailsHeroViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.details_hero_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailsHeroViewModel::class.java)
        // TODO: Use the ViewModel
    }

}