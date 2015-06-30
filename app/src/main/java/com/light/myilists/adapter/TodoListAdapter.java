package com.light.myilists.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.light.myilists.R;
import com.light.myilists.db.DataBaseUtils;
import com.light.myilists.model.TodoInfoBean;
import com.light.myilists.utils.Constant;
import com.light.myilists.utils.DateUtils;

import java.util.List;

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

    public void updateInfo(List<TodoInfoBean> infoBeans){
        list = infoBeans;
        this.notifyDataSetChanged();
    }

    public void addInfo(List<TodoInfoBean> infoBeans){
        list.addAll(infoBeans);
        this.notifyItemRangeInserted(0, list.size() - 1);
    }

    public void clearApplications() {
        int size = this.list.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                list.remove(0);
            }

            this.notifyItemRangeRemoved(0, size);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        final int index = i;

        viewHolder.tvCreatedTime.setText(DateUtils.getDate(Long.parseLong(list.get(i).getCreated_time())));
        viewHolder.tvCreatedTime.setBackgroundResource(Constant.PRIORITY_COLOR[list.get(index).getPriority()]);
        viewHolder.tvContent.setText(list.get(i).getContent());

        viewHolder.tvContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Message msg = new Message();
                msg.what = Constant.MSG_TODOLIST_CLICK;
                msg.arg1 = index;
                handler.sendMessage(msg);

            }
        });

        viewHolder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBaseUtils.deleteTodoList(context, list.get(index));
                list.remove(index);
                updateInfo(list);

                //删除完之后，需要显示tip
                if(list.size() == 0){
                    handler.sendEmptyMessage(Constant.MSG_DISPLAY_TIP);
                }
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

        private ImageView imgDelete;

        public ViewHolder(View itemView) {
            super(itemView);

            tvCreatedTime = (TextView)itemView.findViewById(R.id.tv_created_time);
            tvContent = (TextView)itemView.findViewById(R.id.tv_content);
            imgDelete = (ImageView)itemView.findViewById(R.id.img_delete);

        }
    }


}
