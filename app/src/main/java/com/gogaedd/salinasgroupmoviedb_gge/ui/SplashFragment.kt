package com.gogaedd.salinasgroupmoviedb_gge.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.gogaedd.salinasgroupmoviedb_gge.R
import com.gogaedd.salinasgroupmoviedb_gge.databinding.FragmentSplashBinding
import com.gogaedd.salinasgroupmoviedb_gge.infraestructure.ConstantsApp
import com.gogaedd.salinasgroupmoviedb_gge.viewmodel.SplashViewModel

class SplashFragment : Fragment() {
    lateinit var viewModel: SplashViewModel
    lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(SplashViewModel::class.java)
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.getLvdStateload().observe(viewLifecycleOwner, observerStateload)
        viewModel.startLoadCat()


    }
    val observerStateload = Observer<Int>{
        if (it == ConstantsApp.STATE_LOAD.LOAD_OK){
            val action = SplashFragmentDirections.actionGoToHomeFragment()
            findNavController().navigate(action)
        }
    }
}