import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.media.MediaMetadataRetriever
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.viewpager2.widget.ViewPager2
import com.erkutaras.statelayout.StateLayout
import com.example.pregnancykotlin.R
import com.example.pregnancykotlin.models.ErrorTest
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import kotlinx.android.synthetic.main.fragment_show_content.*
import kotlinx.android.synthetic.main.layout_error.view.*
import retrofit2.HttpException


fun Throwable.handleErrorBody(): ErrorTest? {
    var errorBody: ErrorTest? = null
    if (this is HttpException) {
        val body = this.response()?.errorBody()
        val gson = Gson()
        val adapter: TypeAdapter<ErrorTest> =
            gson.getAdapter(ErrorTest::class.java)
        errorBody = adapter.fromJson(body!!.string())
//        if (errorBody.httpErrorCode)
    }
    return errorBody
}

fun View.gradientColor(colors: List<String>): Drawable {
    var myColors = intArrayOf(Color.parseColor(colors[0]), Color.parseColor(colors[1]))
    val gradientDrawable = GradientDrawable(GradientDrawable.Orientation.TL_BR, myColors);
    background = gradientDrawable
    return background
}

fun BottomNavigationView.disableShiftMode() {
    val menuView = getChildAt(0) as BottomNavigationMenuView

    menuView.javaClass.getDeclaredField("mShiftingMode").apply {
        isAccessible = true
        setBoolean(menuView, false)
        isAccessible = false
    }

}

//fun RecyclerView.ViewHolder.animate(context :Context?){
//        var animation = AnimationUtils.loadAnimation(context, R.anim.recyclerview_anim)
//        this.itemView.animation = animation
//
//
//}
fun TabLayout.changeIconColor(viewPager2: ViewPager2) {
    var tablayout = this
    this.getTabAt(2)?.icon?.setColorFilter(
        resources.getColor(R.color.gray_text_faded),
        PorterDuff.Mode.SRC_IN
    );
    this.getTabAt(1)?.icon?.setColorFilter(
        resources.getColor(R.color.gray_text_faded),
        PorterDuff.Mode.SRC_IN
    );
    this.getTabAt(0)?.icon?.setColorFilter(
        resources.getColor(R.color.white),
        PorterDuff.Mode.SRC_IN
    );

//    this.setTabTextColors(resources.getColor(R.color.gray_border), resources.getColor(R.color.colorPrimary));
    this.addOnTabSelectedListener(object : OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab?) {
            when (tab?.position) {
                0 -> tablayout.setSelectedTabIndicatorColor(resources.getColor(R.color.blue))
                1 -> tablayout.setSelectedTabIndicatorColor(resources.getColor(R.color.pink_500))
                2 -> tablayout.setSelectedTabIndicatorColor(resources.getColor(R.color.teal_500))

            }
            viewPager2.currentItem = tab?.position!!
            tab?.icon?.setColorFilter(
                resources.getColor(R.color.white),
                PorterDuff.Mode.SRC_IN
            );

        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {
            viewPager2.currentItem = tab?.position!!

            tab?.icon!!.setColorFilter(
                resources.getColor(R.color.gray_text_faded),
                PorterDuff.Mode.SRC_IN
            )
        }

        override fun onTabReselected(tab: TabLayout.Tab?) {
        }
    })


}

fun ImageView.retrieveVideoFrameFromVideo(videoPath: String?) {
    var bitmap: Bitmap
    var mediaMetadataRetriever: MediaMetadataRetriever? = null
    try {
        mediaMetadataRetriever = MediaMetadataRetriever()
        mediaMetadataRetriever.setDataSource(
            videoPath,
            HashMap()
        )
        //   mediaMetadataRetriever.setDataSource(videoPath);
        bitmap = mediaMetadataRetriever.getFrameAtTime(10, MediaMetadataRetriever.OPTION_CLOSEST)
    } catch (e: Exception) {
        e.printStackTrace()
        throw Throwable("Exception in retrieveVideoFrameFromVideo(String videoPath)" + e.message)
    } finally {
        mediaMetadataRetriever?.release()
    }

    this.setImageBitmap(bitmap)
}
fun View.startFadeIn(){
    val fadeIn: Animation = AlphaAnimation(0.0f, 1.0f)
    fadeIn.duration = 500
    this.startAnimation(fadeIn)
}
