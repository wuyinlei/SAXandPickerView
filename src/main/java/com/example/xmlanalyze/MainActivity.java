package com.example.xmlanalyze;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.example.xmlanalyze.city.XmlParserHandler;
import com.example.xmlanalyze.city.model.CityModel;
import com.example.xmlanalyze.city.model.DistrictModel;
import com.example.xmlanalyze.city.model.ProvinceModel;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by wuyinlei on 2015/12/13.
 * 一个懂得了编程乐趣的小白，希望自己
 * 能够在这个道路上走的很远，也希望自己学习到的
 * 知识可以帮助更多的人,分享就是学习的一种乐趣
 * QQ:1069584784
 * csdn:http://blog.csdn.net/wuyinlei
 */
public class MainActivity extends AppCompatActivity {

    private List<ProvinceModel> mProvinces;
    private ArrayList<ArrayList<String>> mCities = new ArrayList<ArrayList<String>>();
    private ArrayList<ArrayList<ArrayList<String>>> mDistricts = new ArrayList<ArrayList<ArrayList<String>>>();

    private OptionsPickerView mCityPikerView;
    private Button btnStart;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStart = (Button) findViewById(R.id.analyzeXml);
        textView = (TextView) findViewById(R.id.textView);
        btnStartAnalyze();
    }

    private void btnStartAnalyze() {
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init();
            }
        });
    }

    /**
     * 初始化Android-PickerView控件
     */
    private void init() {
        initProvinceDatas();
        mCityPikerView = new OptionsPickerView(this);
        //三级联动效果
        mCityPikerView.setPicker((ArrayList) mProvinces, mCities, mDistricts, true);
        //设置选择的三级单位
//        pwOptions.setLabels("省", "市", "区");
        mCityPikerView.setTitle("选择城市");
        mCityPikerView.setCyclic(false, true, true);
        //监听确定选择按钮
        mCityPikerView.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {

            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                //返回的分别是三个级别的选中位置
                String tx = mProvinces.get(options1).toString()
                        + mCities.get(options1).get(option2)
                        + mDistricts.get(options1).get(option2).get(options3);
                textView.setText(tx);
            }
        });

        //这一步千万不要忘了，要不然就不会出现想要的效果哈
        mCityPikerView.show();
    }


    /**
     * 初始化省
     */
    private void initProvinceDatas() {
        //assets类资源放在工程根目录的assets子目录下，它里面保存的是一些原始的文件，可以以任何方式来进行组织
        AssetManager asset = getAssets();
        try {
            //把从assets子目录下的文件转化为inputstream流
            InputStream input = asset.open("province_data.xml");
            // 创建一个解析xml的工厂对象
            // 它的构造器是受保护的，因而只能用newInstance（）方法获得实例
            SAXParserFactory spf = SAXParserFactory.newInstance();
            // 解析xml
            //定义了一个继承自XMLReader类的API，其构造器也是受保护的，
            /**
             * 定义了一个继承自XMLReader类的API，其构造器也是受保护的，
             * 通过newSAXParser() 方法获得实例，可以把各种数据源作为解析用的XML
             */
            SAXParser parser = spf.newSAXParser();
            XmlParserHandler handler = new XmlParserHandler();
            /**
             * 这个方法就是public void parse (InputSource is, DefaultHandler dh)
             * 这些输入数据源包括输入流，文件，URL以及SAX输入资源。
             */
            parser.parse(input, handler);
            //关闭输入流
            input.close();
            // 获取解析出来的数据
            mProvinces = handler.getDataList();

        } catch (Throwable e) {
            e.printStackTrace();
        }
        if (mProvinces != null) {

            for (ProvinceModel p : mProvinces) {   //对province进行循环

                //得到省包含的市级信息
                List<CityModel> cities = p.getCityList();

                //城市List
                ArrayList<String> cityStrs = new ArrayList<>(cities.size());

                //对市列表进行遍历，因为有的市级包含的还有县级呢
                for (CityModel c : cities) {

                    //把城市名称放入 cityStrs
                    cityStrs.add(c.getName());

                    //地区 List
                    ArrayList<ArrayList<String>> dts = new ArrayList<>();

                    //把该市级中包含的县级存入到list数组中
                    List<DistrictModel> districts = c.getDistricModels();

                    //然后给县级分配空间大小
                    ArrayList<String> districtStrs = new ArrayList<>(districts.size());

                    //对县级进行遍历
                    for (DistrictModel d : districts) {
                        //取得到的名字加入到districtStrs里面
                        districtStrs.add(d.getName()); // 把城市名称放入 districtStrs
                    }
                    dts.add(districtStrs);
                    //组装地区数据
                    mDistricts.add(dts);
                }
                mCities.add(cityStrs); // 组装城市数据

            }
        }
    }


}
