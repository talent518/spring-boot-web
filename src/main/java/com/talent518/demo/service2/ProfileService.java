package com.talent518.demo.service2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talent518.demo.entity2.Profile;
import com.talent518.demo.mapper2.ProfileMapper;

@Service
public class ProfileService {
	@Autowired
	ProfileMapper profileMapper;
	
	public List<Profile> list(int offset, int size) {
		return profileMapper.list(offset, size);
	}
	
	public int count() {
		return profileMapper.count();
	}

	public Profile find(int id) {
		return profileMapper.find(id);
	}

	public boolean insert(Profile profile) {
		return profileMapper.insert(profile) > 0;
	}
}
