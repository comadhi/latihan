/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.implementation;

import com.dao.implementation.*;
import com.dao.ComKomponenDarahDao;
import com.entity.ComKomponenDarah;
import com.entity.ComKomponenDarah;
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
public class ComKomponenDarahImplementation implements ComKomponenDarahDao {

    private Connection connection;

    public ComKomponenDarahImplementation(Connection pConnection) {
        this.connection = pConnection;
    }

    @Override
    public void insert(ComKomponenDarah pComKomponenDarah) {
        PreparedStatement statement;
        statement = null;
        try {
            connection.setAutoCommit(false);

            String sql = "INSERT INTO comKomponenDarah SET comKomponenDarahName = ?, comKomponenDarahDesc = ?;";

            statement = connection.prepareStatement(sql);

            statement.setString(1, pComKomponenDarah.getComKomponenDarahName());
            statement.setString(2, pComKomponenDarah.getComKomponenDarahDesc());

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
                Logger.getLogger(ComKomponenDarahImplementation.class.getName()).log(Level.SEVERE, null, ex);
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
    public void update(ComKomponenDarah pComKomponenDarah) {
        
        PreparedStatement statement;
        statement = null;
        try {
            connection.setAutoCommit(false);

            String sql = "UPDATE comKomponenDarah SET comKomponenDarahName = ?, comKomponenDarahDesc = ? WHERE comKomponenDarahId = ?;";

            statement = connection.prepareStatement(sql);

            statement.setString(1, pComKomponenDarah.getComKomponenDarahName());
            statement.setString(2, pComKomponenDarah.getComKomponenDarahDesc());
            statement.setInt(3, pComKomponenDarah.getComKomponenDarahId());

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
                Logger.getLogger(ComKomponenDarahImplementation.class.getName()).log(Level.SEVERE, null, ex);
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
    public void delete(Integer pComKomponenDarahId) {
        PreparedStatement statement;
        statement = null;
        try {
            connection.setAutoCommit(false);

            String sql = "DELETE FROM comKomponenDarah WHERE comKomponenDarahId = ?";

            statement = connection.prepareStatement(sql);
            statement.setInt(1, pComKomponenDarahId);

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
                Logger.getLogger(ComKomponenDarahImplementation.class.getName()).log(Level.SEVERE, null, ex);
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
    public List<ComKomponenDarah> selectAll() throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        List<ComKomponenDarah> list;
        try {
            list = new ArrayList<>();
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM comKomponenDarah;";
            System.out.println(sql);

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ComKomponenDarah dokter = new ComKomponenDarah();

                dokter.setComKomponenDarahId(resultSet.getInt("comKomponenDarahId"));
                dokter.setComKomponenDarahName(resultSet.getString("comKomponenDarahName"));
                dokter.setComKomponenDarahDesc(resultSet.getString("comKomponenDarahDesc"));

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
    public ComKomponenDarah selectComKomponenDarahById(Integer pComKomponenDarahId) throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        try {
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM comKomponenDarah where comKomponenDarahId = " + pComKomponenDarahId + " ;";
            System.out.println(sql);

            ComKomponenDarah comKomponenDarah = null;

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                comKomponenDarah = new ComKomponenDarah();

                comKomponenDarah.setComKomponenDarahId(resultSet.getInt("comKomponenDarahId"));
                comKomponenDarah.setComKomponenDarahName(resultSet.getString("comKomponenDarahName"));
                comKomponenDarah.setComKomponenDarahDesc(resultSet.getString("comKomponenDarahDesc"));

            }

            connection.setAutoCommit(false);
            connection.commit();
            return comKomponenDarah;
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

            String sql = "SELECT COUNT(*) FROM comKomponenDarah;";
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
    public ComKomponenDarah getComKomponenDarahById(Integer id) throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        try {
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM comKomponenDarah "
                    + "WHERE comKomponenDarahId = " + id + ";";

            System.out.println(sql);

            ComKomponenDarah comKomponenDarah = null;

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                comKomponenDarah = new ComKomponenDarah();

                comKomponenDarah.setComKomponenDarahId(resultSet.getInt("comKomponenDarahId"));

//                Category category;
//                CategoryDao categoryDao = ConnectionMySQL.getCategoryDao();
//                category = categoryDao.selectCategotyById(resultSet.getInt("id_cat"));

//                comKomponenDarah.setCategory(category);
                comKomponenDarah.setComKomponenDarahName(resultSet.getString("comKomponenDarahName"));
                comKomponenDarah.setComKomponenDarahDesc(resultSet.getString("comKomponenDarahDesc"));

            }
            connection.setAutoCommit(false);
            connection.commit();
            return comKomponenDarah;
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
