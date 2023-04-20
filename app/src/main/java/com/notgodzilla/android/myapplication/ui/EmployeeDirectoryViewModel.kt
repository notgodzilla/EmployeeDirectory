package com.notgodzilla.android.myapplication.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.notgodzilla.android.myapplication.api.Employee
import com.notgodzilla.android.myapplication.api.EmployeeListType
import com.notgodzilla.android.myapplication.api.EmployeeRepository
import com.notgodzilla.android.myapplication.api.PreferencesRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class EmployeeDirectoryViewModel : ViewModel() {

    private val employeeRepo = EmployeeRepository()
    private val _uiState: MutableStateFlow<EmployeeDirectoryUIState> =
        MutableStateFlow(EmployeeDirectoryUIState())
    val directoryUIState: StateFlow<EmployeeDirectoryUIState>
        get() = _uiState.asStateFlow()
    private val preferencesRepository = PreferencesRepository.get()


    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        _uiState.update {
            it.copy(
                employees = emptyList(),
                error = true,
                isRefreshing = false
            )
        }
    }

    init {
        viewModelScope.launch {
            preferencesRepository.lastEmployeeListTypeQuery.collect {
                getEmployees(EmployeeListType.valueOf(it))
            }


        }
    }

    fun getEmployeesRefresh() {
        viewModelScope.launch(exceptionHandler) {
            preferencesRepository.lastEmployeeListTypeQuery.collect {
                getEmployees(EmployeeListType.valueOf(it))
            }
        }
    }

    fun getEmployees(type: EmployeeListType) {

        _uiState.update {
            it.copy(isRefreshing = true)
        }
        viewModelScope.launch(exceptionHandler) {
            preferencesRepository.setStoredQuery(type.toString())
            val employees = fetchEmployees(type)
            _uiState.update {
                it.copy(
                    employees = employees,
                    isRefreshing = false,
                    error = false
                )
            }
        }
    }

    private suspend fun fetchEmployees(type: EmployeeListType): List<Employee> {
        return when (type) {
            EmployeeListType.VALID -> employeeRepo.fetchEmployees()
            EmployeeListType.EMPTY -> employeeRepo.fetchEmptyEmployees()
            EmployeeListType.MALFORMED -> employeeRepo.fetchMalformedEmployees()
        }
    }


}