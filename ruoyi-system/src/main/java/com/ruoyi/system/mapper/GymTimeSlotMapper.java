package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.GymTimeSlot;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GymTimeSlotMapper {
    int updateCurrentBookings(GymTimeSlot slot);
    List<GymTimeSlot> selectSlotsByResourceId(Long resourceId);
    GymTimeSlot selectSlotById(Long slotId);
    GymTimeSlot selectGymTimeSlotById(@Param("slotId") Long slotId);
}