package com.xingxue.class11.controller;

import com.xingxue.class11.entity.Deal;
import com.xingxue.class11.entity.DealCategory;
import com.xingxue.class11.exception.BaseException;
import com.xingxue.class11.exception.DealException;
import com.xingxue.class11.framework.dto.IndexCategoryDealDTO;
import com.xingxue.class11.framework.page.PagingResult;
import com.xingxue.class11.service.DealCategoryService;
import com.xingxue.class11.service.DealService;
import com.xingxue.class11.service.impl.DealServiceImpl;
import com.xingxue.class11.util.IpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController{

	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@Autowired private DealService dealService;
	@Autowired private DealCategoryService categoryService;


	/**
	 * 进入首页方法
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model, HttpServletRequest request) {

		try {
			//1.分类
			//2.首页商品
			//3.每个大分类下显示4个商品
			List<DealCategory> categories = categoryService.getCategories();
			model.addAttribute("categories", categories);

			//根据IP确定地址
			Long areaId = IpUtil.getArea(request).getId();
			//1.构造一个结构体存放每个分类的4个商品
			//2.页面循环结构体的集合
			//3.结构体包含4个商品,1各分类
			List<IndexCategoryDealDTO> indexCategoryDealDTOs = new ArrayList<>();
			for(DealCategory dealCategory:categories){
                List<Deal> deals = dealService.getDealsForIndex(areaId, dealCategory.getSelfAndChildrenIds());
                indexCategoryDealDTOs.add(new IndexCategoryDealDTO(dealCategory, deals));
            }
			model.addAttribute("indexCategoryDealDTOs", indexCategoryDealDTOs);
			return "index";
		} catch (DealException e) {
			logger.error(e.getMessage());
			return "redirect:/user/login";
		} catch (BaseException e) {
			logger.error(e.getMessage());
			return "redirect:/user/login";
		}
	}

	/**
	 * 商品搜索
	 * @param s
	 * @param model
	 * @param page
     * @return
     */
	@RequestMapping(value = "/search/{page}/{name}",method = RequestMethod.GET)
	public String search(@PathVariable("name") String s, Model model, @PathVariable("page") Integer page) {
		try {
			List<DealCategory> categories = categoryService.getCategories();
			model.addAttribute("categories", categories);

			//get提交中文为ISO-8859-1,需转成UTF-8
			String word = new String(s.getBytes("ISO-8859-1"), "UTF-8");
			PagingResult<Deal> pageResult = dealService.searchByName(word,page,10);
			model.addAttribute("pagingDealList", pageResult);
			model.addAttribute("s", word);
			return "deal/search";
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage());
			return "redirect:/user/login";
		} catch (DealException e) {
			logger.error(e.getMessage());
			return "redirect:/user/login";
		} catch (BaseException e) {
			logger.error(e.getMessage());
			return "redirect:/user/login";
		}
	}

}