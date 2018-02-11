package my.mdata.prep.mydata.model

import android.os.Parcel
import android.os.Parcelable


/**
 * Created by yusuf on 09/02/2018.
 */
object Model {
    data class Result(val response: Response)
    data class Response(val RespDateTime: String, val RespSource: String, val RespCode: Int, val RespDesc: String)
    data class UsernameValidation(val userName: String, val isLogin: Int)

    data class User(var token: String? = "", var email: String? = "") : Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readString(),
                parcel.readString())

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(token)
            parcel.writeString(email)
        }

        override fun describeContents(): Int = 0

        companion object CREATOR : Parcelable.Creator<User> {
            override fun createFromParcel(parcel: Parcel): User = User(parcel)


            override fun newArray(size: Int): Array<User?> = arrayOfNulls(size)
        }
    }
}