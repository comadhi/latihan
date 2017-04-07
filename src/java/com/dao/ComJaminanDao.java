/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.dao.*;
import com.entity.ComJaminan;
import java.util.List;

/**
 *
 * @author heri
 */
public interface ComJaminanDao {

    public void insert(ComJaminan pComJaminan);

    public void update(ComJaminan pComJaminan);

    public void delete(Integer pComJaminanId);

    public List<ComJaminan> selectAll() throws Exception;

    public Integer getNoOfRecords() throws Exception;
    
    public ComJaminan getComJaminanById(Integer id) throws Exception;
}
