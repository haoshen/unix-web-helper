package com.haoshen.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.haoshen.manager.UnixManager;

@CrossOrigin
@RestController
@RequestMapping("/unix")
public class UnixController {

    @Resource
    UnixManager unixManager;

    @RequestMapping(value="/check")
    public boolean check(@RequestParam String password) {
        return unixManager.check(password);
    }
    
    @RequestMapping(value="/test")
    public String test(@RequestParam String password, @RequestParam String cmd) {
        return unixManager.executeCmd(password, cmd);
    }

    @RequestMapping(value="/load")
    public String loadFile(@RequestParam String password, @RequestParam String path) {
        return unixManager.loadFile(password, path);
    }

    @RequestMapping(value="/update")
    public String updateFile(@RequestParam String password,
    						 @RequestParam String path,
                             @RequestParam String text) {
        return unixManager.updateFile(password, path, text);
    }


}
