package com.itchen.usercenter.rocketmq;

import com.itchen.usercenter.dao.bonus.BonusEventLogMapper;
import com.itchen.usercenter.dao.user.UserMapper;
import com.itchen.usercenter.domain.dto.messaging.UserAddBonusMsgDTO;
import com.itchen.usercenter.domain.entity.bonus.BonusEventLog;
import com.itchen.usercenter.domain.entity.user.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 用户中心-积分消息监听器 .
 *
 * @author BibiChen
 * @version v1.0
 * @since 2020-02-09
 */
@Slf4j
@Service
@RocketMQMessageListener(consumerGroup = "consumer-group", topic = "add-bonus")
public class AddBonusListener implements RocketMQListener<UserAddBonusMsgDTO> {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BonusEventLogMapper bonusEventLogMapper;

    @Override
    public void onMessage(UserAddBonusMsgDTO message) {
        // 当收到消息的时候，执行的业务
        // 1. 为用户加积分
        Integer userId = message.getUserId();
        Integer bonus = message.getBonus();
        User user = this.userMapper.selectByPrimaryKey(userId);
        user.setBonus(user.getBonus() + bonus);
        this.userMapper.updateByPrimaryKey(user);

        // 2. 记录日志到 bonus_event_log 表里面
        this.bonusEventLogMapper.insert(
                BonusEventLog.builder()
                        .userId(userId)
                        .value(bonus)
                        .event("CONTRIBUTE")
                        .description("投稿加积分...")
                        .createTime(new Date())
                        .build()
        );
        log.info("积分添加完毕...");
    }

}
