package com.remind.sampleapp.binding_adapter_example

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

data class User(
    val id: String?,
    val name: String?,
    val photoUrl: String?,
    val etc: HashMap<String, Int>?
)

class BindingAdapterExampleViewModel : ViewModel() {

    private val _user: MutableLiveData<User> = MutableLiveData()
    val user: LiveData<User> get() = _user

    private val names = listOf("Maia", "Kavi", "Evelynn", "Malique", "Kingstyn", "Mahdi")

    fun fetchUser(userId: String?) {
        viewModelScope.launch {
            delay(1000)

            _user.postValue(
                User(
                    id = userId,
                    name = names[Random.nextInt(0, names.size-1)],
                    photoUrl = "https://i.pravatar.cc/300?u=$userId",
                    etc = hashMapOf("score" to Random.nextInt(0, 100))
                )
            )
        }
    }

}