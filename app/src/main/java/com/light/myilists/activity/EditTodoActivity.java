package com.light.myilists.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.light.myilists.R;
import com.light.myilists.db.DataBaseUtils;
import com.light.myilists.model.TodoInfoBean;
import com.light.myilists.utils.Constant;

/**
 * Created by light on 15/6/23.
 */
public class EditTodoActivity extends Activity implements View.OnClickListener{

    public static final String ITEM_PRIORITY = "priority";

    public static final String ITME_CONTENT = "content";

    public static final int INTENT_ADD = 0;

    private int itemPriority;

    private String itemContent;

    private EditText tvContent;

    private FloatingActionButton fab;
    private FloatingActionButton fab1;
    private FloatingActionButton fab2;
    private FloatingActionButton fab3;
    private FloatingActionButton fab4;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edittodo);

        initView();
    }

    private void initView(){

        tvContent = (EditText)findViewById(R.id.et_content);

        Intent intent = getIntent();
        itemContent = intent.getStringExtra(ITME_CONTENT);
        itemPriority = intent.getIntExtra(ITEM_PRIORITY,-1);

        tvContent.setText(itemContent);
        tvContent.setBackgroundResource(Constant.PRIORITY_COLOR[itemPriority]);

        fab = (FloatingActionButton)findViewById(R.id.action_a);
        fab.setOnClickListener(this);
        fab1 = (FloatingActionButton)findViewById(R.id.action_b);
        fab1.setOnClickListener(this);
        fab2 = (FloatingActionButton)findViewById(R.id.action_c);
        fab2.setOnClickListener(this);
        fab3 = (FloatingActionButton)findViewById(R.id.action_d);
        fab3.setOnClickListener(this);
        fab4 = (FloatingActionButton)findViewById(R.id.action_e);
        fab4.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.action_a:
                itemPriority = 0;
                insertInfo();
                break;
            case R.id.action_b:
                itemPriority = 1;
                insertInfo();
                break;
            case R.id.action_c:
                itemPriority = 2;
                insertInfo();
                break;
            case R.id.action_d:
                itemPriority = 3;
                insertInfo();
                break;
            case R.id.action_e:
                itemPriority = 4;
                insertInfo();
                break;
        }

    }

    private void insertInfo(){

        itemContent = tvContent.getText().toString();
        long createTime = System.currentTimeMillis();

        TodoInfoBean bean = new TodoInfoBean();
        bean.setContent(itemContent);
        bean.setCreated_time(String.valueOf(createTime));
        bean.setPriority(itemPriority);

        DataBaseUtils.insertTodoList(this, bean);

        setResult(INTENT_ADD);
        this.finish();
    }
}
