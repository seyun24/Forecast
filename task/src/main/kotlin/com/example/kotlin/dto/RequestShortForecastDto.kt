package com.example.kotlin.dto

data class RequestShortForecastDto (
    val serviceKey : String,
    val pageNo : String,
    val numOfRows : String,
    val dataType : String,
    val BASE_DATE : String,
    val BASE_TIME : String,
    val nx : Int,
    val ny : Int
)