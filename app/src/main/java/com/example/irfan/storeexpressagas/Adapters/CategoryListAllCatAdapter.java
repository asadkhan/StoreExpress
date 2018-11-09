package com.example.irfan.storeexpressagas.Adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.irfan.storeexpressagas.R;
import com.example.irfan.storeexpressagas.activities.ProductActivity;
import com.example.irfan.storeexpressagas.activities.ProductsListActivity;
import com.example.irfan.storeexpressagas.extras.ActivityManager;
import com.example.irfan.storeexpressagas.models.CategoryResponse;
import com.example.irfan.storeexpressagas.models.FproductResponse;
import com.example.irfan.storeexpressagas.models.FproductTwoCol;
import com.example.irfan.storeexpressagas.models.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryListAllCatAdapter  extends RecyclerView.Adapter<CategoryListAllCatAdapter.ListViewHolder>{

    private List<CategoryResponse.catValue> categories;
    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public Button btnallcat;

        private String mItem;

        public ListViewHolder(View view) {
            super(view);
           // view.setOnClickListener(this);

            btnallcat = (Button) view.findViewById(R.id.btn_allcat);
            btnallcat.setOnClickListener(this);
        }



        @Override
        public void onClick(View view) {
            Log.d("test", "All Catclick");
            CategoryResponse.catValue obj =categories.get(getPosition());

            ProductsListActivity.catName=obj.getName();
            ActivityManager.startActivity(view.getContext(),ProductsListActivity.class);




        }


    }


    public CategoryListAllCatAdapter(List<CategoryResponse.catValue> catLst) {
        this.categories = catLst;

    }



    @Override
    public CategoryListAllCatAdapter.ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.all_cate_btn, parent, false);

        return new CategoryListAllCatAdapter.ListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CategoryListAllCatAdapter.ListViewHolder holder, int position) {

        CategoryResponse.catValue catObj = categories.get(position);

        String imgURL=catObj.getImage();
        holder.btnallcat.setText(catObj.getName());

        //Picasso.with(holder.catImg.getContext()).load(imgURL).resize(60, 60).centerCrop().into(holder.catImg);

        // formatting the date appropriately.


    }

    @Override
    public int getItemCount() {
        return categories.size();
    }




}
