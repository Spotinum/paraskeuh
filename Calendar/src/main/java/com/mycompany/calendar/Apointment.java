/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.calendar;

/**
 *
 * @author giannis
 */
public class Apointment {
    private String dateStart;
    private String dateEnd;
    private String desc;
    private String summary;

    public String getDateStart() {
        return dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public String getDesc() {
        return desc;
    }

    public String getSummary() {
        return summary;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Apointment(String dateStart, String dateEnd, String desc, String summary) {
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.desc = desc;
        this.summary = summary;
    }
    
    
    
}
