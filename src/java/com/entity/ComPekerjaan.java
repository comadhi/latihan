/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

import entity.GeneralModel;

/**
 *
 * @author adhi
 */
public class ComPekerjaan {
    
    private final ComPekerjaan comPekerjaan ;
    private Integer comPekerjaanId;
    private String comPekerjaanName;
    private String comPekerjaanDesc;
    private GeneralModel gmComPekerjaan;
    
    public ComPekerjaan(){
        comPekerjaan = this;
    }
    
    public Integer getComPekerjaanId(){
        return this.comPekerjaanId;
    }
    
    public void setComPekerjaanId(Integer pComPekerjaanId){
        this.comPekerjaanId = pComPekerjaanId;
    }
    
    public String getComPekerjaanName(){
        return this.comPekerjaanName;
    }
    
    public void setComPekerjaanName(String pComPekerjaanName){
        this.comPekerjaanName = pComPekerjaanName;
    }
    
    public String getComPekerjaanDesc(){
        return this.comPekerjaanDesc;
    }
    
    public void setComPekerjaanDesc(String pComPekerjaanDesc){
        this.comPekerjaanDesc = pComPekerjaanDesc;
    }
        
    public GeneralModel getGmComPekerjaan() {
        gmComPekerjaan = new GeneralModel(comPekerjaan.getComPekerjaanId(), comPekerjaan.getComPekerjaanName(), "ComPekerjaan", comPekerjaan);

        return gmComPekerjaan;
    }
    
    @Override
    public String toString() {
        return "ComPekerjaan[comPekerjaanId=" + comPekerjaanId + ",comPekerjaanName=" + comPekerjaanName + ",comPekerjaanDesc=" + comPekerjaanDesc + "]";
    }
}
