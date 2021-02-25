package com.demo.bookmarklocation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.demo.bookmarklocation.R
import com.demo.bookmarklocation.databinding.ActivityMapsBinding
import com.demo.bookmarklocation.databinding.FragmentSecondBinding
import com.demo.bookmarklocation.viewModel.MapsViewModel
import com.google.android.gms.maps.GoogleMap

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */


class SecondFragment : Fragment() {
    private lateinit var mMap: GoogleMap
    //private lateinit var binding: ActivityMapsBinding
    val locationListViewModel: MapsViewModel by viewModels()
    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}