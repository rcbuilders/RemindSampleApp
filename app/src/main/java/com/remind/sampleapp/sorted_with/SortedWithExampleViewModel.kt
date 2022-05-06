package com.remind.sampleapp.sorted_with

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class SortedWithExampleViewModel : ViewModel(), DefaultLifecycleObserver {

    private val _userScores: MutableLiveData<List<UserScore>?> = MutableLiveData()
    val userScores: LiveData<List<UserScore>?> get() = _userScores

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        viewModelScope.launch {
            fetchUserScoreList()
        }
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
    }

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