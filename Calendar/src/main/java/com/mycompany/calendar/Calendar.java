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
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
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
                LocalDateTime dateStart = null;
                LocalDateTime dateEnd = null;
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
                           dateStart = tmp.atStartOfDay();
                         }
                         else{
                           dateStart = LocalDateTime.parse(tempDate, DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss"));
                         }
                     }
                     if(currentLine.contains("DTEND")){
                         String[] parts = currentLine.split(":");
                         String tempDate = parts[1].trim();
                         if(!tempDate.contains("T")){
                           LocalDate tmp = LocalDate.parse(tempDate, DateTimeFormatter.BASIC_ISO_DATE);
                           dateEnd = tmp.atStartOfDay();
                         }
                         else{
                            LocalDateTime tmp = LocalDateTime.parse(tempDate, DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss"));
                           dateEnd = LocalDateTime.parse(tempDate, DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss"));
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
                        String date = apoint.getDateStart().toString();
                        /*
                       if(!temp.contains("T")){ //an den exei xrono
                           String[] parts = currentDateTime.split("T"); //xwrizoume ton twra xrono gia na exei mono date
                         String tempDate = parts[0].trim();

                         if(tempDate.equals(apoint.getDateStart())){ //an einai idia ta emfanizw
                             System.out.println(apoint.getSummary());
                         }
                       }*/
                       //else{ //an exei xrono
                         String[] parts = currentDateTime.split("/."); //bgazw ta nano second apo to current DateTIme
                         String tempDateAndTime = parts[0].trim();
                         
                         String[] parts2 = tempDateAndTime.split("T"); //xwrizw thn hmerominia k ton xrono se 2 diaforetikes metablites
                         String justCurDate = parts2[0].trim();
                         String justCurTime = parts2[1].trim();
                         
                         String[] partsApoint = date.split("T"); //xwrizw k to apoint se idia meri
                         
                         String apointDate = partsApoint[0].trim();
                         String apointTime = partsApoint[1].trim();
                         if(justCurDate.equals(apointDate)){ //blepw an gia arxh h mera einai h idia
                             if(justCurTime.compareTo(apointTime) < 0){ //elegxo twra thn wra
                                 System.out.println(apoint.getSummary());
                             }     
                         }
                      // }
                        
                }
   
                
                }
                
                if(args[0].equals("week")){ //gia week
                     LocalDateTime dateTime = LocalDateTime.parse(currentDateTime);
                     LocalDateTime endOfWeek = dateTime.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
                     for(Apointment apoint : apointments){
                         String date = apoint.getDateStart().toString();
                         String[] parts = currentDateTime.split("/."); //bgazw ta nano second apo to current DateTIme
                         String tempDateAndTime = parts[0].trim();
                         
                         String[] parts2 = tempDateAndTime.split("T"); //xwrizw thn hmerominia k ton xrono se 2 diaforetikes metablites
                         String justCurDate = parts2[0].trim();
                         String justCurTime = parts2[1].trim();
                         
                         
                         String[] partsEOW = endOfWeek.toString().split("/.");
                         String EOWDateAndTime = partsEOW[0].trim();
                         
                         String[] partsEOW2 = EOWDateAndTime.split("T"); //xwrizw thn hmerominia k ton xrono se 2 diaforetikes metablites
                         String justEOWDate = partsEOW2[0].trim();
                         String justEOWTime = partsEOW2[1].trim();
                         
                         
                          String[] partsApoint = date.split("T"); //xwrizw k to apoint se idia meri
                         
                         String apointDate = partsApoint[0].trim();
                         String apointTime = partsApoint[1].trim();
                                                  
                         if((apointDate.compareTo(justCurDate) > 0 || apointDate.equals(justCurDate)) && (apointDate.compareTo(justEOWDate) < 0 || apointDate.equals(justEOWDate))){
                             if(justCurTime.compareTo(apointTime) > 0 && apointDate.equals(justCurDate)){ //to compare weekTime den to bazoume epeidh upothetoume oti einai oloi h mera
                                 
                             }
                             else{
                                 System.out.println(apoint.getSummary());
                             }
                             
                         }
                     }

                 }
                
                if(args[0].equals("month")){ //gia month
                    LocalDateTime dateTime = LocalDateTime.parse(currentDateTime);
                    LocalDateTime endOfMonth = dateTime.with(TemporalAdjusters.lastDayOfMonth());
                    for(Apointment apoint : apointments){
                         String date = apoint.getDateStart().toString();
                         String[] parts = currentDateTime.split("/."); //bgazw ta nano second apo to current DateTIme
                         String tempDateAndTime = parts[0].trim();
                         
                         String[] parts2 = tempDateAndTime.split("T"); //xwrizw thn hmerominia k ton xrono se 2 diaforetikes metablites
                         String justCurDate = parts2[0].trim();
                         String justCurTime = parts2[1].trim();
                         
                         
                         String[] partsEOM = endOfMonth.toString().split("/.");
                         String EOMDateAndTime = partsEOM[0].trim();
                         
                         String[] partsEOM2 = EOMDateAndTime.split("T"); //xwrizw thn hmerominia k ton xrono se 2 diaforetikes metablites
                         String justEOMDate = partsEOM2[0].trim();
                         String justEOMTime = partsEOM2[1].trim();
                         
                         
                          String[] partsApoint = date.split("T"); //xwrizw k to apoint se idia meri
                         
                         String apointDate = partsApoint[0].trim();
                         String apointTime = partsApoint[1].trim();
                                                  
                         if((apointDate.compareTo(justCurDate) > 0 || apointDate.equals(justCurDate)) && (apointDate.compareTo(justEOMDate) < 0 || apointDate.equals(justEOMDate))){
                               if(justCurTime.compareTo(apointTime) > 0 && apointDate.equals(justCurDate)){ //to compare weekTime den to bazoume epeidh upothetoume oti einai oloi h mera
                                 
                             }
                             else{
                                 System.out.println(apoint.getSummary());
                             }
                             
                         }
                     }

                    
                }
                
                if(args[0].equals("pastday")){ //idio me day allazei to < se > mono
                     for(Apointment apoint : apointments){ //elegxoume ola ta apointments
                        String date = apoint.getDateStart().toString();
                        /*
                       if(!temp.contains("T")){ //an den exei xrono
                           String[] parts = currentDateTime.split("T"); //xwrizoume ton twra xrono gia na exei mono date
                         String tempDate = parts[0].trim();

                         if(tempDate.equals(apoint.getDateStart())){ //an einai idia ta emfanizw
                             System.out.println(apoint.getSummary());
                         }
                       }*/
                       //else{ //an exei xrono
                         String[] parts = currentDateTime.split("/."); //bgazw ta nano second apo to current DateTIme
                         String tempDateAndTime = parts[0].trim();
                         
                         String[] parts2 = tempDateAndTime.split("T"); //xwrizw thn hmerominia k ton xrono se 2 diaforetikes metablites
                         String justCurDate = parts2[0].trim();
                         String justCurTime = parts2[1].trim();
                         
                         String[] partsApoint = date.split("T"); //xwrizw k to apoint se idia meri
                         
                         String apointDate = partsApoint[0].trim();
                         String apointTime = partsApoint[1].trim();
                         if(justCurDate.equals(apointDate)){ //blepw an gia arxh h mera einai h idia
                             if(justCurTime.compareTo(apointTime) > 0){ //elegxo twra thn wra
                                 System.out.println(apoint.getSummary());
                             }     
                         }
                      // }
                        
                }
                }
                
                if(args[0].equals("pastweek")){
                    LocalDateTime dateTime = LocalDateTime.parse(currentDateTime);
                    LocalDateTime beginningOfWeek = dateTime.with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY));
                     for(Apointment apoint : apointments){
                         String date = apoint.getDateStart().toString();
                         String[] parts = currentDateTime.split("/."); //bgazw ta nano second apo to current DateTIme
                         String tempDateAndTime = parts[0].trim();
                         
                         String[] parts2 = tempDateAndTime.split("T"); //xwrizw thn hmerominia k ton xrono se 2 diaforetikes metablites
                         String justCurDate = parts2[0].trim();
                         String justCurTime = parts2[1].trim();
                         
                         
                         String[] partsBOW = beginningOfWeek.toString().split("/.");
                         String BOWDateAndTime = partsBOW[0].trim();
                         
                         String[] partsBOW2 = BOWDateAndTime.split("T"); //xwrizw thn hmerominia k ton xrono se 2 diaforetikes metablites
                         String justBOWDate = partsBOW2[0].trim();
                         String justBOWTime = partsBOW2[1].trim();
                         
                         
                          String[] partsApoint = date.split("T"); //xwrizw k to apoint se idia meri
                         
                         String apointDate = partsApoint[0].trim();
                         String apointTime = partsApoint[1].trim();
                                                  
                         if((apointDate.compareTo(justCurDate) < 0 || apointDate.equals(justCurDate)) && (apointDate.compareTo(justBOWDate) > 0 || apointDate.equals(justBOWDate))){
                             if(justCurTime.compareTo(apointTime) < 0 && apointDate.equals(justCurDate)){ //to compare weekTime den to bazoume epeidh upothetoume oti einai oloi h mera
                                 
                             }else{
                                 System.out.println(apoint.getSummary());
                             }
                             
                         }
                     }
                }
                
                if(args[0].equals("pastmonth")){
                    LocalDateTime dateTime = LocalDateTime.parse(currentDateTime);
                    LocalDateTime beginningOfMonth = dateTime.withDayOfMonth(1);
                    for(Apointment apoint : apointments){
                         String date = apoint.getDateStart().toString();
                         String[] parts = currentDateTime.split("/."); //bgazw ta nano second apo to current DateTIme
                         String tempDateAndTime = parts[0].trim();
                         
                         String[] parts2 = tempDateAndTime.split("T"); //xwrizw thn hmerominia k ton xrono se 2 diaforetikes metablites
                         String justCurDate = parts2[0].trim();
                         String justCurTime = parts2[1].trim();
                         
                         
                         String[] partsBOM = beginningOfMonth.toString().split("/.");
                         String BOMDateAndTime = partsBOM[0].trim();
                         
                         String[] partsBOM2 = BOMDateAndTime.split("T"); //xwrizw thn hmerominia k ton xrono se 2 diaforetikes metablites
                         String justBOMDate = partsBOM2[0].trim();
                         String justBOMTime = partsBOM2[1].trim();
                         
                         
                          String[] partsApoint = date.split("T"); //xwrizw k to apoint se idia meri
                         
                         String apointDate = partsApoint[0].trim();
                         String apointTime = partsApoint[1].trim();
                                                  
                         if((apointDate.compareTo(justCurDate) < 0 || apointDate.equals(justCurDate)) && (apointDate.compareTo(justBOMDate) > 0 || apointDate.equals(justBOMDate))){
                             if(justCurTime.compareTo(apointTime) < 0 && apointDate.equals(justCurDate)){ //to compare weekTime den to bazoume epeidh upothetoume oti einai oloi h mera
                                 
                             }else{
                                 System.out.println(apoint.getSummary());
                             }
                             
                         }
                     }
                    
                }
            }//lathos plirofories magka m
            else{
                System.out.println("Wrong");
            }
        
        }else if(args.length == 1 ){
            //function to add will do last
        }
        else{
            System.out.println("Not accepted try again");
            System.exit(1);     
        }
        
        
    }
}
