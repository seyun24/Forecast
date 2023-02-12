package com.example.kotlin.controller

import com.example.kotlin.domain.MiddleForecasts
import com.example.kotlin.domain.ShortForecasts
import com.example.kotlin.service.MiddleForecastService
import com.example.kotlin.service.ShortForecastService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/app/task")
class TaskController(
    private val shortForecastService: ShortForecastService,
    private val middleForecastService: MiddleForecastService,
) {

    @GetMapping("")
    fun getShortForecast(): List<ShortForecasts>{
        return shortForecastService.getForecast()
    }

    @GetMapping("/middleForecast")
    fun getMiddleForecast(): List<MiddleForecasts>{
        return middleForecastService.getWfSv()
    }


    @PostMapping("")
    fun linkShortOpenAPI(): List<ShortForecasts>{
        shortForecastService.linkShortForecast()
        return shortForecastService.getForecast()
    }

    @PostMapping("/middleForecast")
    fun linkMiddleOpenAPI(): List<MiddleForecasts>{
        middleForecastService.linkMiddleForecast()
        return middleForecastService.getWfSv()
    }


}