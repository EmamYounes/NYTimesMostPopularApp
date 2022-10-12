package com.example.nytimesmostpopularapp.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nytimesmostpopularapp.R
import com.example.nytimesmostpopularapp.adapter.MostViewAdapter
import com.example.nytimesmostpopularapp.callback.ItemSelect
import com.example.nytimesmostpopularapp.data.network.responses.ResultsItem
import com.example.nytimesmostpopularapp.ui.LoadingDialog
import com.example.nytimesmostpopularapp.utils.Coroutines
import com.example.swensonheweatherapp.view_model.MyViewModel
import com.example.swensonheweatherapp.view_model.MyViewModelFactory
import kotlinx.android.synthetic.main.home_fragment.view.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class HomeFragment : Fragment(), KodeinAware, ItemSelect {


    override val kodein by kodein()

    private var dialog: LoadingDialog? = null
    private val factory: MyViewModelFactory by instance()

    private val viewModel: MyViewModel by instance()


    lateinit var mostViewAdapter: MostViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewModel = ViewModelProviders.of(this, factory)[MyViewModel::class.java]
        dialog = LoadingDialog(requireContext())
        bindView(view)
    }

    private fun bindView(view: View) = Coroutines.main {
        initAdapter(view)
        callMostPopularApi()
        manageSuccessResponse()
        manageFailResponse()
    }

    private suspend fun callMostPopularApi() {
        showLoading()
        viewModel.callMostPopularApi()
    }

    private fun manageSuccessResponse() {
        viewModel.mostPopularApiResponse.observe(viewLifecycleOwner) {
            dismissLoading()
            it?.results.let { it1 -> mostViewAdapter.addList(it1) }
        }
    }

    private fun manageFailResponse() {
        viewModel.errorException.observe(viewLifecycleOwner) {
            dismissLoading()
            showErrorDialog(it)
        }
    }


    private fun showLoading() {
        dialog?.show()
    }

    private fun dismissLoading() {
        dialog?.dismiss()
    }


    private fun initAdapter(view: View) {
        mostViewAdapter = MostViewAdapter(mutableListOf())
        mostViewAdapter.callbacks = this
        val linearLayoutManager = LinearLayoutManager(requireContext())
        view.most_view_list.layoutManager = linearLayoutManager
        view.most_view_list.adapter = mostViewAdapter
    }

    private fun showErrorDialog(it: String?) {
        AlertDialog.Builder(requireContext())
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setTitle(R.string.error)
            .setMessage(it)
            .setPositiveButton(
                R.string.dismiss
            ) { dialogInterface, _ ->
                dialogInterface.dismiss()
            }
            .show()
    }

    override fun onItemSelected(itemModel: ResultsItem) {
        viewModel.itemSelected.postValue(itemModel)
        activity?.findNavController(R.id.nav_host_fragment)?.navigate(R.id.detailsFragment)
    }
}