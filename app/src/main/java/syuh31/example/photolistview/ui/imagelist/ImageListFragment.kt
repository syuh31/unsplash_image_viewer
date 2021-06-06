package syuh31.example.photolistview.ui.imagelist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import syuh31.example.photolistview.R
import syuh31.example.photolistview.UnsplashImageInfo
import syuh31.example.photolistview.UnsplashImageInfoUrls
import syuh31.example.photolistview.UnsplashImageInfoUser
import syuh31.example.photolistview.api.ApiEmptyResponse
import syuh31.example.photolistview.api.ApiErrorResponse
import syuh31.example.photolistview.api.ApiSuccessResponse
import javax.inject.Inject

@AndroidEntryPoint
class ImageListFragment : Fragment() {

    class ImageListViewAdapter(context: Context, var imageInfoList: List<UnsplashImageInfo>) :
        ArrayAdapter<UnsplashImageInfo>(context, 0, imageInfoList) {

        private val layoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val imageInfo = imageInfoList[position]

            var view = convertView
            if (convertView == null) {
                view = layoutInflater.inflate(R.layout.image_list_view_item, parent, false)
            }

            val name = view?.findViewById<TextView>(R.id.imageListViewItemTitle)
            name?.text = "Photo by ${imageInfo.user.name} on Unsplash"

            val imageView = view?.findViewById<ImageView>(R.id.imageListViewItemImageView)
            Picasso.get()
                .load(imageInfo.urls.small)
                .into(imageView)

            return view!!
        }
    }

    @Inject
    lateinit var viewModel: ImageListViewModel

    var imageInfoList: ArrayList<UnsplashImageInfo> = ArrayList()
    lateinit var adapter: ImageListViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.image_list_fragment, container, false);
        val listView = view.findViewById<ListView>(R.id.image_list_view)
        adapter = ImageListViewAdapter(view.context, imageInfoList)
        listView.adapter = adapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.imageList.observe(
            viewLifecycleOwner, { apiResponse ->

                when (apiResponse) {
                    is ApiSuccessResponse -> {
                        imageInfoList.addAll(apiResponse.body)
                        adapter.notifyDataSetChanged()
                    }
                    is ApiErrorResponse -> {
                        // TODO syuh31: handle api error
                    }
                    is ApiEmptyResponse -> {
                        // TODO syuh31: handle api empty
                    }
                }

            }
        )
        viewModel.init()

//        imageInfoList.addAll(
//            arrayListOf(
//                UnsplashImageInfo(
//                    id = "",
//                    urls = UnsplashImageInfoUrls(
//                        regular = "",
//                        small = "https://images.unsplash.com/photo-1622495549609-523be9cc6f32?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwyMzU2Nzd8MXwxfGFsbHwxfHx8fHx8Mnx8MTYyMjYzMzk5MQ&ixlib=rb-1.2.1&q=80&w=400"
//                    ),
//                    user = UnsplashImageInfoUser(name = "test")
//                ),
//                UnsplashImageInfo(
//                    id = "",
//                    urls = UnsplashImageInfoUrls(
//                        regular = "",
//                        small = "https://images.unsplash.com/photo-1622495549609-523be9cc6f32?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwyMzU2Nzd8MXwxfGFsbHwxfHx8fHx8Mnx8MTYyMjYzMzk5MQ&ixlib=rb-1.2.1&q=80&w=400"
//                    ),
//                    user = UnsplashImageInfoUser(name = "test")
//                ),
//            )
//        )
//        adapter.notifyDataSetChanged()
    }
}