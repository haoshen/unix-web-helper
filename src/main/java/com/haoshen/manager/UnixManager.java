package com.haoshen.manager;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.haoshen.service.UnixService;
import com.haoshen.utils.SecurityUtil;

@Service
public class UnixManager {

    @Resource
    UnixService unixService;
    
    private String PASSWORD_NOT_CORRECT = "password is not correct";

    public String executeCmd(String password, String cmd) {
    	if (!check(password)) {
    		return PASSWORD_NOT_CORRECT;
    	}
        return unixService.executeCmd(cmd);
    }

    public String loadFile(String password, String path) {
    	if (!check(password)) {
    		return PASSWORD_NOT_CORRECT;
    	}
        return unixService.loadFile(path);
    }

    public String updateFile(String password, String path, String text) {
    	if (!check(password)) {
    		return PASSWORD_NOT_CORRECT;
    	}
        return unixService.updateFile(path, text);
    }
    
    public boolean check(String password) {
    	return SecurityUtil.check(password);
    }

}
