/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.dao.*;
import com.entity.ComGolonganDarah;
import com.entity.ComKomponenDarah;
import java.util.List;

/**
 *
 * @author heri
 */
public interface ComKomponenDarahDao {

    public void insert(ComKomponenDarah pComKomponenDarah);

    public void update(ComKomponenDarah pComKomponenDarah);

    public void delete(Integer pComKomponenDarahId);

    public List<ComKomponenDarah> selectAll() throws Exception;
    
    public ComKomponenDarah selectComKomponenDarahById(Integer pComKomponenDarahId) throws Exception;

    public Integer getNoOfRecords() throws Exception;
    
    public ComKomponenDarah getComKomponenDarahById(Integer id) throws Exception;
}
