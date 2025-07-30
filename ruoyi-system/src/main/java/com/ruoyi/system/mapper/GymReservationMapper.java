package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.GymReservation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GymReservationMapper {

    int insertReservation(GymReservation reservation);

    int deleteByUserAndSlot(@Param("userId") Long userId, @Param("slotId") Long slotId);

    int countByUserAndSlot(@Param("userId") Long userId, @Param("slotId") Long slotId);

    int countSlotBookings(@Param("slotId") Long slotId);

    int updateCurrentBookings(@Param("slotId") Long slotId, @Param("total") int total);

    List<GymReservation> selectBySlotId(@Param("slotId") Long slotId); // 可用于显示详情等
}
