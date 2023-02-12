package com.example.kotlin


import com.example.kotlin.repository.MiddleForecastRepository
import com.example.kotlin.repository.ShortForecastRepository
import com.example.kotlin.service.ShortForecastService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.restdocs.RestDocumentationContextProvider
import org.springframework.restdocs.RestDocumentationExtension
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@SpringBootTest
@ExtendWith(RestDocumentationExtension::class)
class KotlinApplicationTests {

    @Test
    fun contextLoads() {

    }

    private var mockMvc: MockMvc? = null


    @BeforeEach
    fun setUp(webApplicationContext: WebApplicationContext, restDocumentation: RestDocumentationContextProvider) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .apply<DefaultMockMvcBuilder>(MockMvcRestDocumentation.documentationConfiguration(restDocumentation))
            .build()
    }


    @Test
    @DisplayName("단기예보 조회")
    fun getShortForecast() {
        this.mockMvc!!.perform(
            MockMvcRequestBuilders.get("/app/task")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(
                MockMvcResultMatchers.status().isOk)
            .andDo(MockMvcResultHandlers.print())
            .andDo(document("get-shortForecast"))
    }

    @Test
    @DisplayName("중기예보 조회")
    fun getMiddleForecast() {
        this.mockMvc!!.perform(
            MockMvcRequestBuilders.get("/app/task/middleForecast")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(
                MockMvcResultMatchers.status().isOk)
            .andDo(MockMvcResultHandlers.print())
            .andDo(document("get-middleForecast"))
    }


}
