-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE =
        'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema SailToTMDB
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema SailToTMDB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `SailToTMDB` DEFAULT CHARACTER SET utf8mb3;
USE `SailToTMDB`;

-- -----------------------------------------------------
-- Table `SailToTMDB`.`Users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SailToTMDB`.`Users`
(
    `user_id`       INT          NOT NULL AUTO_INCREMENT,
    `password`      VARCHAR(250) NOT NULL,
    `first_name`    VARCHAR(50)  NOT NULL,
    `last_name`     VARCHAR(50)  NOT NULL,
    `email`         VARCHAR(250) NOT NULL,
    `phone_number`  VARCHAR(45)  NULL DEFAULT NULL,
    `date_of_birth` DATETIME     NULL DEFAULT NULL,
    `created_date`  DATETIME     NOT NULL,
    `updated_date`  DATETIME     NOT NULL,
    `user_name`     VARCHAR(500) NOT NULL,
    PRIMARY KEY (`user_id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 100001
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `SailToTMDB`.`CartItems`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `SailToTMDB`.`CartItems`
(
    `card_id`      INT          NOT NULL AUTO_INCREMENT,
    `user_id`      INT          NOT NULL,
    `quantity`     INT          NOT NULL,
    `created_date` datetime     NOT NULL,
    `num_iid`      VARCHAR(100) NOT NULL,
    PRIMARY KEY (`card_id`, `user_id`),
    INDEX `fk_Cart_list_Users_idx` (`user_id` ASC) VISIBLE,
    CONSTRAINT `fk_Cart_list_Users`
        FOREIGN KEY (`user_id`)
            REFERENCES `SailToTMDB`.`Users` (`user_id`)
            ON DELETE NO ACTION
            ON UPDATE CASCADE,
    FOREIGN KEY (`num_iid`)
        REFERENCES `SailToTMDB`.`OneBoundApiTaobaoProduct` (`num_iid`)
        ON DELETE NO ACTION
        ON UPDATE CASCADE
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 10000001
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `SailToTMDB`.`ChinaDeliverDetails`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SailToTMDB`.`ChinaDeliverDetails`
(
    `deliver_id`   INT          NOT NULL AUTO_INCREMENT,
    `name`         VARCHAR(50)  NOT NULL,
    `phone_number` VARCHAR(15)  NOT NULL,
    `address`      VARCHAR(250) NOT NULL,
    PRIMARY KEY (`deliver_id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 100001
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `SailToTMDB`.`RoleType`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SailToTMDB`.`RoleType`
(
    `role_id` INT         NOT NULL,
    `role`    VARCHAR(50) NOT NULL,
    PRIMARY KEY (`role_id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `SailToTMDB`.`StoreLocations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SailToTMDB`.`StoreLocations`
(
    `location_id` INT          NOT NULL AUTO_INCREMENT,
    `location`    VARCHAR(250) NOT NULL,
    PRIMARY KEY (`location_id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 100001
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `SailToTMDB`.`StuffStatusType`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SailToTMDB`.`StuffStatusType`
(
    `status_id` INT         NOT NULL,
    `status`    VARCHAR(50) NOT NULL,
    PRIMARY KEY (`status_id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `SailToTMDB`.`Stuff`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SailToTMDB`.`Stuff`
(
    `stuff_id`     INT          NOT NULL AUTO_INCREMENT,
    `role_id`      INT          NOT NULL,
    `location_id`  INT          NOT NULL,
    `status_id`    INT          NOT NULL,
    `first_name`   VARCHAR(50)  NOT NULL,
    `last_name`    VARCHAR(50)  NOT NULL,
    `email`        VARCHAR(250) NOT NULL,
    `phone_number` VARCHAR(15)  NOT NULL,
    `password`     VARCHAR(250) NOT NULL,
    `created_date` DATETIME     NOT NULL,
    `resign_date`  DATETIME     NULL DEFAULT NULL,
    `salary`       FLOAT        NOT NULL,
    PRIMARY KEY (`stuff_id`, `role_id`, `location_id`, `status_id`),
    INDEX `fk_Stuff_Stuff_status1_idx` (`status_id` ASC) VISIBLE,
    INDEX `fk_Stuff_RoleType1_idx` (`role_id` ASC) VISIBLE,
    INDEX `fk_Stuff_StoreLocations1_idx` (`location_id` ASC) VISIBLE,
    CONSTRAINT `fk_Stuff_RoleType1`
        FOREIGN KEY (`role_id`)
            REFERENCES `SailToTMDB`.`RoleType` (`role_id`)
            ON DELETE NO ACTION
            ON UPDATE CASCADE,
    CONSTRAINT `fk_Stuff_StoreLocations1`
        FOREIGN KEY (`location_id`)
            REFERENCES `SailToTMDB`.`StoreLocations` (`location_id`),
    CONSTRAINT `fk_Stuff_Stuff_status1`
        FOREIGN KEY (`status_id`)
            REFERENCES `SailToTMDB`.`StuffStatusType` (`status_id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1001
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `SailToTMDB`.`GoodsDetails`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `SailToTMDB`.`GoodsDetails`
(
    `good_detail_id` INT          NOT NULL,
    `quantity`       INT          NOT NULL,
    `num_iid`        VARCHAR(100) NOT NULL,
    PRIMARY KEY (`good_detail_id`),
    CONSTRAINT `fk_Goods_details`
        FOREIGN KEY (`num_iid`)
            REFERENCES `SailToTMDB`.`OneBoundApiTaobaoProduct` (`num_iid`)
            ON DELETE NO ACTION
            ON UPDATE CASCADE
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SailToTMDB`.`TransportTypes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SailToTMDB`.`TransportTypes`
(
    `transport_id` INT          NOT NULL AUTO_INCREMENT,
    `transport`    VARCHAR(250) NOT NULL,
    `price`        FLOAT        NOT NULL,
    `updated_date` DATETIME     NOT NULL,
    PRIMARY KEY (`transport_id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 100001
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `SailToTMDB`.`CarrierInfo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SailToTMDB`.`CarrierInfo`
(
    `carrier_id`     INT         NOT NULL,
    `transport_id`   INT         NOT NULL,
    `carrier_number` VARCHAR(50) NULL,
    `send_date`      DATETIME    NULL,
    `receive_date`   DATETIME    NULL,
    PRIMARY KEY (`carrier_id`, `transport_id`),
    INDEX `fk_CarrierInfo_TransportTypes1_idx` (`transport_id` ASC) VISIBLE,
    CONSTRAINT `fk_CarrierInfo_TransportTypes1`
        FOREIGN KEY (`transport_id`)
            REFERENCES `SailToTMDB`.`TransportTypes` (`transport_id`)
            ON DELETE NO ACTION
            ON UPDATE CASCADE
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SailToTMDB`.`TransportDetails`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SailToTMDB`.`TransportDetails`
(
    `transport_detail_id`        INT      NOT NULL,
    `stuff_id`                   INT      NOT NULL,
    `carrier_id`                 INT      NOT NULL,
    `transport_id`               INT      NOT NULL,
    `location_id`                INT      NOT NULL,
    `width`                      INT      NULL,
    `height`                     INT      NULL,
    `length`                     INT      NULL,
    `weight`                     FLOAT    NULL,
    `estimated_transport_cost`   FLOAT    NULL,
    `transport_cost`             FLOAT    NULL,
    `transport_cost_paid_status` INT      NOT NULL,
    `transport_cost_paid_date`   DATETIME NULL,
    PRIMARY KEY (`transport_detail_id`, `stuff_id`, `carrier_id`, `transport_id`, `location_id`),
    INDEX `fk_TransportDetails_CarrierInfo1_idx` (`carrier_id` ASC, `transport_id` ASC) VISIBLE,
    INDEX `fk_TransportDetails_StoreLocations1_idx` (`location_id` ASC) VISIBLE,
    INDEX `fk_TransportDetails_Stuff1_idx` (`stuff_id` ASC) VISIBLE,
    CONSTRAINT `fk_TransportDetails_CarrierInfo1`
        FOREIGN KEY (`carrier_id`, `transport_id`)
            REFERENCES `SailToTMDB`.`CarrierInfo` (`carrier_id`, `transport_id`)
            ON DELETE NO ACTION
            ON UPDATE CASCADE,
    CONSTRAINT `fk_TransportDetails_StoreLocations1`
        FOREIGN KEY (`location_id`)
            REFERENCES `SailToTMDB`.`StoreLocations` (`location_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_TransportDetails_Stuff1`
        FOREIGN KEY (`stuff_id`)
            REFERENCES `SailToTMDB`.`Stuff` (`stuff_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SailToTMDB`.`ChinaDetails`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SailToTMDB`.`ChinaDetails`
(
    `china_detail_id` INT         NOT NULL,
    `deliver_id`      INT         NOT NULL,
    `stuff_id`        INT         NOT NULL,
    `taobao_id`       VARCHAR(50) NULL,
    `send_date`       DATETIME    NULL,
    `receive_date`    DATETIME    NULL,
    `purchase_date`   DATETIME    NULL,
    PRIMARY KEY (`china_detail_id`, `deliver_id`, `stuff_id`),
    INDEX `fk_ChinaDetails_ChinaDeliverDetails1_idx` (`deliver_id` ASC) VISIBLE,
    INDEX `fk_ChinaDetails_Stuff1_idx` (`stuff_id` ASC) VISIBLE,
    CONSTRAINT `fk_ChinaDetails_ChinaDeliverDetails1`
        FOREIGN KEY (`deliver_id`)
            REFERENCES `SailToTMDB`.`ChinaDeliverDetails` (`deliver_id`)
            ON DELETE NO ACTION
            ON UPDATE CASCADE,
    CONSTRAINT `fk_ChinaDetails_Stuff1`
        FOREIGN KEY (`stuff_id`)
            REFERENCES `SailToTMDB`.`Stuff` (`stuff_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SailToTMDB`.`OrdersType`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SailToTMDB`.`OrdersType`
(
    `type_id` INT         NOT NULL,
    `type`    VARCHAR(50) NOT NULL,
    PRIMARY KEY (`type_id`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SailToTMDB`.`Orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SailToTMDB`.`Orders`
(
    `order_id`            INT      NOT NULL,
    `user_id`             INT      NOT NULL,
    `stuff_id`            INT      NOT NULL,
    `good_detail_id`      INT      NOT NULL,
    `transport_detail_id` INT      NOT NULL,
    `china_detail_id`     INT      NOT NULL,
    `type_id`             INT      NOT NULL,
    `status`              INT      NOT NULL,
    `pickup_code`         INT      NOT NULL,
    `paid_date`           DATETIME NOT NULL,
    `created_date`        DATETIME NOT NULL,
    PRIMARY KEY (`order_id`, `user_id`, `stuff_id`, `good_detail_id`, `transport_detail_id`, `china_detail_id`,
                 `type_id`),
    INDEX `fk_Orders_Users1_idx` (`user_id` ASC) VISIBLE,
    INDEX `fk_Orders_Stuff1_idx` (`stuff_id` ASC) VISIBLE,
    INDEX `fk_Orders_GoodsDetails1_idx` (`good_detail_id` ASC) VISIBLE,
    INDEX `fk_Orders_TransportDetails1_idx` (`transport_detail_id` ASC) VISIBLE,
    INDEX `fk_Orders_ChinaDetails1_idx` (`china_detail_id` ASC) VISIBLE,
    INDEX `fk_Orders_OrdersType1_idx` (`type_id` ASC) VISIBLE,
    CONSTRAINT `fk_Orders_Users1`
        FOREIGN KEY (`user_id`)
            REFERENCES `SailToTMDB`.`Users` (`user_id`)
            ON DELETE NO ACTION
            ON UPDATE CASCADE,
    CONSTRAINT `fk_Orders_Stuff1`
        FOREIGN KEY (`stuff_id`)
            REFERENCES `SailToTMDB`.`Stuff` (`stuff_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_Orders_GoodsDetails1`
        FOREIGN KEY (`good_detail_id`)
            REFERENCES `SailToTMDB`.`GoodsDetails` (`good_detail_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_Orders_TransportDetails1`
        FOREIGN KEY (`transport_detail_id`)
            REFERENCES `SailToTMDB`.`TransportDetails` (`transport_detail_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_Orders_ChinaDetails1`
        FOREIGN KEY (`china_detail_id`)
            REFERENCES `SailToTMDB`.`ChinaDetails` (`china_detail_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_Orders_OrdersType1`
        FOREIGN KEY (`type_id`)
            REFERENCES `SailToTMDB`.`OrdersType` (`type_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `SailToTMDB`.`FavouriteItems`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `SailToTMDB`.`FavouriteItems`
(
    `favourite_id` INT          NOT NULL AUTO_INCREMENT,
    `user_id`      INT          NOT NULL,
    `created_date` DATETIME     NOT NULL,
    `num_iid`      varchar(100) NOT NULL,
    PRIMARY KEY (`favourite_id`, `user_id`),
    INDEX `fk_Favourite_list_Users1_idx` (`user_id` ASC) VISIBLE,
    CONSTRAINT `fk_Favourite_list_Users1`
        FOREIGN KEY (`user_id`)
            REFERENCES `SailToTMDB`.`Users` (`user_id`)
            ON DELETE NO ACTION
            ON UPDATE CASCADE,
    FOREIGN KEY (`num_iid`)
        REFERENCES `SailToTMDB`.`OneBoundApiTaobaoProduct` (`num_iid`)
        ON DELETE NO ACTION
        ON UPDATE CASCADE
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 10000001
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `SailToTMDB`.`HistoryItems`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `SailToTMDB`.`HistoryItems`
(
    `history_id`   INT          NOT NULL AUTO_INCREMENT,
    `user_id`      INT          NOT NULL,
    `num_iid`      VARCHAR(100) NOT NULL,
    `created_date` DATETIME     NOT NULL,
    PRIMARY KEY (`history_id`, `user_id`),
    INDEX `fk_History_list_Users1_idx` (`user_id` ASC) VISIBLE,
    CONSTRAINT `fk_History_list_Users1`
        FOREIGN KEY (`user_id`)
            REFERENCES `SailToTMDB`.`Users` (`user_id`)
            ON DELETE NO ACTION
            ON UPDATE CASCADE,
    FOREIGN KEY (`num_iid`)
        REFERENCES `SailToTMDB`.`OneBoundApiTaobaoProduct` (`num_iid`)
        ON DELETE NO ACTION
        ON UPDATE CASCADE
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 10000001
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `SailToTMDB`.`MembershipBalanceHistoryType`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SailToTMDB`.`MembershipBalanceHistoryType`
(
    `type_id` INT         NOT NULL,
    `type`    VARCHAR(50) NOT NULL,
    PRIMARY KEY (`type_id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `SailToTMDB`.`MembershipUsers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SailToTMDB`.`MembershipUsers`
(
    `membership_id` INT          NOT NULL AUTO_INCREMENT,
    `user_id`       INT          NOT NULL,
    `balance`       FLOAT        NOT NULL,
    `password`      VARCHAR(250) NOT NULL,
    `created_date`  DATETIME     NOT NULL,
    PRIMARY KEY (`membership_id`, `user_id`),
    INDEX `fk_MembershipUsers_Users1_idx` (`user_id` ASC) VISIBLE,
    CONSTRAINT `fk_MembershipUsers_Users1`
        FOREIGN KEY (`user_id`)
            REFERENCES `SailToTMDB`.`Users` (`user_id`)
            ON DELETE NO ACTION
            ON UPDATE CASCADE
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 10001
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `SailToTMDB`.`MembershipUsersBalanceHistory`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SailToTMDB`.`MembershipUsersBalanceHistory`
(
    `history_id`    INT      NOT NULL AUTO_INCREMENT,
    `membership_id` INT      NOT NULL,
    `user_id`       INT      NOT NULL,
    `stuff_id`      INT      NOT NULL,
    `status_id`     INT      NOT NULL,
    `total`         FLOAT    NOT NULL,
    `created_date`  DATETIME NOT NULL,
    PRIMARY KEY (`history_id`, `membership_id`, `user_id`, `stuff_id`, `status_id`),
    INDEX `fk_Membership_users_history_Membership_history_status1_idx` (`status_id` ASC) VISIBLE,
    INDEX `fk_Membership_users_history_Stuff1_idx` (`stuff_id` ASC) VISIBLE,
    INDEX `fk_MembershipUsersBalanceHistory_MembershipUsers1_idx` (`membership_id` ASC, `user_id` ASC) VISIBLE,
    CONSTRAINT `fk_Membership_users_history_Membership_history_status1`
        FOREIGN KEY (`status_id`)
            REFERENCES `SailToTMDB`.`MembershipBalanceHistoryType` (`type_id`)
            ON DELETE NO ACTION
            ON UPDATE CASCADE,
    CONSTRAINT `fk_Membership_users_history_Stuff1`
        FOREIGN KEY (`stuff_id`)
            REFERENCES `SailToTMDB`.`Stuff` (`stuff_id`),
    CONSTRAINT `fk_MembershipUsersBalanceHistory_MembershipUsers1`
        FOREIGN KEY (`membership_id`, `user_id`)
            REFERENCES `SailToTMDB`.`MembershipUsers` (`membership_id`, `user_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `SailToTMDB`.`SearchItems`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SailToTMDB`.`SearchItems`
(
    `search_id`      INT          NOT NULL AUTO_INCREMENT,
    `user_id`        INT          NOT NULL,
    `search`         VARCHAR(100) NOT NULL,
    `searched_times` INT          NOT NULL,
    PRIMARY KEY (`search_id`, `user_id`),
    INDEX `fk_SearchItems_Users1_idx` (`user_id` ASC) VISIBLE,
    CONSTRAINT `fk_SearchItems_Users1`
        FOREIGN KEY (`user_id`)
            REFERENCES `SailToTMDB`.`Users` (`user_id`)
            ON DELETE NO ACTION
            ON UPDATE CASCADE
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `SailToTMDB`.`Stuff_history`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SailToTMDB`.`Stuff_history`
(
    `history_id`   INT          NOT NULL AUTO_INCREMENT,
    `stuff_id`     INT          NOT NULL,
    `role_id`      INT          NOT NULL,
    `location_id`  INT          NOT NULL,
    `status_id`    INT          NOT NULL,
    `first_name`   VARCHAR(50)  NOT NULL,
    `last_name`    VARCHAR(50)  NOT NULL,
    `email`        VARCHAR(250) NOT NULL,
    `phone_number` VARCHAR(15)  NOT NULL,
    `password`     VARCHAR(250) NOT NULL,
    `created_date` DATETIME     NOT NULL,
    `resign_date`  DATETIME     NOT NULL,
    `salary`       FLOAT        NOT NULL,
    PRIMARY KEY (`history_id`, `stuff_id`),
    INDEX `fk_Stuff_history_Stuff1_idx` (`stuff_id` ASC) VISIBLE,
    CONSTRAINT `fk_Stuff_history_Stuff1`
        FOREIGN KEY (`stuff_id`)
            REFERENCES `SailToTMDB`.`Stuff` (`stuff_id`)
            ON DELETE NO ACTION
            ON UPDATE CASCADE
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `SailToTMDB`.`CarriersPriceHistory`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SailToTMDB`.`CarriersPriceHistory`
(
    `history_id`   INT      NOT NULL,
    `transport_id` INT      NOT NULL,
    `price`        FLOAT    NOT NULL,
    `date`         DATETIME NOT NULL,
    PRIMARY KEY (`history_id`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SailToTMDB`.`ExchangeRate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SailToTMDB`.`ExchangeRate`
(
    `rate_id`      INT      NOT NULL,
    `rmb`          FLOAT    NOT NULL,
    `usd`          FLOAT    NOT NULL,
    `manat`        FLOAT    NOT NULL,
    `updated_date` DATETIME NOT NULL,
    PRIMARY KEY (`rate_id`)
)
    ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `SailToTMDB`.`InterestRate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SailToTMDB`.`InterestRate`
(
    `interest_id`   INT   NOT NULL,
    `interest_rate` FLOAT NOT NULL,
    PRIMARY KEY (`interest_id`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SailToTMDB`.`ProgressDetailsInfo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SailToTMDB`.`ProgressDetailsInfo`
(
    `progress_id`          INT   NOT NULL,
    `new_users_number`     INT   NOT NULL,
    `new_orders_number`    INT   NOT NULL,
    `active_users_number`  INT   NOT NULL,
    `search_number`        INT   NOT NULL,
    `search_items_number`  INT   NOT NULL,
    `new_membership_users` INT   NOT NULL,
    `total_order_sum`      FLOAT NOT NULL,
    `created_date`         DATE  NOT NULL,
    PRIMARY KEY (`progress_id`)
)
    ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `SailToTMDB`.`OneBoundApiTaobaoProduct`
(
    created_date    DATETIME      NOT NULL,
    detail_url      VARCHAR(2000),
    num_iid         VARCHAR(100)  NOT NULL,
    pic_url         VARCHAR(2000) NOT NULL,
    price           FLOAT         NOT NULL,
    promotion_price FLOAT         NOT NULL,
    sales           INT,
    saller_nick     VARCHAR(100),
    title           VARCHAR(1000) NOT NULL,
    PRIMARY KEY (`num_iid`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 10000001
    DEFAULT CHARACTER SET = utf8mb3;

ALTER TABLE `SailToTMDB`.OneBoundApiTaobaoProduct
    ADD `q` VARCHAR(200) NOT NULL;


SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;
