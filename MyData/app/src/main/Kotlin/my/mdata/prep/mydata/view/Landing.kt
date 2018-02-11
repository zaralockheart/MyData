package my.mdata.prep.mydata.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_landing.*
import my.mdata.prep.mydata.R
import my.mdata.prep.mydata.view.register.RegisterUser
import org.jetbrains.anko.startActivity

/**
 * Created by yusuf on 08/02/2018.
 */
class Landing : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)

        button_register.setOnClickListener({ startActivity<RegisterUser>() })
    }
}