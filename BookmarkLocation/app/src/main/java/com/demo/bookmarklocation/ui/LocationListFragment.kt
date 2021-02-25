package com.demo.bookmarklocation.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demo.bookmarklocation.R
import com.demo.bookmarklocation.adapter.LocationAdapter
import com.demo.bookmarklocation.interfaces.ItemClickListener
import com.demo.bookmarklocation.model.LocationModel
import com.demo.bookmarklocation.viewModel.LocationListViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationListFragment : Fragment(), ItemClickListener {

    companion object {
        fun newInstance() = LocationListFragment()
    }

    private lateinit var mLoactionAdapter: LocationAdapter
    private lateinit var viewModel: LocationListViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var fab: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.location_list_fragment, container, false)
        recyclerView = view.findViewById(R.id.location_recycler)
        fab = view.findViewById(R.id.fab)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LocationListViewModel::class.java)
        // TODO: Use the ViewModel#
        viewModel.getLocationList()

        viewModel.observeMovieList().observe(viewLifecycleOwner, {
            if (it != null) {
                initRecyclerView(it)
            }
        })
        fab.setOnClickListener { view ->
            var fag =
                goToFragment(MapsFragment.newInstance())
        }


    }

    private fun goToFragment(fragment: Fragment?) {

        //val someFragment = LocationListFragment.newInstance();
        val transaction = activity?.supportFragmentManager?.beginTransaction();
        transaction?.replace(
            R.id.framlayout,
            fragment!!
        ) // give your fragment container id in first parameter
        transaction?.addToBackStack(null)
        transaction?.commit();
    }

    private fun initRecyclerView(movie: List<LocationModel>) {

        mLoactionAdapter = LocationAdapter(activity, movie, this)

        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = mLoactionAdapter

    }


    override fun onItemClick(locationList: LocationModel) {
        Toast.makeText(activity, "${locationList.city}", Toast.LENGTH_SHORT).show()
        goToShowWeatherFragment(locationList.city.toString())
        //viewModel.getWeatherWithLocationWithCity(locationList.city.toString())//viewModel.getWeatherWithLocationList(latLng = LatLng(
//            locationList.latValue?.toDouble()!!,
//            locationList.lugValue?.toDouble()!!
//        ))
    }

    private fun goToShowWeatherFragment(city: String) {

        val someFragment = ShowWeatherFragment.newInstance(city);
        val transaction = activity?.supportFragmentManager?.beginTransaction();
        transaction?.replace(
            R.id.framlayout,
            someFragment
        ) // give your fragment container id in first parameter
        transaction?.addToBackStack(null)
        transaction?.commit();
    }

}