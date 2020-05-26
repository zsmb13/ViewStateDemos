package co.zsmb.viewstatesdemo.upload

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.zsmb.viewstatesdemo.select
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random
import kotlin.random.nextLong

class UploadViewModel : ViewModel() {

    private val viewState = MutableLiveData<UploadViewState>()

    fun <T> select(property: UploadViewState.() -> T) = viewState.select(property)

    init {
        viewState.value = UploadViewState.initial()
    }

    fun startUpload() {
        viewModelScope.launch {
            viewState.value = UploadViewState.initial()
            viewState.value = UploadViewState.uploadInProgress(10)
            delay()
            viewState.value = UploadViewState.uploadInProgress(30)
            delay()
            viewState.value = UploadViewState.uploadInProgress(42)
            delay()
            viewState.value = UploadViewState.uploadInProgress(50)
            delay()

            if (Random.nextBoolean()) {
                viewState.value = UploadViewState.uploadFailed()
                return@launch
            }

            viewState.value = UploadViewState.uploadInProgress(70)
            delay()
            viewState.value = UploadViewState.uploadInProgress(90)
            delay()
            viewState.value = UploadViewState.uploadInProgress(94)
            delay()
            viewState.value = UploadViewState.uploadInProgress(99)
            delay()
            viewState.value = UploadViewState.uploadInProgress(100)

            viewState.value = UploadViewState.uploadSuccess()
        }
    }

    private suspend fun delay() {
        delay(Random.nextLong(500L..1200L))
    }

}
