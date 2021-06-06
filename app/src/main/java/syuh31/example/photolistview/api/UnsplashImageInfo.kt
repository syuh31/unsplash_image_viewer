package syuh31.example.photolistview

import com.google.gson.annotations.SerializedName

data class UnsplashImageInfoUrls(
    @field:SerializedName("regular")
    val regular: String,
    @field:SerializedName("small")
    val small: String,
)

data class UnsplashImageInfoUser(
    @field:SerializedName("name")
    val name: String,
)

data class UnsplashImageInfo(
    @field:SerializedName("id")
    val id: String,
    @field:SerializedName("urls")
    val urls: UnsplashImageInfoUrls,
    @field:SerializedName("user")
    val user: UnsplashImageInfoUser,
)