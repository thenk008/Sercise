package com.wlld.entity;

import org.wlld.imageRecognition.segmentation.RegionBody;
import org.wlld.nerveEntity.ModelParameter;

import java.util.List;

public class Response {
    public int error;
    public ModelParameter modelParameter;
    public List<RegionBody> regionBody;

    public List<RegionBody> getRegionBody() {
        return regionBody;
    }

    public void setRegionBody(List<RegionBody> regionBody) {
        this.regionBody = regionBody;
    }

    public ModelParameter getModelParameter() {
        return modelParameter;
    }

    public void setModelParameter(ModelParameter modelParameter) {
        this.modelParameter = modelParameter;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }
}
