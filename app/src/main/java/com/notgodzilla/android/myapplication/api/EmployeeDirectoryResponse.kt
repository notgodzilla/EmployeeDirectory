package com.notgodzilla.android.myapplication.api
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EmployeeDirectoryResponse(
    val employees: List<Employee>
)