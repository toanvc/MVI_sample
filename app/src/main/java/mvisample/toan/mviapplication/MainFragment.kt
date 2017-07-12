package mvisample.toan.mviapplication

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.mosby3.mvi.MviFragment
import com.jakewharton.rxbinding2.widget.RxAdapterView
import com.madsciencesoftware.mvitestenvironment.MainView.MainPresenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_list_git.*
import mvisample.toan.mviapplication.model.RepositoryItem
import mvisample.toan.mviapplication.util.MyUtils
import mvisample.toan.mviapplication.mvi.MainView
import javax.inject.Inject


/**
 * Created by ToanVu on 7/11/17.
 */
class MainFragment : MviFragment<MainView, MainPresenter>(), MainView {
    @Inject lateinit var mainPresenter: MainPresenter

    var adapter: RepositoryAdapter? = null

    companion object {
        fun newInstance(): MainFragment {
            val fragment = MainFragment()
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MyApplication.instance?.appComponent?.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_list_git, container, false)

        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = RepositoryAdapter(ArrayList<RepositoryItem>())
        recycleView.layoutManager = LinearLayoutManager(activity)
        recycleView.adapter = adapter
    }

    override fun loadIntent(): Observable<String> {
        return Observable.just(spinner.selectedItem.toString())
    }

    override fun selectIntent(): Observable<String> {
        return RxAdapterView.itemSelections(spinner)
                .subscribeOn(AndroidSchedulers.mainThread())
                .flatMap { pos ->
                    Observable.just(spinner.adapter.getItem(pos).toString())
                }
    }

    override fun render(viewState: MainViewState) {
        if (viewState.loading) {
            renderLoadingView(viewState)
        } else if (viewState.isError) {
            renderErrorView(viewState)
        } else {
            renderResultView(viewState)
        }
    }


    override fun createPresenter(): MainPresenter {
        return mainPresenter
    }

    private fun renderLoadingView(viewState: MainViewState) {
        contentText.visibility = View.VISIBLE
        contentText.text = "Loading"
        swipeLayout.visibility = View.GONE
    }

    private fun renderErrorView(viewState: MainViewState) {
        contentText.visibility = View.VISIBLE
        contentText.text = viewState.error.message
        swipeLayout.visibility = View.GONE
    }

    private fun renderResultView(viewState: MainViewState) {
        contentText.visibility = View.GONE
        swipeLayout.visibility = View.VISIBLE
        adapter?.setRepoList(viewState.listData)
        adapter?.notifyDataSetChanged()
    }

}