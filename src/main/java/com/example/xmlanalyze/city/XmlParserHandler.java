package com.example.xmlanalyze.city;


import com.example.xmlanalyze.city.model.CityModel;
import com.example.xmlanalyze.city.model.DistrictModel;
import com.example.xmlanalyze.city.model.ProvinceModel;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by wuyinlei on 2015/12/13.
 * 一个懂得了编程乐趣的小白，希望自己
 * 能够在这个道路上走的很远，也希望自己学习到的
 * 知识可以帮助更多的人,分享就是学习的一种乐趣
 * QQ:1069584784
 * csdn:http://blog.csdn.net/wuyinlei
 */

public class XmlParserHandler extends DefaultHandler {

    /**
     * 存储所有的解析对象
     */
    private List<ProvinceModel> provinceList = new ArrayList<ProvinceModel>();

    public XmlParserHandler() {

    }

    public List<ProvinceModel> getDataList() {
        return provinceList;
    }

    @Override
    public void startDocument() throws SAXException {
        // 当读到第一个开始标签的时候，会触发这个方法
    }

    /**
     * 初始化三个类，分别为省、市、县
     */
    ProvinceModel provinceModel = new ProvinceModel();
    CityModel cityModel = new CityModel();
    DistrictModel districtModel = new DistrictModel();


    /**
     * 解析器在 XML 文档中的每个元素的开始调用此方法；对于每个 startElement
     * 事件都将有相应的 endElement 事件（即使该元素为空时）。所有元素的内容都将在
     * 相应的 endElement 事件之前顺序地报告。

     参数说明:

     * @param uri - 名称空间 URI，如果元素没有名称空间 URI，或者未执行名称空间处理，则为空字符串
     * @param localName - 本地名称（不带前缀），如果未执行名称空间处理，则为空字符串
     * @param qName - 限定名（带有前缀），如果限定名不可用，则为空字符串
     * @param attributes - 连接到元素上的属性。如果没有属性，则它将是空 Attributes 对象。
     * @throws SAXException
     * 在startElement 返回后，此对象的值是未定义的
     */
    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        // 当遇到开始标记的时候，调用这个方法
        if (qName.equals("province")) {   //如果碰见了province这个标签
            //实例化
            provinceModel = new ProvinceModel();
            provinceModel.setName(attributes.getValue(0));
            provinceModel.setCityList(new ArrayList<CityModel>());
        } else if (qName.equals("city")) {
            cityModel = new CityModel();
            cityModel.setName(attributes.getValue(0));
            cityModel.setDistricModels(new ArrayList<DistrictModel>());
        } else if (qName.equals("district")) {
            districtModel = new DistrictModel();
            districtModel.setName(attributes.getValue(0));
            districtModel.setZipcode(attributes.getValue(1));
        }
    }

    /**
     *                 throws SAXException接收元素结束的通知。
     SAX 解析器会在 XML 文档中每个元素的末尾调用此方法；对于每个 endElement
     事件都将有相应的 startElement 事件（即使该元素为空时）。
     参数：
     * @param uri - 名称空间 URI，如果元素没有名称空间 URI，或者未执行名称空间处理，则为空字符串
     * @param localName - 本地名称（不带前缀），如果未执行名称空间处理，则为空字符串
     * @param qName - 限定的 XML 名称（带前缀），如果限定名不可用，则为空字符串
     * @throws SAXException
     */
    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        // 遇到结束标记的时候，会调用这个方法
        if (qName.equals("district")) {
            cityModel.getDistricModels().add(districtModel);
        } else if (qName.equals("city")) {
            provinceModel.getCityList().add(cityModel);
        } else if (qName.equals("province")) {
            provinceList.add(provinceModel);
        }
    }

    /**
     * 接收字符数据的通知,可以通过new String(ch,start,length)构造器，创建解析出来的字符串文本.
     参数：

     * @param ch - 来自 XML 文档的字符
     * @param start- 数组中的开始位置
     * @param length - 从数组中读取的字符的个数
     * @throws SAXException
     */
    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
    }
}
