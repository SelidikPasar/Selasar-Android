package com.selasarteam.selidikpasar.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.selasarteam.selidikpasar.R
import com.selasarteam.selidikpasar.databinding.FragmentMarketplaceBinding
import com.selasarteam.selidikpasar.view.viewmodel.ViewModelFactory


class MarketplaceFragment : Fragment() {

    private var _binding: FragmentMarketplaceBinding? = null
    private val binding get() = _binding!!

    private var factory: ViewModelFactory? = null
    private var gMap: GoogleMap? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMarketplaceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupMaps()
        setupList()
    }

    private fun setupViewModel() {
        factory = ViewModelFactory.getInstance(requireActivity())
    }

    private fun setupMaps() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    private val callback = OnMapReadyCallback { googleMap ->
        gMap = googleMap
        gMap!!.uiSettings.apply {
            isRotateGesturesEnabled = true
            isZoomControlsEnabled = true
            isIndoorLevelPickerEnabled = true
            isCompassEnabled = true
            isMapToolbarEnabled = true
        }

        val bekasi = LatLng(-6.241586, 106.992416)
        gMap!!.animateCamera(CameraUpdateFactory.newLatLngZoom(bekasi, 15f))
    }

    private fun setupList() {
//        viewModel.list.observe(requireActivity()) {
//            it?.listStory?.forEach { list ->
//                gMap!!.addMarker(
//                    MarkerOptions()
//                        .position(LatLng(list.lat, list.lon))
//                        .title("Toko : ${list.name}")
//                        .snippet("ID : ${list.id}")
//                )
//            }
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}