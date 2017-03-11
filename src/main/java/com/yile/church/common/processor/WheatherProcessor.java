package com.yile.church.common.processor;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hankcs.hanlp.dictionary.py.PinyinUtil;
import com.yile.church.common.model.ProcessorContext;
import com.yile.church.util.HttpClientUtil;
import com.yile.church.util.Pinyin4jUtil;
import net.sourceforge.pinyin4j.PinyinHelper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.URLEncoder;

/**
 * Created by dcx on 17/3/11.
 */
public class WheatherProcessor implements AbstractProcessor {

    private static Logger LOGGER = LoggerFactory.getLogger(WheatherProcessor.class);

    private String citySearchUrl;

    private String wheatherSearchUrl;

    @Override
    public void process(ProcessorContext context) throws Exception {
        String resource = context.getResource();

        LOGGER.info(">>>>>>天气服务开始处理.......");
        LOGGER.info(">>>>>>原始请求需求：" + resource);

        String city = resource.replace("天气","").trim();
        // 无结果：{"rc":{"c":0}}
        // 有结果：{"city_list":[{"cityId":379,"city_lable":[],"counname":"中国","id":489,"localCounname":"中国","localName":"郑州市","localPname":"河南省","name":"郑州市","pname":"河南省"}],"rc":{"c":0}}
        String citySearchResult = HttpClientUtil.httpGet(this.citySearchUrl + URLEncoder.encode(city,"UTF-8"));
        if(!StringUtils.isEmpty(citySearchResult) && citySearchResult.contains("city_list")){
            JSONObject jsonObject = JSONObject.parseObject(citySearchResult);
            JSONArray jsonArray = jsonObject.getJSONArray("city_list");
            JSONObject cityJson = jsonArray.getJSONObject(0);
            String provinceName = cityJson.getString("pname").replace("省","");
            String cityName = cityJson.getString("name").replace("市","");
            String whetherSearchResult = HttpClientUtil.httpGet(this.wheatherSearchUrl + Pinyin4jUtil.converterToSpell(provinceName) + "/" + Pinyin4jUtil.converterToSpell(cityName));
            LOGGER.info(">>>>>>天气搜索结果：" + whetherSearchResult);
            context.setMsg(whetherSearchResult);
        }else{

        }
    }

    public String getCitySearchUrl() {
        return citySearchUrl;
    }

    public void setCitySearchUrl(String citySearchUrl) {
        this.citySearchUrl = citySearchUrl;
    }

    public String getWheatherSearchUrl() {
        return wheatherSearchUrl;
    }

    public void setWheatherSearchUrl(String wheatherSearchUrl) {
        this.wheatherSearchUrl = wheatherSearchUrl;
    }
}
