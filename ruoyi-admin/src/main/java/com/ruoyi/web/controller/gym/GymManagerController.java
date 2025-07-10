package com.ruoyi.web.controller.gym;

import com.ruoyi.system.domain.GymManager;
import com.ruoyi.system.service.IGymManagerService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/gym/manager")
public class GymManagerController extends BaseController {

    @Autowired
    private IGymManagerService gymManagerService;


    @GetMapping()
    public String page() {
        return "gym/list";
    }


    @GetMapping("/list")
    public AjaxResult list() {
        List<GymManager> list = gymManagerService.selectGymManagerList();
        return AjaxResult.success(list);
    }


}
