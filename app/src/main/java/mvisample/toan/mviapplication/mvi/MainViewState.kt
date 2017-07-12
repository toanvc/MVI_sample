package mvisample.toan.mviapplication

import mvisample.toan.mviapplication.common.CustomException
import mvisample.toan.mviapplication.model.RepositoryItem

/**
 * Created by ToanVu on 7/11/17.
 */
class MainViewState() {
    var query: String = "1"
    var loading: Boolean = false
    var listData: MutableList<RepositoryItem> = ArrayList<RepositoryItem>()
    var isError: Boolean = false
    var error: Throwable = CustomException("")


    constructor(query: String, loading: Boolean, listData: MutableList<RepositoryItem>, isError: Boolean, error: Throwable) : this() {
        this.query = query
        this.loading = loading
        this.listData = listData
        this.isError = isError
        this.error = error
    }

    fun builder(): Builder {
        return Builder(this)
    }


    class Builder() {
        private var query = "1"
        private var loading = false
        private var listData: MutableList<RepositoryItem> = ArrayList()
        private var isError = false
        private var error: Throwable = CustomException("")

        constructor(toCopyFrom: MainViewState) : this() {
            listData = ArrayList()
            listData.addAll(toCopyFrom.listData)
            loading = toCopyFrom.loading
            isError = toCopyFrom.isError
            error = toCopyFrom.error
        }

        fun setQuery(query: String): Builder {
            this.query = query
            return this
        }
        fun setLoading(loading: Boolean): Builder {
            this.loading = loading
            return this
        }

        fun setListData(listData: MutableList<RepositoryItem>): Builder {
            this.listData = listData
            return this
        }

        fun setIsError(isError: Boolean): Builder {
            this.isError = isError
            return this
        }

        fun setError(error: Throwable): Builder {
            this.error = error
            return this
        }

        fun build(): MainViewState {
            return MainViewState(query, loading, listData, isError, error)
        }
    }
}