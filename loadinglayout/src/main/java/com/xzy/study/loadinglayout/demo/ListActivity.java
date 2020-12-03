/*
 * Copyright 2016 czy1121
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xzy.study.loadinglayout.demo;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.xzy.study.loadinglayout.R;
import com.xzy.study.loadinglayout.lib.LoadingLayout;


public class ListActivity extends AppCompatActivity implements View.OnClickListener  {


    int mCount = 10;

    LoadingLayout vLoading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        final RecyclerView list = (RecyclerView) findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                TextView view = new TextView(parent.getContext());
                view.setGravity(Gravity.CENTER);
                view.setPadding(40, 40, 40, 40);
                return new ItemHolder(view);
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

                ((TextView)holder.itemView).setText("text " + position);
            }

            @Override
            public int getItemCount() {
                return mCount;
            }
        });

        vLoading = LoadingLayout.wrap(list);
        vLoading.setRetryListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "retry" , Toast.LENGTH_LONG).show();
            }
        });
        vLoading.showContent();

        final SwipeRefreshLayout refresh = (SwipeRefreshLayout)findViewById(R.id.refresh);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mCount += 10;
                // mock error
                if(mCount == 20) {
                    mCount = 0;
                    vLoading.showEmpty();
                }else{
                    vLoading.showContent();
                }
                list.getAdapter().notifyDataSetChanged();
                refresh.setRefreshing(false);

            }
        });
     }


    @Override
    public void onClick(View v) {
    }

    public static class ItemHolder extends RecyclerView.ViewHolder {
        public ItemHolder(View view) {
            super(view);
        }
    }
}
