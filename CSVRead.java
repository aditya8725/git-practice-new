package com.kpit;
 
import java.io.IOException;
 
import java.io.FileReader;
import java.util.*;

import org.apache.commons.collections4.map.HashedMap;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
 
 
public class CSVRead
{
    private List<String[]> data;
 
    public CSVRead(String filepath) throws IOException,CsvException
    {
        try(CSVReader reader = new CSVReader(new FileReader(filepath)))
        {
            data = reader.readAll();
        }
    }
 
    // Method ro get number of rows and columns
    public void getRowsandColumnCount()
    {
        int rowCount = data.size();
        int colCount = data.get(0).length;
        System.out.println("Number of rows: "+rowCount);
        System.out.println("Number of Columns: "+colCount);
        System.out.println("================================");
    }
 
    //Method to count number of cars based on transmission type
    public void getCarCountByTransmission()
    {
        int manualCount = 0;
        int AutoCount = 0;
 
        for(String[] row : data)
        {
            String transmission = row[6];
            if("Manual".equalsIgnoreCase(transmission))
            {
                manualCount++;
            }
            else if ("Automatic".equalsIgnoreCase(transmission))
            {
                AutoCount++;
               
            }
        }
 
        System.out.println("Manual : "+manualCount);
        System.out.println("Automatic: "+AutoCount);
        System.out.println("================================");
    }
 
    // // Method to find average , min, and max selling price;
    public void getPiceStats()
    {
        
        int minPrice = Integer.MAX_VALUE;
        int maxPrice = 0;
        long totalPrice = 0;

        for(String[] row : data.subList(1, data.size()))
        {
            int price = Integer.parseInt(row[2]);
            totalPrice += price;

            if(price<minPrice)
            {
                minPrice = price;
            }
            if(price>maxPrice)
            {
                maxPrice = price;
            }
        }
        long avgPrice = totalPrice / (data.size()-1);
 
        System.out.println("Avg Price: "+avgPrice);
        System.out.println("Min Price: "+minPrice);
        System.out.println("Max Price: "+maxPrice);
        System.out.println("================================");

    }
 
 
    // // Method to show details of a car by model name
    public void getCarDetailsByModel(String modelName)
    {
        for(String[] row : data)
        {
            if(row[0].equalsIgnoreCase(modelName))
            {
                System.out.println("Car Details: "+Arrays.toString(row));
                return;
            }
        }
        System.out.println("Car model not found");
        System.out.println("================================");
    }
 
    // //Method to count vehicle by year
    public void getCountByYear()
    {
        Map<String, Integer> yearCount = new HashedMap<>();
 
        for(String[] row : data)
        {
            String year = row[1];
            yearCount.put(year,yearCount.getOrDefault(year,0)+1);
        }
       
        for(Map.Entry<String,Integer> entry : yearCount.entrySet())
        {
            System.out.println("Year: "+entry.getKey()+ " Count: "+entry.getValue());
        }

        System.out.println("================================");
    }
}