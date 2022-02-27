package developer.unam.practicocoppel.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import developer.unam.practicocoppel.repository.MainRepository
import developer.unam.practicocoppel.retrofit.character.Characters
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel constructor(private val repository: MainRepository) : ViewModel() {

    val movieList = MutableLiveData<Characters>()
    val errorMessage = MutableLiveData<String>()

    fun getAllMovies() {
        val response = repository.getCharacters()
        response.enqueue(object : Callback<Characters> {
            override fun onResponse(call: Call<Characters>, response: Response<Characters>) {
                movieList.postValue(response.body())
            }

            override fun onFailure(call: Call<Characters>, t: Throwable) {
                errorMessage.postValue(t.message)
            }

        })

    }

}