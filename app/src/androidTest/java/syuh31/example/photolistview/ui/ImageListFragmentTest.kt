package syuh31.example.photolistview.ui

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.CoreMatchers
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import syuh31.example.photolistview.R
import syuh31.example.photolistview.UnsplashImageInfo
import syuh31.example.photolistview.api.ApiResponse
import syuh31.example.photolistview.ui.imagelist.ImageListViewModel

class ImageListFragmentTest {

    private lateinit var viewModel: ImageListViewModel
    private val imageInfoListData = MediatorLiveData<ApiResponse<List<UnsplashImageInfo>>>()
    private val imageInfoListMutableLiveData =
        MutableLiveData<ApiResponse<List<UnsplashImageInfo>>>()

    @Before
    fun init() {
        viewModel = Mockito.mock(ImageListViewModel::class.java)
        imageInfoListData.addSource(imageInfoListMutableLiveData) { res ->
            imageInfoListData.setValue(res)
        }
        Mockito.`when`(viewModel.imageList).thenReturn(imageInfoListData)
    }

    @Test
    fun error() {
        imageInfoListMutableLiveData.postValue(ApiResponse.create(Throwable()))
        Espresso.onView(withId(R.id.imageListViewItemTitle))
            .check(ViewAssertions.matches(CoreMatchers.not(ViewMatchers.isDisplayed())))
    }
}