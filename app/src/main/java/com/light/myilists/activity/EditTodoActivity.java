package com.light.myilists.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.light.myilists.R;
import com.light.myilists.utils.Constant;

/**
 * Created by light on 15/6/23.
 */
public class EditTodoActivity extends Activity {

    public static final String ITEM_PRIORITY = "priority";

    public static final String ITME_CONTENT = "content";

    private int itemPriority;

    private String itemContent;

    private EditText tvContent;

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
    }


}
