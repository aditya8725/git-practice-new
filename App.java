package com.kpit;
 
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
 
import com.opencsv.exceptions.CsvException;
 
public class App
{
    public static void main( String[] args ) throws IOException, CsvException
    {
        CSVRead readutil = new CSVRead("C:\\Users\\adityap16\\Desktop\\Genisis Pro\\Day 2\\task2\\src\\main\\java\\com\\kpit\\CAR_DETAILS_DATA.csv");
 
        ExecutorService es = Executors.newFixedThreadPool(5);
 
        es.execute(readutil::getRowsandColumnCount);
        es.execute(readutil::getCarCountByTransmission);
        es.execute(readutil::getPiceStats);
        es.execute(() -> readutil.getCarDetailsByModel("name"));
        es.execute(readutil::getCountByYear);
 
        es.shutdown();
 
    }
}