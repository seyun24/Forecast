package com.example.kotlin.service.impl

import com.example.kotlin.domain.ShortForecasts
import com.example.kotlin.repository.ShortForecastRepository
import com.example.kotlin.service.ShortForecastService
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import org.springframework.stereotype.Service
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import java.text.SimpleDateFormat

@Service
class ShortForecastServiceImpl(
    val shortForecastRepository: ShortForecastRepository
): ShortForecastService {

    override fun getForecast(): List<ShortForecasts> {
        return shortForecastRepository.getForecast()
    }

    override fun linkShortForecast() {
        val serviceKey = "ToaGFo2p54gaPKUm5tEbeePdXBYD3VF5yVRBcxyQdRFuThg70%2Fs2dy8erbFEqBPWhoh0h63hiub%2FAT%2B%2F6Lik%2Bg%3D%3D"
        val pageNo = "1" //페이지 번호
        val numOfRows = "10" //한 페이지 결과 수
        val dataType = "JSON" //데이터 타입
        val BASE_DATE = "20230211"
        val BASE_TIME = "0500"
        val nx = 55
        val ny = 127

        val apiUrl ="http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst"
        val urlBuilder = StringBuilder(apiUrl)
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" +serviceKey)
        urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" +pageNo)
        urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8")+ "=" +numOfRows)
        urlBuilder.append("&" + URLEncoder.encode("dataType", "UTF-8")+ "=" + dataType)
        urlBuilder.append("&" + URLEncoder.encode("base_date", "UTF-8")+ "=" +BASE_DATE)
        urlBuilder.append("&" + URLEncoder.encode("base_time", "UTF-8")+ "=" +BASE_TIME)
        urlBuilder.append("&" + URLEncoder.encode("nx", "UTF-8")+ "=" +nx)
        urlBuilder.append("&" + URLEncoder.encode("ny", "UTF-8")+ "=" +ny)

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

        var temp: Double? = null
        var humid: Double? = null
        var rainAmount: Double? = null

        val j: JSONObject? = null
        val jsonParser = JSONParser()
        val jsonObj = jsonParser.parse(data) as JSONObject
        val parseResponse =jsonObj.get("response") as JSONObject
        val parseBody =parseResponse.get("body") as JSONObject
        val parseItems =parseBody.get("items") as JSONObject
        val jArray =parseItems.get("item") as JSONArray

        for (i in 0 until jArray.size) {
            val obj = jArray.get(i) as JSONObject
            val category= obj.get("category") as String
            val fcstValue= obj.get("fcstValue") as String
            shortForecastRepository.save(ShortForecasts(category = category, fcstValue = fcstValue))
        }
    }

}