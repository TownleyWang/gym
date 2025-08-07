package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.system.domain.GymReservation;
import com.ruoyi.system.domain.GymTimeSlot;
import com.ruoyi.system.mapper.GymReservationMapper;
import com.ruoyi.system.service.IGymReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.GymTimeSlotMapper;


import java.util.Date;
import java.util.UUID;

@Service
public class GymReservationServiceImpl implements IGymReservationService {

    @Autowired
    private GymTimeSlotMapper gymTimeSlotMapper;

    @Autowired
    private GymReservationMapper gymReservationMapper;

    @Override
    public AjaxResult reserve(Long slotId) {
        Long userId = ShiroUtils.getUserId();

        // 是否已预约
        int exist = gymReservationMapper.countByUserAndSlot(userId, slotId);
        if (exist > 0) {
            return AjaxResult.error("你已经预约过该时间段，不能重复预约");
        }

        // 🔍 查询 slot 详情（获取 resourceId）
        GymTimeSlot slot = gymTimeSlotMapper.selectGymTimeSlotById(slotId);
        if (slot == null) {
            return AjaxResult.error("时间段不存在");
        }

        Long resourceId = slot.getResourceId();

        // 插入预约记录
        GymReservation r = new GymReservation();
        r.setUserId(userId);
        r.setSlotId(slotId);
        r.setResourceId(resourceId);  // ✅ 设置 resource_id
        r.setReservationDate(new Date()); // 可选：补充预约日期
        r.setCreateTime(new Date());
        r.setUpdateTime(new Date());
        r.setStatus("ACTIVE"); // 可选字段，视表结构而定
        r.setCurrentBookings(1);  // 先设为 1（后面统一更新）
        r.setReservationCode(UUID.randomUUID().toString().substring(0, 8));


        gymReservationMapper.insertReservation(r);

        // 更新该时间段所有记录的 current_bookings
        int total = gymReservationMapper.countSlotBookings(slotId);
        gymReservationMapper.updateCurrentBookings(slotId, total);

        return AjaxResult.success("预约成功");
    }


    @Override
    public AjaxResult cancel(Long slotId) {
        Long userId = ShiroUtils.getUserId();

        // 删除记录
        gymReservationMapper.deleteByUserAndSlot(userId, slotId);

        // 更新 current_bookings
        int total = gymReservationMapper.countSlotBookings(slotId);
        gymReservationMapper.updateCurrentBookings(slotId, total);

        return AjaxResult.success("取消预约成功");
    }

    @Override
    public int getCurrentBookings(Long slotId, Date reservationDate) {
        return gymReservationMapper.countBySlotAndDate(slotId, reservationDate);
    }

    @Override
    public GymReservation selectBySlotIdAndUser(Long slotId, String username) {
        return gymReservationMapper.selectBySlotIdAndUsername(slotId, username);
    }

}
