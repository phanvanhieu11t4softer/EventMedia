package com.framgia.controller;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.framgia.bean.GroupInfo;
import com.framgia.bean.ImageInfo;
import com.framgia.bean.UserInfo;
import com.framgia.service.GroupService;
import com.framgia.service.ImageService;
import com.framgia.service.UserService;
import com.framgia.util.Constants;
import com.framgia.util.DateUtil;
import com.framgia.util.Helpers;

/**
 * 
 * @version 30/05/2017
 * @author vu.thi.tran.van@framgia.com
 * 
 */
@RestController
@Component
@EnableScheduling
public class ManagerController {

	private static final Logger logger = Logger.getLogger(ManagerController.class);
	
	private Integer idUser;

	@Autowired
	GroupService groupService;

	@Autowired
	UserService userService;

	@Autowired
	ImageService imageService;

	@Autowired
	private SimpMessagingTemplate template;
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(DateUtil.getSimpleDateFormat(), true));
	}

	@RequestMapping(value = { "/manager" }, method = RequestMethod.GET)
	public ModelAndView initPage() {
		logger.info("Init page");

		GroupInfo groupInfo = new GroupInfo();
		groupInfo.setName("");
		groupInfo.setDescription("");
		groupInfo.setNote("");
		groupInfo.setStatus(Constants.GROUP_STATUS_CODE_ACTIVE);
		groupInfo.setType(Constants.GROUP_TYPE_CODE_PRIVATE);
		groupInfo.setDateStart(null);
		groupInfo.setDateEnd(null);
		idUser = Helpers.getIdUser();
		return new ModelAndView("initGroup", "group", groupInfo);
	}

	@RequestMapping(value = "/manager/init", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GroupInfo> getGroup() {

		UserInfo user = userService.findById(Helpers.getIdUser(), false);
		if (user == null) {
			return new ResponseEntity<GroupInfo>(HttpStatus.NOT_FOUND);
		}

		GroupInfo group = groupService.findById(user.getIdGroup(), false);

		if (group == null) {
			return new ResponseEntity<GroupInfo>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<GroupInfo>(group, HttpStatus.OK);
	}

	@RequestMapping(value = "/manager/", method = RequestMethod.POST)
	public ResponseEntity<Void> updateGroup(@ModelAttribute("group") GroupInfo group) {

		if (groupService.updateGroup(group)) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}

		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/manager/{id}", method = RequestMethod.GET)
	public RedirectView deleteLogicGroup(@PathVariable("id") int id) {

		if (groupService.deleteLogicGroup(id)) {
			return new RedirectView("/EventMedia/logout");
		}
		return new RedirectView("error");
	}

	@RequestMapping(value = "/manager/image/{id}", method = RequestMethod.GET)
	@ResponseBody
	public boolean removeImage(@PathVariable("id") int id) {
		return imageService.removeImageInGroup(id);
	}

	@RequestMapping(value = "/manager/user/remove/{idGroup}/{id}", method = RequestMethod.GET)
	@ResponseBody
	public boolean removeUser(@PathVariable("idGroup") int idGroup, @PathVariable("id") int id) {
		return userService.removeUser(id, idGroup);
	}

	@RequestMapping(value = "/manager/user/reject/{idGroup}/{id}", method = RequestMethod.GET)
	@ResponseBody
	public boolean rejectUserJoinGroup(@PathVariable("idGroup") int idGroup, @PathVariable("id") int id) {
		return userService.removeUser(id, idGroup);
	}

	@RequestMapping(value = "/manager/user/accept/{idGroup}/{id}", method = RequestMethod.GET)
	@ResponseBody
	public boolean racceptUserJoinGroup(@PathVariable("idGroup") int idGroup, @PathVariable("id") int id) {
		return userService.acceptUserJoinGroup(id, idGroup);
	}

	@RequestMapping(value = "/manager/user/{id}", method = RequestMethod.GET)
	public ModelAndView detailPage(@PathVariable("id") int id) {
		logger.info("call service: get user and get list permission");

		// get infor user
		UserInfo user = userService.findById(id, false);

		// render page detail user
		ModelAndView mv = new ModelAndView("detailUserOfManagerPermission", "user", user);

		if (user == null) {
			mv.addObject("image", null);
			return mv;
		}

		// get image
		ImageInfo image = imageService.getImageByUserCreate(user.getUsername(), user.getIdGroup());
		mv.addObject("image", image);

		return mv;
	}
	
	//TODO Scheduled notification 
	@Scheduled(cron="*/10 * * * * *", zone="Asia/Saigon")
	public void notificationGroup() {
		
		//TODO get information user login in Security 
		String username = getUserName();
		
		//TODO username is empty
		logger.info("Notification group " + username);
		
		String notification = groupService.getNotification(idUser);
		if(notification != null){
			this.template.convertAndSend("/topic/notification", notification);
		}
	}
	
	//TODO get infomation in SecurityContextHolder
	public String getUserName() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			logger.info("username: " + userDetail.getUsername());

			return userDetail.getUsername();
		} else {
			return null;
		}
	}
}
