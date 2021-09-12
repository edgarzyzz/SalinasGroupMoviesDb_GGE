package com.gogaedd.salinasgroupmoviedb_gge.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.gogaedd.salinasgroupmoviedb_gge.databinding.FragmentDetailMovieBinding

class DetailMovieFragment : Fragment() {
    lateinit var mBinding:FragmentDetailMovieBinding
//    lateinit var viewModel: DetailMovieViewModel
    val args: DetailMovieFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = FragmentDetailMovieBinding.inflate(inflater, container, false)
//        viewModel = ViewModelProvider(this).get(DetailMovieViewModel::class.java)
        mBinding.lifecycleOwner = viewLifecycleOwner


        return mBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.movie  = args.movie
    }


}