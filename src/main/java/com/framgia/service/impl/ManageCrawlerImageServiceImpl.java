package com.framgia.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.select.Elements;

import com.framgia.bean.GroupInfo;
import com.framgia.bean.ImageInfo;
import com.framgia.model.Group;
import com.framgia.model.Image;
import com.framgia.service.ManageCrawlerImageService;
import com.framgia.util.Constants;
import com.framgia.util.ConvetBeanAndModel;
import com.framgia.util.CrawlerUtil;
import com.framgia.util.DateUtil;

/**
 * 
 * @author phan.van.hieu@framgia.com
 *
 */
@SuppressWarnings("serial")
public class ManageCrawlerImageServiceImpl extends BaseServiceImpl implements ManageCrawlerImageService {

	private static final Logger logger = Logger.getLogger(ManageCrawlerImageServiceImpl.class);

	@Override
	public void crawlerData() {

		// Delete key in Redis
		CrawlerUtil.deleteCredis(Constants.KEY_URL_GIRL);
		CrawlerUtil.deleteCredis(Constants.KEY_TITLE_GIRL);
		CrawlerUtil.deleteCredis(Constants.KEY_URL_YUMMY);
		CrawlerUtil.deleteCredis(Constants.KEY_TITLE_YUMMY);
		CrawlerUtil.deleteCredis(Constants.KEY_URL_ANIME);
		CrawlerUtil.deleteCredis(Constants.KEY_TITLE_ANIME);
		CrawlerUtil.deleteCredis(Constants.KEY_URL_MARVEL);
		CrawlerUtil.deleteCredis(Constants.KEY_TITLE_MARVEL);

		// get all images in Girl
		Elements imageGirls = CrawlerUtil.crawData(Constants.URL_GIRL);
		CrawlerUtil.sets(imageGirls, Constants.KEY_URL_GIRL, Constants.KEY_TITLE_GIRL);

		// get all images in Yummy
		Elements imageFoods = CrawlerUtil.crawData(Constants.URL_YUMMY);
		CrawlerUtil.sets(imageFoods, Constants.KEY_URL_YUMMY, Constants.KEY_TITLE_YUMMY);

		// get all images in Anime
		Elements imageAnimes = CrawlerUtil.crawData(Constants.URL_ANIME);
		CrawlerUtil.sets(imageAnimes, Constants.KEY_URL_ANIME, Constants.KEY_TITLE_ANIME);

		// get all images in Marvel
		Elements imageMarvels = CrawlerUtil.crawData(Constants.URL_MARVEL);
		CrawlerUtil.sets(imageMarvels, Constants.KEY_URL_MARVEL, Constants.KEY_TITLE_MARVEL);

	}

	@Override
	public void saveData() {
		try {
			// get all images Girl
			List<String> urlGirlList = CrawlerUtil.gets(Constants.KEY_URL_GIRL);
			List<String> titleGirlList = CrawlerUtil.gets(Constants.KEY_TITLE_GIRL);

			// Save Image in Group Girl
			save(5, urlGirlList, titleGirlList);

			// get all images Yummy
			List<String> urlFoodList = CrawlerUtil.gets(Constants.KEY_URL_YUMMY);
			List<String> titleFoodList = CrawlerUtil.gets(Constants.KEY_TITLE_YUMMY);

			// Save Image in Group Yummy
			save(6, urlFoodList, titleFoodList);

			// get all images Yummy
			List<String> urlAnimeList = CrawlerUtil.gets(Constants.KEY_URL_ANIME);
			List<String> titleAnimeList = CrawlerUtil.gets(Constants.KEY_TITLE_ANIME);

			// Save Image in Group Yummy
			save(7, urlAnimeList, titleAnimeList);

			// get all images Yummy
			List<String> urlMarvelList = CrawlerUtil.gets(Constants.KEY_URL_MARVEL);
			List<String> titleMarvelList = CrawlerUtil.gets(Constants.KEY_TITLE_MARVEL);

			// Save Image in Group Yummy
			save(8, urlMarvelList, titleMarvelList);

		} catch (Exception e) {
			logger.info("Exception at function uploadImage in FileUploadServiceImpl: ", e);
			throw e;
		}

	}

	@Override
	public List<ImageInfo> getListImage(Integer idGroup, Integer page) {
		try {
			List<Image> listImage = getImageDAO().findListImage(idGroup, (page - 1) * Constants.NUMBER_PAGE_LIMIT,
					Constants.NUMBER_PAGE_LIMIT);
			if (listImage == null || listImage.size() == 0)
				return null;
			List<ImageInfo> listImageInfo = new ArrayList<ImageInfo>();

			for (Image item : listImage) {
				ImageInfo image = ConvetBeanAndModel.convertImageModelToBean(item);

				if (image.getTitle().length() > 10) {
					image.setTitle(image.getTitle().substring(0, 9) + Constants.TITLE);
				}
				GroupInfo group = new GroupInfo();
				group.setId(item.getGroup().getId());
				group.setName(item.getGroup().getName());
				image.setGroup(group);
				listImageInfo.add(image);

			}

			return listImageInfo;
		} catch (Exception e) {
			logger.error("get list image error", e);
		}
		return null;
	}

	@Override
	public Integer getNoOfRecord(Integer i) {
		return getImageDAO().getNumberRecord(i);
	}

	private void save(Integer id, List<String> url, List<String> title) {
		// Save Image
		Group dataGroup = groupDAO.findById(id, false);
		Image image;
		if (url != null && dataGroup != null) {
			for (int i = 0; i < url.size(); i++) {

				// Check exists image
				Long count = imageDAO.count(url.get(i), title.get(i));
				if (count == 0) {
					image = new Image();
					image.setGroup(dataGroup);
					image.setUrl(url.get(i));
					image.setTitle(title.get(i));
					image.setDescription(title.get(i));
					image.setDeleteFlag(Constants.DEL_FLG);
					image.setDateCreate(DateUtil.getDateNow());
					image.setDateUpdate(DateUtil.getDateNow());
					image.setUserCreate(Constants.NAME_SYSTEM);
					image.setUserUpdate(Constants.NAME_SYSTEM);

					// Insert data into table Image
					imageDAO.saveOrUpdate(image);
				}
			}
		}
	}

}