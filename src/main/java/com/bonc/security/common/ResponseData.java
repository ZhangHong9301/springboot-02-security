package com.bonc.security.common;

import com.bonc.security.common.utils.StringUtil;
import com.github.pagehelper.Page;
import lombok.Data;


import java.util.HashMap;
import java.util.Map;

/**
 * @author JokerJodas
 * @date 2018.6.11
 */
@Data
public class ResponseData<T> {


    private int code;
    private String msg;
    private T data;


    /**
     * 仅反馈成功，无详细数据，请用这个
     */
    public static final ResponseData SUCCESS = new ResponseData(ResponseEnum.SUCCESS);

    /**
     * 未知错误，无详细数据，请用这个
     */
    public static final ResponseData FAILURE = new ResponseData(ResponseEnum.FAILURE);


    /**
     * 异常码反馈
     *
     * @param responseEnum
     */
    public ResponseData(ResponseEnum responseEnum) {
        this.code = responseEnum.getCode();
        this.msg = responseEnum.getMsg();
    }

    /**
     * 成功消息
     *
     * @param data
     */
    public ResponseData(T data) {
        this(ResponseEnum.SUCCESS, data);
    }

    /**
     * 异常时还需要数据的情况
     *
     * @param responseEnum
     * @param data
     */
    public ResponseData(ResponseEnum responseEnum, T data) {
        this.code = responseEnum.getCode();
        this.msg = responseEnum.getMsg();
        this.data = data;
    }

    /**
     * 成功消息,含
     *
     * @param map
     */
    public static String success(Object map) {
        Map<String, Object> resMap = new HashMap<>();
        if (map instanceof Page) {
            resMap.put("code", ResponseEnum.SUCCESS.getCode());
            resMap.put("msg", ResponseEnum.SUCCESS.getMsg());
            resMap.put("totalCount", ((Page) map).getTotal());
            resMap.put("data", map);
        } else {
            resMap.put("code", ResponseEnum.SUCCESS.getCode());
            resMap.put("msg", ResponseEnum.SUCCESS.getMsg());
            resMap.put("data", map);
        }
        return StringUtil.objectToString(resMap);
    }

    /**
     * 成功消息
     *
     * @param
     */
    public static String success() {
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("code", ResponseEnum.SUCCESS.getCode());
        resMap.put("msg", ResponseEnum.SUCCESS.getMsg());
        return StringUtil.objectToString(resMap);
    }

    /**
     * 失败消息，但是不回滚事务
     *
     * @param
     */
    public static String failure(ResponseEnum cre) {
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("code", cre.getCode());
        resMap.put("msg", cre.getMsg());
        return StringUtil.objectToString(resMap);
    }


}


