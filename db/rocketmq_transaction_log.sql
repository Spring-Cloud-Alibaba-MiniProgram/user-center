USE `content_center`;
-- -----------------------------------------------------
-- Table `rocketmq_transaction_log`
-- -----------------------------------------------------
CREATE TABLE rocketmq_transaction_log
(
    id             INT auto_increment COMMENT 'id' PRIMARY KEY,
    transaction_Id VARCHAR(45) NOT NULL COMMENT '事务id',
    log            VARCHAR(45) NOT NULL COMMENT '日志'
) COMMENT 'RocketMQ事务日志表';