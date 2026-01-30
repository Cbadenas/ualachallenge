package com.ualachallenge.common

sealed interface UIStatus {
    data object Idle: UIStatus
    data object Success: UIStatus
    data object Loading: UIStatus
    data class Error(val msg: String): UIStatus

}