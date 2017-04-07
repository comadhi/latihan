/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.implementation;

import com.dao.ComGolonganDarahDao;
import com.dao.ComPekerjaanDao;
import com.entity.ComGolonganDarah;
import com.entity.ComPekerjaan;
import dao.PendonorDao;
import entity.Pendonor;
import java.sql.Connection;
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
public class PendonorImplementation implements PendonorDao {

    private Connection connection;

    public PendonorImplementation(Connection pConnection) {
        this.connection = pConnection;
    }

    @Override
    public void insert(Pendonor pPendonor) {
        PreparedStatement statement;
        statement = null;
        try {
            connection.setAutoCommit(false);

            String sql = "INSERT INTO pendonor SET "
                    + "pendonorNo = ?, "
                    + "pendonorName = ?,"
                    + "pendonorGender = ?, "
                    + "pendonorBirthPlace = ?, "
                    + "pendonorBirthDate = ?, "
                    + "pendonorAddress = ?, "
                    + "pendonorTelp = ?, "
                    + "pendonorEmail = ?, "
                    + "pendonorPassword = ?, "
                    + "comPekerjaanId = ?, "
                    + "comGolonganDarahId = ? "
                    + ";";

            statement = connection.prepareStatement(sql);

            statement.setString(1, pPendonor.getPendonorNo());
            statement.setString(2, pPendonor.getPendonorName());
            statement.setString(3, pPendonor.getPendonorGender());
            statement.setString(4, pPendonor.getPendonorBirthPlace());
            statement.setString(5, pPendonor.getPendonorBirthDate());
            statement.setString(6, pPendonor.getPendonorAddress());
            statement.setString(7, pPendonor.getPendonorTelp());
            statement.setString(8, pPendonor.getPendonorEmail());
            statement.setString(9, pPendonor.getPendonorPassword());
            statement.setInt(10, pPendonor.getComPekerjaan().getComPekerjaanId());
            statement.setInt(11, pPendonor.getComGolonganDarah().getComGolonganDarahId());

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
                Logger.getLogger(PendonorImplementation.class.getName()).log(Level.SEVERE, null, ex);
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
    public void update(Pendonor pPendonor) {

        PreparedStatement statement;
        statement = null;
        try {
            connection.setAutoCommit(false);

            String sql = "UPDATE pendonor SET "
                    + "pendonorNo = ?, "
                    + "pendonorName = ?,"
                    + "pendonorGender = ?, "
                    + "pendonorBirthPlace = ?, "
                    + "pendonorBirthDate = ?, "
                    + "pendonorAddress = ?, "
                    + "pendonorTelp = ?, "
                    + "pendonorEmail = ?, "
                    + "pendonorPassword = ?, "
                    + "comPekerjaanId = ?, "
                    + "comGolonganDarahId = ? "
                    + "WHERE pendonorId = ?"
                    + ";";

            statement = connection.prepareStatement(sql);

            statement.setString(1, pPendonor.getPendonorNo());
            statement.setString(2, pPendonor.getPendonorName());
            statement.setString(3, pPendonor.getPendonorGender());
            statement.setString(4, pPendonor.getPendonorBirthPlace());
            statement.setString(5, pPendonor.getPendonorBirthDate());
            statement.setString(6, pPendonor.getPendonorAddress());
            statement.setString(7, pPendonor.getPendonorTelp());
            statement.setString(8, pPendonor.getPendonorEmail());
            statement.setString(9, pPendonor.getPendonorPassword());
            statement.setInt(10, pPendonor.getComPekerjaan().getComPekerjaanId());
            statement.setInt(11, pPendonor.getComGolonganDarah().getComGolonganDarahId());
            statement.setInt(12, pPendonor.getPendonorId());

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
                Logger.getLogger(PendonorImplementation.class.getName()).log(Level.SEVERE, null, ex);
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
    public void delete(Integer pPendonorId) {
        PreparedStatement statement;
        statement = null;
        try {
            connection.setAutoCommit(false);

            String sql = "DELETE FROM pendonor WHERE pendonorId = ?";

            statement = connection.prepareStatement(sql);
            statement.setInt(1, pPendonorId);

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
                Logger.getLogger(PendonorImplementation.class.getName()).log(Level.SEVERE, null, ex);
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
    public List<Pendonor> selectAll() throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        List<Pendonor> list;
        try {
            list = new ArrayList<>();
            connection.setAutoCommit(false);

            String sql = "SELECT A.pendonorId, "
                    + "A.pendonorNo, "
                    + "A.pendonorName, "
                    + "A.pendonorGender, "
                    + "A.pendonorAddress, "
                    + "A.pendonorBirthPlace, "
                    + "A.pendonorBirthDate, "
                    + "A.pendonorTelp, "
                    + "A.pendonorEmail, "
                    + "A.pendonorPassword, "
                    + "B.comPekerjaanId, "
                    + "C.comGolonganDarahId \n"
                    + "FROM Pendonor AS A INNER JOIN ComPekerjaan AS B ON A.comPekerjaanId = B.comPekerjaanId\n"
                    + "INNER JOIN comGolonganDarah C ON a.comGolonganDarahId=c.comGolonganDarahId";
            System.out.println(sql);

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Pendonor oPendonor = new Pendonor();

                oPendonor.setPendonorId(resultSet.getInt("pendonorId"));

                ComPekerjaan oComPekerjaan;
                ComPekerjaanDao comPekerjaanDao = ConnectionMySQL.getComPekerjaanDao();
                oComPekerjaan = comPekerjaanDao.selectComPekerjaanById(resultSet.getInt("comPekerjaanId"));

                ComGolonganDarah oComGolonganDarah;
                ComGolonganDarahDao comGolonganDarahDao = ConnectionMySQL.getComGolonganDarahDao();
                oComGolonganDarah = comGolonganDarahDao.selectComGolonganDarahById(resultSet.getInt("comGolonganDarahId"));

                oPendonor.setComPekerjaan(oComPekerjaan);
                oPendonor.setComGolonganDarah(oComGolonganDarah);
                oPendonor.setPendonorNo(resultSet.getString("pendonorNo"));
                oPendonor.setPendonorName(resultSet.getString("pendonorName"));
                oPendonor.setPendonorGender(resultSet.getString("pendonorGender"));
                oPendonor.setPendonorBirthPlace(resultSet.getString("pendonorBirthPlace"));
                oPendonor.setPendonorBirthDate(resultSet.getString("pendonorBirthDate"));
                oPendonor.setPendonorAddress(resultSet.getString("pendonorAddress"));
                oPendonor.setPendonorTelp(resultSet.getString("pendonorTelp"));
                oPendonor.setPendonorEmail(resultSet.getString("pendonorEmail"));
                oPendonor.setPendonorPassword(resultSet.getString("pendonorPassword"));

                list.add(oPendonor);
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
    public List<Pendonor> selectAllForAutoCompleteComboBox(String pPendonorName) throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        List<Pendonor> list;
        try {
            list = new ArrayList<>();
            connection.setAutoCommit(false);

            String sql = "SELECT A.pendonorId, "
                    + "A.pendonorNo, "
                    + "A.pendonorName, "
                    + "A.pendonorGender, "
                    + "A.pendonorAddress, "
                    + "A.pendonorBirthPlace, "
                    + "A.pendonorBirthDate, "
                    + "A.pendonorTelp, "
                    + "A.pendonorEmail, "
                    + "A.pendonorPassword, "
                    + "B.comPekerjaanId, "
                    + "C.comGolonganDarahId \n"
                    + "FROM Pendonor AS A INNER JOIN ComPekerjaan AS B ON A.comPekerjaanId = B.comPekerjaanId\n"
                    + "INNER JOIN comGolonganDarah C ON a.comGolonganDarahId=c.comGolonganDarahId \n"
                    + "WHERE pendonorName LIKE '%"+pPendonorName+"%'";
            System.out.println(sql);

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Pendonor oPendonor = new Pendonor();

                oPendonor.setPendonorId(resultSet.getInt("pendonorId"));

                ComPekerjaan oComPekerjaan;
                ComPekerjaanDao comPekerjaanDao = ConnectionMySQL.getComPekerjaanDao();
                oComPekerjaan = comPekerjaanDao.selectComPekerjaanById(resultSet.getInt("comPekerjaanId"));

                ComGolonganDarah oComGolonganDarah;
                ComGolonganDarahDao comGolonganDarahDao = ConnectionMySQL.getComGolonganDarahDao();
                oComGolonganDarah = comGolonganDarahDao.selectComGolonganDarahById(resultSet.getInt("comGolonganDarahId"));

                oPendonor.setComPekerjaan(oComPekerjaan);
                oPendonor.setComGolonganDarah(oComGolonganDarah);
                oPendonor.setPendonorNo(resultSet.getString("pendonorNo"));
                oPendonor.setPendonorName(resultSet.getString("pendonorName"));
                oPendonor.setPendonorGender(resultSet.getString("pendonorGender"));
                oPendonor.setPendonorBirthPlace(resultSet.getString("pendonorBirthPlace"));
                oPendonor.setPendonorBirthDate(resultSet.getString("pendonorBirthDate"));
                oPendonor.setPendonorAddress(resultSet.getString("pendonorAddress"));
                oPendonor.setPendonorTelp(resultSet.getString("pendonorTelp"));
                oPendonor.setPendonorEmail(resultSet.getString("pendonorEmail"));
                oPendonor.setPendonorPassword(resultSet.getString("pendonorPassword"));

                list.add(oPendonor);
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
    public Pendonor selectPendonorById(Integer pPendonorId) throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        try {
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM pendonor where pendonorId = " + pPendonorId + " ;";
            System.out.println(sql);

            Pendonor oPendonor = null;

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                oPendonor = new Pendonor();

                oPendonor.setPendonorId(resultSet.getInt("pendonorId"));

                oPendonor.setPendonorNo(resultSet.getString("pendonorNo"));
                oPendonor.setPendonorName(resultSet.getString("pendonorName"));
                oPendonor.setPendonorGender(resultSet.getString("pendonorGender"));
                oPendonor.setPendonorBirthPlace(resultSet.getString("pendonorBirthPlace"));
                oPendonor.setPendonorBirthDate(resultSet.getString("pendonorBirthDate"));
                oPendonor.setPendonorAddress(resultSet.getString("pendonorAddress"));
                oPendonor.setPendonorTelp(resultSet.getString("pendonorTelp"));
                oPendonor.setPendonorEmail(resultSet.getString("pendonorEmail"));
                oPendonor.setPendonorPassword(resultSet.getString("pendonorPassword"));

            }

            connection.setAutoCommit(false);
            connection.commit();
            return oPendonor;
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
            }
        }

    }

    @Override
    public Pendonor getPendonorById(Integer id) throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        try {
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM pendonor "
                    + "WHERE pendonorId = " + id + ";";

            System.out.println(sql);
            Pendonor oPendonor = null;

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {

                oPendonor = new Pendonor();

                oPendonor.setPendonorId(resultSet.getInt("pendonorId"));

                ComPekerjaan oComPekerjaan;
                ComPekerjaanDao pendonorDao = ConnectionMySQL.getComPekerjaanDao();
                oComPekerjaan = pendonorDao.selectComPekerjaanById(resultSet.getInt("comPekerjaanId"));
                
                ComGolonganDarah oComGolonganDarah;
                ComGolonganDarahDao comGolonganDarahDao = ConnectionMySQL.getComGolonganDarahDao();
                oComGolonganDarah = comGolonganDarahDao.getComGolonganDarahById(resultSet.getInt("comGolonganDarahId"));

                oPendonor.setComGolonganDarah(oComGolonganDarah);
                oPendonor.setComPekerjaan(oComPekerjaan);
                oPendonor.setPendonorNo(resultSet.getString("pendonorNo"));
                oPendonor.setPendonorName(resultSet.getString("pendonorName"));
                oPendonor.setPendonorGender(resultSet.getString("pendonorGender"));
                oPendonor.setPendonorBirthPlace(resultSet.getString("pendonorBirthPlace"));
                oPendonor.setPendonorBirthDate(resultSet.getString("pendonorBirthDate"));
                oPendonor.setPendonorAddress(resultSet.getString("pendonorAddress"));
                oPendonor.setPendonorTelp(resultSet.getString("pendonorTelp"));
                oPendonor.setPendonorEmail(resultSet.getString("pendonorEmail"));
                oPendonor.setPendonorPassword(resultSet.getString("pendonorPassword"));

            }
            connection.setAutoCommit(false);
            connection.commit();
            return oPendonor;
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
