package com.qq.connect.demo;


import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qq.connect.QQConnectException;
import com.qq.connect.oauth.Oauth;
import com.ticket.util.GeneralUtil;

/**
 * Date: 12-12-4
 * Time: 上午10:28
 */
public class IndexServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        try {
            String pc = (String)request.getParameter("pc");
            if(GeneralUtil.isNotNull(pc)){
            	request.getSession().setAttribute("pc", pc);
            }
            String bjdj = (String)request.getParameter("bjdj");
            if(GeneralUtil.isNotNull(bjdj)){
            	request.getSession().setAttribute("bjdj", bjdj);
            }
            String wap = (String)request.getParameter("wap");
            if(GeneralUtil.isNotNull(wap)){
            	request.getSession().setAttribute("wap", wap);
            }
            String lastUrl = (String)request.getParameter("lastUrl");
            if(GeneralUtil.isNotNull(lastUrl)){
            	request.getSession().setAttribute("lastUrl", lastUrl);
            }
            String bjdjYinCanUrl = (String)request.getParameter("bjdjYinCanUrl");
            if(GeneralUtil.isNotNull(bjdjYinCanUrl)){
            	request.getSession().setAttribute("bjdjYinCanUrl", bjdjYinCanUrl);
            }
            response.sendRedirect(new Oauth().getAuthorizeURL(request));
        } catch (QQConnectException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request,  response);
    }
}
