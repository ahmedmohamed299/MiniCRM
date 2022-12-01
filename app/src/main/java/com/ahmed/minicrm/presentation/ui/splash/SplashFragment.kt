package com.ahmed.minicrm.presentation.ui.splash

import android.os.Build
import android.os.Bundle
import android.view.*
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ahmed.minicrm.R
import com.ahmed.minicrm.databinding.FragmentSplashBinding


class SplashFragment : Fragment() {
    private var logoAnim: Animation? = null

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        anim()
        fullScreen()

        return binding.root
    }

    private fun fullScreen() {
        (activity as AppCompatActivity).supportActionBar?.hide()
//        (activity as MainActivity).supportActionBar?.hide()

        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            requireActivity().window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            requireActivity().window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

    }

    private fun anim() {

        logoAnim = AnimationUtils.loadAnimation(requireActivity(), R.anim.logo_anim)
        binding.splashLogo.animation = logoAnim
        binding.splashLogo.animation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(p0: Animation?) {
            }

            override fun onAnimationEnd(p0: Animation?) {
                findNavController().navigate(R.id.action_splashFragment_to_FirstFragment)
            }

            override fun onAnimationRepeat(p0: Animation?) {
            }

        })
    }





    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

        (requireActivity() as AppCompatActivity).supportActionBar!!.show()

        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            requireActivity().window.insetsController?.show(WindowInsets.Type.statusBars())
        } else {
            requireActivity().window.setFlags(
                WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN
            )
        }
    }
}