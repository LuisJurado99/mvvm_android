package developer.unam.practicocoppel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import developer.unam.practicocoppel.repository.FragmentMainRepository
import developer.unam.practicocoppel.viewmodel.MainViewModelFragment
import java.lang.IllegalArgumentException

class MainViewFactory constructor(private val repository: FragmentMainRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(MainViewModelFragment::class.java))
            MainViewModelFragment(repository) as T
        else
            throw IllegalArgumentException("ViewModelNotFound")
    }
}