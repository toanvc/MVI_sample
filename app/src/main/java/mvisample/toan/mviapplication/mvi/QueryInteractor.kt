package mvisample.toan.mviapplication.mvi

import android.text.TextUtils
import android.util.Log
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import mvisample.toan.mviapplication.MainViewAction
import mvisample.toan.mviapplication.api.GithubApiService
import mvisample.toan.mviapplication.common.CustomException
import mvisample.toan.mviapplication.util.MyUtils
import java.io.IOException

/**
 * Created by ToanVu on 7/11/17.
 */
class QueryInteractor(val apiService: GithubApiService) {
    fun search(searchString: String): Observable<MainViewAction> {
        if (TextUtils.isEmpty(searchString)) {
            return Observable.just(MainViewAction.ErrorState(CustomException("Empty input!")))
        }

        //sort by star and order desc
        return apiService.getStarRepositiories(MyUtils.getDayQuery(searchString), "star", "desc")
                .subscribeOn(Schedulers.io())
                .doOnNext { Log.d("Search...", "loading")}.doOnError { }
                .flatMap { response ->
                    val res = response.body()
                    res?.let {
                        if (res.items == null || res.items?.size == 0) {
                            Observable.just(MainViewAction.ErrorState(CustomException("No Result!")))
                        } else {
                            Observable.just(MainViewAction.ResultData(res.items!!))
                        }

                    } ?: Observable.just(MainViewAction.ErrorState(IOException("Fail to connect!")))
                }.doOnError { e -> Log.e("Toan","msg " + e.message) }
    }

    fun select(value: String): Observable<MainViewAction>  {
        return Observable.just(MainViewAction.QueryValueData(value))
    }


}