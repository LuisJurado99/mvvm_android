package developer.unam.practicocoppel.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import developer.unam.practicocoppel.R
import developer.unam.practicocoppel.databinding.DetailsHeroFragmentBinding
import developer.unam.practicocoppel.viewmodel.DetailsHeroViewModel

class DetailsHeroFragment : Fragment() {
    private var _binding:DetailsHeroFragmentBinding?=null
    private val binding get() = _binding!!
    private val args by navArgs<DetailsHeroFragmentArgs>()

    companion object {
        fun newInstance() = DetailsHeroFragment()
    }

    private lateinit var viewModel: DetailsHeroViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailsHeroFragmentBinding.inflate(LayoutInflater.from(context),container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailsHeroViewModel::class.java)

    }

}