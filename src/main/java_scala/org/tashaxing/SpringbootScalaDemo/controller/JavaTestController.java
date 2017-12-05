package org.tashaxing.SpringbootScalaDemo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tashaxing.SpringbootScalaDemo.model.ScalaModel;

@RestController
@RequestMapping("/javatest")
public class JavaTestController {

    @RequestMapping
    public String root() {
        return "java test results";
    }

    @GetMapping("/info")
    public Map<String, String> getInfo(@RequestParam String name) {
        // java map can be returned without extra code
        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        return map;
    }

    @RequestMapping("/list")
    public List<Map<String, String>> getList() {
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map = null;
        for (int i = 1; i <= 5; i++) {
            map = new HashMap<>();
            map.put("name", "hello-" + i);
            list.add(map);
        }
        return list;
    }

    // test model serialize
    @GetMapping("/model")
    public ScalaModel getModel() {
        ScalaModel scalaModel = new ScalaModel();
        scalaModel.setId(10L);
        scalaModel.setName("Ethan");
        scalaModel.setAge(23);

        return scalaModel;
    }
}
