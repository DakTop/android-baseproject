package com.runtop.android.core.base.view.recycle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.runtop.android.baselibrary.tool.ListTool;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * 通用的Adapter
 * Created by runTop on 2017/9/12.
 */
public abstract class BaseRecycleViewAdapter<T> extends RecyclerView.Adapter<BaseRecycleViewHolder> {
    private List<T> listData = new ArrayList<>();

    private int mItemLayoutId;
    private Context mContext;

    public BaseRecycleViewAdapter(Context context, int itemLayoutId) {
        mItemLayoutId = itemLayoutId;
        mContext = context;
    }

    @Override
    public BaseRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(mItemLayoutId, parent, false);
        BaseRecycleViewHolder currencyViewHolder = new BaseRecycleViewHolder(view);
        return currencyViewHolder;
    }

    @Override
    public void onBindViewHolder(BaseRecycleViewHolder holder, int position) {
        onBindView(holder, listData.get(position), position);
    }

    /**
     * 外部初始化Adapter或者继承此Adapter时必须实现这个方法，这个方法主要处理列表上item显示的逻辑
     *
     * @param holder
     * @param t        item上绑定的数据项
     * @param position item的位置
     */
    public abstract void onBindView(BaseRecycleViewHolder holder, T t, int position);

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public T getItemByPosition(int position) {
        return position <= listData.size() - 1 ? listData.get(position) : null;
    }

    /**
     * 刷新列表页
     *
     * @param list
     */
    public void refreshData(List<T> list) {
        listData.clear();
        if (ListTool.isNotEmpty(list)) {
            listData.addAll(list);
        }

        this.notifyDataSetChanged();
    }

    /**
     * 加载新的列表数据
     *
     * @param list
     */
    public void loadMoreData(List<T> list) {
        if (ListTool.isEmpty(list)) {
            return;
        }
        listData.addAll(list);
        this.notifyDataSetChanged();
    }

}
