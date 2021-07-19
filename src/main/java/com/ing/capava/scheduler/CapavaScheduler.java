package com.ing.capava.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


@Component
public class CapavaScheduler {
    final Logger logger = LoggerFactory.getLogger(CapavaScheduler.class);
    @Scheduled(cron = "0 0 4 * * *")
    public void downloadStaticalData() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/capava/download";
        ResponseEntity<Resource> response = restTemplate.getForEntity(url, Resource.class);
        try {
            File outputFile = new File(System.getProperty("user.home")+"/Downloads/output.csv");
            OutputStream out = new FileOutputStream(outputFile);
            StreamUtils.copy(response.getBody().getInputStream(), out);
            out.close();
        }catch (IOException e){
            logger.warn(e.getMessage());
        }

        logger.info("Down load success",response.getBody());
    }
}
