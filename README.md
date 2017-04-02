### Exercise 3 - Technical Comparison of an SQL and Graph Database

This exercise compares two differnts database paradigms relational (mySQL) against graph database (Neo4j) in different search depths. 
The dataset consinsts of 500.000 nodes and some millions edges. 


### Results

In our experiment we ran a query on each database with 20 random persons on each depth ranging from 1-5. 
	

|                  | MySQL Average  | MySQL Median | Neo4j Average | Neo4j Median |
 ----------------- | -------------- | -------------| --------------| -------------
| Depth one | 0.65ms| 0.0ms  | 5.4ms  |4.5ms
| Depth two | 11.7ms | 11.5ms |6.0ms|3.5ms
| Depth three | 236.55ms | 175.0ms | 14.1ms | 12.0ms
| Depth four  | 5256.7ms | 3054.0ms |420.75ms | 336.0ms
| Depth five  | 69547.25ms |62073.5ms |6996.5ms | 6208.5ms

Our results show that Neo4j, is much faster than the MySQL database when the depth increases. 
Neo4j completes the query at depth 5 at a average of 6s, where MySQL take 69s, that means that is it roughly 11 times slower.

The complexity of the queries also increases for MySQL, but almost remains the same for Neo4j. 
Code example for depth five query for MySQL and Neo4j:

MySQL
```
        String sqlString = "SELECT count(DISTINCT target_node_id) " +
                "FROM t_user " +
                "INNER JOIN t_endorses ON t_user.id = source_node_id " +
                "WHERE source_node_id IN (SELECT target_node_id " +
                " FROM t_user " +
                "INNER JOIN t_endorses ON t_user.id = source_node_id " +
                "WHERE source_node_id IN (SELECT target_node_id " +
                "FROM t_user " +
                "INNER JOIN t_endorses ON t_user.id = source_node_id " +
                "WHERE source_node_id IN (SELECT target_node_id " +
                "FROM t_user " +
                "INNER JOIN t_endorses ON t_user.id = source_node_id " +
                "WHERE source_node_id IN (SELECT target_node_id " +
                "FROM t_user " +
                "INNER JOIN t_endorses ON t_user.id = source_node_id " +
                "WHERE source_node_id = " + nodeId + "))))";
```
Neo4j
```
        String cypherString = "MATCH (a:Person)-[:ENDORSES*5..5]->(c)" +
                "WHERE a.id = " + nodeId +
                " RETURN count(distinct(c))";
  
