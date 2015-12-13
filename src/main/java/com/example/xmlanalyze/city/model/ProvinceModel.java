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
public class ProvinceModel {

    private String name;
    private List<CityModel> cityList;

    public ProvinceModel() {
    }

    public ProvinceModel(String name, List<CityModel> cityList) {
        this.name = name;
        this.cityList = cityList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CityModel> getCityList() {
        return cityList;
    }

    public void setCityList(List<CityModel> cityList) {
        this.cityList = cityList;
    }

    ////这个用来显示在PickerView上面的字符串,PickerView会通过反射获取getPickerViewText方法显示出来。
    @Override
    public String toString() {
        return name;
    }
}
