package mvisample.toan.mviapplication.mvi

import com.hannesdorfmann.mosby3.mvp.MvpView
import io.reactivex.Observable
import mvisample.toan.mviapplication.MainViewState

/**
 * Created by ToanVu on 7/11/17.
 */
interface MainView : MvpView {
    fun loadIntent(): Observable<String>
    fun selectIntent(): Observable<String>
    fun render(viewState: MainViewState)
}