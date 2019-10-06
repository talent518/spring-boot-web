package com.talent518.demo.neo4j.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talent518.demo.neo4j.mapper.UserRelationRepository;
import com.talent518.demo.neo4j.mapper.UserRepository;
import com.talent518.demo.neo4j.pojo.UserNode;
import com.talent518.demo.neo4j.pojo.UserRelation;

@Service
public class UserNeo4jService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserRelationRepository relationRepository;
	
	public List<UserNode> createNode(Long userId, String name, Long age) {
		return userRepository.createNode(userId, name, age);
	}
	
	public List<UserNode> getNodelist(int limit) {
		return userRepository.getNodelist(limit);
	}
	public List<UserRelation> findRelationAll(int limit) {
		return relationRepository.findRelationAll(limit);
	}
	
	public List<UserRelation> findRelationBySingleId(Long firstUserId, Long secondUserId) {
		return relationRepository.findRelationBySingleId(firstUserId, secondUserId);
	}
	
	public List<UserRelation> findRelationByEachId(Long firstUserId, Long secondUserId) {
		return relationRepository.findRelationByEachId(firstUserId, secondUserId);
	}
	
	public List<UserRelation> createRelationByEachId(Long firstUserId, Long secondUserId) {
		return relationRepository.createRelationByEachId(firstUserId, secondUserId);
	}
}
