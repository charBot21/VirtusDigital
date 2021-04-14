package com.carlostorres.virtusdigital.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.carlostorres.virtusdigital.R
import com.carlostorres.virtusdigital.data.local.entity.Items
import com.carlostorres.virtusdigital.databinding.ActivityHomeBinding
import com.carlostorres.virtusdigital.model.interfacs.HomeListener
import com.carlostorres.virtusdigital.ui.adapter.ItemsAdapter
import com.carlostorres.virtusdigital.ui.utils.hide
import com.carlostorres.virtusdigital.ui.utils.show
import com.carlostorres.virtusdigital.ui.utils.toast
import com.carlostorres.virtusdigital.ui.viewmodel.HomeVM

class HomeActivity : AppCompatActivity(), HomeListener {

    // Data Binding and view model
    private lateinit var viewModel: HomeVM
    private lateinit var binding: ActivityHomeBinding

    // RecyclerView
    private lateinit var recyclerView: RecyclerView

    // Progressbar
    private lateinit var progressBar: ProgressBar

    // Refresh
    private lateinit var refreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Data binding and view model
        binding = DataBindingUtil.setContentView( this, R.layout.activity_home )
        viewModel = ViewModelProvider(this).get(HomeVM::class.java)
        binding.viewmodel = viewModel
        viewModel.listener = this

        onFindIDs()

        onPrepareData()
        viewModel.getItemsInfo()

        refreshView()
        swipeItems()
    }

    private fun onFindIDs() {
        progressBar = findViewById(R.id.pbHome)
        refreshLayout = findViewById(R.id.swipeRefreshLayout)
    }

    private fun refreshView() {
        refreshLayout.setOnRefreshListener {
            viewModel.getItemsInfo()
            refreshLayout.isRefreshing = false
        }
    }

    private fun onPrepareData() {
        recyclerView = findViewById(R.id.rvItems)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        viewModel.allItems.observe(this, Observer { items ->
            items?.let {
                val adapter = ItemsAdapter(it, this)
                recyclerView.adapter = adapter
            }
        })
    }

    private fun swipeItems() {
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewModel.deleteItem(viewHolder.adapterPosition)
            }
        }).attachToRecyclerView(recyclerView)

    }

    override fun onError() {
        toast(getString(R.string.error_connection))
    }

    override fun showProgressBar() {
        progressBar.show()
    }

    override fun hideProgressBar() {
        progressBar.hide()
    }

    override fun itemClicked(items: Items, position: Int) {
        val intent = Intent(this, WebVActivity::class.java)
        intent.putExtra("title", items.title)
        intent.putExtra("url", items.url)
        startActivity(intent)
    }
}