package mvisample.toan.mviapplication.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Toan Vu on 6/1/16.
 */
class GithubData {

    @SerializedName("total_count")
    var totalCount: Int = 0
    var items: MutableList<RepositoryItem>? = null
}
