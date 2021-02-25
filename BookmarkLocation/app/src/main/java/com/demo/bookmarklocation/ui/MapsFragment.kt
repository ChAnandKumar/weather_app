package com.demo.bookmarklocation.ui

import android.location.Geocoder
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import com.demo.bookmarklocation.R
import com.demo.bookmarklocation.model.LocationModel
import com.demo.bookmarklocation.viewModel.MapsViewModel

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MapsFragment : Fragment() {
    private val locationListViewModel: MapsViewModel by viewModels()
    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        val hyderabad = LatLng(17.387140, 78.491684)
        //googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(hyderabad))
        googleMap.setMinZoomPreference(6.0f)
        //googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hyderabad.center, 10f))
        googleMap.setOnMapClickListener {
            showAlertDialog(it)
        }
    }

    companion object {
        fun newInstance() = MapsFragment()
    }

    private fun showAlertDialog(it: LatLng) {
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(requireActivity())
        alertDialog.setTitle("AlertDialog")
        alertDialog.setMessage("Do you wanna close this Dialog?")
        alertDialog.setPositiveButton(
            "yes"
        ) { _, _ ->
            Toast.makeText(activity, "Alert dialog closed.", Toast.LENGTH_LONG).show()

            val gcd = Geocoder(activity, Locale.getDefault())
            val addresses = gcd.getFromLocation(it.latitude, it.longitude, 1)
            if (addresses.size > 0) {
                Log.i(
                    "Anand",
                    "City is : ${addresses[0].countryCode} and pincode is : ${addresses[0].postalCode}"
                )
                if(addresses[0].subAdminArea != null && addresses[0].postalCode != null)
                    locationListViewModel.addLocation(LocationModel(it.latitude.toString(),it.longitude.toString(),addresses[0].subAdminArea,addresses[0].postalCode.toInt()))
            } else {
                // do your stuff
                Toast.makeText(activity,"Please select different address.",Toast.LENGTH_SHORT).show()
                //locationListViewModel.addLocation(LocationModel(it.latitude.toString(),it.longitude.toString(),addresses[0].locality,addresses[0].postalCode.toInt()))
            }


        }
        alertDialog.setNegativeButton(
            "No"
        ) { _, _ -> }
        val alert: AlertDialog = alertDialog.create()
        alert.setCanceledOnTouchOutside(false)
        alert.show()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
        locationListViewModel.observeInsertMessage().observe(viewLifecycleOwner, {
            Toast.makeText(activity,"$it",Toast.LENGTH_SHORT).show()
            addFragmentToActivity(LocationListFragment.newInstance())
        })
    }

    private fun addFragmentToActivity(fragment: Fragment?){

        if (fragment == null) return
        val fm = activity?.supportFragmentManager
        val tr = fm?.beginTransaction()
        tr?.replace(R.id.framlayout, fragment)
        tr?.commitAllowingStateLoss()

    }
}