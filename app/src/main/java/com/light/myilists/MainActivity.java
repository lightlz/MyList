package com.light.myilists;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.light.myilists.activity.EditTodoActivity;
import com.light.myilists.adapter.TodoListAdapter;
import com.light.myilists.db.DataBaseUtils;
import com.light.myilists.model.TodoInfoBean;
import com.light.myilists.utils.Constant;
import com.light.myilists.widget.CustomItemAnimator;
import com.light.myilists.widget.SwipeAddLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private SwipeAddLayout swipeAddLayout;

    private RecyclerView recyclerView;

    private TodoListAdapter adapter;

    private List<TodoInfoBean> todoInfoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        swipeAddLayout = (SwipeAddLayout)findViewById(R.id.swipe_container);
        swipeAddLayout.setColorSchemeColors(getResources().getColor(R.color.cyan700));

        swipeAddLayout.setOnRefreshListener(new SwipeAddLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                Intent intent = new Intent(MainActivity.this, EditTodoActivity.class);
                intent.putExtra(EditTodoActivity.ITEM_PRIORITY, 0);
                intent.putExtra(EditTodoActivity.ITME_CONTENT, "");
                startActivityForResult(intent,EditTodoActivity.INTENT_ADD);

                swipeAddLayout.setRefreshing(false);
            }
        });


        todoInfoList = new ArrayList<TodoInfoBean>();
        adapter = new TodoListAdapter(this,handler,todoInfoList);

        recyclerView = (RecyclerView)findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new CustomItemAnimator());
        recyclerView.setAdapter(adapter);


        handler.postAtTime(new Runnable() {
            @Override
            public void run() {
                handler.sendEmptyMessage(Constant.MSG_COMMON);
            }
        },500);

    }

    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch(msg.what){
                case Constant.MSG_COMMON:
                    refreshData();
                    break;
                case Constant.MSG_TODOLIST_CLICK:

                    int index = msg.arg1;
                    Intent intent = new Intent(MainActivity.this, EditTodoActivity.class);
                    intent.putExtra(EditTodoActivity.ITEM_PRIORITY,todoInfoList.get(index).getPriority());
                    intent.putExtra(EditTodoActivity.ITME_CONTENT,todoInfoList.get(index).getContent());
                    intent.putExtra(EditTodoActivity.ITEM_ID,todoInfoList.get(index).getTodo_id());
                    startActivityForResult(intent,EditTodoActivity.INTENT_ADD);

                    break;
            }
        }
    };


    private void refreshData(){
        todoInfoList = DataBaseUtils.queryTodoList(this);
        adapter.addInfo(todoInfoList);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == EditTodoActivity.INTENT_ADD){
            adapter.clearApplications();

            handler.postAtTime(new Runnable() {
                @Override
                public void run() {
                    handler.sendEmptyMessage(Constant.MSG_COMMON);
                }
            },500);

        }

    }
}
