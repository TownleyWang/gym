package com.ruoyi.web.controller.gym;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.GymResource;
import com.ruoyi.system.service.IGymReservationService;
import com.ruoyi.system.service.IGymResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Date;

import static com.ruoyi.common.utils.LogUtils.getUsername;

import com.ruoyi.system.domain.GymTimeSlot;
import com.ruoyi.system.service.IGymTimeSlotService;
import org.springframework.ui.ModelMap;


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

    @PostMapping("/updateByUser/{id}")
    @ResponseBody
    public AjaxResult updateByUser(@PathVariable Long id) {
        String username = getUsername(); // 获取当前登录用户名（若依内置方法）
        GymResource resource = gymResourceService.selectGymResourceById(id);
        if (resource == null) {
            return AjaxResult.error("资源不存在");
        }
        resource.setUpdateBy(username);
        resource.setUpdateTime(new Date());
        return toAjax(gymResourceService.updateGymResource(resource));
    }

    @Autowired
    private IGymTimeSlotService gymTimeSlotService;

    /**
     * 根据资源 ID 展示对应时间段列表
     */
    @GetMapping("/slot/{resourceId}")
    public String viewSlots(@PathVariable("resourceId") Long resourceId, ModelMap mmap) {
        List<GymTimeSlot> slotList = gymTimeSlotService.selectSlotsByResourceId(resourceId);
        mmap.put("slotList", slotList);
        return "gym/slot";
    }

    @PostMapping("/slot/book/{id}")
    @ResponseBody
    public AjaxResult bookSlot(@PathVariable("id") Long slotId) {
        int result = gymTimeSlotService.bookSlot(slotId, getUsername());
        return result > 0 ? AjaxResult.success() : AjaxResult.error("预约失败，可能已满或参数错误");
    }

    @PostMapping("/slot/cancel/{id}")
    @ResponseBody
    public AjaxResult cancelSlot(@PathVariable("id") Long slotId) {
        int result = gymTimeSlotService.cancelSlot(slotId, getUsername());
        return result > 0 ? AjaxResult.success() : AjaxResult.error("取消失败，可能未预约或参数错误");
    }

    @Autowired
    private IGymReservationService gymReservationService;

    @PostMapping("/book/{slotId}")
    @ResponseBody
    public AjaxResult book(@PathVariable("slotId") Long slotId) {
        return gymReservationService.reserve(slotId);
    }

    @PostMapping("/cancel/{slotId}")
    @ResponseBody
    public AjaxResult cancel(@PathVariable("slotId") Long slotId) {
        return gymReservationService.cancel(slotId);
    }

}