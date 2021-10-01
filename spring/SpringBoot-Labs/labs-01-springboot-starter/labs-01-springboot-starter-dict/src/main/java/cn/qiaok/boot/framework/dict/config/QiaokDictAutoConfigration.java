package cn.qiaok.boot.framework.dict.config;

import cn.qiaok.boot.framework.dict.core.DictDemo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QiaokDictAutoConfigration {

    @Bean
    public DictDemo dictDemo(){
        System.out.println("=====================[张三]==============================");
        return new DictDemo(1,"张三");
    }

}
