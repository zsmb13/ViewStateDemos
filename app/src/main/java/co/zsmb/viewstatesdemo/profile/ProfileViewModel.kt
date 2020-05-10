package co.zsmb.viewstatesdemo.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {

    val viewState = MutableLiveData<ProfileViewState>()

    init {
        viewState.value = Loading
    }

    fun load(shouldError: Boolean = false) {
        viewModelScope.launch {
            viewState.value = Loading

            delay(500L)

            viewState.value = if (shouldError) {
                Error
            } else {
                ProfileLoaded(
                    name = "Sam Doe",
                    email = "sam.doe@example.com"
                )
            }
        }
    }

}
