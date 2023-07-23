package org.bziembanski.androidexample.screens.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.bziembanski.androidexample.data.Person
import org.bziembanski.androidexample.repository.RemoteRepository
import java.lang.Exception

class DetailsViewModel() : ViewModel() {
    private val _person = MutableLiveData<Person?>(null)
    val person: LiveData<Person?> = _person


    fun getPerson(personId: Int) {
        viewModelScope.launch {
            try {
                val result = RemoteRepository().fetchPerson(personId)
                _person.value = result
                Log.d("DetailsViewModel fetch", result.toString())
            } catch (_: Exception) {
                _person.value = null
            }
        }
    }
}