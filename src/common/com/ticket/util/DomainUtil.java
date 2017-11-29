package com.ticket.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 二级域名解析类
 */
public class DomainUtil {
	private static final Log log = LogFactory.getLog(DomainUtil.class);
	//private ISubSitesService subSitesService = (ISubSitesService)SpringUtil.getObjectFromSpring("subSitesService");
	public void index(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			//网站的基网址
			String baseUrl = request.getSession().getServletContext().getAttribute("baseUrl").toString();
			//网站的一级域名地址 例如:www.test.com
			String siteUrl = request.getSession().getServletContext().getAttribute("siteUrl").toString();
			//网站的当前域名地址 例如:www.abc.test.com
			String s = request.getServerName();
			//如果一级域名和当前域名不相同, 则说明是子站
			if (!s.equals(siteUrl)) {
				String domain = s.replaceAll(baseUrl, ".").replaceAll("www.", "").replaceAll("\\.", "");
				log.info(domain);
				/*SubSites subSite = subSitesService.queryBySiteFlag(domain);
				if(subSite != null && subSite.getIsOpen().intValue() == ContextConstants.AUDIT_FLAG) {
					request.getRequestDispatcher("/index.xhtml?type="+domain+"").forward(request, response);
					return;
				} else {
					//转向首页
					request.getRequestDispatcher("/index.xhtml").forward(request, response);
					return;
				}*/
				request.getRequestDispatcher("/index.xhtml").forward(request, response);
			} else {
				request.getRequestDispatcher("/index.xhtml").forward(request, response);
				return;
			}
		} catch(Exception e) {
			request.getRequestDispatcher("/index.xhtml").forward(request, response);
			return;
		}
	}
}