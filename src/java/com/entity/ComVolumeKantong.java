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
public class ComVolumeKantong {
    
    private final ComVolumeKantong comVolumeKantong ;
    private Integer comVolumeKantongId;
    private String comVolumeKantongName;
    private String comVolumeKantongDesc;
    private GeneralModel gmComVolumeKantong;
    
    public ComVolumeKantong(){
        comVolumeKantong = this;
    }
    
    public Integer getComVolumeKantongId(){
        return this.comVolumeKantongId;
    }
    
    public void setComVolumeKantongId(Integer pComVolumeKantongId){
        this.comVolumeKantongId = pComVolumeKantongId;
    }
    
    public String getComVolumeKantongName(){
        return this.comVolumeKantongName;
    }
    
    public void setComVolumeKantongName(String pComVolumeKantongName){
        this.comVolumeKantongName = pComVolumeKantongName;
    }
    
    public String getComVolumeKantongDesc(){
        return this.comVolumeKantongDesc;
    }
    
    public void setComVolumeKantongDesc(String pComVolumeKantongDesc){
        this.comVolumeKantongDesc = pComVolumeKantongDesc;
    }
        
    public GeneralModel getGmComVolumeKantong() {
        gmComVolumeKantong = new GeneralModel(comVolumeKantong.getComVolumeKantongId(), comVolumeKantong.getComVolumeKantongName(), "ComVolumeKantong", comVolumeKantong);

        return gmComVolumeKantong;
    }
    
    @Override
    public String toString() {
        return "ComVolumeKantong[comVolumeKantongId=" + comVolumeKantongId + ",comVolumeKantongName=" + comVolumeKantongName + ",comVolumeKantongDesc=" + comVolumeKantongDesc + "]";
    }
}
