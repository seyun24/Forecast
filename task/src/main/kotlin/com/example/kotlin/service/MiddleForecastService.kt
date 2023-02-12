package com.example.kotlin.service

import com.example.kotlin.domain.MiddleForecasts

interface MiddleForecastService {
    fun getWfSv() : List<MiddleForecasts>
    fun linkMiddleForecast()
}