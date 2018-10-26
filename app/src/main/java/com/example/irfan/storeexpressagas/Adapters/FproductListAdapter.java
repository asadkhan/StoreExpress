package com.example.irfan.storeexpressagas.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.irfan.storeexpressagas.R;
import com.example.irfan.storeexpressagas.models.CategoryResponse;
import com.example.irfan.storeexpressagas.models.FproductTwoCol;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FproductListAdapter  extends RecyclerView.Adapter<FproductListAdapter.ListViewHolder>{

    private List<FproductTwoCol> productsList;
    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView img1,img2;
        public TextView TxtPrice1,TxtPrice2,TxtName1,TxtName2;


        private String mItem;

        public ListViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);

            img1 = (ImageView) view.findViewById(R.id.Img_Product1);
            img2 = (ImageView) view.findViewById(R.id.ImgProduct2);

            TxtPrice1 = (TextView) view.findViewById(R.id.txt_price1);
            TxtPrice2 = (TextView) view.findViewById(R.id.txt_price2);
            TxtName1 = (TextView) view.findViewById(R.id.txt_name1);
            TxtName2 = (TextView) view.findViewById(R.id.txt_name2);


        }



        @Override
        public void onClick(View view) {
            // Log.d("APITEST:", "onClick " + getPosition() + " " + mItem);



        }


    }


    public FproductListAdapter(List<FproductTwoCol> fProductsList) {
        this.productsList = fProductsList;

    }



    @Override
    public FproductListAdapter.ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cate_pro_list, parent, false);

        return new FproductListAdapter.ListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FproductListAdapter.ListViewHolder holder, int position) {
        FproductTwoCol Obj = productsList.get(position);

        String imgURL1=Obj.ProductoneImg;
        String imgURL2=Obj.ProducttwoImg;

        holder.TxtName1.setText(Obj.ProductoneName);
        holder.TxtName2.setText(Obj.ProducttwoName);

        holder.TxtPrice1.setText(Obj.ProductonePrice);
        holder.TxtPrice2.setText(Obj.ProducttwoPrice);


        Picasso.with(holder.img1.getContext()).load(imgURL1).resize(90, 90).centerCrop().into(holder.img1);
        Picasso.with(holder.img2.getContext()).load(imgURL2).resize(90, 90).centerCrop().into(holder.img2);

        // formatting the date appropriately.


    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }




}
