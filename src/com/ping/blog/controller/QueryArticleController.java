package com.ping.blog.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ping.blog.common.ResponseEnum;
import com.ping.blog.controller.base.BaseController;
import com.ping.blog.dao.ArticleDao;
import com.ping.blog.daoimpl.ArticleDaoImpl;
import com.ping.blog.vo.Article;

public class QueryArticleController extends BaseController {

	@Override
	public Map<String, Object> doService(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map<String, Object> modelMap = new HashMap<String, Object>();
		String pageNumber = req.getParameter("pageNumber");
		ArticleDao articleDao = new ArticleDaoImpl();
		try {
			List<Article> list = articleDao.queryArticleByPageNumber(Integer.parseInt(pageNumber));
			if (list == null || list.isEmpty()) {
				buildResponse(modelMap, ResponseEnum.FAILURE);
				return modelMap;
			}
			buildResponse(modelMap, ResponseEnum.SUCCESS, list);
		} catch (Exception e) {
			e.printStackTrace();
			buildResponse(modelMap, ResponseEnum.FAILURE);
		}
		return modelMap;
	}

}