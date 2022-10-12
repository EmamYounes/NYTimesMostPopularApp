package com.example.nytimesmostpopularapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.nytimesmostpopularapp.R
import com.example.nytimesmostpopularapp.utils.Coroutines
import com.example.swensonheweatherapp.view_model.MyViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.details_fragment.view.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class DetailsFragment : Fragment(), KodeinAware {


    override val kodein by kodein()
    private val viewModel: MyViewModel by instance()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView(view)
    }

    private fun bindView(view: View) = Coroutines.main {

        val model = viewModel.itemSelected.value
        view.title.text = model?.section
        view.sub_title.text = model?.title
        view.date.text = model?.publishedDate
        view.source.text = model?.source
        view.details.text = model?.jsonMemberAbstract

        val url =
            model?.media?.filter { it?.type == "image" }?.map { it?.mediaMetadata?.get(2)?.url }
                ?.first()
        Picasso.get()
            .load(url)
            .error(com.google.android.material.R.drawable.mtrl_ic_error)
            .into(view.image_view)
    }
}