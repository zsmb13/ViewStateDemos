package co.zsmb.viewstatesdemo.upload

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import co.zsmb.viewstatesdemo.R
import co.zsmb.viewstatesdemo.exhaustive
import kotlinx.android.synthetic.main.fragment_upload.*

class UploadFragment : Fragment() {

    companion object {
        private const val ANIMATION_DURATION = 100
    }

    private lateinit var viewModel: UploadViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_upload, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, NewInstanceFactory()).get(UploadViewModel::class.java)

        viewModel.viewState.observe(viewLifecycleOwner, Observer { state ->
            render(state)
        })

        retryUploadButton.setOnClickListener {
            viewModel.startUpload()
        }

        viewModel.startUpload()
    }

    private fun render(viewState: UploadViewState) {
        when (viewState) {
            Initial -> {
                uploadProgressText.isVisible = false
                progressBar.isVisible = false
                uploadDoneIcon.isVisible = false
                uploadStatusText.isVisible = false
                retryUploadButton.isVisible = false
            }
            is UploadInProgress -> {
                uploadProgressText.isVisible = true
                progressBar.isVisible = true
                uploadDoneIcon.isVisible = false
                uploadStatusText.isVisible = false
                retryUploadButton.isVisible = false
                progressBar.setProgressWithAnimation(
                    viewState.percentage.toFloat(),
                    ANIMATION_DURATION
                )
                uploadProgressText.text = "${viewState.percentage}%"
            }
            UploadFailed -> {
                uploadProgressText.isVisible = false
                progressBar.isVisible = false
                uploadDoneIcon.isVisible = false
                uploadStatusText.isVisible = true
                uploadStatusText.text = "Sorry, something went wrong."
                retryUploadButton.isVisible = true
            }
            UploadSuccess -> {
                uploadProgressText.isVisible = false
                progressBar.isVisible = false
                uploadDoneIcon.isVisible = true
                uploadStatusText.isVisible = true
                uploadStatusText.text = "Upload complete!"
                retryUploadButton.isVisible = false
            }
        }.exhaustive
    }

    private fun renderAlt(viewState: UploadViewState) {
        uploadProgressText.isVisible = viewState is UploadInProgress
        progressBar.isVisible = viewState is UploadInProgress
        retryUploadButton.isVisible = viewState is UploadFailed
        uploadDoneIcon.isVisible = viewState is UploadSuccess
        uploadStatusText.isVisible = viewState is UploadFailed || viewState is UploadSuccess

        when (viewState) {
            Initial -> {
                // Empty
            }
            is UploadInProgress -> {
                progressBar.setProgressWithAnimation(
                    viewState.percentage.toFloat(),
                    ANIMATION_DURATION
                )
                uploadProgressText.text = "${viewState.percentage.toInt()}%"
            }
            UploadFailed -> {
                uploadStatusText.text = "Sorry, something went wrong."
            }
            UploadSuccess -> {
                uploadStatusText.text = "Upload complete!"
            }
        }.exhaustive
    }

}
