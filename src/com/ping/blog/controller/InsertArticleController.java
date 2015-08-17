package com.ping.blog.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ping.blog.common.ResponseEnum;
import com.ping.blog.controller.base.BaseController;
import com.ping.blog.dao.ArticleDao;
import com.ping.blog.daoimpl.ArticleDaoImpl;
import com.ping.blog.vo.Article;

public class InsertArticleController extends BaseController {

	@Override
	public Map<String, Object> doService(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map<String, Object> modelMap = new HashMap<String, Object>();
		String title = req.getParameter("title");
		String tag = req.getParameter("tag");
		String content = req.getParameter("content");
		String author = req.getParameter("userName");
		// String createTime = req.getParameter("createTime");
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String createTime = format.format(new Date());
			Article article = new Article();
			article.setTitle(title);
			article.setTag(tag);
			article.setContent(content);
			article.setCreateTime(createTime);
			article.setUpdateTime(createTime);
			article.setAuthor(author);
			ArticleDao articleDao = new ArticleDaoImpl();
			if (!articleDao.insertArticle(article)) {
				buildResponse(modelMap, ResponseEnum.FAILURE);
				return modelMap;
			}
			buildResponse(modelMap, ResponseEnum.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			buildResponse(modelMap, ResponseEnum.FAILURE);
		}
		return modelMap;
	}

}
