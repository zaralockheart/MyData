package my.mdata.prep.mydata.api

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.grapesnberries.curllogger.CurlLoggerInterceptor
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import my.mdata.prep.mydata.api.ApiService.Companion.SUCCESS
import my.mdata.prep.mydata.model.Model
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST


/**
 * Created by yusuf on 09/02/2018.
 */
interface ApiService {

    companion object {

        val BASE_URL: String = ""

        val SUCCESS: Int = 1234

        fun create(): ApiService {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val httpClient = OkHttpClient.Builder()

            httpClient.addInterceptor(CurlLoggerInterceptor())

            return Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .client(httpClient.build())
                    .build()
                    .create(ApiService::class.java)
        }
    }

    @POST("")
    fun validateUsername(@Header("") reqType: Int,
                         @Body usernameValidation: Model.UsernameValidation): Observable<Model.Result>
}

interface Listener<in T> {
    fun onPreRequest()

    fun onResponse(`object`: T)

    fun onError(error: String?)

}

class RetrofitRequest(context: Context) {

    var networkInfo: NetworkInfo? = (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo

    fun isConnected(): Boolean = networkInfo != null && networkInfo!!.isConnected


    fun <T> makeJSONRequest(request: Observable<T>, listener: Listener<T>?): Disposable {

        var networkError = ""

        if (!isConnected()) {
            networkError = "No internet connection"
        }

        listener?.onPreRequest()

        return request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            if ((result as Model.Result).response.RespCode != SUCCESS) {
                                listener?.onError(result.response.RespDesc)
                            } else {
                                listener?.onResponse(result)
                            }
                        },
                        { error -> listener?.onError(if (networkError.isNotEmpty()) networkError else error.message) }
                )
    }
}
