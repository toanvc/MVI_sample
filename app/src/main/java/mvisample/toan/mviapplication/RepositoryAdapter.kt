package mvisample.toan.mviapplication

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.repo_item_layout.view.*
import mvisample.toan.mviapplication.model.RepositoryItem

/**
 * Created by ToanVu on 7/11/17.
 */
class RepositoryAdapter(private var repoList: MutableList<RepositoryItem>) : RecyclerView.Adapter<RepositoryAdapter.MyViewHolder>() {
    override fun getItemCount(): Int {
        return repoList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder?, position: Int) {
        holder?.bindData(repoList.get(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.repo_item_layout, parent, false)

        return MyViewHolder(itemView)
    }


    fun setRepoList(list: MutableList<RepositoryItem>?) {
        repoList.clear()
        list?.let { repoList.addAll(it) }
    }

    inner class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bindData(item: RepositoryItem?) {
            item?.let {
                view.repoName.text = it.name
                view.date.text = it.createdAt
                view.language.text = it.language
                view.star.text = it.stargazersCount.toString()
            }

        }
    }

}