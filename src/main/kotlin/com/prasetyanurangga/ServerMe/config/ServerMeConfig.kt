package com.prasetyanurangga.ServerMe.config

import com.prasetyanurangga.ServerMe.interceptor.UserInterceptor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class ServerMeConfig : WebMvcConfigurer {

    @Autowired
    private lateinit var userInterceptor: UserInterceptor
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/api").allowedOrigins("http://localhost:9091")
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(userInterceptor!!).addPathPatterns("/api")
    }
}