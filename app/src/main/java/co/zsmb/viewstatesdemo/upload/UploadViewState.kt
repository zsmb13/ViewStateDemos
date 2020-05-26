package co.zsmb.viewstatesdemo.upload

data class UploadViewState(
    val isProgressVisible: Boolean = false,
    val progressPercentage: Int = 0,
    val isUploadDoneVisible: Boolean = false,
    val isStatusTextVisible: Boolean = false,
    val statusText: String = "",
    val isRetryVisible: Boolean = false
) {

    companion object {
        fun initial() = UploadViewState()

        fun uploadInProgress(percentage: Int) = initial().copy(
            isProgressVisible = true,
            progressPercentage = percentage
        )

        fun uploadFailed() = initial().copy(
            isStatusTextVisible = true,
            statusText = "Sorry, something went wrong.",
            isRetryVisible = true
        )

        fun uploadSuccess() = initial().copy(
            isUploadDoneVisible = true,
            isStatusTextVisible = true,
            statusText = "Upload complete!"
        )
    }
}
