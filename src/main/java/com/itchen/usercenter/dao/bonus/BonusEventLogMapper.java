package com.itchen.usercenter.dao.bonus;

import com.itchen.usercenter.domain.entity.bonus.BonusEventLog;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

@Component
public interface BonusEventLogMapper extends Mapper<BonusEventLog> {
}