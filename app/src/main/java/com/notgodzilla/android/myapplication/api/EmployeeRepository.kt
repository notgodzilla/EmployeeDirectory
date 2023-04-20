package com.notgodzilla.android.myapplication.api

import com.squareup.moshi.*
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class EmployeeRepository {
    private val employeeApi: EmployeeApi

    init {

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://s3.amazonaws.com/sq-mobile-interview/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
        employeeApi = retrofit.create()
    }

    suspend fun fetchEmployees(): List<Employee> =
        employeeApi.fetchEmployees().employees

    suspend fun fetchMalformedEmployees(): List<Employee> =
        employeeApi.fetchMalformedList().employees

    suspend fun fetchEmptyEmployees(): List<Employee> =
        employeeApi.fetchEmptyList().employees
}

