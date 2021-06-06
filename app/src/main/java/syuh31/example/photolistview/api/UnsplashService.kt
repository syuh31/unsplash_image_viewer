package syuh31.example.photolistview.api

import androidx.lifecycle.LiveData
import retrofit2.http.GET
import retrofit2.http.Headers
import syuh31.example.photolistview.UnsplashImageInfo

interface UnsplashService {
    companion object {
        const val ACCESS_KEY = "S901jhh0ld9QC7yCr5MJSOb8yINMxxEdzkj0LDpkwgs"
    }

    @Headers(
        "Accept-Version: v1",
        "Authorization: Client-ID $ACCESS_KEY"
    )
    @GET("photos")
    fun getPhotos(): LiveData<ApiResponse<List<UnsplashImageInfo>>>
}