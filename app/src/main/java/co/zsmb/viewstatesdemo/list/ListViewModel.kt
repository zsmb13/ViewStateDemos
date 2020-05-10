package co.zsmb.viewstatesdemo.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ListViewModel : ViewModel() {

    val viewState = MutableLiveData<ListViewState>()
    private var state: ListViewState
        get() {
            return viewState.value!!
        }
        set(value) {
            viewState.value = value
        }

    init {
        state = ListViewState(
            isLoading = true,
            items = emptyList()
        )
    }

    fun loadMore() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)

            val newWords = getMoreWords()

            state = state.copy(
                isLoading = false,
                items = state.items + newWords
            )
        }
    }

    private suspend fun getMoreWords(): List<String> {
        delay(500L)
        return listOf("Nitwit", "Blubber", "Oddment", "Tweak")
    }

}
