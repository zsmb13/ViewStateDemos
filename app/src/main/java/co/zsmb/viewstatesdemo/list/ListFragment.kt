package co.zsmb.viewstatesdemo.list

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
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {

    private lateinit var viewModel: ListViewModel
    private lateinit var wordAdapter: WordAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, NewInstanceFactory()).get(ListViewModel::class.java)

        viewModel.viewState.observe(viewLifecycleOwner, Observer { state ->
            render(state)
        })

        wordAdapter = WordAdapter()
        wordList.adapter = wordAdapter

        loadMoreButton.setOnClickListener {
            viewModel.loadMore()
        }

        viewModel.loadMore()
    }

    private fun render(viewState: ListViewState) {
        progressBar.isVisible = viewState.isLoading
        wordAdapter.submitList(viewState.items)
    }

}
