package com.demo.bookmarklocation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.demo.bookmarklocation.R
import com.demo.bookmarklocation.viewModel.ShowWeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowWeatherFragment : Fragment() {

    var slectedCity : String = "Nalgonda"
    companion object {
        const val SENDING_NAME_ADDRESS = "Nalgonda"
        fun newInstance(city : String) : Fragment{
            val args = Bundle()
            args.putString(SENDING_NAME_ADDRESS , city)
            val fragment = ShowWeatherFragment()
            fragment.arguments = args
            return fragment
        }

    }

    private lateinit var viewModel: ShowWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.show_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ShowWeatherViewModel::class.java)
        var cityName = arguments?.getString(SENDING_NAME_ADDRESS)
        viewModel.getWeatherWithLocationWithCity(cityName.toString())
        var cityNameText = view?.findViewById<TextView>(R.id.cityName)
        var temp_textView = view?.findViewById<TextView>(R.id.temp_textView)
        viewModel.observeWeatherData().observe(viewLifecycleOwner,{
            Log.i("anand","${it.name}")
            if(it.main.temp !=null) {
                temp_textView?.text = it.main.temp.toString()
                cityNameText?.text = cityName
            }


        })
    }

}