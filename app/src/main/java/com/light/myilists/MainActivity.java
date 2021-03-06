package com.light.myilists;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.light.myilists.activity.EditTodoActivity;
import com.light.myilists.adapter.TodoListAdapter;
import com.light.myilists.db.DataBaseUtils;
import com.light.myilists.http.HttpRequestMap;
import com.light.myilists.http.HttpResponse;
import com.light.myilists.model.TodoInfoBean;
import com.light.myilists.model.VersionBean;
import com.light.myilists.utils.Constant;
import com.light.myilists.utils.VersionUtils;
import com.light.myilists.volley.VolleyHttp;
import com.light.myilists.widget.CustomItemAnimator;
import com.light.myilists.widget.SwipeAddLayout;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private SwipeAddLayout swipeAddLayout;

    private RecyclerView recyclerView;

    private TodoListAdapter adapter;

    private List<TodoInfoBean> todoInfoList;

    private TextView tvTip;

    private RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //you meng
        MobclickAgent.updateOnlineConfig(this);



        initView();
    }

    private void initView(){

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        layout = (RelativeLayout)findViewById(R.id.layout_main);
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            setTranslucentStatus(true);
//        }
//
//        SystemBarTintManager tintManager = new SystemBarTintManager(this);
//        tintManager.setStatusBarTintEnabled(true);
//        tintManager.setStatusBarTintResource(R.color.cyan800);
//        SystemBarTintManager.SystemBarConfig config = tintManager.getConfig();
//        layout.setPadding(0, config.getPixelInsetTop(true), 0, config.getPixelInsetBottom());

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

        tvTip = (TextView)findViewById(R.id.tv_tip_main);

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
                case Constant.MSG_DISPLAY_TIP:
                    tvTip.setVisibility(View.VISIBLE);
                    break;

                case Constant.TYPE_VERSION:
                    String response = msg.obj.toString();
                    Log.v("response handler : ",response);
                    handlerUpdate(response);
                    break;
            }
        }
    };


    private void refreshData(){
        todoInfoList = DataBaseUtils.queryTodoList(this);
        adapter.addInfo(todoInfoList);

        if(todoInfoList.size() == 0){
            tvTip.setVisibility(View.VISIBLE);
        }else{
            tvTip.setVisibility(View.GONE);
        }
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


        switch(id){
            case R.id.action_update:
                VolleyHttp.post(this,Constant.URL_VERSION,
                        HttpRequestMap.versionMap(this),handler,Constant.TYPE_VERSION);
                break;
            case R.id.action_exit:
                MainActivity.this.finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void handlerUpdate(String versionResponse){

        VersionBean bean =  HttpResponse.handlerVersionRet(versionResponse);

        if(VersionUtils.isUpdate(this,bean.getVersionCode())){
            VersionUtils. displayUpdateDialog(this,bean.getContent(),bean.getUrl());
        }else{
            Toast.makeText(this,this.getString(R.string.tip_last_version),Toast.LENGTH_SHORT).show();
        }

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
            }, 500);

        }

    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }



    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }



}
