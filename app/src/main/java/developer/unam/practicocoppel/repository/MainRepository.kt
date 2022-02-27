package developer.unam.practicocoppel.repository

import android.util.Log
import developer.unam.practicocoppel.retrofit.RetrofitInstance
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.sql.Timestamp as Timestamp1

class MainRepository constructor(private val retrofitInstance: RetrofitInstance) {
    fun getCharacters(offset:Int=0,limit:Int=100)=retrofitInstance.getAllMovies(addParams(offset,limit))

    private fun createMd5(message: String): String {
        val MD5 = "MD5"
        try {
            // Create MD5 Hash
            val digest: MessageDigest = MessageDigest.getInstance(MD5)
            digest.update(message.toByteArray())
            val messageDigest: ByteArray = digest.digest()
            // Create Hex String
            val hexString = StringBuilder()
            for (aMessageDigest in messageDigest) {
                var h = Integer.toHexString(0xFF and aMessageDigest.toInt())
                while (h.length < 2) h = "0$h"
                hexString.append(h)
            }
            return hexString.toString()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        return ""
    }

    private fun addParams(offset: Int, limit: Int):HashMap<String,String>{
        val private = "435e550bd1e38ea6b922a2ae784a200caeb4613f"
        val public = "48d2901781db31818938d1517c19700b"
        val ts = Timestamp1(System.currentTimeMillis())

        val map = hashMapOf<String,String>()
        map["apikey"] = public
        map["ts"] = ts.toString()
        map["hash"] = createMd5("$ts$private$public")
        map["offset"] = offset.toString()
        map["limit"] = limit.toString()
        Log.e("params", "params $map")
        return  map
    }
}