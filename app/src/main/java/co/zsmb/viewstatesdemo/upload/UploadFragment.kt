package co.zsmb.viewstatesdemo.upload

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import androidx.lifecycle.observe
import co.zsmb.viewstatesdemo.R
import co.zsmb.viewstatesdemo.setVisible
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

        viewModel.observeState()

        retryUploadButton.setOnClickListener {
            viewModel.startUpload()
        }

        viewModel.startUpload()
    }

    private fun UploadViewModel.observeState() {
        select { isProgressVisible }.observe(viewLifecycleOwner) {
            progressBar.isVisible = it
            uploadProgressText.isVisible = it
        }

        select { progressPercentage }.observe(viewLifecycleOwner) {
            progressBar.setProgressWithAnimation(it.toFloat(), ANIMATION_DURATION)
            uploadProgressText.text = "${it}%"
        }

        select { isUploadDoneVisible }.observe(viewLifecycleOwner, uploadDoneIcon::setVisible)
        select { isStatusTextVisible }.observe(viewLifecycleOwner, uploadStatusText::setVisible)
        select { statusText }.observe(viewLifecycleOwner, uploadStatusText::setText)
        select { isRetryVisible }.observe(viewLifecycleOwner, retryUploadButton::setVisible)
    }
}
