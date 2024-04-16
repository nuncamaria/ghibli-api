package com.nuncamaria.ui.utils

sealed interface UiState<out T> {
    data object Idle : UiState<Nothing>
    data object Loading : UiState<Nothing>
    data class Success<out T>(val data: T) : UiState<T>
    data class Error<out T>(val e: Throwable, val message: String = "") : UiState<T>
}