package com.talent518.demo.neo4j.mapper;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.talent518.demo.neo4j.pojo.UserRelation;

@Repository
public interface UserRelationRepository extends Neo4jRepository<UserRelation, Long> {
	@Query("MATCH (fu:User)-[r:UserRelation]->(su:User) RETURN fu,r,su LIMIT {limit}")
	List<UserRelation> findRelationAll(int limit);
	
	@Query("MATCH (fu:User)-[r:UserRelation]->(su:User) WHERE fu.userId={firstUserId} AND su.userId={secondUserId} RETURN fu,r,su")
	List<UserRelation> findRelationBySingleId(Long firstUserId, Long secondUserId);

	@Query("MATCH (fu:User)<-[r:UserRelation]->(su:User) WHERE fu.userId={firstUserId} AND su.userId={secondUserId} RETURN fu,r,su")
	List<UserRelation> findRelationByEachId(Long firstUserId, Long secondUserId);

	@Query("MATCH (fu:User),(su:User) WHERE fu.userId={firstUserId} AND su.userId={secondUserId} MERGE (fu)-[r:UserRelation]->(su) RETURN fu,r,su")
	List<UserRelation> createRelationByEachId(@Param("firstUserId") Long firstUserId, @Param("secondUserId") Long secondUserId);
}
