/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.implementation;

import com.dao.implementation.*;
import com.dao.ComVolumeKantongDao;
import com.entity.ComVolumeKantong;
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
public class ComVolumeKantongImplementation implements ComVolumeKantongDao {

    private Connection connection;

    public ComVolumeKantongImplementation(Connection pConnection) {
        this.connection = pConnection;
    }

    @Override
    public void insert(ComVolumeKantong pComVolumeKantong) {
        PreparedStatement statement;
        statement = null;
        try {
            connection.setAutoCommit(false);

            String sql = "INSERT INTO comVolumeKantong SET comVolumeKantongName = ?, comVolumeKantongDesc = ?;";

            statement = connection.prepareStatement(sql);

            statement.setString(1, pComVolumeKantong.getComVolumeKantongName());
            statement.setString(2, pComVolumeKantong.getComVolumeKantongDesc());

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
                Logger.getLogger(ComVolumeKantongImplementation.class.getName()).log(Level.SEVERE, null, ex);
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
    public void update(ComVolumeKantong pComVolumeKantong) {
        
        PreparedStatement statement;
        statement = null;
        try {
            connection.setAutoCommit(false);

            String sql = "UPDATE comVolumeKantong SET comVolumeKantongName = ?, comVolumeKantongDesc = ? WHERE comVolumeKantongId = ?;";

            statement = connection.prepareStatement(sql);

            statement.setString(1, pComVolumeKantong.getComVolumeKantongName());
            statement.setString(2, pComVolumeKantong.getComVolumeKantongDesc());
            statement.setInt(3, pComVolumeKantong.getComVolumeKantongId());

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
                Logger.getLogger(ComVolumeKantongImplementation.class.getName()).log(Level.SEVERE, null, ex);
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
    public void delete(Integer pComVolumeKantongId) {
        PreparedStatement statement;
        statement = null;
        try {
            connection.setAutoCommit(false);

            String sql = "DELETE FROM comVolumeKantong WHERE comVolumeKantongId = ?";

            statement = connection.prepareStatement(sql);
            statement.setInt(1, pComVolumeKantongId);

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
                Logger.getLogger(ComVolumeKantongImplementation.class.getName()).log(Level.SEVERE, null, ex);
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
    public List<ComVolumeKantong> selectAll() throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        List<ComVolumeKantong> list;
        try {
            list = new ArrayList<>();
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM comVolumeKantong;";
            System.out.println(sql);

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ComVolumeKantong dokter = new ComVolumeKantong();

                dokter.setComVolumeKantongId(resultSet.getInt("comVolumeKantongId"));
                dokter.setComVolumeKantongName(resultSet.getString("comVolumeKantongName"));
                dokter.setComVolumeKantongDesc(resultSet.getString("comVolumeKantongDesc"));

                list.add(dokter);
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
    public ComVolumeKantong selectComVolumeKantongById(Integer pComVolumeKantongId) throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        try {
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM comVolumeKantong where comVolumeKantongId = " + pComVolumeKantongId + " ;";
            System.out.println(sql);

            ComVolumeKantong comVolumeKantong = null;

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                comVolumeKantong = new ComVolumeKantong();

                comVolumeKantong.setComVolumeKantongId(resultSet.getInt("comVolumeKantongId"));
                comVolumeKantong.setComVolumeKantongName(resultSet.getString("comVolumeKantongName"));
                comVolumeKantong.setComVolumeKantongDesc(resultSet.getString("comVolumeKantongDesc"));

            }

            connection.setAutoCommit(false);
            connection.commit();
            return comVolumeKantong;
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
    
    public Integer getNoOfRecords() throws Exception {

        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        Integer count = 0;
        try {
            connection.setAutoCommit(false);

            String sql = "SELECT COUNT(*) FROM comVolumeKantong;";
            System.out.println(sql);

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }

            connection.setAutoCommit(false);
            connection.commit();

            return count;
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
    public ComVolumeKantong getComVolumeKantongById(Integer id) throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        try {
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM comVolumeKantong "
                    + "WHERE comVolumeKantongId = " + id + ";";

            System.out.println(sql);

            ComVolumeKantong comVolumeKantong = null;

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                comVolumeKantong = new ComVolumeKantong();

                comVolumeKantong.setComVolumeKantongId(resultSet.getInt("comVolumeKantongId"));

//                Category category;
//                CategoryDao categoryDao = ConnectionMySQL.getCategoryDao();
//                category = categoryDao.selectCategotyById(resultSet.getInt("id_cat"));

//                comVolumeKantong.setCategory(category);
                comVolumeKantong.setComVolumeKantongName(resultSet.getString("comVolumeKantongName"));
                comVolumeKantong.setComVolumeKantongDesc(resultSet.getString("comVolumeKantongDesc"));

            }
            connection.setAutoCommit(false);
            connection.commit();
            return comVolumeKantong;
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
