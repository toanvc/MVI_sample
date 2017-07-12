package mvisample.toan.mviapplication.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Toan Vu on 6/1/16.
 */
class Contributor {
    @SerializedName("login")
    var name: String? = null
    @SerializedName("avatar_url")
    var avatarUrl: String? = null
    @SerializedName("html_url")
    var htmlUrl: String? = null
}

