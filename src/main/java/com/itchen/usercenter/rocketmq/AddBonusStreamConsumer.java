package com.itchen.usercenter.rocketmq;

import com.itchen.usercenter.dao.bonus.BonusEventLogMapper;
import com.itchen.usercenter.domain.dto.messaging.UserAddBonusMsgDTO;
import com.itchen.usercenter.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Service;

/**
 * 测试 Stream 消费者 .
 *
 * @author BibiChen
 * @version v1.0
 * @since 2020-02-09
 */
@Service
@Slf4j
public class AddBonusStreamConsumer {

    @Autowired
    private UserService userService;

    @Autowired
    private BonusEventLogMapper bonusEventLogMapper;

    @StreamListener(Sink.INPUT)
    public void receive(UserAddBonusMsgDTO msgDTO) {
        this.userService.addBonus(msgDTO);
    }


}
