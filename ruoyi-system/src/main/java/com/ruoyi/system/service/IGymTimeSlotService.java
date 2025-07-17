package com.ruoyi.system.service;

import com.ruoyi.system.domain.GymTimeSlot;
import java.util.List;

public interface IGymTimeSlotService {
    List<GymTimeSlot> selectSlotsByResourceId(Long resourceId);
}
