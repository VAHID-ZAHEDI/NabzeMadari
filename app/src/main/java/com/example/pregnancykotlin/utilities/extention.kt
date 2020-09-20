import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.View
import com.example.pregnancykotlin.login.remote.Resource
import com.example.pregnancykotlin.models.ErrorTest
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import retrofit2.HttpException

fun Throwable.handleErrorBody(): ErrorTest? {
    var errorBody: ErrorTest? = null
    if (this is HttpException) {
        val body = this.response()?.errorBody()
        val gson = Gson()
        val adapter: TypeAdapter<ErrorTest> =
            gson.getAdapter(ErrorTest::class.java)
        errorBody = adapter.fromJson(body!!.string())
    }
    return errorBody
}

fun View.gradientColor(colors: List<String>) {
    var myColors = intArrayOf(Color.parseColor(colors[0]), Color.parseColor(colors[1]))
    val gradientDrawable = GradientDrawable(GradientDrawable.Orientation.RIGHT_LEFT, myColors);
    background = gradientDrawable
}