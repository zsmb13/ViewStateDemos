package co.zsmb.viewstatesdemo.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import androidx.navigation.fragment.navArgs
import co.zsmb.viewstatesdemo.R
import co.zsmb.viewstatesdemo.exhaustive
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    private object Flipper {
        const val LOADING = 0
        const val CONTENT = 1
        const val ERROR = 2
    }

    private lateinit var viewModel: ProfileViewModel

    private val args: ProfileFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, NewInstanceFactory()).get(ProfileViewModel::class.java)

        viewModel.viewState.observe(viewLifecycleOwner, Observer { state ->
            render(state)
        })

        viewModel.load(args.shouldError)
    }

    private fun render(viewState: ProfileViewState) {
        when (viewState) {
            Loading -> {
                viewFlipper.displayedChild = Flipper.LOADING
            }
            Error -> {
                viewFlipper.displayedChild = Flipper.ERROR
            }
            is ProfileLoaded -> {
                viewFlipper.displayedChild = Flipper.CONTENT
                profileNameText.text = viewState.name
                profileEmailText.text = viewState.email
            }
        }.exhaustive
    }

}
