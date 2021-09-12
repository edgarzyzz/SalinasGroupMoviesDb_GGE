package com.gogaedd.salinasgroupmoviedb_gge.infraestructure.persistence.net

interface NetListener {
    fun onResultOk(any:Any, code: Int)
    fun onResultError(meesageError:String, code: Int)

}