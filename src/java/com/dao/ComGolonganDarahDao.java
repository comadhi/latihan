/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.dao.*;
import com.entity.ComGolonganDarah;
import java.util.List;

/**
 *
 * @author heri
 */
public interface ComGolonganDarahDao {

    public void insert(ComGolonganDarah pComGolonganDarah);

    public void update(ComGolonganDarah pComGolonganDarah);

    public void delete(Integer pComGolonganDarahId);

    public List<ComGolonganDarah> selectAll() throws Exception;
    
    public ComGolonganDarah selectComGolonganDarahById(Integer pComGolonganDarahId) throws Exception;

    public Integer getNoOfRecords() throws Exception;
    
    public ComGolonganDarah getComGolonganDarahById(Integer id) throws Exception;
}
