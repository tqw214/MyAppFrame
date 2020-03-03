package com.viger.recyleview.wannengadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView万能适配器
 *
 * @param <T>
 */
public abstract class RecyclerViewUniversalAdapter<T> extends RecyclerView.Adapter<UniversalViewHolder> {
    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;
    protected ViewGroup mRv;
    private OnItemClickListener mOnItemClickListener;

    public final RecyclerViewUniversalAdapter setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
        return this;
    }

    public OnItemClickListener getmOnItemClickListener() {
        return this.mOnItemClickListener;
    }

    public RecyclerViewUniversalAdapter(Context context, List<T> datas, int layoutId) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mLayoutId = layoutId;
        this.mDatas = datas;
    }

    public final UniversalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        UniversalViewHolder viewHolder = UniversalViewHolder.get(this.mContext, (View) null, parent, this.mLayoutId);
        if (null == this.mRv) {
            this.mRv = parent;
        }

        return viewHolder;
    }

    protected final int getPosition(RecyclerView.ViewHolder viewHolder) {
        return viewHolder.getAdapterPosition();
    }

    protected boolean isEnabled(int viewType) {
        return true;
    }

    /**
     * @deprecated
     */
    @Deprecated
    protected void setListener(final ViewGroup parent, final UniversalViewHolder viewHolder, int viewType) {
        if (this.isEnabled(viewType)) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (RecyclerViewUniversalAdapter.this.mOnItemClickListener != null) {
                        int position = RecyclerViewUniversalAdapter.this.getPosition(viewHolder);
                        RecyclerViewUniversalAdapter.this.mOnItemClickListener.onItemClick(parent, v, RecyclerViewUniversalAdapter.this.mDatas.get(position), position);
                    }

                }
            });
            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View v) {
                    if (RecyclerViewUniversalAdapter.this.mOnItemClickListener != null) {
                        int position = RecyclerViewUniversalAdapter.this.getPosition(viewHolder);
                        return RecyclerViewUniversalAdapter.this.mOnItemClickListener.onItemLongClick(parent, v, RecyclerViewUniversalAdapter.this.mDatas.get(position), position);
                    } else {
                        return false;
                    }
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(@NonNull UniversalViewHolder holder, int position) {
        this.setListener(position, holder);
        this.convert(holder, this.mDatas.get(position));
    }

//    public void onBindViewHolder(ViewHolder holder, int position) {
//        this.setListener(position, holder);
//        this.convert(holder, this.mDatas.get(position));
//    }

    protected void setListener(final int position, final UniversalViewHolder viewHolder) {
        if (this.isEnabled(this.getItemViewType(position))) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (RecyclerViewUniversalAdapter.this.mOnItemClickListener != null) {
                        RecyclerViewUniversalAdapter.this.mOnItemClickListener.onItemClick(RecyclerViewUniversalAdapter.this.mRv, v, RecyclerViewUniversalAdapter.this.mDatas.get(position), position);
                    }

                }
            });
            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View v) {
                    if (RecyclerViewUniversalAdapter.this.mOnItemClickListener != null) {
                        int position = RecyclerViewUniversalAdapter.this.getPosition(viewHolder);
                        return RecyclerViewUniversalAdapter.this.mOnItemClickListener.onItemLongClick(RecyclerViewUniversalAdapter.this.mRv, v, RecyclerViewUniversalAdapter.this.mDatas.get(position), position);
                    } else {
                        return false;
                    }
                }
            });
        }
    }

    // 将 var2的数据 显示在ViewHolder 的对应 View 中
    public abstract void convert(UniversalViewHolder var1, T var2);

    public int getItemCount() {
        return this.mDatas != null ? this.mDatas.size() : 0;
    }

    public void setDatas(List<T> list) {
        if (this.mDatas != null) {
            if (null != list) {
                ArrayList temp = new ArrayList();
                temp.addAll(list);
                this.mDatas.clear();
                this.mDatas.addAll(temp);
            } else {
                this.mDatas.clear();
            }
        } else {
            this.mDatas = list;
        }

        this.notifyDataSetChanged();
    }

    public void remove(int i) {
        if (null != this.mDatas && this.mDatas.size() > i && i > -1) {
            this.mDatas.remove(i);
            this.notifyDataSetChanged();
        }

    }

    public void addDatas(List<T> list) {
        if (null != list) {
            ArrayList temp = new ArrayList();
            temp.addAll(list);
            if (this.mDatas != null) {
                this.mDatas.addAll(temp);
            } else {
                this.mDatas = temp;
            }

            this.notifyDataSetChanged();
        }

    }

    public List<T> getDatas() {
        return this.mDatas;
    }

    public T getItem(int position) {
        return position > -1 && null != this.mDatas && this.mDatas.size() > position ? this.mDatas.get(position) : null;
    }

    public interface OnItemClickListener<T> {
        void onItemClick(ViewGroup var1, View var2, T var3, int var4);

        boolean onItemLongClick(ViewGroup var1, View var2, T var3, int var4);
    }

}
