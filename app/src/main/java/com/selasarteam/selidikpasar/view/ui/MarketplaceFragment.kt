package com.selasarteam.selidikpasar.view.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.selasarteam.selidikpasar.BuildConfig
import com.selasarteam.selidikpasar.R
import com.selasarteam.selidikpasar.databinding.FragmentMarketplaceBinding
import com.selasarteam.selidikpasar.view.adapter.ListMarketAdapter
import com.selasarteam.selidikpasar.view.viewmodel.MainViewModel
import com.selasarteam.selidikpasar.view.viewmodel.ViewModelFactory

class MarketplaceFragment : Fragment() {

    private var _binding: FragmentMarketplaceBinding? = null
    private val binding get() = _binding!!

    private var factory: ViewModelFactory? = null
    private val viewModel: MainViewModel by viewModels { factory!! }

    private var locationPermissionGranted = false
    private val defaultLocation = LatLng(-6.241586, 106.992416)

    private var gMap: GoogleMap? = null
    private var cameraPosition: CameraPosition? = null
    private var lastKnownLocation: Location? = null

    private lateinit var marketAdapter: ListMarketAdapter
    private lateinit var placesClient: PlacesClient
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    private var token = ""
    private var auth: FirebaseAuth? = null
    private var currentUser: FirebaseUser? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        if (savedInstanceState != null) {
            lastKnownLocation = savedInstanceState.getParcelable(KEY_LOCATION)
            cameraPosition = savedInstanceState.getParcelable(KEY_CAMERA_POSITION)
        }

        _binding = FragmentMarketplaceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupPermission()
        setupViewModel()
        setupAction()
        checkUserStatus()
        setupAdapter()
        setupMaps()
        setupList()
        getLocationPermission()
        updateLocationUI()
        getDeviceLocation()
    }

    private fun setupPermission() {
        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireActivity())
    }

    private fun setupViewModel() {
        factory = ViewModelFactory.getInstance(requireActivity())
    }

    private fun setupAction() {
        binding.apply {
            btnRegisterHere.setOnClickListener {
                startActivity(Intent(requireActivity(), RegisterActivity::class.java))
            }
        }
    }

    private fun checkUserStatus() {
        auth = Firebase.auth
        currentUser = auth?.currentUser
        viewModel.getSession().observe(viewLifecycleOwner) {
            token = it.token
            if (!it.isLogin && currentUser == null) {
                setupView(false)
            } else {
                setupView(true)
                getPriceList(token)
            }
        }
    }


    private fun getPriceList(token: String) {
        viewModel.getMarketList(token)
    }

    private fun setupView(state: Boolean) {
        if (state) {
            binding.apply {
                whitespace.visibility = View.GONE
                tvNotLogin.visibility = View.GONE
                btnRegisterHere.visibility = View.GONE

                rvMarketplace.visibility = View.VISIBLE
            }
        } else {
            binding.apply {
                whitespace.visibility = View.VISIBLE
                tvNotLogin.visibility = View.VISIBLE
                btnRegisterHere.visibility = View.VISIBLE

                rvMarketplace.visibility = View.GONE
            }
        }
    }

    private fun setupAdapter() {
        marketAdapter = ListMarketAdapter()
        binding.rvMarketplace.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = marketAdapter
            setHasFixedSize(true)
        }
    }

    private fun setupMaps() {
        activity?.applicationContext?.let { Places.initialize(it, BuildConfig.GCP_KEY) }
        placesClient = Places.createClient(requireActivity())

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    private val callback = OnMapReadyCallback { googleMap ->
        gMap = googleMap
        gMap?.uiSettings?.apply {
            isRotateGesturesEnabled = true
            isZoomControlsEnabled = true
            isIndoorLevelPickerEnabled = true
            isCompassEnabled = true
            isMapToolbarEnabled = true
        }
        gMap!!.animateCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, DEFAULT_ZOOM))
    }

    private fun showLoading() {
        viewModel.showLoading.observe(viewLifecycleOwner) {
            binding.pbMarket.visibility = if (it) View.VISIBLE else View.GONE
        }
    }

    private fun setupList() {
        viewModel.listMarket.observe(viewLifecycleOwner) {
            showLoading()
            marketAdapter.submitList(it.market)
            it?.market?.forEach { market ->
                gMap?.addMarker(
                    MarkerOptions()
                        .position(LatLng(market.lat, market.long))
                        .title(context?.getString(R.string.market) + market.name)
                        .snippet(context?.getString(R.string.address) + market.address)
                )
            }
        }
    }

    private fun getLocationPermission() {
        if (activity?.let {
                ContextCompat.checkSelfPermission(
                    it.applicationContext,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            }
            == PackageManager.PERMISSION_GRANTED) {
            locationPermissionGranted = true
        } else {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                locationPermissionGranted = true
                Log.e(TAG, "onActivityResult: PERMISSION GRANTED")
            } else {
                getLocationPermission()
                Log.e(TAG, "onActivityResult: PERMISSION DENIED")
            }
            updateLocationUI()
        }

    private fun updateLocationUI() {
        if (gMap == null) {
            return
        }
        try {
            if (locationPermissionGranted) {
                gMap?.isMyLocationEnabled = true
                gMap?.uiSettings?.isMyLocationButtonEnabled = true
            } else {
                gMap?.isMyLocationEnabled = false
                gMap?.uiSettings?.isMyLocationButtonEnabled = false
                lastKnownLocation = null
                getLocationPermission()
            }
        } catch (e: SecurityException) {
            Log.e("Exception: %s", e.message, e)
        }
    }

    private fun getDeviceLocation() {
        try {
            if (locationPermissionGranted) {
                val locationResult = fusedLocationProviderClient.lastLocation
                locationResult.addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        lastKnownLocation = task.result
                        if (lastKnownLocation != null) {
                            gMap?.moveCamera(
                                CameraUpdateFactory.newLatLngZoom(
                                    LatLng(
                                        lastKnownLocation!!.latitude,
                                        lastKnownLocation!!.longitude
                                    ), DEFAULT_ZOOM
                                )
                            )
                        }
                    } else {
                        Log.d(TAG, "Current location is null. Using defaults.")
                        Log.e(TAG, "Exception: %s", task.exception)
                        gMap?.moveCamera(
                            CameraUpdateFactory
                                .newLatLngZoom(defaultLocation, DEFAULT_ZOOM)
                        )
                        gMap?.uiSettings?.isMyLocationButtonEnabled = false
                    }
                }
            }
        } catch (e: SecurityException) {
            Log.e("Exception: %s", e.message, e)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private val TAG = MarketplaceFragment::class.java.simpleName

        const val DEFAULT_ZOOM = 15f

        private const val KEY_CAMERA_POSITION = "camera_position"
        private const val KEY_LOCATION = "location"
    }
}