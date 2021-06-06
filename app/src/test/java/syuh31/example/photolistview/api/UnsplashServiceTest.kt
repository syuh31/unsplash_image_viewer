package syuh31.example.photolistview.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.CoreMatchers.`is`
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import syuh31.example.photolistview.getOrAwaitValue
import syuh31.example.photolistview.util.LiveDataCallAdapterFactory


@RunWith(JUnit4::class)
class UnsplashServiceTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var service: UnsplashService

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun createService() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
            .create(UnsplashService::class.java)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun getImageInfoList() {
        enqueueResponse("photos_001.json")
        val photoList = (service.getPhotos().getOrAwaitValue() as ApiSuccessResponse).body

        mockWebServer.takeRequest()
        Assert.assertThat(photoList[0].id, `is`("DPbemOrlS6A"))
        Assert.assertThat(
            photoList[0].urls.small,
            `is`("https://images.unsplash.com/photo-1622495549609-523be9cc6f32?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwyMzU2Nzd8MXwxfGFsbHwxfHx8fHx8Mnx8MTYyMjYzMzk5MQ&ixlib=rb-1.2.1&q=80&w=400")
        )
        Assert.assertThat(photoList[0].user.name, `is`("Jarritos Mexican Soda"))
        Assert.assertThat(photoList[9].id, `is`("xxoJXJcY9Nw"))
    }

    private fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap()) {
        val inputStream = javaClass.classLoader!!
            .getResourceAsStream("api-response/$fileName")
        val source = okio.Okio.buffer(okio.Okio.source(inputStream))
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockWebServer.enqueue(
            mockResponse
                .setBody(source.readString(Charsets.UTF_8))
        )
    }
}