package my.mdata.prep.mydata.view.register

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_registertype.*
import my.mdata.prep.mydata.R
import my.mdata.prep.mydata.view.register.RegisterUser.Companion.pelangganType
import my.mdata.prep.mydata.view.register.RegisterUser.Companion.pembekalType
import org.jetbrains.anko.startActivity

/**ø
 * Created by yusuf on 08/02/2018.
 */
class RegisterType : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registertype)

        button_pelanggan.setOnClickListener({ startActivity<RegisterUser>(pelangganType) })

        button_pembekal.setOnClickListener({ startActivity<RegisterUser>(pembekalType) })
    }
}