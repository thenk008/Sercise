package com.wlld.controller;

import com.alibaba.fastjson.JSONObject;
import com.shareData.chainMarket.i.Central;
import com.wlld.entity.Look;
import com.wlld.entity.Parameter;
import com.wlld.entity.Study;
import com.wlld.service.Business;
import org.wlld.nerveEntity.ModelParameter;

import java.util.Map;

@Central(url = "/ai")
public class Controller extends Business {

    @Central(url = "/study")//进行学习
    public String study(String message, Map<Object, Object> map) throws Exception {
        Study study = JSONObject.parseObject(message, Study.class);
        return study(study);
    }

    @Central(url = "/active")//激活模板
    public String active(String message, Map<Object, Object> map) throws Exception {
        Parameter parameter = JSONObject.parseObject(message, Parameter.class);
        return active(parameter);
    }

    @Central(url = "/look")
    public String look(String message, Map<Object, Object> map) throws Exception {
        Look look = JSONObject.parseObject(message, Look.class);
        return look(look);
    }

    @Central(url = "/insertModel")
    public String insertModel(String message, Map<Object, Object> map) throws Exception {
        ModelParameter modelParameter = JSONObject.parseObject(message, ModelParameter.class);
        return insertModel(modelParameter);
    }

}
