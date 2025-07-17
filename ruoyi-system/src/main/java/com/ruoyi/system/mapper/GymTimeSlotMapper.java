package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.GymTimeSlot;
import java.util.List;

public interface GymTimeSlotMapper {
    int updateCurrentBookings(GymTimeSlot slot);
    List<GymTimeSlot> selectSlotsByResourceId(Long resourceId);
    GymTimeSlot selectSlotById(Long slotId);
}