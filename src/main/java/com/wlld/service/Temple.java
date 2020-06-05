package com.wlld.service;

import com.wlld.entity.Parameter;
import org.wlld.config.Classifier;
import org.wlld.config.StudyPattern;
import org.wlld.imageRecognition.TempleConfig;
import org.wlld.imageRecognition.segmentation.Specifications;
import org.wlld.nerveEntity.ModelParameter;
import org.wlld.param.Cutting;
import org.wlld.param.Food;

import java.util.List;

public class Temple {
    private static Temple Temple = new Temple();
    private static TempleConfig templeConfig;
    private static double maxRain = 320;
    private static double th = 0.88;
    private static int regionNub = 200;
    private static int knnNub = 3;
    private static int poolSize = 2;
    private static int featureNub = 3;
    private static int shrink = 60;
    private static int times = 2;
    private List<Specifications> specificationsList;

    public List<Specifications> getSpecificationsList() {
        return specificationsList;
    }

    public void clear() {//模板清除
        templeConfig = null;
    }

    public static TempleConfig getTempleConfig() {
        return templeConfig;
    }

    public static void insertModel(ModelParameter modelParameter) throws Exception {
        if (templeConfig != null) {
            templeConfig.insertModel(modelParameter);
        } else {
            throw new Exception("templeConfig is not active");
        }
    }

    public void active(Parameter parameter) throws Exception {
        maxRain = parameter.getCutting().getMaxRain();
        th = parameter.getCutting().getTh();
        regionNub = parameter.getCutting().getRegionNub();
        knnNub = parameter.getKnnNub();
        poolSize = parameter.getPoolSize();
        featureNub = parameter.getFeatureNub();
        shrink = parameter.getFood().getShrink();
        times = parameter.getFood().getTimes();
        specificationsList = parameter.getSpecificationsList();
        start();
    }

    private void start() throws Exception {//模型参数重置，重新启动
        templeConfig = new TempleConfig();
        Cutting cutting = templeConfig.getCutting();
        Food food = templeConfig.getFood();
        //切割
        cutting.setMaxRain(maxRain);//切割阈值
        cutting.setTh(th);
        cutting.setRegionNub(regionNub);
        //knn参数
        templeConfig.setKnnNub(knnNub);
        //池化比例
        templeConfig.setPoolSize(poolSize);//缩小比例
        //聚类
        templeConfig.setFeatureNub(featureNub);//聚类特征数量
        //菜品识别实体类
        food.setShrink(shrink);//缩紧像素
        food.setTimes(times);//聚类数据增强
        templeConfig.setClassifier(Classifier.KNN);
        templeConfig.init(StudyPattern.Cover_Pattern, true, 400, 400, 3);
    }

    private Temple() {
    }

    public static Temple get() {
        return Temple;
    }
}
