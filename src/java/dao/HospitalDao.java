/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Hospital;
import java.util.List;

/**
 *
 * @author Administrator
 */
public interface HospitalDao {

    public void Insert(Hospital hospital) throws Exception;

    public void Update(Hospital hospital) throws Exception;

    public void Delete(Integer idHospital) throws Exception;

    public List<Hospital> selectAll() throws Exception;

    public List<Hospital> selectAllWithLimit(int offset, int noOfRecords) throws Exception;
    
    public Hospital selectHospitalById(Integer pHospitalId) throws Exception;

    public Integer getNoOfRecords() throws Exception;

    public Hospital getHospitalById(Integer idHospital) throws Exception;

    public Hospital getHospitalByNameAndPass(String Name, String Password) throws Exception;

}
