/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.entity.ComPekerjaan;
import entity.Aftap;
import java.util.List;

/**
 *
 * @author heri
 */
public interface AftapDao {

    public void insert(Aftap pAftap);

    public void update(Aftap pAftap);

    public void delete(Integer pAftapId);

    public List<Aftap> selectAll() throws Exception;
    
    public List<Aftap> selectAllForCrossMatch() throws Exception;
    
    public Aftap getAftapById(Integer id) throws Exception;
    
    public Aftap selectAftapById(Integer pAftapId) throws Exception;
    
    public Aftap selectAftapByBagNumber(String pAftapNoKantong) throws Exception;
    
    public Aftap checkPendonorById(String pPendonorNo) throws Exception;
    
    public boolean isExistByBagNumber(String idBagNumber) throws Exception;
}
