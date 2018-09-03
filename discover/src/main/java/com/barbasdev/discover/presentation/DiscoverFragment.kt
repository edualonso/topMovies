package com.barbasdev.discover.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.barbasdev.base.BaseFragment
import com.barbasdev.base.livedata.Event
import com.barbasdev.base.livedata.EventObserver
import com.barbasdev.discover.data.DiscoverRepository
import com.barbasdev.discover.databinding.FragmentDiscoverBinding
import com.barbasdev.discover.databinding.ItemMovieBinding
import com.barbasdev.discover.presentation.interfaces.DiscoverMovie
import com.barbasdev.discover.presentation.interfaces.DiscoverResult
import com.barbasdev.discover.presentation.item.MovieItemViewModel
import org.joda.time.DateTime
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *
 */
class DiscoverFragment : BaseFragment() {

    private lateinit var binding: FragmentDiscoverBinding

    private val viewModel: DiscoverViewModel by viewModel()

    private val discoverAdapter = PagedAdapter()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        restoreState(savedInstanceState)

        binding = FragmentDiscoverBinding
                .inflate(inflater, container, false)
                .also {
                    it.setLifecycleOwner(this)
                    it.viewModel = viewModel
                }

        bindObservers()
        initRecyclerView()

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        viewModel.onResume()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putSerializable(EXTRA_UPDATE_TIME, viewModel.updateTime)
        outState.putSerializable(EXTRA_DISCOVER_RESULTS, viewModel.discoverInteractor.discoverRepository.getCache())

        super.onSaveInstanceState(outState)
    }


    private fun restoreState(savedInstanceState: Bundle?) {
//        savedInstanceState?.run {
//            with(viewModel) {
//                Log.e("------->", "RESTORING PREVIOUS STATE")
//
//                // refresh cache
//                val now = System.currentTimeMillis()
//                val newUpdateTime = getSerializable(EXTRA_UPDATE_TIME) as DateTime
//
//                Log.e("------->", "-------------- expiration:  ${DateTime(newUpdateTime.millis + DiscoverRepository.CACHE_EXPIRES_AFTER)}")
//                Log.e("------->", "-------------- now:         ${DateTime(now)}")
//
//                if (updateTime.millis + DiscoverRepository.CACHE_EXPIRES_AFTER < now) {
//                    clearCache()
//                    return
//                }
//
//                restoreCache(
//                        getSerializable(EXTRA_UPDATE_TIME) as DateTime,
//                        getSerializable(EXTRA_DISCOVER_RESULTS) as HashMap<Int, DiscoverResult>
//                )
//            }
//        }
    }

    private fun bindObservers() {
        viewModel.itemPagedList.observe(this, pagedListObserver)
        viewModel.cacheExpired.observe(this, EventObserver {
            viewModel.itemPagedList.removeObservers(this)
            viewModel.initPagedList()
            viewModel.itemPagedList.observe(this, pagedListObserver)
        })
        viewModel.error.observe(this, EventObserver {
            Toast.makeText(context, "ERROR", Toast.LENGTH_SHORT).show()
            Log.e("------->", "OH NOES!!! This happened: ${it.message}")
        })
    }

    private fun initRecyclerView() {
        binding.recyclerview.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = discoverAdapter
        }
    }


    /**
     *
     */
    val pagedListObserver = Observer<PagedList<DiscoverMovie>> {
        discoverAdapter.submitList(it)
    }


    companion object {
        const val EXTRA_UPDATE_TIME = "EXTRA_UPDATE_TIME"
        const val EXTRA_DISCOVER_RESULTS = "EXTRA_DISCOVER_RESULTS"

        fun newInstance() = DiscoverFragment()
    }
}


/**
 *
 */
private class PagedAdapter: PagedListAdapter<DiscoverMovie, PagedAdapter.MovieItemViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PagedAdapter.MovieItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        holder.bind(MovieItemViewModel(getItem(position)))
    }


    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DiscoverMovie>() {
            override fun areItemsTheSame(oldItem: DiscoverMovie, newItem: DiscoverMovie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: DiscoverMovie, newItem: DiscoverMovie): Boolean {
                return oldItem == newItem
            }
        }
    }

    /**
     *
     */
    private class MovieItemViewHolder(
            private val binding: ItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: MovieItemViewModel) {
            binding.viewModel = viewModel
        }
    }
}
