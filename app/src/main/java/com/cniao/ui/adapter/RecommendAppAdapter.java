package com.cniao.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cniao.R;
import com.cniao.bean.AppInfo;
import com.cniao.common.Constant;
import com.cniao.common.imageloader.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chenqi on 2017/6/7.
 */

public class RecommendAppAdapter extends RecyclerView.Adapter<RecommendAppAdapter.ViewHolder> {

    private Context mContext;
    private List<AppInfo> mDatas;
    private LayoutInflater mLayoutInflater;

    public RecommendAppAdapter(Context context, List<AppInfo> mDatas) {
        this.mDatas = mDatas;
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.template_recomend_app, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AppInfo appInfo = mDatas.get(position);
        String baseImgUrl = "http://file.market.xiaomi.com/mfc/thumbnail/png/w150q80/";
//        Picasso.with(mContext).load(baseImgUrl + appInfo.getIcon()).into(holder.imgIcon);
        ImageLoader.load(Constant.BASE_IMG_URL+appInfo.getIcon(),holder.imgIcon);
        holder.textTitle.setText(appInfo.getDisplayName());
        holder.textSize.setText(appInfo.getApkSize() / 1024 / 1024 + "MB");

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_icon)
        ImageView imgIcon;
        @BindView(R.id.text_title)
        TextView textTitle;
        @BindView(R.id.text_size)
        TextView textSize;
        @BindView(R.id.btn_dl)
        Button btnDl;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
