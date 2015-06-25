package com.light.myilists.db;

import android.content.Context;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.light.myilists.model.TodoInfoBean;

import java.sql.SQLException;
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


    /**
     * TODO(获取全部 todolist)
     * @param context
     * @return
     */
    public static List<TodoInfoBean> queryTodoList(Context context){

        RuntimeExceptionDao<TodoInfoBean, Integer> simpleTodoDao = DatabaseHelper.getHelper(context)
                .getSimpleTodoDao();

        QueryBuilder queryBuilder = simpleTodoDao.queryBuilder();
        queryBuilder.orderBy("priority",true);
        List<TodoInfoBean> list = null;
        try {
            list = queryBuilder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * TODO(更新 todoList)
     * @param context
     * @param infoBean
     */
    public static void updateTodoList(Context context,TodoInfoBean infoBean){

        RuntimeExceptionDao<TodoInfoBean, Integer> simpleTodoDao = DatabaseHelper.getHelper(context)
                .getSimpleTodoDao();
        simpleTodoDao.update(infoBean);
    }


}
