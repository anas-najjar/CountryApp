package com.sv.sampleapp.ui.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso

import com.sv.sampleapp.R
import com.sv.sampleapp.model.CountryDataStore
import com.sv.sampleapp.ui.activities.MainActivity.Companion.EXTRA_SELECT_COUNTRY_POSITION
import com.sv.sampleapp.util.AppConstants
import kotlinx.android.synthetic.main.fragment_country_info.*

class CountryInfoFragment : Fragment() {

    private var countrySelectedPosition: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_country_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadIntent()
        loadCountryData()
    }

    private fun loadIntent() {
        if (arguments != null && arguments!!.containsKey(EXTRA_SELECT_COUNTRY_POSITION)) {
            countrySelectedPosition = arguments!!.getInt(EXTRA_SELECT_COUNTRY_POSITION)
        }
    }

    private fun loadCountryData() {
        if (countrySelectedPosition > -1) {
            val countryItem = CountryDataStore.getCountryItem(countrySelectedPosition)
            if (countryItem != null) {
                nameValue.text = countryItem.name
                regionValue.text = countryItem.region
                capitalValue.text = countryItem.capital
                populationValue.text = "${countryItem.population}"

                val flagUrl = AppConstants.getFlagUrl(countryItem.alphaCode2 ?: "")
                Picasso.get()
                    .load(flagUrl)
                    .error(R.drawable.ic_launcher_background)
                    .into(imageView)
            }
        } else {
            // show error that country data not available
        }
    }


}
