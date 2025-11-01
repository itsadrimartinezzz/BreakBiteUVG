package com.martinezzf.breakbitee.ui.viewmodel

data class UiState<T>(
    val isLoading: Boolean = false,
    val data: T? = null,
    val error: String? = null
)
