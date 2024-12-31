package com.sp.jasperdemo;

import com.sp.jasperdemo.model.Food;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ReportController {

    @GetMapping("/report")
    public ResponseEntity<?> report() throws JRException, FileNotFoundException {
        File initialFile = new File("C:\\template-jasper\\demo.jasper");

        InputStream demoReportStream = new FileInputStream(initialFile);


        Map<String, Object> parameters = new HashMap<>();
        parameters.put("EMPLOYEE_NAME", "Tosukoi Naja");

        JasperPrint jasperPrint
                = JasperFillManager.fillReport(demoReportStream, parameters, new JREmptyDataSource());

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());

        File f = new File("C:\\output-pdf\\demo-" + timeStamp + ".pdf");

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

    @GetMapping("/sub-report")
    public ResponseEntity<?> subReport() throws JRException, FileNotFoundException {

        File initialFile = new File("C:\\template-jasper\\main.jasper");

        InputStream demoReportStream = new FileInputStream(initialFile);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("foodReport", getSubReport());
        parameters.put("foodReportParam", getFoodParameters());

        JasperPrint jasperPrint
                = JasperFillManager.fillReport(demoReportStream, parameters, new JREmptyDataSource());

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());

        File f = new File("C:\\output-pdf\\demo-nutrition-" + timeStamp + ".pdf");

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

    private JasperReport getSubReport() throws JRException, FileNotFoundException {
        File foodnutritionFile = new File("C:\\template-jasper\\foodnutrition.jasper");

        InputStream foodnutritionStream = new FileInputStream(foodnutritionFile);
        JasperReport subReport = (JasperReport) JRLoader.loadObject(foodnutritionStream);
        return subReport;
    }

    private Map getFoodParameters() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("foodDataset", getDataSource());
        return parameters;
    }

    private JRBeanCollectionDataSource getDataSource() {
        List<Food> foods = new ArrayList<>();
        Food food1 = new Food("food1", "breakfast", 1, 1, 1);
        Food food2 = new Food("food2", "breakfast", 1, 1, 1);
        Food food3 = new Food("food3", "breakfast", 1, 1, 1);
        Food food4 = new Food("food4", "breakfast", 1, 1, 1);
        Food food5 = new Food("food5", "breakfast", 1, 1, 1);
        Food food6 = new Food("food6", "breakfast", 1, 1, 1);
        Food food7 = new Food("food7", "breakfast", 1, 1, 1);
        Food food8 = new Food("food8", "breakfast", 1, 1, 1);
        Food food9 = new Food("food9", "breakfast", 1, 1, 1);
        Food food10 = new Food("food10", "breakfast", 1, 1, 1);
        Food food11 = new Food("food11", "breakfast", 1, 1, 1);
        Food food12 = new Food("food12", "breakfast", 1, 1, 1);
        Food food13 = new Food("food13", "breakfast", 1, 1, 1);
        Food food14 = new Food("food14", "breakfast", 1, 1, 1);
        Food food15 = new Food("food15", "breakfast", 1, 1, 1);
        Food food16 = new Food("food16", "breakfast", 1, 1, 1);
        Food food17 = new Food("food17", "breakfast", 1, 1, 1);
        Food food18 = new Food("food18", "breakfast", 1, 1, 1);
        Food food19 = new Food("food19", "breakfast", 1, 1, 1);
        Food food20 = new Food("food20", "breakfast", 1, 1, 1);


        foods.add(food1);
        foods.add(food2);
        foods.add(food3);
        foods.add(food4);
        foods.add(food5);
        foods.add(food6);
        foods.add(food7);
        foods.add(food8);
        foods.add(food9);
        foods.add(food10);
        foods.add(food11);
        foods.add(food12);
        foods.add(food13);
        foods.add(food14);
        foods.add(food15);
        foods.add(food16);
        foods.add(food17);
        foods.add(food18);
        foods.add(food19);
        foods.add(food20);

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(foods);
        return dataSource;
    }

}
