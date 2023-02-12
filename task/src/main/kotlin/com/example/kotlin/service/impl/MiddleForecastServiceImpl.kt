package com.example.kotlin.service.impl

import com.example.kotlin.domain.MiddleForecasts
import com.example.kotlin.domain.ShortForecasts
import com.example.kotlin.repository.MiddleForecastRepository
import com.example.kotlin.service.MiddleForecastService
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import org.springframework.stereotype.Service
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Service
class MiddleForecastServiceImpl (
    val middleForecastRepository: MiddleForecastRepository,
): MiddleForecastService {
    override fun getWfSv(): List<MiddleForecasts> {
        return middleForecastRepository.getWfSv()
    }

    override fun linkMiddleForecast() {
        val now = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("yyyyMMdd")
        val formatedNow = now.format(formatter).toString()

        val serviceKey = "ToaGFo2p54gaPKUm5tEbeePdXBYD3VF5yVRBcxyQdRFuThg70%2Fs2dy8erbFEqBPWhoh0h63hiub%2FAT%2B%2F6Lik%2Bg%3D%3D"
        val pageNo = "1" //페이지 번호
        val numOfRows = "2" //한 페이지 결과 수
        val dataType = "JSON" //데이터 타입
        val stnId = "108" // 108 전국, 109 서울
        val tmFc = formatedNow+"1800" //최근 24시 내에 0600,1800 2회



        val apiUrl ="http://apis.data.go.kr/1360000/MidFcstInfoService/getMidFcst"
        val urlBuilder = StringBuilder(apiUrl)
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" +serviceKey)
        urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" +pageNo)
        urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8")+ "=" +numOfRows)
        urlBuilder.append("&" + URLEncoder.encode("dataType", "UTF-8")+ "=" + dataType)
        urlBuilder.append("&" + URLEncoder.encode("stnId", "UTF-8")+ "=" + stnId)
        urlBuilder.append("&" + URLEncoder.encode("tmFc", "UTF-8")+ "=" + tmFc)

        val url = URL(urlBuilder.toString())
        val conn = url.openConnection() as HttpURLConnection
        conn.requestMethod = "GET"
        conn.setRequestProperty("Content-type", "application/json")

        val rd: BufferedReader
        rd = if (conn.responseCode >= 200 && conn.responseCode <= 300) {
            BufferedReader(InputStreamReader(conn.inputStream))
        } else {
            BufferedReader(InputStreamReader(conn.errorStream))
        }
        val sb = StringBuilder()
        var line: String?
        while (rd.readLine().also { line = it } != null) {
            sb.append(line)
        }
        rd.close()
        conn.disconnect()
        val data = sb.toString()


        val jsonParser = JSONParser()
        val jsonObj = jsonParser.parse(data) as JSONObject
        val parseResponse =jsonObj.get("response") as JSONObject
        val parseBody =parseResponse.get("body") as JSONObject
        val parseItems =parseBody.get("items") as JSONObject
        val jArray =parseItems.get("item") as JSONArray

        val obj = jArray.get(0) as JSONObject
        val wfSv= obj.get("wfSv") as String
        middleForecastRepository.save(MiddleForecasts(wfSv = wfSv))
    }

}