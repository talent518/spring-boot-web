
package com.talent518.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talent518.demo.entity.User;
import com.talent518.demo.mapper.UserMapper;

/**
 * @Author:wjup
 * @Date: 2018/9/26 0026
 * @Time: 15:23
 */
@Service
public class UserService {
	@Autowired
	UserMapper userMapper;

	public List<User> list(int offset, int size) {
		return userMapper.list(offset, size);
	}
	
	public int count() {
		return userMapper.count();
	}

	public User find(int id) {
		return userMapper.find(id);
	}

	public boolean register(User user) {
		return userMapper.register(user) > 0;
	}
	
	public User findByUsername(String username) {
		return userMapper.findByUsername(username);
	}
}
