package com.nanda.swiperecyclerview;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.nanda.swiperecyclerview.adapter.SwipeRecyclerviewAdapter;
import com.nanda.swiperecyclerview.model.UserItem;
import com.tubb.smrv.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SwipeMenuRecyclerView recyclerView;

    private SwipeRecyclerviewAdapter adapter;
    private List<UserItem> userItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new SwipeRecyclerviewAdapter(this);
        recyclerView = findViewById(R.id.swipe_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(null);
        recyclerView.setAdapter(adapter);

        userItemList = fetchUserList();
        if (userItemList != null && userItemList.size() > 0) {
            adapter.setUserItemList(userItemList);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    openMenuAnimation();
                }
            }, 200);
        }
    }

    private void openMenuAnimation() {
        RecyclerView.ViewHolder holder = recyclerView.findViewHolderForLayoutPosition(0);
        SwipeRecyclerviewAdapter.SwipeViewHolder swipeViewHolder = (SwipeRecyclerviewAdapter.SwipeViewHolder) holder;
        View view = swipeViewHolder.itemView;
        swipeViewHolder.onClick(view);
    }

    private List<UserItem> fetchUserList() {
        List<UserItem> userItemList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            userItemList.add(new UserItem("Test - " + i));
        }
        return userItemList;
    }

}
