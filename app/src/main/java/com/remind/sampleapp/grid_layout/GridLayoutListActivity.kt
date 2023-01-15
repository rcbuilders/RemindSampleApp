package com.remind.sampleapp.grid_layout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.remind.sampleapp.BaseActivity
import com.remind.sampleapp.R
import com.remind.sampleapp.databinding.ActivityGridLayoutListBinding
import com.remind.sampleapp.databinding.ListItemGirdBinding
import com.remind.sampleapp.utils.dp
import com.rubensousa.decorator.ColumnProvider
import com.rubensousa.decorator.GridMarginDecoration
import com.rubensousa.decorator.LinearDividerDecoration

class GridLayoutListActivity:
    BaseActivity<ActivityGridLayoutListBinding>(R.layout.activity_grid_layout_list) {

    override fun initView() {
        super.initView()

        with(binding) {
//           rvList.addItemDecoration(
//               LinearDividerDecoration.create(
//                   color = ContextCompat.getColor(this@GridLayoutListActivity, R.color.purple_200),
//                   size = 8.dp,
//                   orientation = RecyclerView.VERTICAL
//               )
//           )
//            rvList.addItemDecoration(
//                LinearDividerDecoration.create(
//                    color = ContextCompat.getColor(this@GridLayoutListActivity, R.color.purple_200),
//                    size = 8.dp,
//                    orientation = RecyclerView.HORIZONTAL
//                )
//            )
            rvList.addItemDecoration(
                GridMarginDecoration(
                    horizontalMargin = 8.dp,
                    verticalMargin = 8.dp,
                    columnProvider = object : ColumnProvider {
                        override fun getNumberOfColumns(): Int = 3
                    }
                )
            )
            rvList.adapter = GridLayoutAdapter()
        }
    }

    inner class GridLayoutAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return GridViewHolder(
                ListItemGirdBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        }

        override fun getItemCount(): Int = 30

        inner class GridViewHolder(
            private val binding: ListItemGirdBinding
        ): RecyclerView.ViewHolder(binding.root) {

        }

    }
}