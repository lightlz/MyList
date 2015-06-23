package com.light.myilists;

import android.content.Intent;
import android.os.Bundle;
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
import com.light.myilists.widget.SwipeAddLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private SwipeAddLayout swipeAddLayout;

    private RecyclerView recyclerView;

    private RecyclerView.Adapter adapter;

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
                startActivity(intent);

                swipeAddLayout.setRefreshing(false);
            }
        });

        initData();

        todoInfoList = DataBaseUtils.queryStudent(this);
        adapter = new TodoListAdapter(this,null,todoInfoList);

        recyclerView = (RecyclerView)findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

    private void initData(){

        todoInfoList = new ArrayList<TodoInfoBean>();

        TodoInfoBean bean = new TodoInfoBean(0,"6 / 7","超市买被子",0);
        TodoInfoBean bean1 = new TodoInfoBean(0,"12 / 7","校对 Android Source",1);
        TodoInfoBean bean2 = new TodoInfoBean(0,"11 / 7","帮小明拍照",2);
        TodoInfoBean bean3 = new TodoInfoBean(0,"4 / 7","学校调档案",3);
        TodoInfoBean bean4 = new TodoInfoBean(0,"1 / 7","完成表格应用",4);

        todoInfoList.add(bean);
        todoInfoList.add(bean1);
        todoInfoList.add(bean2);
        todoInfoList.add(bean3);
        todoInfoList.add(bean4);

        DataBaseUtils.insertTodoList(this,bean);
        DataBaseUtils.insertTodoList(this,bean1);

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
}
