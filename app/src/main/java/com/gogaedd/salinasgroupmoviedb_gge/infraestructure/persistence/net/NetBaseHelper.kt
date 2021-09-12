package com.gogaedd.salinasgroupmoviedb_gge.infraestructure.persistence.net

open class NetBaseHelper {
    protected var apiService : ApiService
    protected var mListener: NetListener?=null
    init {
        val retrofit = ApiClient.getRetrofit()
        apiService = retrofit.create(ApiService::class.java)
    }


    public fun setListener(listener:NetListener){
        mListener = listener
    }
}