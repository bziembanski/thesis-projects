package org.bziembanski.composeexample.screens.details_screen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.bziembanski.composeexample.data.Person
import org.bziembanski.composeexample.repository.RemoteRepository
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