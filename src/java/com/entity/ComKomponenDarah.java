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
public class ComKomponenDarah {
   
    private final ComKomponenDarah comKomponenDarah ;
    private Integer comKomponenDarahId;
    private String comKomponenDarahName;
    private String comKomponenDarahDesc;
    private GeneralModel gmComKomponenDarah;
    
    public ComKomponenDarah(){
        comKomponenDarah = this;
    }
    
    public Integer getComKomponenDarahId(){
        return this.comKomponenDarahId;
    }
    
    public void setComKomponenDarahId(Integer pComKomponenDarahId){
        this.comKomponenDarahId = pComKomponenDarahId;
    }
    
    public String getComKomponenDarahName(){
        return this.comKomponenDarahName;
    }
    
    public void setComKomponenDarahName(String pComKomponenDarahName){
        this.comKomponenDarahName = pComKomponenDarahName;
    }
    
    public String getComKomponenDarahDesc(){
        return this.comKomponenDarahDesc;
    }
    
    public void setComKomponenDarahDesc(String pComKomponenDarahDesc){
        this.comKomponenDarahDesc = pComKomponenDarahDesc;
    }
        
    public GeneralModel getGmComKomponenDarah() {
        gmComKomponenDarah = new GeneralModel(comKomponenDarah.getComKomponenDarahId(), comKomponenDarah.getComKomponenDarahName(), "ComKomponenDarah", comKomponenDarah);

        return gmComKomponenDarah;
    }
    
    @Override
    public String toString() {
        return "ComKomponenDarah[comKomponenDarahId=" + comKomponenDarahId + ",comKomponenDarahName=" + comKomponenDarahName + ",comKomponenDarahDesc=" + comKomponenDarahDesc + "]";
    }
}

