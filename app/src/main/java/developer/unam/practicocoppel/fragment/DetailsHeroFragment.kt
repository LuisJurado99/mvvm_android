package developer.unam.practicocoppel.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import developer.unam.practicocoppel.R
import developer.unam.practicocoppel.adapter.AdapterGenericDetails
import developer.unam.practicocoppel.databinding.DetailsHeroFragmentBinding
import developer.unam.practicocoppel.databinding.RepeatItemIncCharacterBinding
import developer.unam.practicocoppel.retrofit.character.Item

class DetailsHeroFragment : Fragment() {
    private var _binding: DetailsHeroFragmentBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<DetailsHeroFragmentArgs>()

    companion object {
        fun newInstance() = DetailsHeroFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            DetailsHeroFragmentBinding.inflate(LayoutInflater.from(context), container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val support = activity as AppCompatActivity
        support.supportActionBar?.title = args.character.name.toString()
        binding.incFragment.tvIncDescription.text = args.character.description
        binding.incFragment.tvIncName.text = args.character.name
        val imageFinal = "${args.character.thumbnail?.path}.${args.character.thumbnail?.extension}"

        Picasso.get().load(imageFinal)
            .into(binding.incFragment.ivIncDetailsFragment)

        val stories = args.character.stories
        val comics = args.character.comics
        val events = args.character.events
        val series = args.character.series

        if (stories?.returned!! > 0) {
            configurationRecycler(
                stories.items?.toMutableList() ?: mutableListOf(),
                binding.incStories, R.string.stories
            )
        } else {
            binding.incStories.root.visibility = View.GONE
        }

        if (comics?.returned!! > 0) {
            configurationRecycler(
                comics.items?.toMutableList() ?: mutableListOf(),
                binding.incComic, R.string.comics
            )
        } else {
            binding.incComic.root.visibility = View.GONE
        }

        if (events?.returned!! > 0) {
            configurationRecycler(
                events.items?.toMutableList() ?: mutableListOf(),
                binding.incEvents, R.string.events
            )
        } else {
            binding.incEvents.root.visibility = View.GONE
        }

        if (series?.returned!! > 0) {
            configurationRecycler(
                series.items?.toMutableList() ?: mutableListOf(),
                binding.incSeries, R.string.series
            )
        } else {
            binding.incSeries.root.visibility = View.GONE
        }

        if (binding.incComic.root.visibility == View.GONE &&
            binding.incEvents.root.visibility == View.GONE &&
            binding.incSeries.root.visibility == View.GONE &&
            binding.incStories.root.visibility == View.GONE)
            binding.mcvNotElementShow.visibility = View.VISIBLE

    }

    private fun configurationRecycler(
        list: MutableList<Item>,
        include: RepeatItemIncCharacterBinding,
        @StringRes title: Int
    ) {
        include.tvCategoryCharacter.setText(title)
        include.rvRepeatCharacter.adapter = AdapterGenericDetails().apply {
            addItems(list)
        }
        include.rvRepeatCharacter.layoutManager =
            object : LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false) {
                override fun canScrollHorizontally(): Boolean {
                    return false
                }

                override fun canScrollVertically(): Boolean {
                    return false
                }
            }

    }


}