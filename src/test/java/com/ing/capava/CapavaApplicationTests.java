package com.ing.capava;

import com.ing.capava.controller.CapavaController;
import com.ing.capava.service.CapavaService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.util.StreamUtils;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.FileOutputStream;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CapavaApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CapavaController controller;

    @Test
    public void contextLoads()  {
        assertThat(controller).isNotNull();
    }

    @Test
    void downloadCSV() {
        String url = "http://localhost:"+port+"/capava/download";
        File file = restTemplate.execute(url, HttpMethod.GET, null, clientHttpResponse -> {
            File ret = File.createTempFile("output", "csv");
            StreamUtils.copy(clientHttpResponse.getBody(), new FileOutputStream(ret));
            return ret;
        });

        assertThat(file.exists());
        assertThat(file).isNotNull();
        assertThat(file.length()).isGreaterThan(0);

    }

}
