package com.example.kotlin.repository.querydsl.impl

import com.example.kotlin.domain.MiddleForecasts
import com.example.kotlin.domain.QMiddleForecasts.middleForecasts
import com.example.kotlin.domain.ShortForecasts
import com.example.kotlin.repository.querydsl.CustomQuerydslRepositorySupport
import com.example.kotlin.repository.querydsl.MiddleForecastSupport
import com.querydsl.jpa.impl.JPAQueryFactory

class MiddleForecastSupportImpl (
    private val query: JPAQueryFactory,
) : CustomQuerydslRepositorySupport(ShortForecasts::class.java), MiddleForecastSupport {
    override fun getWfSv(): List<MiddleForecasts> {
        return query.selectFrom(middleForecasts)
            .fetch()
    }
}