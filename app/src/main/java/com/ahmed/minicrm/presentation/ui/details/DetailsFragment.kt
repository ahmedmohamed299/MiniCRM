package com.ahmed.minicrm.presentation.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ahmed.minicrm.R
import com.ahmed.minicrm.databinding.FragmentDetailsBinding
import com.ahmed.minicrm.presentation.ui.MainActivity
import com.ahmed.minicrm.presentation.ui.customers.CustomersViewModel
import com.ahmed.minicrm.presentation.ui.util.EventObserver
import com.ahmed.minicrm.presentation.ui.util.setupSnackBar
import com.google.android.material.snackbar.Snackbar

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null

    private val args: DetailsFragmentArgs by navArgs()
    private val binding get() = _binding!!
    private lateinit var viewModel: CustomersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDetailsBinding.inflate(inflater, container, false)

        viewModel = (activity as MainActivity).customerVieModel
        viewModel.start(args.id)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSnackBar()
        setupNavigation()
        onClick(args.id)

    }

    private fun setupSnackBar() {

        view?.setupSnackBar(viewLifecycleOwner, viewModel.snackBarText, Snackbar.LENGTH_SHORT)

    }

    private fun setupNavigation() {
        viewModel.taskUpdatedEvent.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)

        })

    }

    private fun onClick(id:Int){
        binding.done.setOnClickListener {

            if (id!=0){
                viewModel.updateCustomer(args.id)
            }else{
                viewModel.insertCustomer()

            }
        }
        if (id!=0){
            binding.delete.visibility=View.VISIBLE
        }else{
            binding.delete.visibility=View.GONE


        }

        binding.delete.setOnClickListener {
            viewModel.deleteCustomerById(id)


        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}