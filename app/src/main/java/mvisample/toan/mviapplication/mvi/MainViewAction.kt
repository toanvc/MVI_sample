package mvisample.toan.mviapplication

import mvisample.toan.mviapplication.model.RepositoryItem

/**
 * Created by ToanVu on 7/11/17.
 */
sealed class MainViewAction {
    class Loading : MainViewAction()

    class ErrorState(var err: Throwable) : MainViewAction()

    class ResultData(var listData: MutableList<RepositoryItem>) : MainViewAction()

    class QueryValueData(var query: String) : MainViewAction()
}