package developer.unam.practicocoppel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import developer.unam.practicocoppel.repository.MainRepository
import developer.unam.practicocoppel.viewmodel.MainViewModel
import java.lang.IllegalArgumentException

class MainViewModelFactory constructor(private val repository: MainRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(MainViewModel::class.java))
            MainViewModel(repository) as T
        else
            throw IllegalArgumentException("ViewModelNotFound")
    }
}