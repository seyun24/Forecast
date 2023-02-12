package com.example.kotlin.repository

import com.example.kotlin.domain.MiddleForecasts
import com.example.kotlin.repository.querydsl.MiddleForecastSupport
import org.springframework.data.jpa.repository.JpaRepository

interface MiddleForecastRepository : JpaRepository<MiddleForecasts,Long>, MiddleForecastSupport{
}