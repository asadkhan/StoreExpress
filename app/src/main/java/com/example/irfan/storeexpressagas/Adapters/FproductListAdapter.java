package com.example.irfan.storeexpressagas.Adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.irfan.storeexpressagas.R;
import com.example.irfan.storeexpressagas.activities.ProductActivity;
import com.example.irfan.storeexpressagas.extras.ActivityManager;
import com.example.irfan.storeexpressagas.models.CategoryResponse;
import com.example.irfan.storeexpressagas.models.FproductResponse;
import com.example.irfan.storeexpressagas.models.FproductTwoCol;
import com.example.irfan.storeexpressagas.models.Product;
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
           // view.setOnClickListener(this);

            img1 = (ImageView) view.findViewById(R.id.Img_Product1);
            img2 = (ImageView) view.findViewById(R.id.ImgProduct2);

            TxtPrice1 = (TextView) view.findViewById(R.id.txt_price1);
            TxtPrice2 = (TextView) view.findViewById(R.id.txt_price2);
            TxtName1 = (TextView) view.findViewById(R.id.txt_name1);
            TxtName2 = (TextView) view.findViewById(R.id.txt_name2);
            img1.setOnClickListener(this);
            TxtName1.setOnClickListener(this);
            img2.setOnClickListener(this);
        }



        @Override
        public void onClick(View view) {
            Log.d("test:", "click");
            if(view.getId()==R.id.Img_Product1  ) {
              FproductTwoCol   mobj = productsList.get(getPosition());

                Product obj =new Product();
                obj.img=mobj.ProductoneImg;
                obj.name=mobj.ProductoneName;
                obj.price=mobj.ProductonePrice;
                ProductActivity.obj=null;
                ProductActivity.obj=obj;
                ActivityManager.startActivity(view.getContext(),ProductActivity.class);

                Log.d("test:", "image view click");
            }

            if(view.getId()==R.id.ImgProduct2  ) {
                //   obj = trackableOrderList.get(getPosition());
                FproductTwoCol   mobj = productsList.get(getPosition());
                Product obj =new Product();
                obj.img=mobj.ProducttwoImg;
                obj.name=mobj.ProducttwoName;
                obj.price=mobj.ProducttwoPrice;
                ProductActivity.obj=null;
                ProductActivity.obj=obj;
                ActivityManager.startActivity(view.getContext(),ProductActivity.class);


                Log.d("test:", "image view click");
            }



            if(view.getId()==R.id.txt_name1) {
                Log.d("test:", "name click");
            }



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
