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
public class ComGolonganDarah {
    
    private final ComGolonganDarah comGolonganDarah ;
    private Integer comGolonganDarahId;
    private String comGolonganDarahName;
    private String comGolonganDarahDesc;
    private GeneralModel gmComGolonganDarah;
    
    public ComGolonganDarah(){
        comGolonganDarah = this;
    }
    
    public Integer getComGolonganDarahId(){
        return this.comGolonganDarahId;
    }
    
    public void setComGolonganDarahId(Integer pComGolonganDarahId){
        this.comGolonganDarahId = pComGolonganDarahId;
    }
    
    public String getComGolonganDarahName(){
        return this.comGolonganDarahName;
    }
    
    public void setComGolonganDarahName(String pComGolonganDarahName){
        this.comGolonganDarahName = pComGolonganDarahName;
    }
    
    public String getComGolonganDarahDesc(){
        return this.comGolonganDarahDesc;
    }
    
    public void setComGolonganDarahDesc(String pComGolonganDarahDesc){
        this.comGolonganDarahDesc = pComGolonganDarahDesc;
    }
        
    public GeneralModel getGmComGolonganDarah() {
        gmComGolonganDarah = new GeneralModel(comGolonganDarah.getComGolonganDarahId(), comGolonganDarah.getComGolonganDarahName(), "ComGolonganDarah", comGolonganDarah);

        return gmComGolonganDarah;
    }
    
    @Override
    public String toString() {
        return "ComGolonganDarah[comGolonganDarahId=" + comGolonganDarahId + ",comGolonganDarahName=" + comGolonganDarahName + ",comGolonganDarahDesc=" + comGolonganDarahDesc + "]";
    }
}
