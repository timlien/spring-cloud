CREATE TABLE `loan`
(
    `loan_id`            INT          NOT NULL AUTO_INCREMENT,
    `mobile_number`      VARCHAR(15)  NOT NULL,
    `loan_number`        VARCHAR(100) NOT NULL,
    `loan_type`          VARCHAR(100) NOT NULL,
    `total_loan`         INT          NOT NULL,
    `amount_paid`        INT          NOT NULL,
    `outstanding_amount` INT          NOT NULL,
    `created_at`         DATETIME     NOT NULL,
    `created_by`         VARCHAR(30)  NOT NULL,
    `updated_at`         DATETIME    DEFAULT NULL,
    `updated_by`         VARCHAR(30) DEFAULT NULL,
    PRIMARY KEY (`loan_id`)
);