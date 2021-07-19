package com.ing.capava.service;

import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;


@Service
public interface CapavaService {
    public InputStreamResource generateStaticalSummary() ;
}
