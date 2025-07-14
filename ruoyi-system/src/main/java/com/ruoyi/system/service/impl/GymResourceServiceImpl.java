package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.GymResource;
import com.ruoyi.system.mapper.GymResourceMapper;
import com.ruoyi.system.service.IGymResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GymResourceServiceImpl implements IGymResourceService {

    @Autowired
    private GymResourceMapper gymResourceMapper;

    @Override
    public List<GymResource> selectResourceList(GymResource resource) {
        return gymResourceMapper.selectResourceList(resource);
    }
}
