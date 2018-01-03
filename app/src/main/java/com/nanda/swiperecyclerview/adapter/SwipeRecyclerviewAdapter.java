package com.nanda.swiperecyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.nanda.swiperecyclerview.R;
import com.nanda.swiperecyclerview.model.UserItem;
import com.tubb.smrv.SwipeHorizontalMenuLayout;

import java.util.ArrayList;
import java.util.List;

public class SwipeRecyclerviewAdapter extends RecyclerView.Adapter<SwipeRecyclerviewAdapter.SwipeViewHolder> {

    private Context context;
    private List<UserItem> userItemList;
    private LayoutInflater inflater;

    public SwipeRecyclerviewAdapter(Context context) {
        this.context = context;
        userItemList = new ArrayList<>();
        inflater = LayoutInflater.from(context);
    }

    public void setUserItemList(List<UserItem> itemList) {
        if (itemList == null) {
            return;
        }
        userItemList.clear();
        userItemList.addAll(itemList);
        notifyDataSetChanged();
    }

    @Override
    public SwipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_swipe, parent, false);
        return new SwipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SwipeViewHolder holder, int position) {
        UserItem userItem = userItemList.get(position);
        holder.bindDataToView(userItem);
    }

    @Override
    public int getItemCount() {
        return userItemList.size();
    }

    public class SwipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Button btnTest;
        private TextView tvName;
        private SwipeHorizontalMenuLayout swipeMenuLayout;

        public SwipeViewHolder(View itemView) {
            super(itemView);
            swipeMenuLayout = (SwipeHorizontalMenuLayout) itemView;
            tvName = itemView.findViewById(R.id.tvName);
            btnTest = itemView.findViewById(R.id.btn_test);
            btnTest.setOnClickListener(this);
        }

        public void bindDataToView(UserItem item) {
            tvName.setText(item.getUserName());
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position < 0)
                return;
            swipeMenuLayout.smoothOpenEndMenu();
        }
    }

}
