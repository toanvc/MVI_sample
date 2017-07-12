package mvisample.toan.mviapplication.api
import io.reactivex.Observable
import mvisample.toan.mviapplication.model.GithubData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by ToanVu on 7/11/17.
 */
interface GithubApiService {

    @GET("repositories")
    fun getStarRepositiories(@Query("q") query: String,
                             @Query("sort") sortBy: String,
                             @Query("order") orderBy: String): Observable<Response<GithubData>>
}


