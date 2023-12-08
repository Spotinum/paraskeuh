/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.calendar;
import gr.hua.dit.oop2.calendar.TimeService;
import gr.hua.dit.oop2.calendar.TimeTeller;
import gr.hua.dit.oop2.calendar.TimeListener;
import gr.hua.dit.oop2.calendar.TimeEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author giannis
 */
public class Calendar {

    public static void main(String[] args) throws FileNotFoundException {
    
        if(args.length == 2){ //first function
            if((args[0].equals("day") || args[0].equals("week") || args[0].equals("month") || args[0].equals("todo") || "pastweek".equals(args[0]) || "pastmonth".equals(args[0]) || "due".equals(args[0]) || args[0].equals("pastday")) && args[1].contains(".ics")){
                List<Apointment> apointments = new ArrayList<>();
                
                File file = new File(args[1]);
                Scanner sc = new Scanner(file);
                String currentLine ="";
                String desc = "";
                String dateStart = "";
                String dateEnd = "";
                String sum = "";
                Boolean inEvent = false;
                Boolean inTask = false;
                
                while(sc.hasNextLine()){
                     currentLine = sc.nextLine();
                     if(currentLine.contains("BEGIN:VEVENT")){
                         inEvent = true;
                         inTask = false;
                     }
                     if(currentLine.contains("END:VEVENT")){
                         inEvent = false;
                         Apointment apoint = new Apointment(dateStart,dateEnd,desc,sum);
                         apointments.add(apoint);
                     }
                     if(currentLine.contains("DESCRIPTION")){
                         String[] parts = currentLine.split(":");
                         desc = parts[1].trim(); 
                     }
                     if(currentLine.contains("DTSTART")){
                         String[] parts = currentLine.split(":");
                         String tempDate = parts[1].trim();
                         if(!tempDate.contains("T")){
                            LocalDate tmp = LocalDate.parse(tempDate, DateTimeFormatter.BASIC_ISO_DATE);
                            dateStart = tmp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                         }
                         else{
                            LocalDateTime tmp = LocalDateTime.parse(tempDate, DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss"));
                            dateStart = tmp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
                         }
                     }
                     if(currentLine.contains("DTEND")){
                         String[] parts = currentLine.split(":");
                         String tempDate = parts[1].trim();
                         if(!tempDate.contains("T")){
                            LocalDate tmp = LocalDate.parse(tempDate, DateTimeFormatter.BASIC_ISO_DATE);
                            dateEnd = tmp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                         }
                         else{
                            LocalDateTime tmp = LocalDateTime.parse(tempDate, DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss"));
                            dateEnd = tmp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
                         }
                     }
                     if(currentLine.contains("SUMMARY")){
                         String[] parts = currentLine.split(":");
                         sum = parts[parts.length - 1].trim(); 
                     }
                }
                
                
                String currentDateTime = LocalDateTime.now().toString();
                
                //elegos gia prwto orisma twra
                if(args[0].equals("day")){ // an einai day
                    for(Apointment apoint : apointments){ //elegxoume ola ta apointments
                       if(!apoint.getDateStart().contains("T")){ //an den exei xrono
                           String[] parts = currentDateTime.split("T"); //xwrizoume ton twra xrono gia na exei mono date
                         String tempDate = parts[0].trim();

                         if(tempDate.equals(apoint.getDateStart())){ //an einai idia ta emfanizw
                             System.out.println(apoint.getSummary());
                         }
                       }
                       else{ //an exei xrono
                           String[] parts = currentDateTime.split("/."); //bgazw ta nano second apo to current DateTIme
                         String tempDateAndTime = parts[0].trim();
                         
                         String[] parts2 = tempDateAndTime.split("T"); //xwrizw thn hmerominia k ton xrono se 2 diaforetikes metablites
                         String justCurDate = parts2[0].trim();
                         String justCurTime = parts2[1].trim();
                         
                         String[] partsApoint = apoint.getDateStart().split("T"); //xwrizw k to apoint se idia meri
                         
                         String apointDate = partsApoint[0].trim();
                         String apointTime = partsApoint[1].trim();
                         if(justCurDate.equals(apointDate)){ //blepw an gia arxh h mera einai h idia
                             if(justCurTime.compareTo(apointTime) < 0){ //elegxo twra thn wra
                                 System.out.println(apoint.getSummary());
                             }     
                         }
                       }
                        
                }
                
                
                
                
                
                
                
            }//lathos plirofories magka m
            else{
                System.out.println("Wrong");
            }
        }
        else if(args.length == 1 ){
            //function to add will do last
        }
        else{
            System.out.println("Not accepted try again");
            System.exit(1);     
        }
        }
        
    }
}
