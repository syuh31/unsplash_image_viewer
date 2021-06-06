package syuh31.example.photolistview

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import syuh31.example.photolistview.api.UnsplashService
import syuh31.example.photolistview.util.LiveDataCallAdapterFactory

@Module
@InstallIn(ActivityComponent::class)
object AppModule {
    @Provides
    fun provideUnsplashService(
    ): UnsplashService {
        return Retrofit.Builder()
            .baseUrl("https://api.unsplash.com")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
            .create(UnsplashService::class.java)
    }
}

@HiltAndroidApp
class PhotoListViewApp : Application()