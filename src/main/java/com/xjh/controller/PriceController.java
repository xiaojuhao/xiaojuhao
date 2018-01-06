package com.xjh.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xjh.service.PriceService;

@Controller
@RequestMapping("/price")
public class PriceController {
	@Resource
	HttpServletRequest request;
	@Resource
	PriceService priceService;

}
