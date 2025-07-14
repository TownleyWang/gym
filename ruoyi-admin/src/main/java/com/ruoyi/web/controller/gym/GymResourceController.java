package com.ruoyi.web.controller.gym;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.GymResource;
import com.ruoyi.system.service.IGymResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/gym/resource")
public class GymResourceController extends BaseController {

    @Autowired
    private IGymResourceService gymResourceService;

    /**
     * 返回页面路径
     */
    @GetMapping()
    public String page() {
        return "gym/resource/list";  // 假设你后面有 HTML 页面放在 templates/gym/resource/list.html
    }

    /**
     * 返回 JSON 数据列表
     */
    @GetMapping("/list")
    @ResponseBody
    public AjaxResult list(GymResource resource) {
        List<GymResource> list = gymResourceService.selectResourceList(resource);
        return AjaxResult.success(list);
    }
}
