package mvisample.toan.mviapplication

import android.util.Log
import mvisample.toan.mviapplication.model.RepositoryItem

/**
 * Created by ToanVu on 7/11/17.
 */
sealed class MainViewAction {
    class Loading : MainViewAction() {
        init {
            Log.e("toanvc", "loading ne")
        }
    }

    class ErrorState(var err: Throwable) : MainViewAction()

    class ResultData(var listData: MutableList<RepositoryItem>) : MainViewAction()

    class QueryValueData(var query: String) : MainViewAction()
}