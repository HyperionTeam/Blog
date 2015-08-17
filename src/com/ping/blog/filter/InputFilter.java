package com.ping.blog.filter;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.ping.blog.common.ResponseEnum;
import com.ping.blog.util.CalendarUtil;

public class InputFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub

		String userName = req.getParameter("userName");
		String path = HttpServletRequest.class.cast(req).getServletPath();
		if ("/insertArticle.do".contains(path) || "/updateArticle.do".contains(path)
				|| "/deleteArticle.do".contains(path)) {
			String loginTimeStr = (String) req.getServletContext().getAttribute(userName);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				if (loginTimeStr == null || "".equals(loginTimeStr)
						|| CalendarUtil.isAfterHalfHour(dateFormat.parse(loginTimeStr))) {
					req.getServletContext().removeAttribute(userName);
					Map<String, Object> map = new HashMap<String, Object>();
					ResponseEnum responseEnum = ResponseEnum.FAILURE;
					responseEnum.setMessage("please login");
					map.put("code", responseEnum.getCode());
					map.put("msg", responseEnum.getMessage());
					Gson gson = new Gson();
					String json = gson.toJson(map);
					resp.getWriter().write(json);
					return;

				}

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		chain.doFilter(req, resp);

	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
