package com.buzynski.pokedex.base

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.buzynski.pokedex.helpers.Event
import com.buzynski.pokedex.navigation.NavigationCommand
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BaseViewModel : ViewModel() {

    // ---

    private val mSnackBarError = MutableLiveData<Event<String>>()
    val snackBarError: LiveData<Event<String>> get() = mSnackBarError

    private val _loadingState = MutableLiveData<Event<Boolean>>()
    val loadingState: LiveData<Event<Boolean>> get() = _loadingState

    private val _navigation = MutableLiveData<Event<NavigationCommand>>()
    val navigation: LiveData<Event<NavigationCommand>> = _navigation

    // ---

    protected fun setLoadingState(isLoading: Boolean) {
        _loadingState.postValue(Event(isLoading))
    }

    protected fun postErrorValue(e: Exception) {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                val errorMessage = e.localizedMessage ?: e.message ?: "Unknown error occurred."
                mSnackBarError.value = Event(errorMessage)
                Log.i("Error occurred:", errorMessage)
            }
        }
    }

    // --- NAVIGATION

    fun navigateTo(directions: NavDirections) {
        _navigation.value = Event(NavigationCommand.To(directions))
    }

    fun <T> navigateBackWithObservableData(key: String, data: T) {
        _navigation.value = Event(NavigationCommand.BackWithArgs(key, data))
    }

    fun navigateBack() {
        _navigation.value = Event(NavigationCommand.Back)
    }
}