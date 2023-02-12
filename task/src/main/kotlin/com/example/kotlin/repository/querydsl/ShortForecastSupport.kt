package com.example.kotlin.repository.querydsl

import com.example.kotlin.domain.ShortForecasts

interface ShortForecastSupport {
    fun getForecast() : List<ShortForecasts>
}