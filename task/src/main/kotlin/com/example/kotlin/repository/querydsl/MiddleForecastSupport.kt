package com.example.kotlin.repository.querydsl

import com.example.kotlin.domain.MiddleForecasts

interface MiddleForecastSupport {
    fun getWfSv() : List<MiddleForecasts>
}