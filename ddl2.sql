CREATE TABLE `profile` (
  `uid` int(11) unsigned NOT NULL,
  `realName` varchar(30) NOT NULL,
  `sex` enum('B','G','N') NOT NULL,
  `birthday` date NOT NULL,
  `address` varchar(100) NOT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO profile VALUES(1,'阿宝', 'B', '1987-01-30', '上海市浦东新区');
