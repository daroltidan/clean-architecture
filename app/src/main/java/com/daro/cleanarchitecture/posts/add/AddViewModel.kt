package com.daro.cleanarchitecture.posts.add

import android.text.Editable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daro.domain.entities.ResultState
import com.daro.domain.error.ErrorHandler
import com.daro.domain.usecases.CreatePost
import kotlinx.coroutines.launch

class AddViewModel constructor(
    private val createPost: CreatePost,
    private val errorHandlerImpl: ErrorHandler
) : ViewModel() {

    val title = MutableLiveData<String>()
    val body = MutableLiveData<String>()

    val buttonEnabled = MutableLiveData<Boolean>().apply { value = false }

    fun savePost() {
        viewModelScope.launch {
            val createState = createPost()
            println(createState)
            // TODO: 10/07/2020 handle createState 
        }
    }

    private suspend fun createPost(): ResultState<Boolean> =
        try {
            createPost.invoke(title.value.toString(), body.value.toString())
            ResultState.SuccessState(true)
        } catch (e: Exception) {
            ResultState.ErrorState(errorHandlerImpl.handleError(e))
        }

    fun onTextChanged(title: Editable?, body: Editable?) {
        buttonEnabled.value = !title.isNullOrBlank() && !body.isNullOrBlank()
    }

}