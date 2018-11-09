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
import com.example.irfan.storeexpressagas.models.Cart;
import com.example.irfan.storeexpressagas.models.CategoryResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartItemListAdapter extends RecyclerView.Adapter<CartItemListAdapter.ListViewHolder>{

    private List<Cart> itemList;
    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView img;
        public TextView txtItemName,txtQtyBox,txtItemPrice,txtItemRemove;
        public Button btnPlus,btnMinus;

        private String mItem;
        private TextView mTextView;
        public ListViewHolder(View view) {
            super(view);
            //view.setOnClickListener(this);

            img = (ImageView) view.findViewById(R.id.cartitem_img);
            txtItemName = (TextView) view.findViewById(R.id.txt_cartitem_name);
            txtQtyBox = (TextView) view.findViewById(R.id.txt_cartitem_qty_box);
            txtItemPrice = (TextView) view.findViewById(R.id.txt_cartitem_price);
            txtItemRemove = (TextView) view.findViewById(R.id.txt_cartitem_remove);
            btnPlus = (Button) view.findViewById(R.id.btn_cart_plus);
            btnMinus = (Button) view.findViewById(R.id.btn_cartitem_minus);
            txtItemRemove.setOnClickListener(this);
            btnMinus.setOnClickListener(this);
            btnPlus.setOnClickListener(this);

        }



        @Override
        public void onClick(View view) {
            // Log.d("APITEST:", "onClick " + getPosition() + " " + mItem);

            switch (view.getId()) {
                case R.id.btn_cart_plus:


                    break;

                case R.id.btn_cartitem_minus:
                    Log.d("test","show msg call");
                    //  showMessageDailogNextScreen("test","testing message",Login.class);

                    break;

                case R.id.txt_cartitem_remove:
                    Log.d("test","show msg call");
                    //  showMessageDailogNextScreen("test","testing message",Login.class);
                    int newPosition = getAdapterPosition();
                    Cart.removeCartITem(itemList.get(newPosition).ItemID,view.getContext());
                    itemList.remove(newPosition);
                    notifyItemRemoved(newPosition);
                    notifyItemRangeChanged(newPosition, itemList.size());

                    break;



            }



        }


    }





    public CartItemListAdapter(List<Cart> itemlist) {
        this.itemList = itemlist;

    }



    @Override
    public CartItemListAdapter.ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_item, parent, false);

        return new CartItemListAdapter.ListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CartItemListAdapter.ListViewHolder holder, int position) {
        Cart cartitem = itemList.get(position);


        String imgURL=cartitem.ItemImg;
        holder.txtItemName.setText(cartitem.ItemName);
        holder.txtQtyBox.setText(String.valueOf(cartitem.ItemQty));
        holder.txtItemPrice.setText(String.valueOf(cartitem.ItemPrice));


        Picasso.with(holder.img.getContext()).load(imgURL).resize(250, 250).centerCrop().into(holder.img);

        // formatting the date appropriately.


    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }





}
