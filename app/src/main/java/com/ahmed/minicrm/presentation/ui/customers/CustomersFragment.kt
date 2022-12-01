package com.ahmed.minicrm.presentation.ui.customers

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.app.ActivityCompat
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.ahmed.minicrm.R
import com.ahmed.minicrm.databinding.FragmentCustomersBinding
import com.ahmed.minicrm.presentation.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CustomersFragment : Fragment() {

    private var _binding: FragmentCustomersBinding? = null
    private val binding get() = _binding!!
    private val adapter: CustomerAdapter by lazy { CustomerAdapter() }
    private lateinit var viewModel: CustomersViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCustomersBinding.inflate(inflater, container, false)

        viewModel = (activity as MainActivity).customerVieModel
        onBackPressed()

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fab()

        initRecycler()

        if (ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.CALL_PHONE),
                1
            )
            return
        }
    }

    private fun fab() {

        binding.fab.setOnClickListener {
            val action = CustomersFragmentDirections.actionFirstFragmentToSecondFragment(0)
            findNavController().navigate(action)
        }
    }


    private fun initRecycler() {
        binding.customerRecycler.adapter = adapter
        viewModel.getAllCustomers().observe(viewLifecycleOwner) {
            adapter.differ.submitList(it)
        }
    }

    private fun onBackPressed() {

        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (Navigation
                        .findNavController(requireActivity(), R.id.nav_host_fragment_content_main)
                        .currentDestination?.id == R.id.FirstFragment
                ) {

                    requireActivity().finish()
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            requireActivity(),
            onBackPressedCallback
        )

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}