/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.dao.*;
import com.entity.ComPekerjaan;
import com.entity.ComVolumeKantong;
import java.util.List;

/**
 *
 * @author heri
 */
public interface ComVolumeKantongDao {

    public void insert(ComVolumeKantong pComVolumeKantong);

    public void update(ComVolumeKantong pComVolumeKantong);

    public void delete(Integer pComVolumeKantongId);

    public List<ComVolumeKantong> selectAll() throws Exception;
    
    public ComVolumeKantong selectComVolumeKantongById(Integer pComVolumeKantongDaoId) throws Exception;

    public Integer getNoOfRecords() throws Exception;
    
    public ComVolumeKantong getComVolumeKantongById(Integer id) throws Exception;
}
