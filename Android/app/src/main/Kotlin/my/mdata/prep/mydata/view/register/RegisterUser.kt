package my.mdata.prep.mydata.view.register

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_registeruser.*
import my.mdata.prep.mydata.R
import my.mdata.prep.mydata.api.Listener
import my.mdata.prep.mydata.api.RetrofitRequest
import my.mdata.prep.mydata.model.Model
import my.mdata.prep.mydata.view.BaseActivity


/**
 * Created by yusuf on 08/02/2018.
 */
class RegisterUser : BaseActivity() {

    companion object {

        val registerType: String = "registerType"

        val pelangganType = Pair(registerType, "pelanngan")
        val pembekalType = Pair(registerType, "pembekal")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registeruser)

        button_register.setOnClickListener { beginSearch(editText7.text.toString()) }
    }

    private fun beginSearch(username: String) {
        val validate: Model.UsernameValidation = Model.UsernameValidation(username, 0)

        disposable = RetrofitRequest(this)
                .makeJSONRequest(
                        apiServe.validateUsername(9003, validate),
                        object : Listener<Model.Result> {
                            override fun onPreRequest() {
                                Toast.makeText(applicationContext, "loading", Toast.LENGTH_SHORT).show()
                            }

                            override fun onResponse(`object`: Model.Result) {
                                Toast.makeText(applicationContext, `object`.toString(), Toast.LENGTH_SHORT).show()
                                Log.e("check response", `object`.toString())
                            }

                            override fun onError(error: String?) {
                                Toast.makeText(applicationContext, error, Toast.LENGTH_SHORT).show()
                                Log.e("check error", error.toString())

                            }

                        })
    }
}