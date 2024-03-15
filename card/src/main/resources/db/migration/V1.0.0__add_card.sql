CREATE TABLE `card`
(
    `card_id`          INT          NOT NULL AUTO_INCREMENT,
    `mobile_number`    VARCHAR(15)  NOT NULL,
    `card_number`      VARCHAR(100) NOT NULL,
    `card_type`        VARCHAR(100) NOT NULL,
    `total_limit`      INT          NOT NULL,
    `amount_used`      INT          NOT NULL,
    `available_amount` INT          NOT NULL,
    `created_at`       DATETIME     NOT NULL,
    `created_by`       VARCHAR(30)  NOT NULL,
    `updated_at`       DATETIME    DEFAULT NULL,
    `updated_by`       VARCHAR(30) DEFAULT NULL,
    PRIMARY KEY (`card_id`)
);