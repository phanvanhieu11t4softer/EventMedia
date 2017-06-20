package com.framgia.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.framgia.bean.ImageInfo;
import com.framgia.bean.PagingImage;
import com.framgia.service.ManageCrawlerImageService;
import com.framgia.util.Constants;

/**
 * 
 * @version 19/06/2017
 * @author phan.van.hieu@framgia.com
 * 
 */
@RestController
@EnableScheduling
@Component
public class ManageCrawlerImageController {

	private static final Logger logger = Logger.getLogger(ManageCrawlerImageController.class);

	@Autowired
	ManageCrawlerImageService crawlerImageService;

	@RequestMapping(value = "/funny_girl", method = RequestMethod.GET)
	public ModelAndView initPageGirl() {
		logger.info("Init page image");

		List<ImageInfo> listImage = crawlerImageService.getListImage(5, Constants.NUMBER_PAGE_DEFAULT);
		ModelAndView mv = new ModelAndView("pageFunny", "image", listImage);

		Integer noOfRecord = crawlerImageService.getNoOfRecord(5);
		if (noOfRecord == null) {
			mv.addObject("paging", null);
			return mv;
		}

		PagingImage paging = new PagingImage(noOfRecord,
				(int) Math.ceil(noOfRecord * 1.0 / Constants.NUMBER_PAGE_LIMIT), 1, 2, 0);
		mv.addObject("paging", paging);
		mv.addObject("topic", Constants.TOPIC_GIRL);
		mv.addObject("valueSearch", null);

		return mv;
	}

	@RequestMapping(value = "/funny_yummy", method = RequestMethod.GET)
	public ModelAndView initPageYummy() {
		logger.info("Init page image");

		List<ImageInfo> listImage = crawlerImageService.getListImage(6, Constants.NUMBER_PAGE_DEFAULT);
		ModelAndView mv = new ModelAndView("pageFunny", "image", listImage);

		Integer noOfRecord = crawlerImageService.getNoOfRecord(6);
		if (noOfRecord == null) {
			mv.addObject("paging", null);
			return mv;
		}

		PagingImage paging = new PagingImage(noOfRecord,
				(int) Math.ceil(noOfRecord * 1.0 / Constants.NUMBER_PAGE_LIMIT), 1, 2, 0);
		mv.addObject("paging", paging);
		mv.addObject("topic", Constants.TOPIC_YUMMY);
		mv.addObject("valueSearch", null);

		return mv;
	}

	@RequestMapping(value = "/funny_anime", method = RequestMethod.GET)
	public ModelAndView initPageAnime() {
		logger.info("Init page image");

		List<ImageInfo> listImage = crawlerImageService.getListImage(7, Constants.NUMBER_PAGE_DEFAULT);
		ModelAndView mv = new ModelAndView("pageFunny", "image", listImage);

		Integer noOfRecord = crawlerImageService.getNoOfRecord(7);
		if (noOfRecord == null) {
			mv.addObject("paging", null);
			return mv;
		}

		PagingImage paging = new PagingImage(noOfRecord,
				(int) Math.ceil(noOfRecord * 1.0 / Constants.NUMBER_PAGE_LIMIT), 1, 2, 0);
		mv.addObject("paging", paging);
		mv.addObject("topic", Constants.TOPIC_ANIME);
		mv.addObject("valueSearch", null);

		return mv;
	}

	@RequestMapping(value = "/funny_marvel", method = RequestMethod.GET)
	public ModelAndView initPageMarvel() {
		logger.info("Init page image");

		List<ImageInfo> listImage = crawlerImageService.getListImage(8, Constants.NUMBER_PAGE_DEFAULT);
		ModelAndView mv = new ModelAndView("pageFunny", "image", listImage);

		Integer noOfRecord = crawlerImageService.getNoOfRecord(8);
		if (noOfRecord == null) {
			mv.addObject("paging", null);
			return mv;
		}

		PagingImage paging = new PagingImage(noOfRecord,
				(int) Math.ceil(noOfRecord * 1.0 / Constants.NUMBER_PAGE_LIMIT), 1, 2, 0);
		mv.addObject("paging", paging);
		mv.addObject("topic", Constants.TOPIC_MARVEL);
		mv.addObject("valueSearch", null);

		return mv;
	}

	@RequestMapping(value = "/searchImageFunny", method = RequestMethod.POST)
	public ModelAndView findByCondition(@RequestParam String topic, @RequestParam Integer noPage) {

		if (noPage == 0) {
			noPage = Constants.NUMBER_PAGE_DEFAULT;
		}
		List<ImageInfo> image = new ArrayList<ImageInfo>();
		ModelAndView model = new ModelAndView();
		Integer noOfRecord = null;
		if (Constants.TOPIC_GIRL.equals(topic)) {
			image = crawlerImageService.getListImage(5, noPage);
			noOfRecord = crawlerImageService.getNoOfRecord(5);
			model.addObject("topic", Constants.TOPIC_GIRL);
		} else if (Constants.TOPIC_YUMMY.equals(topic)) {
			image = crawlerImageService.getListImage(6, noPage);
			noOfRecord = crawlerImageService.getNoOfRecord(6);
			model.addObject("topic", Constants.TOPIC_YUMMY);
		} else if (Constants.TOPIC_ANIME.equals(topic)) {
			image = crawlerImageService.getListImage(7, noPage);
			noOfRecord = crawlerImageService.getNoOfRecord(7);
			model.addObject("topic", Constants.TOPIC_ANIME);
		} else {
			image = crawlerImageService.getListImage(8, noPage);
			noOfRecord = crawlerImageService.getNoOfRecord(8);
			model.addObject("topic", Constants.TOPIC_MARVEL);
		}

		PagingImage paging = new PagingImage(noOfRecord,
				(int) Math.ceil(noOfRecord * 1.0 / Constants.NUMBER_PAGE_LIMIT), noPage, noPage + 1, noPage - 1);
		model.setViewName("pageFunny");
		model.addObject("image", image);
		model.addObject("paging", paging);
		return model;
	}

	@Scheduled(cron = "0 0 1 * * *", zone = "Asia/Saigon")
	public void crawlerDataService() {

		logger.info("Call background job crawler Image");
		crawlerImageService.crawlerData();
	}

	@Scheduled(cron = "0 0 23 * * *", zone = "Asia/Saigon")
	public void backgroundJobSave() {

		logger.info("Call background job auto save DB");
		crawlerImageService.saveData();
	}
}
