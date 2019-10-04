package com.talent518.demo.mapper;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.talent518.demo.entity.User;
 
/**
 * @Author:wjup
 * @Date: 2018/9/26 0026
 * @Time: 15:20
 */
@Repository
public interface UserMapper {
	public List<User> list(int offset, int size);
	public int count();
    public User find(int id);
	public int register(User user);
}
