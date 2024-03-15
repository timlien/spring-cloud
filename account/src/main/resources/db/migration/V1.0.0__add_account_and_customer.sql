CREATE TABLE `customer`
(
    `customer_id`   INT          NOT NULL AUTO_INCREMENT,
    `name`          VARCHAR(100) NOT NULL,
    `email`         VARCHAR(100) NOT NULL,
    `mobile_number` VARCHAR(20)  NOT NULL,
    `created_at`    DATETIME     NOT NULL,
    `created_by`    VARCHAR(30)  NOT NULL,
    `updated_at`    DATETIME    DEFAULT NULL,
    `updated_by`    VARCHAR(30) DEFAULT NULL,
    PRIMARY KEY (`customer_id`)
);

CREATE TABLE `account`
(
    `account_number` INT          NOT NULL AUTO_INCREMENT,
    `customer_id`    INT          NOT NULL,
    `account_type`   VARCHAR(100) NOT NULL,
    `branch_address` VARCHAR(200) NOT NULL,
    `created_at`     DATETIME     NOT NULL,
    `created_by`     VARCHAR(30)  NOT NULL,
    `updated_at`     DATETIME    DEFAULT NULL,
    `updated_by`     VARCHAR(30) DEFAULT NULL,
    PRIMARY KEY (`account_number`),
    KEY (`customer_id`)
);