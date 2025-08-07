package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.GymReservation;

import java.util.Date;

public interface IGymReservationService {
    AjaxResult reserve(Long slotId);  // 预约接口
    AjaxResult cancel(Long slotId);   // 取消预约接口
    int getCurrentBookings(Long slotId, Date reservationDate);

    GymReservation selectBySlotIdAndUser(Long slotId, String username);
}
