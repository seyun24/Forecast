package com.example.kotlin.service

import com.example.kotlin.domain.ShortForecasts

interface ShortForecastService {
    fun getForecast() : List<ShortForecasts>
    fun linkShortForecast()
}