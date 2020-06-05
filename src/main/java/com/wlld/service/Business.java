package com.wlld.service;

import com.alibaba.fastjson.JSON;
import com.wlld.entity.*;
import org.wlld.imageRecognition.Operation;
import org.wlld.imageRecognition.Picture;
import org.wlld.imageRecognition.TempleConfig;
import org.wlld.imageRecognition.ThreeChannelMatrix;
import org.wlld.imageRecognition.segmentation.RegionBody;
import org.wlld.imageRecognition.segmentation.Specifications;
import org.wlld.nerveEntity.ModelParameter;

import java.util.ArrayList;
import java.util.List;

public class Business {

    public String study(Study study) throws Exception {//学习
        TempleConfig templeConfig = Temple.getTempleConfig();
        Response response = new Response();
        if (templeConfig != null) {
            Operation operation = new Operation(templeConfig);
            List<Specifications> specificationsList = Temple.get().getSpecificationsList();
            Picture picture = new Picture();
            List<StudyMessage> studyMessageList = study.getStudyMessages();
            if (studyMessageList != null && studyMessageList.size() > 0) {
                boolean isError = false;
                for (StudyMessage studyMessage : studyMessageList) {
                    List<String> urls = studyMessage.getUrls();
                    int id = studyMessage.getId();
                    for (String url : urls) {
                        ThreeChannelMatrix threeChannelMatrix1 = picture.getThreeMatrix(url);
                        try {
                            operation.colorStudy(threeChannelMatrix1, id, specificationsList);
                        } catch (Exception e) {
                            response.setError(ErrorMessage.Parameter_Error);
                            Temple.get().clear();//清除模板，重新激活
                            isError = true;
                            break;
                        }
                    }
                    if (isError) {
                        break;
                    }
                }
                if (!isError) {
                    templeConfig.finishStudy();
                    response.setError(ErrorMessage.ok);
                    response.setModelParameter(templeConfig.getModel());
                }
            } else {
                response.setError(ErrorMessage.Study_Message_Null);
            }
        } else {
            response.setError(ErrorMessage.TempleConfig_Not_Active);
        }
        return JSON.toJSONString(response);
    }

    public String active(Parameter parameter) throws Exception {//激活模板
        Response response = new Response();
        response.setError(ErrorMessage.ok);
        Temple.get().active(parameter);
        return JSON.toJSONString(response);
    }

    public String look(Look look) throws Exception {//识别
        String url = look.getUrl();
        Response response = new Response();
        if (url != null) {
            TempleConfig templeConfig = Temple.getTempleConfig();
            if (templeConfig != null) {
                Operation operation = new Operation(templeConfig);
                Picture picture = new Picture();
                List<Specifications> specificationsList = Temple.get().getSpecificationsList();
                ThreeChannelMatrix threeChannelMatrix = picture.getThreeMatrix(url);
                List<RegionBody> regionBody = operation.colorLook(threeChannelMatrix, specificationsList);
                response.setError(ErrorMessage.ok);
                response.setRegionBody(regionBody);
            } else {
                response.setError(ErrorMessage.TempleConfig_Not_Active);
            }
        } else {
            response.setError(ErrorMessage.Url_Null);
        }
        return JSON.toJSONString(response);
    }

    public String insertModel(ModelParameter modelParameter) throws Exception {
        Response response = new Response();
        if (modelParameter != null) {
            response.setError(ErrorMessage.ok);
            Temple.insertModel(modelParameter);
        } else {
            response.setError(ErrorMessage.Model_Null);
        }
        return JSON.toJSONString(response);
    }
}
