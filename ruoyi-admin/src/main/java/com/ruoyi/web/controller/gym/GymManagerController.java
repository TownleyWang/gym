package com.ruoyi.web.controller.gym;

import com.ruoyi.system.domain.GymManager;
import com.ruoyi.system.service.IGymManagerService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    @ResponseBody
    public AjaxResult list() {
        // spring-boot-starter-web  spring boot
        List<GymManager> list = new ArrayList<>();

        GymManager gymManager = new GymManager();
        gymManager.setId(1L);
        gymManager.setName("哑铃");
        gymManager.setStatus("open");
        list.add(gymManager);  // gymManagerService.selectGymManagerList();

        return AjaxResult.success(list);
    }


}
