package cn.qiaok.boot.framework.demo;

import cn.qiaok.boot.framework.dict.core.DictDemo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.annotation.Resource;

@SpringBootApplication
public class Labs01SpringbootStarterDemoApplication {

    @Resource
    private DictDemo dictDemo;
    public static void main(String[] args) {
        SpringApplication.run(Labs01SpringbootStarterDemoApplication.class, args);
    }

}
