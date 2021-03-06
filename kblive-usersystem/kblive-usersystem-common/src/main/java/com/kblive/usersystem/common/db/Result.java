package com.kblive.usersystem.common.db;

import java.io.Serializable;
import java.util.List;

/**
 * title: BaseQuery
 * projectName kbLive
 * description: 分页查询结果
 * author 2671242147@qq.com
 * date 2019-06-29 15:00
 ***/
public class Result<T> implements Serializable {
    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 1L;

    private boolean isSuccess = true;
    private List<T> list;
    private int count;
    private String errorMsg;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

}
