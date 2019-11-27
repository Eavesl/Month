package com.bawei.myapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.myapplication.R;
import com.bawei.myapplication.bean.JsonBean;
import com.bawei.myapplication.net.GlideUtils;

import java.util.List;

/**
 * author : Eaves
 * desc   : 功能描述
 * date   : 2019/11/27
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context mContext;
    private List<JsonBean.ResultBean> mList;
    private AdapterCallBack mAdapterCallBack;

    public MyAdapter(Context context, List<JsonBean.ResultBean> list) {
        mContext = context;
        mList = list;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.my_card_item, null);
        MyViewHolder viewHolder = new MyViewHolder(inflate);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {

        //单击事件
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapterCallBack.onRecyclerItemClick(i);
            }
        });
        //填充数据
        myViewHolder.mName.setText(mList.get(i).getCommodityName());
        myViewHolder.mPrice.setText("￥"+mList.get(i).getPrice());

        GlideUtils.loadImg(mList.get(i).getMasterPic(),myViewHolder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public interface AdapterCallBack{
        void onRecyclerItemClick(int i);
    }

    public void setAdapterCallBack(AdapterCallBack adapterCallBack){
        mAdapterCallBack = adapterCallBack;
    }
    public void setData(List<JsonBean.ResultBean> data){

        mList.clear();
        mList.addAll(data);
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView mImageView;
        TextView mName;
        TextView mPrice;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.recycler_img);
            mName = itemView.findViewById(R.id.recycler_name);
            mPrice = itemView.findViewById(R.id.recycler_price);

        }
    }
}
