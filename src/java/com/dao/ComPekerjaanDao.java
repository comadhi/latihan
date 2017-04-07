/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.dao.*;
import com.entity.ComPekerjaan;
import java.util.List;

/**
 *
 * @author heri
 */
public interface ComPekerjaanDao {

    public void insert(ComPekerjaan pComPekerjaan);

    public void update(ComPekerjaan pComPekerjaan);

    public void delete(Integer pComPekerjaanId);

    public List<ComPekerjaan> selectAll() throws Exception;

    public Integer getNoOfRecords() throws Exception;
    
    public ComPekerjaan getComPekerjaanById(Integer id) throws Exception;
    
    public ComPekerjaan selectComPekerjaanById(Integer pComPekerjaanId) throws Exception;
}
