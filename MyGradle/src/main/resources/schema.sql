DROP TABLE if exists exchangerate;
DROP TABLE if exists exchangeratetemporary;

CREATE TABLE `USER` (
  `ID` varchar(32) NOT NULL,
  `NAME` varchar(50) NOT NULL,
  `AGE` int(3) NOT NULL
  `GENDER` varchar(8) NOT NULL,
  PRIMARY KEY (`ID`)
);