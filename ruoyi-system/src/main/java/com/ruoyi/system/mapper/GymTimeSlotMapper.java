package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.GymTimeSlot;
import java.util.List;

public interface GymTimeSlotMapper {
    List<GymTimeSlot> selectSlotsByResourceId(Long resourceId);
}
