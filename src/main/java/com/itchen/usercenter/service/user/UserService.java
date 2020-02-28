package com.itchen.usercenter.service.user;

import com.itchen.usercenter.dao.bonus.BonusEventLogMapper;
import com.itchen.usercenter.dao.user.UserMapper;
import com.itchen.usercenter.domain.dto.messaging.UserAddBonusMsgDTO;
import com.itchen.usercenter.domain.dto.user.UserLoginDTO;
import com.itchen.usercenter.domain.entity.bonus.BonusEventLog;
import com.itchen.usercenter.domain.entity.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 用户服务类 .
 *
 * @author BibiChen
 * @version v1.0
 * @since 2020-02-05
 */
@Service
@Slf4j
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BonusEventLogMapper bonusEventLogMapper;

    public User findById(Integer id) {
        return this.userMapper.selectByPrimaryKey(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void addBonus(UserAddBonusMsgDTO msgDTO) {
        // 当收到消息的时候，执行的业务
        // 1. 为用户加积分
        Integer userId = msgDTO.getUserId();
        Integer bonus = msgDTO.getBonus();
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

    public User login(UserLoginDTO loginDTO, String openId) {
        User user = this.userMapper.selectOne(
                User.builder()
                        .wxId(openId)
                        .build()
        );
        if (user == null) {
            User userToSave = User.builder()
                    .wxId(openId)
                    .bonus(300)
                    .wxNickname(loginDTO.getWxNickname())
                    .avatarUrl(loginDTO.getAvatarUrl())
                    .roles("user")
                    .createTime(new Date())
                    .updateTime(new Date())
                    .build();
            this.userMapper.insertSelective(userToSave);
            return userToSave;
        }
        return user;
    }

}
