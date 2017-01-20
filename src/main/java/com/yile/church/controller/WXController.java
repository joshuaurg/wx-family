package com.yile.church.controller;

import com.yile.church.service.WXService;
import com.yile.church.util.SignUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author : hema
 * @date : 2016年12月13日 下午5:51
 */
@RestController
@RequestMapping(value = "/wx")
public class WXController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WXController.class);

    @Autowired
    private WXService wxService;

    @RequestMapping(value = "check",method = RequestMethod.GET)
    public void check(HttpServletRequest request, HttpServletResponse response) throws Exception{
        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");

        PrintWriter out = response.getWriter();
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            out.print(echostr);
        }
        out.close();
        out = null;
    }

    @RequestMapping(value = "check",method = RequestMethod.POST)
    public void process(HttpServletRequest request, HttpServletResponse response) throws Exception{
        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // 调用核心业务类接收消息、处理消息
        String respMessage = wxService.processRequest(request);

        LOGGER.info("process end...");
        // 响应消息
        PrintWriter out = response.getWriter();
        out.print(respMessage);
        out.close();
    }
}
