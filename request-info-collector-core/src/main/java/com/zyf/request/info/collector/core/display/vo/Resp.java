package com.zyf.request.info.collector.core.display.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author IvanZhang
 */
public class Resp implements Serializable {
    private String resCode;
    private String resMsg;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date resTime;
    private Object data;

    public Resp(String resCode, Object data) {
        this.resCode = resCode;
        this.data = data;
        this.resTime = new Date();
    }

    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    public String getResMsg() {
        return resMsg;
    }

    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }

    public Date getResTime() {
        return resTime;
    }

    public void setResTime(Date resTime) {
        this.resTime = resTime;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
