package com.school_managemtent.controller;
import com.school_managemtent.dto.MarkDto;
import com.school_managemtent.dto.SaveResponseDto;
import com.school_managemtent.service.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mark")
public class MarkController {

    private final MarkService markService;

    @Autowired
    public MarkController(MarkService markService) {
        this.markService = markService;
    }

    @PostMapping
    public ResponseEntity<SaveResponseDto> create(@RequestBody MarkDto request) {
        var response = markService.create(request);
        return ResponseEntity.ok(response);
    }
}
