package com.light.myilists.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.light.myilists.R;
import com.light.myilists.activity.EditTodoActivity;
import com.light.myilists.model.TodoInfoBean;
import com.light.myilists.utils.Constant;

import java.util.List;
import java.util.logging.Handler;

/**
 * Created by light on 15/6/23.
 */
public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.ViewHolder> {

    private Context context;

    private Handler handler;

    private List<TodoInfoBean> list;


    public TodoListAdapter(Context context, Handler handler, List<TodoInfoBean> list) {
        this.context = context;
        this.handler = handler;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        final int index = i;

        viewHolder.tvCreatedTime.setText(list.get(i).getCreated_time());
        viewHolder.tvCreatedTime.setBackgroundResource(Constant.PRIORITY_COLOR[list.get(index).getPriority()]);
        viewHolder.tvContent.setText(list.get(i).getContent());

        viewHolder.tvContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, EditTodoActivity.class);
                intent.putExtra(EditTodoActivity.ITEM_PRIORITY,list.get(index).getPriority());
                intent.putExtra(EditTodoActivity.ITME_CONTENT,list.get(index).getContent());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvCreatedTime;

        private TextView tvContent;

        public ViewHolder(View itemView) {
            super(itemView);

            tvCreatedTime = (TextView)itemView.findViewById(R.id.tv_created_time);
            tvContent = (TextView)itemView.findViewById(R.id.tv_content);
        }
    }
}
