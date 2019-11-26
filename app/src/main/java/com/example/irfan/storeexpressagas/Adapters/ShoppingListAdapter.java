package com.example.irfan.storeexpressagas.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.irfan.storeexpressagas.R;
import com.example.irfan.storeexpressagas.activities.CartActivity;
import com.example.irfan.storeexpressagas.models.Cart;
import com.example.irfan.storeexpressagas.models.ShoppingList;
import com.squareup.picasso.Picasso;

import java.util.List;



public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ListViewHolder>{

    private Context mContext;

    public void setItemListAdapterContext(Context context){


        this.mContext=context;
    }
    private List<ShoppingList> itemList;
    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView txt_name;
        public Button btn_delete,btn_edit;


        public ListViewHolder(View view) {
            super(view);
            //view.setOnClickListener(this);


            txt_name = (TextView) view.findViewById(R.id.txt_name);

            btn_delete = (Button) view.findViewById(R.id.btn_delete);
            btn_edit = (Button) view.findViewById(R.id.btn_edit);
            btn_delete.setOnClickListener(this);
            btn_edit.setOnClickListener(this);

        }



        @Override
        public void onClick(View view) {
            // Log.d("APITEST:", "onClick " + getPosition() + " " + mItem);
            ShoppingList obj = itemList.get(getPosition());

            switch (view.getId()) {

                case R.id.txt_name:
                    break;

                case R.id.btn_edit:
                    break;

                case R.id.btn_delete:
                    break;



            }



        }


    }





    public ShoppingListAdapter(List<ShoppingList> itemlist) {
        this.itemList = itemlist;

    }



    @Override
    public ShoppingListAdapter.ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.shopping_list_name, parent, false);

        return new ShoppingListAdapter.ListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ShoppingListAdapter.ListViewHolder holder, int position) {
        ShoppingList obj = itemList.get(position);



        holder.txt_name.setText(obj.Name);



    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }





}
