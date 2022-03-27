package com.qiaok.easypoilabs.controller;

import com.qiaok.easypoilabs.dto.PersonDTO;
import com.qiaok.easypoilabs.utils.Constant;
import com.qiaok.easypoilabs.utils.ExcelUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author qiaok
 * @version 1.0
 * @description: TODO
 * @date 2022/3/27 9:30
 */
@RestController
@RequestMapping("excel")
public class PersonController {

    /**
     * 导出
     *
     * @param response
     */
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void exportExcel(HttpServletResponse response) throws IOException {
        long start = System.currentTimeMillis();
        List<PersonDTO> personList = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            PersonDTO personVo = new PersonDTO();
            personVo.setName("张三" + i);
            personVo.setUsername("张三" + i);
            personVo.setPhoneNumber("18888888888");
            personVo.setImageUrl(Constant.DEFAULT_IMAGE);
            personList.add(personVo);
        }
        ExcelUtils.exportExcel(personList, "员工信息表", "员工信息", PersonDTO.class, "员工信息", response);
    }

    /**
     * 导入
     *
     * @param file
     */
    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public Object  importExcel(@RequestParam("file") MultipartFile file) throws IOException {
        return ExcelUtils.importExcel(file, PersonDTO.class);
    }

}
