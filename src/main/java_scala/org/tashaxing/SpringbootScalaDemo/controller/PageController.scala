package org.tashaxing.SpringbootScalaDemo.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class PageController
{
    // root page
    @RequestMapping(Array("/"))
    def index(): String = "redirect:/index.html"

    // an other page
    // use forward instead of redirect will not show xxx.html in browser address bar
    @RequestMapping(Array("/subpage"))
    def sub(): String = "forward:/subpage/newpage.html"

    // no need to redirect apitest
//    @RequestMapping(Array("/apitest"))
//    def apitest(): String = "redirect:/apitest.html"
}