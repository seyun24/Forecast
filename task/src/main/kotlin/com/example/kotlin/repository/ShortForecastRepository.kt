package com.example.kotlin.repository

import com.example.kotlin.domain.ShortForecasts
import com.example.kotlin.repository.querydsl.ShortForecastSupport
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository

interface ShortForecastRepository : JpaRepository <ShortForecasts,Long>, ShortForecastSupport {
}