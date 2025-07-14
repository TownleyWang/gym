package com.ruoyi.system.service;

import com.ruoyi.system.domain.GymResource;
import java.util.List;

public interface IGymResourceService {
    /**
     * 查询健身资源列表
     */
    List<GymResource> selectResourceList(GymResource resource);
}
