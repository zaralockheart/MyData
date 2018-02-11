package my.mdata.prep.mydata.model


/**
 * Created by yusuf on 09/02/2018.
 */
object Model {
    data class Result(val response: Response)
    data class Response(val RespDateTime: String, val RespSource: String, val RespCode: Int, val RespDesc: String)
    data class UsernameValidation(val userName: String, val isLogin: Int)
}