package syuh31.example.photolistview.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import syuh31.example.photolistview.api.UnsplashService
import syuh31.example.photolistview.ui.imagelist.ImageListViewModel
import syuh31.example.photolistview.util.mock

@RunWith(JUnit4::class)
class ItemListViewModelTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val service = Mockito.mock(UnsplashService::class.java)
    private val viewModel = ImageListViewModel(service)

    @Test
    fun testCallRepo() {
        viewModel.imageList.observeForever(mock())
        viewModel.init()
        Mockito.verify(service).getPhotos()
    }
}