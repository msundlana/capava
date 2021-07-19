package com.ing.capava.helper;

import com.ing.capava.model.Asset;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CSVHelper {

    public Iterable<CSVRecord> loadCSV() {
        InputStream input = this.getClass().getClassLoader().getResourceAsStream("data/ING CapAva Test - Data Ingestion - input.csv");
        try {
            return CSVFormat.newFormat(';').withFirstRecordAsHeader()
                    .withIgnoreHeaderCase().withTrim()
                    .parse(new InputStreamReader(input, "UTF-8"));

        }catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    public ByteArrayInputStream downloadCSV(Map<String,Asset> staticalSummary){
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
            String[] csvHeader = {"Asset Name", "Total Incident", "Total Downtime", "Rating"};
            csvPrinter.printRecord(Arrays.asList(csvHeader));
            for (Map.Entry<String, Asset> entrySet : staticalSummary.entrySet()) {
                List<String> data = Arrays.asList(
                        entrySet.getValue().getName(),
                        String.valueOf(entrySet.getValue().getTotalIncidents()),
                        String.valueOf(entrySet.getValue().getUpTime()),
                        String.valueOf(entrySet.getValue().getRating())
                );
                csvPrinter.printRecord(data);
            }
            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
    }
}
