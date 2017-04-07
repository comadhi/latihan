/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Komponen;
import java.util.List;

/**
 *
 * @author heri
 */
public interface KomponenDao {

    public void insert(Komponen pKomponen);

    public void update(Komponen pKomponen);

    public void delete(Integer pKomponenId);

    public List<Komponen> selectAll() throws Exception;
    
    public Komponen getKomponenById(Integer id) throws Exception;
}
