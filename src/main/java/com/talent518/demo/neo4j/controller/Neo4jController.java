package com.talent518.demo.neo4j.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.talent518.demo.neo4j.pojo.UserNode;
import com.talent518.demo.neo4j.pojo.UserRelation;
import com.talent518.demo.neo4j.service.UserNeo4jService;

@RestController
@RequestMapping("/neo4j")
public class Neo4jController {
	@Autowired
	UserNeo4jService userService;
	
	@RequestMapping("/createNode/{userId}/{name}/{age}")
	List<UserNode> createNode(@PathVariable Long userId, @PathVariable String name, @PathVariable Long age) {
		return userService.createNode(userId, name, age);
	}
	
	@RequestMapping("/getNodelist/{limit}")
	Iterable<UserNode> getNodelist(@PathVariable int limit) {
		return userService.getNodelist(limit);
	}
	
	@RequestMapping("/findRelationAll/{limit}")
	List<UserRelation> findRelationAll(@PathVariable int limit) {
		return userService.findRelationAll(limit);
	}
	
	@RequestMapping("/findRelationBySingleId/{firstUserId}/{secondUserId}")
	List<UserRelation> findRelationBySingleId(@PathVariable Long firstUserId, @PathVariable Long secondUserId) {
		return userService.findRelationBySingleId(firstUserId, secondUserId);
	}
	
	@RequestMapping("/findRelationByEachId/{firstUserId}/{secondUserId}")
	List<UserRelation> findRelationByEachId(@PathVariable Long firstUserId, @PathVariable Long secondUserId) {
		return userService.findRelationByEachId(firstUserId, secondUserId);
	}
	
	@RequestMapping("/createRelationByEachId/{firstUserId}/{secondUserId}")
	List<UserRelation> createRelationByEachId(@PathVariable Long firstUserId, @PathVariable Long secondUserId) {
		return userService.createRelationByEachId(firstUserId, secondUserId);
	}
}
