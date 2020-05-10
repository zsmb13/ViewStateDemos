package co.zsmb.viewstatesdemo.upload

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random
import kotlin.random.nextLong

class UploadViewModel : ViewModel() {

    val viewState = MutableLiveData<UploadViewState>()

    init {
        viewState.value = Initial
    }

    fun startUpload() {
        viewModelScope.launch {
            viewState.value = Initial
            viewState.value = UploadInProgress(10)
            delay()
            viewState.value = UploadInProgress(30)
            delay()
            viewState.value = UploadInProgress(42)
            delay()
            viewState.value = UploadInProgress(50)
            delay()

            if (Random.nextBoolean()) {
                viewState.value = UploadFailed
                return@launch
            }

            viewState.value = UploadInProgress(70)
            delay()
            viewState.value = UploadInProgress(90)
            delay()
            viewState.value = UploadInProgress(94)
            delay()
            viewState.value = UploadInProgress(99)
            delay()
            viewState.value = UploadInProgress(100)

            viewState.value = UploadSuccess
        }
    }

    private suspend fun delay() {
        delay(Random.nextLong(500L..1200L))
    }

}
