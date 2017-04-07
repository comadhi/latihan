/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.implementation;

import dao.PendonorDao;
import dao.PeriksaDao;
import entity.Pendonor;
import entity.Periksa;
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
public class PeriksaImplementation implements PeriksaDao {

    private Connection connection;

    public PeriksaImplementation(Connection pConnection) {
        this.connection = pConnection;
    }

    @Override
    public void insert(Periksa pPeriksa) {
        PreparedStatement statement;
        statement = null;
        try {
            connection.setAutoCommit(false);

            String sql = "INSERT INTO periksa SET "
                    + "periksaTanggal = ?, "
                    + "periksaTensi = ?,"
                    + "periksaSuhu = ?, "
                    + "periksaBeratBadan = ?, "
                    + "periksaRiwayatMedis = ?, "
                    + "periksaKeputusan = ?, "
                    + "periksaAlasan = ?, "
                    + "pendonorId = ? "
                    + ";";

            statement = connection.prepareStatement(sql);

            statement.setString(1, pPeriksa.getPeriksaTanggal());
            statement.setString(2, pPeriksa.getPeriksaTensi());
            statement.setString(3, pPeriksa.getPeriksaSuhu());
            statement.setString(4, pPeriksa.getPeriksaBeratBadan());
            statement.setString(5, pPeriksa.getPeriksaRiwayatMedis());
            statement.setString(6, pPeriksa.getPeriksaKeputusan());
            statement.setString(7, pPeriksa.getPeriksaAlasan());
            statement.setInt(8, pPeriksa.getPendonor().getPendonorId());

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
                Logger.getLogger(PeriksaImplementation.class.getName()).log(Level.SEVERE, null, ex);
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
    public void update(Periksa pPeriksa) {

        PreparedStatement statement;
        statement = null;
        try {
            connection.setAutoCommit(false);

            String sql = "UPDATE periksa SET "
                    + "periksaTanggal = ?, "
                    + "periksaTensi = ?,"
                    + "periksaSuhu = ?, "
                    + "periksaBeratBadan = ?, "
                    + "periksaRiwayatMedis = ?, "
                    + "periksaKeputusan = ?, "
                    + "periksaAlasan = ?, "
                    + "periksaEmail = ?, "
                    + "periksaPassword = ?, "
                    + "comPekerjaanId = ? "
                    + "WHERE periksaId = ?"
                    + ";";

            statement = connection.prepareStatement(sql);

            statement.setString(1, pPeriksa.getPeriksaTanggal());
            statement.setString(2, pPeriksa.getPeriksaTensi());
            statement.setString(3, pPeriksa.getPeriksaSuhu());
            statement.setString(4, pPeriksa.getPeriksaBeratBadan());
            statement.setString(5, pPeriksa.getPeriksaRiwayatMedis());
            statement.setString(6, pPeriksa.getPeriksaKeputusan());
            statement.setString(7, pPeriksa.getPeriksaAlasan());
            statement.setInt(8, pPeriksa.getPeriksaId());

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
                Logger.getLogger(PeriksaImplementation.class.getName()).log(Level.SEVERE, null, ex);
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
    public void delete(Integer pPeriksaId) {
        PreparedStatement statement;
        statement = null;
        try {
            connection.setAutoCommit(false);

            String sql = "DELETE FROM periksa WHERE periksaId = ?";

            statement = connection.prepareStatement(sql);
            statement.setInt(1, pPeriksaId);

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
                Logger.getLogger(PeriksaImplementation.class.getName()).log(Level.SEVERE, null, ex);
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
    public List<Periksa> selectAll() throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        List<Periksa> list;
        try {
            list = new ArrayList<>();
            connection.setAutoCommit(false);

            String sql = "SELECT per.periksaId, "
                    + "per.periksaTanggal, "
                    + "per.periksaTensi, "
                    + "per.periksaSuhu, "
                    + "per.periksaKeputusan, "
                    + "per.periksaBeratBadan, "
                    + "per.periksaRiwayatMedis, "
                    + "per.periksaAlasan,"
                    + "p.pendonorId "
                    + "FROM Periksa per INNER JOIN pendonor p ON per.pendonorId=p.pendonorId "
                    + ";";
            System.out.println(sql);

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Periksa oPeriksa = new Periksa();

                Pendonor oPendonor;
                PendonorDao oPendonorDao = ConnectionMySQL.getPendonorDao();
                oPendonor = oPendonorDao.getPendonorById(resultSet.getInt("pendonorId"));

                oPeriksa.setPendonor(oPendonor);
                oPeriksa.setPeriksaId(resultSet.getInt("periksaId"));
                oPeriksa.setPeriksaTanggal(resultSet.getString("periksaTanggal"));
                oPeriksa.setPeriksaTensi(resultSet.getString("periksaTensi"));
                oPeriksa.setPeriksaSuhu(resultSet.getString("periksaSuhu"));
                oPeriksa.setPeriksaBeratBadan(resultSet.getString("periksaBeratBadan"));
                oPeriksa.setPeriksaRiwayatMedis(resultSet.getString("periksaRiwayatMedis"));
                oPeriksa.setPeriksaKeputusan(resultSet.getString("periksaKeputusan"));
                oPeriksa.setPeriksaAlasan(resultSet.getString("periksaAlasan"));

                list.add(oPeriksa);
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
    public Periksa getPeriksaById(Integer id) throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        try {
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM periksa "
                    + "WHERE periksaId = " + id + ";";

            System.out.println(sql);
            Periksa oPeriksa = null;

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {

                oPeriksa = new Periksa();

                oPeriksa.setPeriksaId(resultSet.getInt("periksaId"));

                oPeriksa.setPeriksaTanggal(resultSet.getString("periksaTanggal"));
                oPeriksa.setPeriksaTensi(resultSet.getString("periksaTensi"));
                oPeriksa.setPeriksaSuhu(resultSet.getString("periksaSuhu"));
                oPeriksa.setPeriksaBeratBadan(resultSet.getString("periksaBeratBadan"));
                oPeriksa.setPeriksaRiwayatMedis(resultSet.getString("periksaRiwayatMedis"));
                oPeriksa.setPeriksaKeputusan(resultSet.getString("periksaKeputusan"));
                oPeriksa.setPeriksaAlasan(resultSet.getString("periksaAlasan"));

            }
            connection.setAutoCommit(false);
            connection.commit();
            return oPeriksa;
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
    public boolean isAvailableDonatorPerToday(String pPendonorNo) throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        boolean result = false;
        statement = null;
        resultSet = null;
        try {
            connection.setAutoCommit(false);

            String sql = "SELECT * \n"
                    + "FROM periksa per INNER JOIN pendonor p ON (per.pendonorId=p.pendonorId)\n"
                    + "WHERE pendonorNo = '" + pPendonorNo + "' AND periksaTanggal = DATE_FORMAT(NOW(), '%Y-%m-%d');";
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
