package com.thd.cartoon.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DatNuclear 18/01/2024
 * @project cartoon-movie
 */
@RestController
@RequestMapping("/api/v1/test")
public class TestController {
    @GetMapping()
    public String getTestName() {
        return "test";
    }
}
