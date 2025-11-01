package com.martinezzf.breakbitee.ui.viewmodel


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class User(val id: Int, val name: String, val email: String)

class ProfileViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val userId: Int = checkNotNull(savedStateHandle["userId"])

    private val _user = MutableStateFlow(UiState<ProfileUser>(isLoading = true))
    val user: StateFlow<UiState<ProfileUser>> = _user

    private val _selectedTab = MutableStateFlow(0)
    val selectedTab: StateFlow<Int> = _selectedTab

    init {
        viewModelScope.launch {
            val userData = ProfileUser(userId, "Usuario $userId", "usuario$userId@correo.com")
            _user.value = UiState(data = userData)
        }
    }

    fun onTabChange(newIndex: Int) {
        _selectedTab.value = newIndex
    }

}