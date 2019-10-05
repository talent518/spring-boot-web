package com.talent518.demo.mapper2;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Qualifier;

import com.talent518.demo.entity2.Profile;

@Qualifier("db2SqlSessionFactory")
public interface ProfileMapper {
	@Select("SELECT * FROM profile LIMIT #{offset},#{size}")
	public List<Profile> list(int offset, int size);

	@Select("SELECT COUNT(*) FROM profile")
	public int count();

	@Select("SELECT * FROM profile WHERE uid=#{id}")
	public Profile find(int id);

	@Insert("INSERT INTO profile (uid,realName,sex,birthday,address)VALUES(#{id},#{realName},#{sex},#{birthday},#{address})")
	public int insert(Profile user);
}
