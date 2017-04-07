/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.implementation;

import dao.AftapDao;
import dao.ScreeningDao;
import entity.Aftap;
import dao.implementation.*;
import entity.Screening;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.ConnectionMySQL;
import dao.ScreeningDao;

/**
 *
 * @author heri
 */
public class ScreeningImplementation implements ScreeningDao {

    private Connection connection;

    public ScreeningImplementation(Connection pConnection) {
        this.connection = pConnection;
    }

    @Override
    public void insert(Screening pScreening) {
        PreparedStatement statement;
        statement = null;
        try {
            connection.setAutoCommit(false);

            String sql = "INSERT INTO screening SET "
                    + "screeningNo = ?, "
                    + "screeningTanggal = ?,"
                    + "screeningHbsag = ?, "
                    + "screeningAntiHiv = ?, "
                    + "screeningAntiHcv = ?, "
                    + "screeningVprl = ?, "
                    + "aftapId = ? "
                    + ";";

            statement = connection.prepareStatement(sql);

            statement.setString(1, pScreening.getScreeningNo());
            statement.setString(2, pScreening.getScreeningTanggal());
            statement.setString(3, pScreening.getScreeningHbsag());
            statement.setString(4, pScreening.getScreeningAntiHiv());
            statement.setString(5, pScreening.getScreeningAntiHcv());
            statement.setString(6, pScreening.getScreeningVprl());
            statement.setInt(7, pScreening.getAftap().getAftapId());

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
                Logger.getLogger(ScreeningImplementation.class.getName()).log(Level.SEVERE, null, ex);
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
    public void update(Screening pScreening) {
        PreparedStatement statement;
        statement = null;
        try {
            connection.setAutoCommit(false);

            String sql = "UPDATE screening SET "
                    + "screeningNo = ?, "
                    + "screeningTanggal = ?,"
                    + "screeningHbsag = ?, "
                    + "screeningAntiHiv = ?, "
                    + "screeningAntiHcv = ?, "
                    + "screeningVprl = ?, "
                    + "aftapId = ? "
                    + "WHERE screeningId = ?"
                    + ";";

            statement = connection.prepareStatement(sql);

            statement.setString(1, pScreening.getScreeningNo());
            statement.setString(2, pScreening.getScreeningTanggal());
            statement.setString(3, pScreening.getScreeningHbsag());
            statement.setString(4, pScreening.getScreeningAntiHiv());
            statement.setString(5, pScreening.getScreeningAntiHcv());
            statement.setString(6, pScreening.getScreeningVprl());
            statement.setInt(7, pScreening.getAftap().getAftapId());
            statement.setInt(8, pScreening.getScreeningId());

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
                Logger.getLogger(ScreeningImplementation.class.getName()).log(Level.SEVERE, null, ex);
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
    public void delete(Integer pScreeningId) {
        PreparedStatement statement;
        statement = null;
        try {
            connection.setAutoCommit(false);

            String sql = "DELETE FROM screening WHERE screeningId = ?";

            statement = connection.prepareStatement(sql);
            statement.setInt(1, pScreeningId);

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
                Logger.getLogger(ScreeningImplementation.class.getName()).log(Level.SEVERE, null, ex);
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
    public List<Screening> selectAll() throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        List<Screening> list;
        try {
            list = new ArrayList<>();
            connection.setAutoCommit(false);

            String sql = "SELECT A.screeningId, "
                    + "A.screeningNo, "
                    + "A.screeningTanggal, "
                    + "A.screeningHbsag, "
                    + "A.screeningAntiHiv, "
                    + "A.screeningAntiHcv, "
                    + "A.screeningVprl, "
                    + "B.aftapId \n"
                    + "FROM Screening AS A INNER JOIN Aftap AS B \n"
                    + "ON A.aftapId = B.aftapId";
            System.out.println(sql);

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Screening oScreening = new Screening();

                oScreening.setScreeningId(resultSet.getInt("screeningId"));

                Aftap oAftap;
                AftapDao aftapDao = ConnectionMySQL.getAftapDao();
                oAftap = aftapDao.selectAftapById(resultSet.getInt("aftapId"));

                oScreening.setAftap(oAftap);
                oScreening.setScreeningNo(resultSet.getString("screeningNo"));
                oScreening.setScreeningTanggal(resultSet.getString("screeningTanggal"));
                oScreening.setScreeningHbsag(resultSet.getString("screeningHbsag"));
                oScreening.setScreeningAntiHiv(resultSet.getString("screeningAntiHiv"));
                oScreening.setScreeningAntiHcv(resultSet.getString("screeningAntiHcv"));
                oScreening.setScreeningVprl(resultSet.getString("screeningVprl"));

                list.add(oScreening);
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
    public Screening getScreeningById(Integer id) throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        try {
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM screening "
                    + "WHERE screeningId = " + id + ";";

            System.out.println(sql);
            Screening oScreening = null;

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {

                oScreening = new Screening();

                oScreening.setScreeningId(resultSet.getInt("screeningId"));

                Aftap oAftap;
                AftapDao aftapDao = ConnectionMySQL.getAftapDao();
                oAftap = aftapDao.selectAftapById(resultSet.getInt("aftapId"));

                oScreening.setAftap(oAftap);
                oScreening.setScreeningNo(resultSet.getString("screeningNo"));
                oScreening.setScreeningTanggal(resultSet.getString("screeningTanggal"));
                oScreening.setScreeningHbsag(resultSet.getString("screeningHbsag"));
                oScreening.setScreeningAntiHiv(resultSet.getString("screeningAntiHiv"));
                oScreening.setScreeningAntiHcv(resultSet.getString("screeningAntiHcv"));
                oScreening.setScreeningVprl(resultSet.getString("screeningVprl"));

            }
            connection.setAutoCommit(false);
            connection.commit();
            return oScreening;
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
