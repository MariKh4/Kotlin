package com.marikh.fragments

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_user.*

class UserFragment : Fragment(R.layout.fragment_user) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val increment = UserFragmentArgs.fromBundle(requireArguments()).increment
        userText.text = "$increment"

        user.setOnClickListener {
            navigate(UserFragmentDirections.actionUserFragmentSelf(increment + 1))
        }
        (activity as? AppCompatActivity)?.supportActionBar?.apply {
            title = getString(R.string.user)
            setDisplayHomeAsUpEnabled(true)
        }
    }
}