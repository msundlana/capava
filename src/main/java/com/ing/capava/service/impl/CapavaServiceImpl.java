package com.ing.capava.service.impl;

import com.ing.capava.helper.CSVHelper;
import com.ing.capava.model.Asset;
import com.ing.capava.model.Incident;
import com.ing.capava.service.CapavaService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.apache.commons.csv.CSVRecord;

@Service
public class CapavaServiceImpl implements CapavaService {

    public InputStreamResource generateStaticalSummary()  {
        CSVHelper helper = new CSVHelper();

        Map<String, Asset> assetMap = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        long secondsInADay = 24 * 60 * 60;
        for (CSVRecord csvRecord : helper.loadCSV()) {
            Incident incident = new Incident(
                    csvRecord.get(0),
                    LocalDateTime.parse(csvRecord.get(1),formatter),
                    LocalDateTime.parse(csvRecord.get(2),formatter),
                    Integer.parseInt(csvRecord.get(3))
            );
            getCalculatedAssetPerDayMap(assetMap,incident,secondsInADay);
        }
        return new InputStreamResource(helper.downloadCSV(assetMap));
    }

    private Map<String,Asset> getCalculatedAssetPerDayMap(Map<String, Asset> assetMap,Incident incident, long secondsInADay){

        long downTime = ((incident.getEndTime().toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli()
                - incident.getStartTime().toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli()));
        int weight = incident.getSeverity()==1?30:10;
        String key = incident.getAssetName()+" "+incident.getStartTime().toLocalDate();
        if(assetMap.containsKey(key)){
            int totalIncidents = assetMap.get(key).getTotalIncidents() + 1;
            int rating = assetMap.get(key).getRating()+weight;
            long upTime = assetMap.get(key).getUpTime() - TimeUnit.MILLISECONDS.toSeconds(downTime);
            assetMap.replace(key,
                    new Asset(incident.getAssetName(),totalIncidents,upTime,rating));
        }else {
            long upTime = secondsInADay - TimeUnit.MILLISECONDS.toSeconds(downTime);
            assetMap.put(key,
                    new Asset(incident.getAssetName(),1,upTime,
                            weight) );
        }
        return assetMap;
    }
}
