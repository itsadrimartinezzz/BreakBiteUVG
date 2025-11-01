package com.martinezzf.breakbitee.ui.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _userId = MutableStateFlow(0)
    val userId: StateFlow<Int> = _userId

    private val _selectedTab = MutableStateFlow(0)
    val selectedTab: StateFlow<Int> = _selectedTab

    init {
        viewModelScope.launch {
            _userId.value = 101
        }
    }

    fun onTabChange(newIndex: Int) {
        _selectedTab.value = newIndex
    }
}