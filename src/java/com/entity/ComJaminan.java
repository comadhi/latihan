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
public class ComJaminan {
    
    private final ComJaminan comJaminan ;
    private Integer comJaminanId;
    private String comJaminanName;
    private String comJaminanDesc;
    private GeneralModel gmComJaminan;
    
    public ComJaminan(){
        comJaminan = this;
    }
    
    public Integer getComJaminanId(){
        return this.comJaminanId;
    }
    
    public void setComJaminanId(Integer pComJaminanId){
        this.comJaminanId = pComJaminanId;
    }
    
    public String getComJaminanName(){
        return this.comJaminanName;
    }
    
    public void setComJaminanName(String pComJaminanName){
        this.comJaminanName = pComJaminanName;
    }
    
    public String getComJaminanDesc(){
        return this.comJaminanDesc;
    }
    
    public void setComJaminanDesc(String pComJaminanDesc){
        this.comJaminanDesc = pComJaminanDesc;
    }
        
    public GeneralModel getGmComJaminan() {
        gmComJaminan = new GeneralModel(comJaminan.getComJaminanId(), comJaminan.getComJaminanName(), "ComJaminan", comJaminan);

        return gmComJaminan;
    }
    
    @Override
    public String toString() {
        return "ComJaminan[comJaminanId=" + comJaminanId + ",comJaminanName=" + comJaminanName + ",comJaminanDesc=" + comJaminanDesc + "]";
    }
}
