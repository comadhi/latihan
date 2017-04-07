/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Docter;
import java.util.List;

/**
 *
 * @author heri
 */
public interface DocterDao {

    public void insert(Docter pDocter);

    public void update(Docter pDocter);

    public void delete(Integer pDocterId);

    public List<Docter> selectAll() throws Exception;

    public Integer getNoOfRecords() throws Exception;
    
    public Docter getDocterById(Integer id) throws Exception;
    
    public List<Docter> selectAllWithLimit(int offset, int noOfRecords) throws Exception;
    
    public Docter selectDocterById(Integer pDocterId) throws Exception;
}
