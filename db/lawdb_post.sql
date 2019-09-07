DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;

CREATE TABLE post (
	id bigint PRIMARY key AUTO_INCREMENT,
	title VARCHAR(300) ,
	content VARCHAR(4096),
	tag VARCHAR(300),
	user_id INT(11),
	post_status VARCHAR(10),  
	created_datetime DATETIME,
	updated_datetime DATETIME,
	
	FOREIGN KEY (user_id) REFERENCES user(id)
)