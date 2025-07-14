// GymResourceMapper.java
package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.GymResource;
import java.util.List;

public interface GymResourceMapper {
    List<GymResource> selectResourceList(GymResource resource);
}
