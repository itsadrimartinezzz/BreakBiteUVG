package com.grupo6.breakbiteuvg.ui.viewmodel

data class UiState<T>(
    val isLoading: Boolean = false,
    val data: T? = null,
    val error: String? = null
)