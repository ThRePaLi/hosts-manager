package me.chenjp.hostsmanager.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

@Controller
public class HostsManagerController {

    @RequestMapping("/hosts/config/list")
    public ModelAndView hostsConifgList() {
        try {
            Map<String, String> configMap = loadConfig();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new ModelAndView("manager");
    }

    private Map<String, String> loadConfig() throws FileNotFoundException {
        FileInputStream fis = new FileInputStream("");
        return null;
    }
}
