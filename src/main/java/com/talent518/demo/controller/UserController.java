package com.talent518.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.talent518.demo.entity.Result;
import com.talent518.demo.service.UserService;

/**
 * @Author:wjup
 * @Date: 2018/9/26 0026
 * @Time: 14:42
 */

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("")
	public Result list(@RequestParam(name = "offset", defaultValue = "0") int offset, @RequestParam(name = "size", required = false, defaultValue = "10") int size, @RequestParam(name = "isCount", required = false) boolean isCount) {
		Assert.isTrue(offset >= 0, "The offset parameter should not be less than 0");
		Assert.isTrue(size > 0, "The parameter limit cannot be less than or equal to 0");

		if(isCount) {
			return new Result(userService.count(), userService.list(offset, size));
		} else {
			return new Result(userService.list(offset, size));
		}
	}
	
	@RequestMapping("/count")
	public int count() {
		return userService.count();
	}

	@RequestMapping("/{id}")
	public Result find(@PathVariable int id) {
		return new Result(userService.find(id));
	}
	
	@RequestMapping("/register")
	public Result register(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("email") String email) {
		
		return Result.ok();
	}
}
