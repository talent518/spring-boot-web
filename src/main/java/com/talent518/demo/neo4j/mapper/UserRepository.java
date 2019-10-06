package com.talent518.demo.neo4j.mapper;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import com.talent518.demo.neo4j.pojo.UserNode;

@Repository
public interface UserRepository extends Neo4jRepository<UserNode, Long> {
	@Query("MATCH (n:User) RETURN n LIMIT {limit}")
	List<UserNode> getNodelist(int limit);

	@Query("MERGE (n:User{userId:{userId}}) SET n+={userId:{userId},name:{name},age:{age}} RETURN n")
	List<UserNode> createNode(Long userId, String name, Long age);
}
