package com.madsciencesoftware.mvitestenvironment.MainView

import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import mvisample.toan.mviapplication.MainViewAction
import mvisample.toan.mviapplication.MainViewState
import mvisample.toan.mviapplication.mvi.MainView
import mvisample.toan.mviapplication.mvi.QueryInteractor

class MainPresenter(val searchInteractor: QueryInteractor) : MviBasePresenter<MainView, MainViewState>() {

    override fun bindIntents() {
//        val load = intent(MainView::loadIntent)
//                .flatMap { loadString -> searchInteractor.search(loadString) }
//                .startWith(MainViewAction.Loading())
//                .subscribeOn(Schedulers.io())

        val select = intent(MainView::selectIntent)
                .flatMap { content ->
                    searchInteractor.search(content)
                            .startWith(MainViewAction.Loading())
                }
                .subscribeOn(Schedulers.io())

        val allIntents = select//Observable.merge(load, select)
        val defaultState = MainViewState().builder().setLoading(true).build()
        val stateObservable = allIntents.scan(defaultState, this::stateReducer)
                .observeOn(AndroidSchedulers.mainThread())

        subscribeViewState(stateObservable, MainView::render)
    }

    fun stateReducer(previousState: MainViewState, action: MainViewAction): MainViewState {
        return when (action) {
            is MainViewAction.Loading -> previousState.builder().setLoading(true).build()
            is MainViewAction.QueryValueData -> previousState.builder().setLoading(true).setQuery(action.query).build()
            is MainViewAction.ErrorState -> previousState.builder().setLoading(false).setIsError(true).setError(action.err).build()
            is MainViewAction.ResultData -> previousState.builder().setLoading(false).setIsError(false).setListData(action.listData).build()
        }
    }
}