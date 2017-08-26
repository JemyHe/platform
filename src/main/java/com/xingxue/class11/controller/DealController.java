package com.xingxue.class11.controller;

import com.xingxue.class11.entity.Deal;
import com.xingxue.class11.entity.DealCategory;
import com.xingxue.class11.entity.DealDetail;
import com.xingxue.class11.exception.BaseException;
import com.xingxue.class11.exception.DealException;
import com.xingxue.class11.framework.page.Condition;
import com.xingxue.class11.framework.page.PagingResult;
import com.xingxue.class11.framework.page.Search;
import com.xingxue.class11.service.DealCategoryService;
import com.xingxue.class11.service.DealService;
import com.xingxue.class11.util.IpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Deal,商品
 */
@Controller
public class DealController {

	private static final Logger logger = LoggerFactory.getLogger(DealController.class);

	@Autowired private DealService dealService; // 商品服务
	
	@Autowired private DealCategoryService dealCategoryService;


	/**
	 * 商品详情页
	 * @param model
	 * @param skuId
     * @return
     */
	@RequestMapping(value = "/item/{skuId}", method = RequestMethod.GET)
	public String detail(Model model, @PathVariable Long skuId, HttpServletResponse response) {

		try {
			//1.查询商品即deal信息
			Deal deal = dealService.getBySkuIdForDetailViewOnSite(skuId);
			//2.查询dealDetail信息
			DealDetail dealDetail = dealService.getDetailByDealId(deal.getId());
			deal.setDealDetail(dealDetail);
			model.addAttribute("deal", deal);
			return "/deal/detail";
		} catch (DealException e) {
			logger.error(e.getMessage());
			return "redirect:/";
		} catch (BaseException e) {
			logger.error(e.getMessage());
			return "redirect:/";
		}
	}

	/**
	 * 显示某个类别下的第一页商品
	 * @param urlName
	 * @param model
	 * @param request
     * @return
     */
	@RequestMapping(value = "/category/{urlName}", method = RequestMethod.GET)
	public String listDealsByCategoryFirst(@PathVariable String urlName, Model model, HttpServletRequest request) {
		return listDealsOfDealCategory(urlName, 1, model, request);
	}

	/**
	 * 分页显示某个类别下的商品
	 * @param urlName
	 * @param page
	 * @param model
	 * @param request
     * @return
     */
	@RequestMapping(value = "/category/{urlName}/{page}", method = RequestMethod.GET)
	public String listDealsOfDealCategory(@PathVariable String urlName, @PathVariable Integer page, Model model,
                                          HttpServletRequest request) {
		try {
			List<DealCategory> categories = dealCategoryService.getCategories();
			model.addAttribute("categories", categories);

			DealCategory dealCategory = dealCategoryService.getByUrlName(urlName);
			model.addAttribute("dealCategory", dealCategory);

			List<Condition> list = new ArrayList<>();
			list.add(new Condition("areaId",IpUtil.getArea(request).getId()));
			list.add(new Condition("categoryId",dealCategory.getSelfAndChildrenIds()));
			Search search = new Search(page,10,list);
			PagingResult<Deal> pageResult = dealService.getDealList(search);
			model.addAttribute("pagingDealList", pageResult);
			return "deal/category";
		} catch (DealException e) {
			logger.error(e.getMessage());
			return "redirect:/";
		} catch (BaseException e) {
			logger.error(e.getMessage());
			return "redirect:/";
		}
	}

}