package com.notgodzilla.android.myapplication.ui

import com.notgodzilla.android.myapplication.api.Employee

data class EmployeeDirectoryUIState(
    val employees: List<Employee> = emptyList(),
    val isRefreshing: Boolean = false,
    val error: Boolean = false
)