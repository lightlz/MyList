package com.light.myilists.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.light.myilists.model.TodoInfoBean;

import java.sql.SQLException;


public class DatabaseHelper extends OrmLiteSqliteOpenHelper {


    // 数据库名称
    private static final String DATABASE_NAME = "todolist.db";
    // 数据库版本
    private static final int DATABASE_VERSION = 1;

    private RuntimeExceptionDao<TodoInfoBean, Integer> simpleTodoDao;


    private static DatabaseHelper helper;

    public DatabaseHelper(Context context){
      super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    public static DatabaseHelper getHelper(Context context){

        helper = new DatabaseHelper(context);
        return helper;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {

        try {

            TableUtils.createTable(connectionSource, TodoInfoBean.class);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i2) {

        try {

            TableUtils.dropTable(connectionSource, TodoInfoBean.class, true);

            onCreate(sqLiteDatabase, connectionSource);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public RuntimeExceptionDao<TodoInfoBean, Integer> getSimpleTodoDao() {

        if (simpleTodoDao == null) {
            simpleTodoDao = getRuntimeExceptionDao(TodoInfoBean.class);
        }

        return simpleTodoDao;
    }

    public static void releaseHelper(){
        if(helper != null){
            OpenHelperManager.releaseHelper();
            helper = null;
        }
    }


}
