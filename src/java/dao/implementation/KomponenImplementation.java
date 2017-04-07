/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.implementation;

import com.dao.ComKomponenDarahDao;
import com.entity.ComKomponenDarah;
import dao.AftapDao;
import dao.KomponenDao;
import entity.Aftap;
import dao.implementation.*;
import entity.Komponen;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.ConnectionMySQL;
import dao.KomponenDao;

/**
 *
 * @author heri
 */
public class KomponenImplementation implements KomponenDao {

    private Connection connection;

    public KomponenImplementation(Connection pConnection) {
        this.connection = pConnection;
    }

    @Override
    public void insert(Komponen pKomponen) {
        PreparedStatement statement;
        statement = null;
        try {
            connection.setAutoCommit(false);

            String sql = "INSERT INTO komponen SET "
                    + "komponenNo = ?, "
                    + "komponenTanggal = ?,"
                    + "comKomponenDarahId = ?,"
                    + "aftapId = ? "
                    + ";";

            statement = connection.prepareStatement(sql);

            statement.setString(1, pKomponen.getKomponenNo());
            statement.setString(2, pKomponen.getKomponenTanggal());
            statement.setInt(3, pKomponen.getComKomponenDarah().getComKomponenDarahId());
            statement.setInt(4, pKomponen.getAftap().getAftapId());

            System.out.println(statement.toString());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                }
                throw new Exception(e.getMessage());
            } catch (Exception ex) {
                Logger.getLogger(KomponenImplementation.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                }
            }
        }
    }

    @Override
    public void update(Komponen pKomponen) {
        PreparedStatement statement;
        statement = null;
        try {
            connection.setAutoCommit(false);

            String sql = "UPDATE komponen SET "
                    + "komponenNo = ?, "
                    + "komponenTanggal = ?,"
                    + "comKomponenDarahId = ?,"
                    + "aftapId = ? "
                    + "WHERE komponenId = ?"
                    + ";";

            statement = connection.prepareStatement(sql);

            statement.setString(1, pKomponen.getKomponenNo());
            statement.setString(2, pKomponen.getKomponenTanggal());
            statement.setInt(3, pKomponen.getComKomponenDarah().getComKomponenDarahId());
            statement.setInt(4, pKomponen.getAftap().getAftapId());
            statement.setInt(5, pKomponen.getKomponenId());

            System.out.println(statement.toString());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                }
                throw new Exception(e.getMessage());
            } catch (Exception ex) {
                Logger.getLogger(KomponenImplementation.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                }
            }
        }
    }

    @Override
    public void delete(Integer pKomponenId) {
        PreparedStatement statement;
        statement = null;
        try {
            connection.setAutoCommit(false);

            String sql = "DELETE FROM komponen WHERE komponenId = ?";

            statement = connection.prepareStatement(sql);
            statement.setInt(1, pKomponenId);

            System.out.println(statement.toString());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                }
                throw new Exception(e.getMessage());
            } catch (Exception ex) {
                Logger.getLogger(KomponenImplementation.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                }
            }
        }
    }

    /**
     *
     * @return @throws Exception
     */
    @Override
    public List<Komponen> selectAll() throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        List<Komponen> list;
        try {
            list = new ArrayList<>();
            connection.setAutoCommit(false);

            String sql = "SELECT A.komponenId, \n"
                    + "A.komponenNo, \n"
                    + "A.komponenTanggal, \n"
                    + "B.aftapId, \n"
                    + "ckd.comKomponenDarahId \n"
                    + "FROM Komponen AS A INNER JOIN Aftap AS B ON A.aftapId = B.aftapId \n"
                    + "INNER JOIN comKomponenDarah ckd ON ckd.comKomponenDarahId=a.comKomponenDarahId";
            System.out.println(sql);

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Komponen oKomponen = new Komponen();

                oKomponen.setKomponenId(resultSet.getInt("komponenId"));

                Aftap oAftap;
                AftapDao aftapDao = ConnectionMySQL.getAftapDao();
                oAftap = aftapDao.selectAftapById(resultSet.getInt("aftapId"));
                
                ComKomponenDarah oComKomponenDarah;
                ComKomponenDarahDao comKomponenDarahDao = ConnectionMySQL.getComKomponenDarahDao();
                oComKomponenDarah = comKomponenDarahDao.selectComKomponenDarahById(resultSet.getInt("comKomponenDarahId"));

                oKomponen.setAftap(oAftap);
                oKomponen.setComKomponenDarah(oComKomponenDarah);
                oKomponen.setKomponenNo(resultSet.getString("komponenNo"));
                oKomponen.setKomponenTanggal(resultSet.getString("komponenTanggal"));

                list.add(oKomponen);
            }

            connection.setAutoCommit(false);
            connection.commit();
            return list;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            throw new Exception(e.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                }
            }
        }
    }

    @Override
    public Komponen getKomponenById(Integer id) throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        try {
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM komponen "
                    + "WHERE komponenId = " + id + ";";

            System.out.println(sql);
            Komponen oKomponen = null;

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {

                oKomponen = new Komponen();

                oKomponen.setKomponenId(resultSet.getInt("komponenId"));

                Aftap oAftap;
                AftapDao aftapDao = ConnectionMySQL.getAftapDao();
                oAftap = aftapDao.selectAftapById(resultSet.getInt("aftapId"));

                oKomponen.setAftap(oAftap);
                oKomponen.setKomponenNo(resultSet.getString("komponenNo"));
                oKomponen.setKomponenTanggal(resultSet.getString("komponenTanggal"));

            }
            connection.setAutoCommit(false);
            connection.commit();
            return oKomponen;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            throw new Exception(e.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                }
            }
        }

    }
}
