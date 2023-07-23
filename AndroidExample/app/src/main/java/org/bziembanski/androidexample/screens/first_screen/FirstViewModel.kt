package org.bziembanski.androidexample.screens.first_screen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.launch
import org.bziembanski.androidexample.data.Person
import org.bziembanski.androidexample.repository.RemoteRepository
import java.lang.Exception

class FirstViewModel : ViewModel() {

    private val _people = MutableLiveData<List<Person>>()
    val people: LiveData<List<Person>> = _people

    init {
        getPeople()
    }

    private fun getPeople() {
        viewModelScope.launch {
            try {
                val listResult = RemoteRepository().fetchAll()
                _people.value = listResult
                Log.d("FirstViewModel fetch", listResult.toString())
            } catch (e: Exception) {
                _people.value = listOf()
            }

        }
    }
}