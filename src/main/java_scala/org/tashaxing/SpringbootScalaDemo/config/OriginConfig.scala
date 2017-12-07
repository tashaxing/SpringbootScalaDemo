package org.tashaxing.SpringbootScalaDemo.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.{CorsRegistry, WebMvcConfigurerAdapter}

@Configuration
class OriginConfig extends WebMvcConfigurerAdapter
{
    override def addCorsMappings(registry: CorsRegistry): Unit =
    {
        // cors setting to allow origin access
        registry.addMapping("/**")                      // allow any path
            .allowedOrigins("http://192.168.1.97")      // allow exact ip /** to map any ip
            .allowedMethods("GET", "POST")              // allow methods
            .allowedHeaders("*")                        // allow any header
    }

}