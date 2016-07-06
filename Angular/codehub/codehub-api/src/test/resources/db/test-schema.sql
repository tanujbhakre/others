--
-- Table structure for table `submissions`
--
DROP TABLE IF EXISTS submissions;

CREATE TABLE submissions (
  `submission_id` int(11) NOT NULL AUTO_INCREMENT,	
  `title` varchar(45)   NOT NULL,
  `metadata` text   NOT NULL,
  `source` varchar(20000)   NOT NULL,
  `status` varchar(45)   NOT NULL,
  `language` varchar(45)   NOT NULL,
  PRIMARY KEY (`submission_id`)
)