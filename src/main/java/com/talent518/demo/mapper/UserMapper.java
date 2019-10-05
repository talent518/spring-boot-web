package com.talent518.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Qualifier;

import com.talent518.demo.entity.User;

@Qualifier("defaultSqlSessionFactory")
public interface UserMapper {
	@Select("SELECT * FROM user LIMIT #{offset},#{size}")
	public List<User> list(int offset, int size);

	@Select("SELECT COUNT(*) FROM user")
	public int count();

	@Select("SELECT * FROM user WHERE uid=#{id}")
	public User find(int id);

	@Insert("INSERT INTO user (username,email,password,salt,registerTime)VALUES(#{username},#{email},MD5(CONCAT(MD5(#{password}),#{salt})),#{salt},NOW())")
	public int register(User user);

	@Select("SELECT * FROM user WHERE username=#{username}")
	public User findByUsername(String username);
}
