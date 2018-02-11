package my.mdata.prep.mydata.viewmodel

import android.content.Context
import android.app.Activity



/**
 * Created by yusuf on 08/02/2018.
 */
abstract class BaseViewModel {

    protected var activity: Activity? = null

    constructor(activity: Activity){
        this.activity = activity
    }

}