package com.evans.project.database.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.evans.project.database.handler.DefaultMetaObjectHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @author DUHAOLIN
 * @date 2026/3/16
 */
@AutoConfiguration
@MapperScan("com.evans.project.*.mapper")
public class MybatisPlusConfig {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor()); // 分页插件
        // ↓↓↓ 按需开启，可能会影响到 updateBatch 的地方：例如说文件配置管理 ↓↓↓
//         mybatisPlusInterceptor.addInnerInterceptor(new BlockAttackInnerInterceptor()); // 拦截没有指定条件的 update 和 delete 语句
        return mybatisPlusInterceptor;
    }

    @Bean
    public MetaObjectHandler defaultMetaObjectHandler() {
        return new DefaultMetaObjectHandler(); // 自动填充参数类
    }

}
