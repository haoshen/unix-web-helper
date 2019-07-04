package com.haoshen.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URLDecoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

@Service
public class UnixService implements InitializingBean {

    private Logger logger = LoggerFactory.getLogger(UnixService.class);

    // 项目根目录
    private String ROOT_PATH = "";

    // 前端输入$用来替代根路径
    private String ROOT_DELIM = "\\$";

    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            String cmd = "pwd";
            Process process = Runtime.getRuntime().exec(cmd);
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
            String line;
            if ((line = input.readLine()) != null) {
                ROOT_PATH = line;
            }
            logger.info("ROOT_PATH : " + ROOT_PATH);
            input.close();
            process.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String prepare(String str) {
        return str.replaceAll(ROOT_DELIM, ROOT_PATH);
    }

    public String executeCmd(String str) {
        StringBuffer sb = new StringBuffer();
        String cmd = "";
        int exitValue = -100;
        try {
            cmd = prepare(URLDecoder.decode(str, "UTF-8"));
            Process process = Runtime.getRuntime().exec(cmd);
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
            exitValue = process.waitFor();
            String line;
            while ((line = input.readLine()) != null) {
                sb.append(line).append("\n");
            }
            input.close();
            process.destroy();
        } catch (Exception e) {
            sb.append(e.getMessage()).append("\n");
        }
        sb.append("==> EXIT CODE : " + exitValue);
        return sb.toString();
    }

    public String loadFile(String path) {
        StringBuffer sb = new StringBuffer();
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(prepare(path))));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            br.close();
            return sb.toString();
        } catch (IOException e) {
            sb.append(e.getMessage());
        }
        return "load file failed: " + sb.toString();
    }

    public String updateFile(String path, String text) {
        try {
            File file = ResourceUtils.getFile(prepare(path));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            bw.write(text);
            bw.flush();
            bw.close();
            return "update file success";
        } catch (Exception e) {
            return e.getMessage();
        }
    }


}
