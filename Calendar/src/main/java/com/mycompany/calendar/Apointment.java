/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.calendar;

import java.time.LocalDateTime;

/**
 *
 * @author giannis
 */
public class Apointment {
    private LocalDateTime dateStart;
    private LocalDateTime dateEnd;
    private String desc;
    private String summary;

    public LocalDateTime getDateStart() {
        return dateStart;
    }

    public LocalDateTime getDateEnd() {
        return dateEnd;
    }

   

    public String getDesc() {
        return desc;
    }

    public String getSummary() {
        return summary;
    }

    public void setDateStart(LocalDateTime dateStart) {
        this.dateStart = dateStart;
    }

    public void setDateEnd(LocalDateTime dateEnd) {
        this.dateEnd = dateEnd;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Apointment(LocalDateTime dateStart, LocalDateTime dateEnd, String desc, String summary) {
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.desc = desc;
        this.summary = summary;
    }
    
    
    
}
