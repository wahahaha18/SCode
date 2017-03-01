package easyshopown.yq.com.materailtest;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Administrator on 2017/2/13.
 */

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    private Context mContext;
    private List<Fruit> mFruits;

    public FruitAdapter(List<Fruit> fruits) {
        mFruits = fruits;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null){
         mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.fruit_item, parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Fruit fruit = mFruits.get(position);
        holder.mTextView.setText(fruit.getName());
        Glide.with(mContext).load(fruit.getImgId()).into(holder.mImageView);
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FruitActivity.openData(mContext,fruit.getName(),fruit.getImgId());

            }
        });

    }

    @Override
    public int getItemCount() {
        return mFruits.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView mCardView;
        ImageView mImageView;
        TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mCardView = (CardView) itemView;
            mImageView = (ImageView) itemView.findViewById(R.id.fruit_img);
            mTextView = (TextView) itemView.findViewById(R.id.fruit_name);
        }
    }

}
