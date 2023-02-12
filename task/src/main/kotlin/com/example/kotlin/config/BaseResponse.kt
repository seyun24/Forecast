package com.example.kotlin.config

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder


@JsonPropertyOrder("isSuccess", "code", "message", "result")
class BaseResponse<T> {
    //BaseResponse 객체를 사용할때 성공, 실패 경우
    @JsonProperty("isSuccess")
    private val isSuccess: Boolean
    private val message: String
    private val code: Int

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private var result: T? = null

    // 요청에 성공한 경우
    constructor(result: T) {
        isSuccess = true
        message = "성공"
        this.code = 1000
        this.result = result
    }

}
