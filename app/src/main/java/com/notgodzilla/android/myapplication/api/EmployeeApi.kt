package com.notgodzilla.android.myapplication.api

import retrofit2.http.GET

interface EmployeeApi {
    @GET("employees.json")
    suspend fun fetchEmployees(): EmployeeDirectoryResponse

    @GET("employees_malformed.json")
    suspend fun fetchMalformedList(): EmployeeDirectoryResponse

    @GET("employees_empty.json")
    suspend fun fetchEmptyList(): EmployeeDirectoryResponse
}