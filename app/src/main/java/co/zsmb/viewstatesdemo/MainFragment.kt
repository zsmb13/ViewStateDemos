package co.zsmb.viewstatesdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        uploadButton.setOnClickListener {
            findNavController().navigate(R.id.uploadFragment)
        }
        profileButton1.setOnClickListener {
            findNavController().navigate(R.id.profileFragment, bundleOf("shouldError" to false))
        }
        profileButton2.setOnClickListener {
            findNavController().navigate(R.id.profileFragment, bundleOf("shouldError" to true))
        }
        listButton.setOnClickListener {
            findNavController().navigate(R.id.listFragment)
        }
    }

}
