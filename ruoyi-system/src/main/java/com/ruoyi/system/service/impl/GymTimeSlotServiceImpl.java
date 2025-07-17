package com.ruoyi.system.service.impl;

import com.ruoyi.system.mapper.GymTimeSlotMapper;
import com.ruoyi.system.domain.GymTimeSlot;
import com.ruoyi.system.service.IGymTimeSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GymTimeSlotServiceImpl implements IGymTimeSlotService {

    @Autowired
    private GymTimeSlotMapper gymTimeSlotMapper;

    @Override
    public List<GymTimeSlot> selectSlotsByResourceId(Long resourceId) {
        return gymTimeSlotMapper.selectSlotsByResourceId(resourceId);
    }
}
