package com.example.kotlin.repository.querydsl.impl

import com.example.kotlin.domain.QShortForecasts.shortForecasts
import com.example.kotlin.domain.ShortForecasts
import com.example.kotlin.repository.querydsl.CustomQuerydslRepositorySupport
import com.example.kotlin.repository.querydsl.ShortForecastSupport
import com.querydsl.jpa.impl.JPAQueryFactory

class ShortForecastSupportImpl(
    private val query: JPAQueryFactory,
) : CustomQuerydslRepositorySupport(ShortForecasts::class.java), ShortForecastSupport {
    override fun getForecast(): List<ShortForecasts> {
        return query.selectFrom(shortForecasts).fetch()
    }
}