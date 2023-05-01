package learn.atharv.codehelperrefined.Home.cLanguage.Model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import learn.atharv.codehelperrefined.Home.cLanguage.Repository.C_Language_RV_Repository

class C_Language_RVviewmodel : ViewModel() {

    private  val RV_Repository : C_Language_RV_Repository
    private val _All_RV_Items = MutableLiveData<List<RvItems>>()
    val All_RV_Items : LiveData<List<RvItems>> = _All_RV_Items

    init {
        RV_Repository = C_Language_RV_Repository().getInstance()
        RV_Repository.LoadRVTitle(_All_RV_Items)
    }

}