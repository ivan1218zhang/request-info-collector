package com.zyf.request.info.collector.core.collector.exception;

/**
 * @author IvanZhang
 */
public class FiledNameException extends Throwable {

    public FiledNameException(String s) {
        super("收集的fieldName重复 重复的fieldName为:"+s);
    }
}
