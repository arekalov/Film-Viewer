package com.pro.film_viewer.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.pro.film_viewer.R
import com.pro.film_viewer.retrofit.ConnectionLiveData


class NoInternetFragment : Fragment() {
    private lateinit var connectionLiveData: ConnectionLiveData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finishAffinity()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_no_internet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        connectionLiveData = ConnectionLiveData(requireActivity().application)
        observeConnection()
    }

    private fun observeConnection() {
        connectionLiveData.observe(viewLifecycleOwner){
            isConnected->
            if (isConnected) {
                val controller = findNavController()
                controller.previousBackStackEntry?.destination?.id?.let { controller.navigate(it) }
            }
        }
    }

}