package com.ruoyi.system.service;

import com.ruoyi.system.domain.GymTimeSlot;
import java.util.List;

public interface IGymTimeSlotService {
    int bookSlot(Long slotId, String username);      // 预约
    int cancelSlot(Long slotId, String username);    // 取消预约
    List<GymTimeSlot> selectSlotsByResourceId(Long resourceId);
}
