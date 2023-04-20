package com.notgodzilla.android.myapplication.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Employee(
    @Json(name = "uuid") val UUID: String,
    @Json(name = "full_name") val fullName: String,
    @Json(name = "phone_number") val phoneNumber: String,
    @Json(name = "email_address") val emailAddress: String,
    @Json(name = "photo_url_small") val photoUrlSmall: String,
    @Json(name = "photo_url_large") val photoUrlLarge: String,
    val team: String,
    @Json(name = "employee_type") val employeeType: String
)