package com.farmigo.server.global.config.security;

import com.farmigo.server.domain.auth.security.LoginInterceptor;
import org.apache.logging.log4j.core.config.Order;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static com.farmigo.server.domain.auth.model.constant.AuthConstants.AUTHORIZATION;

@Configuration
@EnableWebMvc
@Order(Ordered.HIGHEST_PRECEDENCE)
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {

        corsRegistry.addMapping("/**")
                .allowedOrigins("http://localhost:3000","https://localhost:3000",
                        "http://localhost:80","https://localhost:80",
                        "http://3.35.154.169:80","https://3.35.154.169:80",
                        "http://api.farmigoapp.com:80","https://api.farmigoapp.com:80")
                .allowedMethods("*")
                .allowedHeaders("*")
                .maxAge(3600L)
                .exposedHeaders(AUTHORIZATION)
                .allowCredentials(true);

    }

    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry) {
        LoginInterceptor loginInterceptor = new LoginInterceptor();

        interceptorRegistry.addInterceptor(loginInterceptor)
                .addPathPatterns(loginInterceptor.loginEssential)
                .excludePathPatterns(loginInterceptor.loginInessential);
    }

}
