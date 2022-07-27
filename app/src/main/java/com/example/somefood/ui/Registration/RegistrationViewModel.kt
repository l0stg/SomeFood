package com.example.somefood.ui.Registration

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.somefood.data.model.UserModel
import com.example.somefood.data.room.Repository
import com.example.somefood.ui.Screens
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class RegistrationViewModel(
    private val router: Router,
    private val myRepository: Repository
): ViewModel() {

    private var job: Job? = null
    val statusRegistration = MutableStateFlow(false)

    fun addUser(email: String, password: String){
        job = viewModelScope.launch {
            myRepository.checkRegistration(email).collect{
                if (it.isNullOrEmpty()){
                    val newUser = UserModel(eMail = email, password = password)
                    myRepository.addUser(newUser)
                    router.navigateTo(Screens().openSignIn())
                    job?.cancel()
                }else statusRegistration.value = true
            }

        }
    }
}