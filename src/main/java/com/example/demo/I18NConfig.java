package com.example.demo;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

@Configuration
public class I18NConfig implements WebMvcConfigurer {
    //nạp file messages.properties vào hệ thống
    @Bean("messageSource")
    public MessageSource getMessageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setDefaultEncoding("utf-8");
        messageSource.setBasenames("classpath:i18n/global");
        return messageSource;
    }

    @Bean("localeResolver")
    public LocaleResolver getLocaleResolver() {
        CookieLocaleResolver cookie = new CookieLocaleResolver();
        cookie.setDefaultLocale(new Locale("vi"));
        cookie.setCookiePath("/");
        cookie.setCookieMaxAge(10 * 24 * 60 * 60);
        return cookie;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        registry.addInterceptor(localeChangeInterceptor)
                .addPathPatterns("/**") //trắng tất cả
                .excludePathPatterns("/img/**"); //không trắng thư mục img


    }
}
