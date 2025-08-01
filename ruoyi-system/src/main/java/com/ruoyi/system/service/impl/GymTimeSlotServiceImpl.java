package com.ruoyi.system.service.impl;

import com.ruoyi.system.mapper.GymReservationMapper;
import com.ruoyi.system.mapper.GymTimeSlotMapper;
import com.ruoyi.system.domain.GymTimeSlot;
import com.ruoyi.system.service.IGymTimeSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Date;


@Service
public class GymTimeSlotServiceImpl implements IGymTimeSlotService {

    @Autowired
    private GymTimeSlotMapper gymTimeSlotMapper;

    @Autowired
    private GymReservationMapper gymReservationMapper;


    @Override
    public List<GymTimeSlot> selectSlotsByResourceId(Long resourceId) {
        List<GymTimeSlot> list = gymTimeSlotMapper.selectSlotsByResourceId(resourceId);
        Date today = new Date();  // 你只关心当天的预约数
        for (GymTimeSlot slot : list) {
            int count = gymReservationMapper.countBySlotAndDate(slot.getSlotId(), today);
            slot.setCurrentBookings(count);
        }
        return list;
    }


    @Override
    public int bookSlot(Long slotId, String username) {
        GymTimeSlot slot = gymTimeSlotMapper.selectSlotById(slotId);
        if (slot == null) {
            return 0;
        }

        int current = slot.getCurrentBookings();
        int max = slot.getMaxBookings();
        if (current >= max) {
            return 0;  // 无法预约
        }

        slot.setCurrentBookings(current + 1);
        slot.setUpdateBy(username);
        slot.setUpdateTime(new Date());
        slot.setStatus(current + 1 >= max ? "FULL" : "AVAILABLE"); // 你也可以加条件设置为 FULL

        return gymTimeSlotMapper.updateCurrentBookings(slot);
    }

    @Override
    public int cancelSlot(Long slotId, String username) {
        GymTimeSlot slot = gymTimeSlotMapper.selectSlotById(slotId);
        if (slot == null || slot.getCurrentBookings() <= 0) {
            return 0;
        }

        slot.setCurrentBookings(slot.getCurrentBookings() - 1);
        slot.setUpdateBy(username);
        slot.setUpdateTime(new Date());
        slot.setStatus(slot.getCurrentBookings() - 1 < slot.getMaxBookings() ? "AVAILABLE" : "FULL");


        return gymTimeSlotMapper.updateCurrentBookings(slot);
    }

}
