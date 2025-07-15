package com.ruoyi.system.service;

import com.ruoyi.system.domain.GymResource;
import java.util.List;

public interface IGymResourceService {
    /**
     * 查询健身资源列表
     */
    List<GymResource> selectResourceList(GymResource resource);

    /**
     * 根据 id 查询资源（用于更新用）
     */
    GymResource selectGymResourceById(Long id);

    /**
     * 更新资源（用于更新 updateBy 和 updateTime）
     */
    int updateGymResource(GymResource resource);
}