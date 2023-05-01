package learn.atharv.codehelperrefined.Home.cLanguage.Model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import learn.atharv.codehelperrefined.Home.cLanguage.Repository.C_Language_Question_Repository

class C_Language_QuestionViewmodel: ViewModel() {
    private  val Question_Repository : C_Language_Question_Repository
    private val _All_Questions = MutableLiveData<List<Question>>()
    val All_Questions : LiveData<List<Question>> = _All_Questions

    init {
        Question_Repository = C_Language_Question_Repository().getInstance()
        Question_Repository.LoadQuestion(_All_Questions)
    }
}