package syuh31.example.photolistview.ui.imagelist

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import syuh31.example.photolistview.UnsplashImageInfo
import syuh31.example.photolistview.api.ApiEmptyResponse
import syuh31.example.photolistview.api.ApiResponse
import syuh31.example.photolistview.api.ApiSuccessResponse
import syuh31.example.photolistview.api.UnsplashService
import javax.inject.Inject

class ImageListViewModel @Inject constructor(private val service: UnsplashService) : ViewModel() {
    val imageList: MediatorLiveData<ApiResponse<List<UnsplashImageInfo>>> = MediatorLiveData()

    fun init() {
        imageList.addSource(service.getPhotos()) {
                res ->
            imageList.setValue(res)
        }
    }
}