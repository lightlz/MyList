package com.light.myilists.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by light on 15/6/23.
 */
@DatabaseTable(tableName="todoinfo")
public class TodoInfoBean {

    @DatabaseField(generatedId = true,allowGeneratedIdInsert = true)
    private int todo_id;
    @DatabaseField(canBeNull = false)
    private String created_time;
    @DatabaseField(canBeNull = false)
    private String content;
    @DatabaseField(canBeNull = false)
    private int priority;

    public TodoInfoBean(int todo_id, String created_time, String content, int priority) {
        this.todo_id = todo_id;
        this.created_time = created_time;
        this.content = content;
        this.priority = priority;
    }

    public TodoInfoBean() {

    }

    public int getTodo_id() {
        return todo_id;
    }

    public void setTodo_id(int todo_id) {
        this.todo_id = todo_id;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
