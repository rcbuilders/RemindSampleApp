package com.remind.sampleapp.sorted_with

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SortedWithExampleViewModel : ViewModel() {

    private val _userScores: MutableLiveData<List<UserScore>?> = MutableLiveData()
    val userScores: LiveData<List<UserScore>?> get() = _userScores

    fun fetchUserScoreList() {
        viewModelScope.launch {
            _userScores.postValue(
                UserScore.fetchList()
                    .sortedWith(compareByDescending(UserScore::score)
                        .thenBy(UserScore::name)

                            // 추가 테스트 코드
//                        .sortedWith(compareBy { it.name.length }
//                        .thenComparator { data1, data2 ->
//                            OrderKoreaFirst.compare(data1.name, data2.name)
//                        }
                    )
            )
        }
    }
}