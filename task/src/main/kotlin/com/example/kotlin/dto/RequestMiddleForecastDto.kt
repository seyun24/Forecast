package com.example.kotlin.dto

data class RequestMiddleForecastDto (
    val serviceKey : String,
    val pageNo : String,
    val numOfRows : String,
    val dataType : String,
    val stnId : String,
    val tmFc : String
)