package com.yile.church;

import com.hankcs.hanlp.HanLP;
import com.yile.church.util.HttpClientUtil;
import com.yile.church.util.Pinyin4jUtil;
import net.sourceforge.pinyin4j.PinyinHelper;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dcx on 17/1/21.
 */
public class HanLp {

    private static Logger LOGGER = LoggerFactory.getLogger(HanLp.class);

    @Test
    public void testHanlp(){
        String content = "财务时间2017年1月1号事情买电脑支出12343钢琴";
        List<String> keywordList = HanLP.extractPhrase(content, 3);
        System.out.println(keywordList);
    }


    @Test
    public void testKeywordValid(){
        //[事情, 支出, 电脑, 时间, 财务]
        List<String> keywordList = Arrays.asList(new String[]{"支出2", "事情", "电脑","时间","财务"});
        List<String> income  = Arrays.asList(new String[]{"事情", "支出","时间", "财务"});
        List<String> outcome = Arrays.asList(new String[]{"事情", "支出","时间", "财务"});
        boolean f =  (keywordList.containsAll(income) || keywordList.containsAll(outcome));

        if(!f){
            System.out.print(">>>>>>no");
        }else{
            System.out.print(">>>>>>yes");
        }
    }


    @Test
    public void testHttpclientGET() throws UnsupportedEncodingException {
        String url = "https://tianqi.moji.com/api/citysearch/"+ URLEncoder.encode("郑州","utf-8");
        String result = HttpClientUtil.httpGet(url);
        LOGGER.info(result);
    }

    @Test
    public void testHanyuPinyin() throws UnsupportedEncodingException {

        LOGGER.info(Pinyin4jUtil.converterToSpell("中国"));
    }




}
