package my.mdata.prep.mydata.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_splash.*
import my.mdata.prep.mydata.R


class Splash : AppCompatActivity() {

    val splashTimer: Long = 1000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            val p1 = Pair(imageView as View, "profile")
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, p1)
            startActivity(Intent(this, Landing::class.java), options.toBundle())
        }, splashTimer)
    }
}
