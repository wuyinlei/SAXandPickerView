package com.example.xmlanalyze.city.model;

import java.util.List;
/**
 * Created by wuyinlei on 2015/12/13.
 * 一个懂得了编程乐趣的小白，希望自己
 * 能够在这个道路上走的很远，也希望自己学习到的
 * 知识可以帮助更多的人,分享就是学习的一种乐趣
 * QQ:1069584784
 * csdn:http://blog.csdn.net/wuyinlei
 */
public class CityModel {

    private String name;
    private List<DistrictModel> mDistricModels;

    public CityModel(){super();}

    public CityModel(String name, List<DistrictModel> districModels) {
        this.name = name;
        mDistricModels = districModels;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DistrictModel> getDistricModels() {
        return mDistricModels;
    }

    public void setDistricModels(List<DistrictModel> districModels) {
        mDistricModels = districModels;
    }

    @Override
    public String toString() {
        return "CityModel{" +
                "name='" + name + '\'' +
                ", mDistricModels=" + mDistricModels +
                '}';
    }
}
