package com.sp.jasperdemo;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ReportController {

    @GetMapping("/report")
    public ResponseEntity<?> report() throws JRException, FileNotFoundException {

//        File initialFile = new File("C:\\template-jasper\\demo.jrxml");
        File initialFile = new File("C:\\template-jasper\\demo.jasper");

        InputStream demoReportStream = new FileInputStream(initialFile);
//        InputStream demoReportStream
//                = getClass().getResourceAsStream("C:\\template-jasper\\demo_2.jasper");
//        JasperReport jasperReport
//                = JasperCompileManager.compileReport(demoReportStream);


        Map<String, Object> parameters = new HashMap<>();
        parameters.put("EMPLOYEE_NAME", "Tosukoi Naja");
        parameters.put("EMPLOYEE_NAME_2", "Tosukoi Naja");
        parameters.put("EMPLOYEE_NAME_3", "Tosukoi Naja");

        JasperPrint jasperPrint
                = JasperFillManager.fillReport(demoReportStream,parameters);

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());

        File f = new File("C:\\output-pdf\\demo-"+timeStamp+".pdf");

        if (f != null) {
            try {
                JasperExportManager.exportReportToPdfFile(jasperPrint, f.getAbsolutePath());
            } catch (JRException e) {
                return new ResponseEntity<>(
                        "Got an Error: " + e.getLocalizedMessage(),
                        HttpStatus.BAD_REQUEST);
            }
        }
        return ResponseEntity.ok("Successfully exported report!");
    }
}
