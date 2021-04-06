package com.marikh.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment(R.layout.fragment_search) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val increment = SearchFragmentArgs.fromBundle(requireArguments()).increment
        searchText.text = "$increment"

        search.setOnClickListener {
            navigate(SearchFragmentDirections.actionSearchFragmentSelf(increment + 1))
        }
        (activity as? AppCompatActivity)?.supportActionBar?.apply {
            title = getString(R.string.search)
            setDisplayHomeAsUpEnabled(true)
        }

    }
}