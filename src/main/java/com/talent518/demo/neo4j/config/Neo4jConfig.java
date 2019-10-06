package com.talent518.demo.neo4j.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@Configuration
@EnableNeo4jRepositories("com.talent518.demo.neo4j.mapper")
public class Neo4jConfig {
}
