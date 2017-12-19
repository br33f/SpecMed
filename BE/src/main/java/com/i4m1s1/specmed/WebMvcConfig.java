package com.i4m1s1.specmed;

import com.i4m1s1.specmed.core.AuthorizationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Najbardziej basic wersja configa dot. filtrów/interceptorów
 *
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthorizationInterceptor());
    }
}
