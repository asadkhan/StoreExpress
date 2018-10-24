package com.example.irfan.storeexpressagas.Adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.irfan.storeexpressagas.R;
import com.example.irfan.storeexpressagas.models.CategoryResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.ListViewHolder> {

    private List<CategoryResponse.catValue> categories;
    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageButton catImg;
        public TextView    txtCatName;


        private String mItem;
        private TextView mTextView;
        public ListViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);

            catImg = (ImageButton) view.findViewById(R.id.imgCat);
            txtCatName = (TextView) view.findViewById(R.id.txtCat);


        }



        @Override
        public void onClick(View view) {
            // Log.d("APITEST:", "onClick " + getPosition() + " " + mItem);



        }


    }


    public CategoryListAdapter(List<CategoryResponse.catValue> categoriesList) {
        this.categories = categoriesList;

    }



    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cate_list, parent, false);

        return new ListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        CategoryResponse.catValue catObj = categories.get(position);


        holder.txtCatName.setText(catObj.getName());


        // formatting the date appropriately.


            }

    @Override
    public int getItemCount() {
        return categories.size();
    }






}
