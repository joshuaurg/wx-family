package com.yile.church.common.processor;

import com.yile.church.common.model.ProcessorContext;
import com.yile.church.model.FinanceModel;
import com.yile.church.service.FinanceService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.DoubleRange;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.util.*;

/**
 * Created by dcx on 17/1/20.
 */
public class FinanceProcessor implements AbstractProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(FinanceProcessor.class);


    @Autowired
    private FinanceService financeService;

    /**
     * 格式：1314 财务 支出123 事项xxx 时间xxx 结束
     */
    @Override
    public void process(ProcessorContext context) {
        String resource = context.getResource();
        if(StringUtils.isEmpty(resource)){
            context.setMsg("你似乎没有说话哦！");
            return;
        }
        if(!(resource.startsWith("1314") && resource.endsWith("结束"))){
            context.setMsg("你貌似不知道这儿的规矩！");
            return;
        }

        if(!(resource.contains("支出") || resource.contains("收入")) && !resource.contains("事项") && !resource.contains("时间")){
            context.setMsg("你貌似不知道这儿的规矩！");
            return;
        }
        FinanceModel finance = new FinanceModel();
        finance.setCreateDate(new Date());
        finance.setUpdateDate(new Date());

        Integer typeIndex = null;
        Integer itemIndex = resource.indexOf("事项");
        Integer dateIndex = resource.indexOf("时间");

        Map<Integer, String> map = new TreeMap<Integer, String>(
                new Comparator<Integer>() {
                    public int compare(Integer obj1, Integer obj2) {
                        // 降序排序
                        return obj1.compareTo(obj2);
                    }
                });
        if(resource.contains("收入")){
            finance.setType(FinanceModel.FINANCE_INCOME);
            typeIndex = resource.indexOf("收入");
            map.put(typeIndex,"收入");
        }
        if(resource.contains("支出")){
            finance.setType(FinanceModel.FINANCE_OUTCOME);
            typeIndex = resource.indexOf("支出");
            map.put(typeIndex,"支出");
        }
        map.put(itemIndex,"事项");
        map.put(dateIndex,"时间");

        List<Integer> keyList = new ArrayList(map.keySet());
        for(int i=0;i<keyList.size();i++){
            Integer start,end = null;
            String keyword    = null;
            if(i==keyList.size()-1){
                keyword = resource.substring(keyList.get(i));
            }else{
                start = keyList.get(i);
                end   = keyList.get(i+1);
                keyword = resource.substring(start,end);
            }
            keyword = keyword.replace(map.get(keyList.get(i)),"");
            if("时间".equals(map.get(keyList.get(i)))){
                String dateStr = keyword.replace("年","-").replace("月","-").replace("日","-").replace("号","-");
                try {
                    finance.setPerformDate(DateUtils.parseDate(dateStr,new String[]{"yyyy-MM-dd"}));
                } catch (ParseException e) {
                    LOGGER.error("parse date error.",e);
                }
            }
            if("收入".equals(map.get(keyList.get(i))) || "支出".equals(map.get(keyList.get(i)))){
                finance.setMoney(Double.parseDouble(keyword));
            }
            if("事项".equals(map.get(keyList.get(i)))){
                finance.setItem(keyword);
            }
            LOGGER.info("keyword:"+keyword);
            financeService.insert(finance);
        }
    }
}
