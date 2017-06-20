package com.framgia.util;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.framgia.service.impl.FileUploadServiceImpl;

import redis.clients.jedis.Jedis;

public class CrawlerUtil {

	static final Logger logger = Logger.getLogger(FileUploadServiceImpl.class);

	private static final String redisHost = "localhost";

	private static Jedis jedis = null;

	public static void sets(Elements images, String url, String title) {
		jedis = new Jedis(redisHost);
		logger.info("Connection to server sucessfully");
		logger.info("Server is running: " + jedis.ping());

		for (Element image : images) {

			if (image.attr("alt") != "") {

				jedis.lpush(url, image.attr("src"));
				jedis.lpush(title, image.attr("alt"));
			}

		}
	}

	public static List<String> gets(String key) {
		jedis = new Jedis(redisHost);
		logger.info("Connection to server sucessfully");
		logger.info("Server is running: " + jedis.ping());
		return jedis.lrange(key, 0, 9);
	}

	public static Elements crawData(String url) {
		Document doc;
		try {
			// get all images
			doc = Jsoup.connect(url).get();

			return doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
		} catch (IOException e) {
			logger.info("Could not upload file to crawData from CrawlerUtil: ", e);
		}
		return null;
	}

	public static void deleteCredis(String key) {
		jedis = new Jedis(redisHost);
		logger.info("Delete key " + key);
		jedis.del(key);
	}
}
