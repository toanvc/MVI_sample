package mvisample.toan.mviapplication.model

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

/**
 * Created by Toan Vu on 6/1/16.
 */
class RepositoryItem {

    var name: String? = null
    @SerializedName("stargazers_count")
    var stargazersCount: Int = 0
    @SerializedName("created_at")
    var createdAt: String? = null
    var language: String? = null
    var owner: Contributor? = null
}
