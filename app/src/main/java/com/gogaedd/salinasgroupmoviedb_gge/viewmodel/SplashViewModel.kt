package com.gogaedd.salinasgroupmoviedb_gge.viewmodel

import android.app.Application
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import com.gogaedd.salinasgroupmoviedb_gge.repository.SplashRepository

class SplashViewModel(application: Application): AndroidViewModel(application) {
    private val repository = SplashRepository(application)

    private val lvdStateload =  repository.getLvdStateload()
    private val lvdMessageError = repository.getLvdMessageError()

    fun getLvdStateload()= lvdStateload
    fun getLvdMessageError()= lvdMessageError



    fun startLoadCat(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            repository.startLoadCat()
        }else{
            repository.setMessageError("version android muy baja")
        }
    }




}