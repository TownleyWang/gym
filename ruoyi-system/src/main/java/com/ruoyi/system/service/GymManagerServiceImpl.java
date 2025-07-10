package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.GymManager;
import com.ruoyi.system.mapper.GymManagerMapper;
import com.ruoyi.system.service.IGymManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GymManagerServiceImpl implements IGymManagerService {

    @Autowired
    private GymManagerMapper gymManagerMapper;

    @Override
    public List<GymManager> selectGymManagerList() {
        return gymManagerMapper.selectGymManagerList();
    }
}
