package com.wlld.entity;

import org.wlld.imageRecognition.segmentation.Specifications;
import org.wlld.param.Cutting;
import org.wlld.param.Food;

import java.util.List;

public class Parameter {
    private List<Specifications> specificationsList;
    private Cutting cutting;
    private Food food;
    private int knnNub;
    private int poolSize;
    private int featureNub;

    public List<Specifications> getSpecificationsList() {
        return specificationsList;
    }

    public void setSpecificationsList(List<Specifications> specificationsList) {
        this.specificationsList = specificationsList;
    }

    public Cutting getCutting() {
        return cutting;
    }

    public void setCutting(Cutting cutting) {
        this.cutting = cutting;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public int getKnnNub() {
        return knnNub;
    }

    public void setKnnNub(int knnNub) {
        this.knnNub = knnNub;
    }

    public int getPoolSize() {
        return poolSize;
    }

    public void setPoolSize(int poolSize) {
        this.poolSize = poolSize;
    }

    public int getFeatureNub() {
        return featureNub;
    }

    public void setFeatureNub(int featureNub) {
        this.featureNub = featureNub;
    }
}
