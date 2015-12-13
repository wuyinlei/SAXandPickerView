package com.example.xmlanalyze.city.model;

/**
 * Created by wuyinlei on 2015/12/13.
 * 一个懂得了编程乐趣的小白，希望自己
 * 能够在这个道路上走的很远，也希望自己学习到的
 * 知识可以帮助更多的人,分享就是学习的一种乐趣
 * QQ:1069584784
 * csdn:http://blog.csdn.net/wuyinlei
 */
public class DistrictModel {

    private String name ;
    private String zipcode;

    public DistrictModel() {
    }

    public DistrictModel(String name, String zipcode) {
        this.name = name;
        this.zipcode = zipcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    @Override
    public String toString() {
        return "DistrictModel{" +
                "name='" + name + '\'' +
                ", zipcode='" + zipcode + '\'' +
                '}';
    }
}
