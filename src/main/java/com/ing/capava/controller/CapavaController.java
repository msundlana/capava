package com.ing.capava.controller;


import com.ing.capava.service.CapavaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class CapavaController {

    @Autowired
    private CapavaService capavaService;

    @GetMapping("/download")
    public ResponseEntity<Resource> downloadCSV()   {
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateTime = dateFormatter.format(new Date());

        String headerValue = "attachment; filename=output_" + currentDateTime + ".csv";

        InputStreamResource file = capavaService.generateStaticalSummary();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);

    }
}
