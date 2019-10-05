package com.talent518.demo.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.Assert;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.talent518.demo.entity.Result;
import com.talent518.demo.entity2.Profile;
import com.talent518.demo.service2.ProfileService;

/**
 * @Author:wjup
 * @Date: 2018/9/26 0026
 * @Time: 14:42
 */

@RestController
@RequestMapping("/profile")
public class ProfileController {
	@Autowired
	private ProfileService profileService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@RequestMapping("")
	public Result list(@RequestParam(name = "offset", defaultValue = "0") int offset, @RequestParam(name = "size", required = false, defaultValue = "10") int size, @RequestParam(name = "isCount", required = false) boolean isCount) {
		Assert.isTrue(offset >= 0, "The offset parameter should not be less than 0");
		Assert.isTrue(size > 0, "The parameter limit cannot be less than or equal to 0");

		if (isCount) {
			return new Result(profileService.count(), profileService.list(offset, size));
		} else {
			return new Result(profileService.list(offset, size));
		}
	}

	@RequestMapping("/count")
	public int count() {
		return profileService.count();
	}

	@RequestMapping("/{id}")
	public Result find(@PathVariable int id) {
		return new Result(profileService.find(id));
	}

	@RequestMapping("/insert")
	public Result insert(Profile profile) {
		Assert.isTrue(profile.getId() > 0, "The parameter id cannot be less than or equal to 0");
		Assert.notNull(profile.getRealName(), "realName is required");
		Assert.notNull(profile.getSex(), "sex is required, And only B, G and N can be included.");
		Assert.notNull(profile.getBirthday(), "birthday is required");
		Assert.notNull(profile.getAddress(), "address is required");

		if (profileService.insert(profile)) return Result.ok();
		return new Result("Failure");
	}

	@Autowired
	JdbcTemplate jdbcTemplate2;

	@RequestMapping("/jdbcTemplate")
	public Result jdbcTemplate(Profile profile) {
		Assert.isTrue(profile.getId() > 0, "The parameter id cannot be less than or equal to 0");
		Assert.notNull(profile.getRealName(), "realName is required");
		Assert.notNull(profile.getSex(), "sex is required, And only B, G and N can be included.");
		Assert.notNull(profile.getBirthday(), "birthday is required");
		Assert.notNull(profile.getAddress(), "address is required");

		int i = jdbcTemplate2.update("INSERT INTO profile (uid,realName,sex,birthday,address)VALUES(?,?,?,?,?)", profile.getId(), profile.getRealName(), profile.getSex().getValue(), profile.getBirthday(), profile.getAddress());
		if (i > 0) return Result.ok();
		return new Result("Failure");
	}
}
