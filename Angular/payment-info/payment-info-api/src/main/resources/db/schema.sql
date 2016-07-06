
CREATE TABLE IF NOT EXISTS VT_Services (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `image` text NOT NULL,
  `description` text NOT NULL,
  `branding` varchar(10) NOT NULL,
  `rating` float NOT NULL,
  `setup_fee` float NOT NULL,
  `transaction_fees` text NOT NULL,
  `how_to_url` text NOT NULL,
  `currencies` text NOT NULL,
  PRIMARY KEY (`id`)
);

truncate table VT_Services;