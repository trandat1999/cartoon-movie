package com.thd.cartoon.rest;

import com.thd.cartoon.dto.response.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DatNuclear 17/01/2024
 * @project cartoon-movie
 */
@RestController
@RequestMapping("/api/v1/publish")
public class PublicController {
    @GetMapping()
    public ResponseEntity<BaseResponse> get(){
        return ResponseEntity.ok(BaseResponse.builder().build());
    }
}
