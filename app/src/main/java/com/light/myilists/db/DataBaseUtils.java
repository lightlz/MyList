package com.light.myilists.db;

import android.content.Context;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.light.myilists.model.TodoInfoBean;

import java.util.List;

/**
 * Created by light on 15/6/23.
 */
public class DataBaseUtils {


    /**
     * TODO(添加 todolist)
     * @param context
     * @param infoBean
     */
    public static void insertTodoList(Context context,TodoInfoBean infoBean){

        RuntimeExceptionDao<TodoInfoBean, Integer> simpleTodoDao = DatabaseHelper.getHelper(context)
                .getSimpleTodoDao();

        simpleTodoDao.create(infoBean);
    }


    public static List<TodoInfoBean> queryStudent(Context context){

        RuntimeExceptionDao<TodoInfoBean, Integer> simpleTodoDao = DatabaseHelper.getHelper(context)
                .getSimpleTodoDao();

        List<TodoInfoBean> list = simpleTodoDao.queryForAll();
        return list;
    }




}
