package com.majuba.majuba.controllers;


import com.majuba.majuba.entities.Waiter;
import com.majuba.majuba.services.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class TableController {


    @Autowired
    private TableService tService;

    @PostMapping("/assignWaiter/{table_id}")
    @ResponseBody
    public RedirectView assignWaiter(@PathVariable Long table_id, @RequestParam(required = false, name = "setwaiters") List<Waiter> waiters) {
        tService.assignWaiters(table_id, waiters);
        return new RedirectView("/system");
    }

}
