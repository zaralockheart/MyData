package my.mdata.prep.mydata.view

import android.support.v7.app.AppCompatActivity
import io.reactivex.disposables.Disposable
import my.mdata.prep.mydata.api.ApiService

/**
 * Created by yusuf on 11/02/2018.
 */
abstract class BaseActivity : AppCompatActivity() {

    protected var disposable: Disposable? = null

    protected val apiServe by lazy { ApiService.create() }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }
}