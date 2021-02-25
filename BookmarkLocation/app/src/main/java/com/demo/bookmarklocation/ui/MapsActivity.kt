package com.demo.bookmarklocation.ui

import android.R.attr.apiKey
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.demo.bookmarklocation.databinding.ActivityMapsBinding
import com.demo.bookmarklocation.model.LocationModel
import com.demo.bookmarklocation.viewModel.MapsViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import androidx.activity.viewModels
import com.demo.bookmarklocation.R

@AndroidEntryPoint
class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    val locationListViewModel: MapsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // Initialize Places.
        Places.initialize(applicationContext, apiKey.toString())



    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        mMap.setOnMapClickListener {
            showAlertDialog(it)
        }


    }

    private fun showAlertDialog(it: LatLng) {
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this)
        alertDialog.setTitle("AlertDialog")
        alertDialog.setMessage("Do you wanna close this Dialog?")
        alertDialog.setPositiveButton(
            "yes"
        ) { _, _ ->
            Toast.makeText(this, "Alert dialog closed.", Toast.LENGTH_LONG).show()

            val gcd = Geocoder(this, Locale.getDefault())
            val addresses = gcd.getFromLocation(it.latitude, it.longitude, 1)
            if (addresses.size > 0) {
                Log.i(
                    "Anand",
                    "City is : ${addresses[0].subAdminArea} and pincode is : ${addresses[0].postalCode}"
                )

                locationListViewModel.addLocation(LocationModel(it.latitude.toString(),it.longitude.toString(),addresses[0].locality,addresses[0].postalCode.toInt()))
            } else {
                // do your stuff
                locationListViewModel.addLocation(LocationModel(it.latitude.toString(),it.longitude.toString(),addresses[0].locality,addresses[0].postalCode.toInt()))
            }


        }
        alertDialog.setNegativeButton(
            "No"
        ) { _, _ -> }
        val alert: AlertDialog = alertDialog.create()
        alert.setCanceledOnTouchOutside(false)
        alert.show()
    }
}