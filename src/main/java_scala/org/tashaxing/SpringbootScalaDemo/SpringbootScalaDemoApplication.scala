package org.tashaxing.SpringbootScalaDemo

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.autoconfigure.{EnableAutoConfiguration, SpringBootApplication}

@SpringBootApplication
class BootConfig

object SpringbootScalaDemoApplication extends App {

    SpringApplication.run(classOf[BootConfig])
}