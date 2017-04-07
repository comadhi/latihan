/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.implementation;

import com.dao.ComPekerjaanDao;
import com.dao.ComVolumeKantongDao;
import com.entity.ComPekerjaan;
import com.entity.ComVolumeKantong;
import dao.implementation.*;
import dao.AftapDao;
import dao.PendonorDao;
import entity.Aftap;
import entity.Pendonor;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.ConnectionMySQL;

/**
 *
 * @author heri
 */
public class AftapImplementation implements AftapDao {

    private Connection connection;

    public AftapImplementation(Connection pConnection) {
        this.connection = pConnection;
    }

    @Override
    public void insert(Aftap pAftap) {
        PreparedStatement statement;
        statement = null;
        try {
            connection.setAutoCommit(false);

            String sql = "INSERT INTO aftap SET "
                    + "aftapNoKantong = ?, "
                    + "aftapTanggal = ?,"
                    + "aftapStatusAmbil = ?, "
                    + "aftapReaksi = ?, "
                    + "aftapStatusDonor = ?, "
                    + "comVolumeKantongId = ?,"
                    + "pendonorId = ?"
                    + ";";

            statement = connection.prepareStatement(sql);

            statement.setString(1, pAftap.getAftapNoKantong());
            statement.setString(2, (pAftap.getAftapTanggal()));
            statement.setString(3, pAftap.getAftapStatusAmbil());
            statement.setString(4, pAftap.getAftapReaksi());
            statement.setString(5, pAftap.getAftapStatusDonor());
            statement.setInt(6, pAftap.getComVolumeKantong().getComVolumeKantongId());
            statement.setInt(7, pAftap.getPendonor().getPendonorId());

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
                Logger.getLogger(AftapImplementation.class.getName()).log(Level.SEVERE, null, ex);
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
    public void update(Aftap pAftap) {

        PreparedStatement statement;
        statement = null;
        try {
            connection.setAutoCommit(false);

            String sql = "UPDATE aftap SET "
                    + "aftapNoKantong = ?, "
                    + "aftapTanggal = ?,"
                    + "aftapStatusAmbil = ?, "
                    + "aftapReaksi = ?,"
                    + "aftapStatusDonor = ?,"
                    + "comVolumeKantongId = ?, "
                    + "comPendonorId = ? "
                    + "WHERE aftapId = ? "
                    + ";";

            statement = connection.prepareStatement(sql);

            statement.setString(1, pAftap.getAftapNoKantong());
            statement.setString(2, pAftap.getAftapTanggal());
            statement.setString(3, pAftap.getAftapStatusAmbil());
            statement.setString(4, pAftap.getAftapReaksi());
            statement.setString(5, pAftap.getAftapStatusDonor());
            statement.setInt(6, pAftap.getComVolumeKantong().getComVolumeKantongId());
            statement.setInt(7, pAftap.getPendonor().getPendonorId());
            statement.setInt(8, pAftap.getAftapId());

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
                Logger.getLogger(AftapImplementation.class.getName()).log(Level.SEVERE, null, ex);
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
    public void delete(Integer pAftapId) {
        PreparedStatement statement;
        statement = null;
        try {
            connection.setAutoCommit(false);

            String sql = "DELETE FROM aftap WHERE aftapId = ?";

            statement = connection.prepareStatement(sql);
            statement.setInt(1, pAftapId);

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
                Logger.getLogger(AftapImplementation.class.getName()).log(Level.SEVERE, null, ex);
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
    public List<Aftap> selectAll() throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        List<Aftap> list;
        try {
            list = new ArrayList<>();
            connection.setAutoCommit(false);

            String sql = "SELECT A.aftapId, "
                    + "A.aftapNoKantong, "
                    + "A.aftapTanggal, "
                    + "A.aftapStatusAmbil, "
                    + "A.aftapReaksi, "
                    + "B.comVolumeKantongId, "
                    + "P.pendonorId \n"
                    + "FROM Aftap AS A INNER JOIN ComVolumeKantong AS B ON A.comVolumeKantongId = B.comVolumeKantongId \n"
                    + "INNER JOIN pendonor P ON A.pendonorId=P.pendonorId";
            System.out.println(sql);

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Aftap oAftap = new Aftap();

                oAftap.setAftapId(resultSet.getInt("aftapId"));

                ComVolumeKantong oComVolumeKantong;
                ComVolumeKantongDao comVolumeKantongDao = ConnectionMySQL.getComVolumeKantongDao();
                oComVolumeKantong = comVolumeKantongDao.selectComVolumeKantongById(resultSet.getInt("comVolumeKantongId"));

                Pendonor oPendonor;
                PendonorDao pendonorDao = ConnectionMySQL.getPendonorDao();
                oPendonor = pendonorDao.selectPendonorById(resultSet.getInt("pendonorId"));

                oAftap.setComVolumeKantong(oComVolumeKantong);
                oAftap.setPendonor(oPendonor);
                oAftap.setAftapNoKantong(resultSet.getString("aftapNoKantong"));
                oAftap.setAftapTanggal(resultSet.getString("aftapTanggal"));
                oAftap.setAftapStatusAmbil(resultSet.getString("aftapStatusAmbil"));
                oAftap.setAftapReaksi(resultSet.getString("aftapReaksi"));

                list.add(oAftap);
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
    public List<Aftap> selectAllForCrossMatch() throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        List<Aftap> list;
        try {
            list = new ArrayList<>();
            connection.setAutoCommit(false);

            String sql = "SELECT A.aftapId, "
                    + "A.aftapNoKantong, "
                    + "A.aftapTanggal, "
                    + "A.aftapStatusAmbil, "
                    + "A.aftapReaksi, "
                    + "B.comVolumeKantongId, "
                    + "P.pendonorId \n"
                    + "FROM Aftap AS A INNER JOIN ComVolumeKantong AS B ON A.comVolumeKantongId = B.comVolumeKantongId \n"
                    + "INNER JOIN pendonor P ON A.pendonorId=P.pendonorId "
                    + "LEFT JOIN screening s ON a.aftapId=s.aftapId \n" 
                    + "WHERE s.screeningHbsag != 'Positive' AND s.screeningAntiHiv != 'Positive' AND s.screeningAntiHcv != 'Positive' AND s.screeningVdrl != 'Positive'";
            System.out.println(sql);

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Aftap oAftap = new Aftap();

                oAftap.setAftapId(resultSet.getInt("aftapId"));

                ComVolumeKantong oComVolumeKantong;
                ComVolumeKantongDao comVolumeKantongDao = ConnectionMySQL.getComVolumeKantongDao();
                oComVolumeKantong = comVolumeKantongDao.selectComVolumeKantongById(resultSet.getInt("comVolumeKantongId"));

                Pendonor oPendonor;
                PendonorDao pendonorDao = ConnectionMySQL.getPendonorDao();
                oPendonor = pendonorDao.selectPendonorById(resultSet.getInt("pendonorId"));

                oAftap.setComVolumeKantong(oComVolumeKantong);
                oAftap.setPendonor(oPendonor);
                oAftap.setAftapNoKantong(resultSet.getString("aftapNoKantong"));
                oAftap.setAftapTanggal(resultSet.getString("aftapTanggal"));
                oAftap.setAftapStatusAmbil(resultSet.getString("aftapStatusAmbil"));
                oAftap.setAftapReaksi(resultSet.getString("aftapReaksi"));

                list.add(oAftap);
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
    public Aftap selectAftapById(Integer pAftapId) throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        try {
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM aftap where aftapId = " + pAftapId + " ;";
            System.out.println(sql);

            Aftap oAftap = null;

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                oAftap = new Aftap();

                ComVolumeKantong oComVolumeKantong;
                ComVolumeKantongDao comVolumeKantongDao = ConnectionMySQL.getComVolumeKantongDao();
                oComVolumeKantong = comVolumeKantongDao.selectComVolumeKantongById(resultSet.getInt("comVolumeKantongId"));

                oAftap.setComVolumeKantong(oComVolumeKantong);
                oAftap.setAftapNoKantong(resultSet.getString("aftapNoKantong"));
                oAftap.setAftapTanggal(resultSet.getString("aftapTanggal"));
                oAftap.setAftapStatusAmbil(resultSet.getString("aftapStatusAmbil"));
                oAftap.setAftapReaksi(resultSet.getString("aftapReaksi"));

            }

            connection.setAutoCommit(false);
            connection.commit();
            return oAftap;
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
    public Aftap selectAftapByBagNumber(String pAftapNoKantong) throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        try {
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM aftap where aftapNoKantong = '" + pAftapNoKantong + "' ;";
            System.out.println(sql);

            Aftap oAftap = null;

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                oAftap = new Aftap();
//
//                ComVolumeKantong oComVolumeKantong;
//                ComVolumeKantongDao comVolumeKantongDao = ConnectionMySQL.getComVolumeKantongDao();
//                oComVolumeKantong = comVolumeKantongDao.selectComVolumeKantongById(resultSet.getInt("comVolumeKantongId"));
//
//                oAftap.setComVolumeKantong(oComVolumeKantong);
                oAftap.setAftapId(resultSet.getInt("aftapId"));
                oAftap.setAftapNoKantong(resultSet.getString("aftapNoKantong"));
                oAftap.setAftapTanggal(resultSet.getString("aftapTanggal"));
                oAftap.setAftapStatusAmbil(resultSet.getString("aftapStatusAmbil"));
                oAftap.setAftapReaksi(resultSet.getString("aftapReaksi"));

            }

            connection.setAutoCommit(false);
            connection.commit();
            return oAftap;
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

    public Aftap checkPendonorById(String pPendonorNo) throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        try {
            connection.setAutoCommit(false);

//            String iPendonorNo = pPendonorId.toString();
            String sql = "SELECT *  \n"
                    + "FROM aftap a INNER JOIN pendonor p ON a.pendonorId=p.pendonorId \n"
                    + "WHERE p.pendonorNo= '" + pPendonorNo + "'\n"
                    + "AND (DATE_FORMAT(NOW(), '%Y-%m-%d') >= DATE_FORMAT(DATE_ADD(a.aftapTanggal,INTERVAL 5 DAY), '%Y-%m-%d'))\n"
                    + "AND a.aftapTanggal = \n"
                    + "(SELECT MAX(aftapTanggal) FROM aftap a INNER JOIN pendonor p ON (a.pendonorId=p.pendonorId) WHERE p.pendonorNo = '" + pPendonorNo + "');";
            System.out.println(sql);

            Aftap oAftap = null;

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                oAftap = new Aftap();

                Pendonor oPendonor;
                PendonorDao pendonorDao = ConnectionMySQL.getPendonorDao();
                oPendonor = pendonorDao.selectPendonorById(resultSet.getInt("pendonorId"));

                oAftap.setPendonor(oPendonor);

                oAftap.setAftapId(resultSet.getInt("aftapId"));
                oAftap.setAftapNoKantong(resultSet.getString("aftapNoKantong"));
                oAftap.setAftapTanggal(resultSet.getString("aftapTanggal"));

            }

            connection.setAutoCommit(false);
            connection.commit();
            return oAftap;
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
    public Aftap getAftapById(Integer id) throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        try {
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM aftap "
                    + "WHERE aftapId = " + id + ";";

            System.out.println(sql);
            Aftap oAftap = null;

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {

                oAftap = new Aftap();

                oAftap.setAftapId(resultSet.getInt("aftapId"));

                ComVolumeKantong oComVolumeKantong;
                ComVolumeKantongDao comVolumeKantongDao = ConnectionMySQL.getComVolumeKantongDao();
                oComVolumeKantong = comVolumeKantongDao.selectComVolumeKantongById(resultSet.getInt("comVolumeKantongId"));

                oAftap.setComVolumeKantong(oComVolumeKantong);
                oAftap.setAftapNoKantong(resultSet.getString("aftapNoKantong"));
                oAftap.setAftapTanggal(resultSet.getString("aftapTanggal"));
                oAftap.setAftapStatusAmbil(resultSet.getString("aftapStatusAmbil"));
                oAftap.setAftapReaksi(resultSet.getString("aftapReaksi"));

            }
            connection.setAutoCommit(false);
            connection.commit();
            return oAftap;
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
    public boolean isExistByBagNumber(String idBagNumber) throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        boolean result = false;
        statement = null;
        resultSet = null;
        try {
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM aftap WHERE aftapNoKantong = '" + idBagNumber + "';";
            System.out.println(sql);

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result = true;
            }

            connection.setAutoCommit(false);
            connection.commit();
            return result;
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
