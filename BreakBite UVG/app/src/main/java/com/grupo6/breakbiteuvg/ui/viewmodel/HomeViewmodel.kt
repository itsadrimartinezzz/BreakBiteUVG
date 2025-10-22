package com.grupo6.breakbiteuvg.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    // Id del usuario actual (en un futuro lo puedes obtener desde DataStore o sesión)
    private val _userId = MutableStateFlow(0)
    val userId: StateFlow<Int> = _userId

    // Control de pestañas inferiores o superior
    private val _selectedTab = MutableStateFlow(0)
    val selectedTab: StateFlow<Int> = _selectedTab

    init {
        viewModelScope.launch {
            // Asignar temporalmente un usuario de ejemplo
            _userId.value = 101
        }
    }

    fun onTabChange(newIndex: Int) {
        _selectedTab.value = newIndex
    }
}