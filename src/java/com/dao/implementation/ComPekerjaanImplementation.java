/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.implementation;

import com.dao.implementation.*;
import com.dao.ComPekerjaanDao;
import com.entity.ComPekerjaan;
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
public class ComPekerjaanImplementation implements ComPekerjaanDao {

    private Connection connection;

    public ComPekerjaanImplementation(Connection pConnection) {
        this.connection = pConnection;
    }

    @Override
    public void insert(ComPekerjaan pComPekerjaan) {
        PreparedStatement statement;
        statement = null;
        try {
            connection.setAutoCommit(false);

            String sql = "INSERT INTO comPekerjaan SET comPekerjaanName = ?, comPekerjaanDesc = ?;";

            statement = connection.prepareStatement(sql);

            statement.setString(1, pComPekerjaan.getComPekerjaanName());
            statement.setString(2, pComPekerjaan.getComPekerjaanDesc());

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
                Logger.getLogger(ComPekerjaanImplementation.class.getName()).log(Level.SEVERE, null, ex);
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
    public void update(ComPekerjaan pComPekerjaan) {
        
        PreparedStatement statement;
        statement = null;
        try {
            connection.setAutoCommit(false);

            String sql = "UPDATE comPekerjaan SET comPekerjaanName = ?, comPekerjaanDesc = ? WHERE comPekerjaanId = ?;";

            statement = connection.prepareStatement(sql);

            statement.setString(1, pComPekerjaan.getComPekerjaanName());
            statement.setString(2, pComPekerjaan.getComPekerjaanDesc());
            statement.setInt(3, pComPekerjaan.getComPekerjaanId());

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
                Logger.getLogger(ComPekerjaanImplementation.class.getName()).log(Level.SEVERE, null, ex);
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
    public void delete(Integer pComPekerjaanId) {
        PreparedStatement statement;
        statement = null;
        try {
            connection.setAutoCommit(false);

            String sql = "DELETE FROM comPekerjaan WHERE comPekerjaanId = ?";

            statement = connection.prepareStatement(sql);
            statement.setInt(1, pComPekerjaanId);

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
                Logger.getLogger(ComPekerjaanImplementation.class.getName()).log(Level.SEVERE, null, ex);
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
    public List<ComPekerjaan> selectAll() throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        List<ComPekerjaan> list;
        try {
            list = new ArrayList<>();
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM comPekerjaan;";
            System.out.println(sql);

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ComPekerjaan dokter = new ComPekerjaan();

                dokter.setComPekerjaanId(resultSet.getInt("comPekerjaanId"));
                dokter.setComPekerjaanName(resultSet.getString("comPekerjaanName"));
                dokter.setComPekerjaanDesc(resultSet.getString("comPekerjaanDesc"));

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
    public ComPekerjaan selectComPekerjaanById(Integer pComPekerjaanId) throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        try {
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM comPekerjaan where comPekerjaanId = " + pComPekerjaanId + " ;";
            System.out.println(sql);

            ComPekerjaan comPekerjaan = null;

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                comPekerjaan = new ComPekerjaan();

                comPekerjaan.setComPekerjaanId(resultSet.getInt("comPekerjaanId"));
                comPekerjaan.setComPekerjaanName(resultSet.getString("comPekerjaanName"));
                comPekerjaan.setComPekerjaanDesc(resultSet.getString("comPekerjaanDesc"));

            }

            connection.setAutoCommit(false);
            connection.commit();
            return comPekerjaan;
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

            String sql = "SELECT COUNT(*) FROM comPekerjaan;";
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
    public ComPekerjaan getComPekerjaanById(Integer id) throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        try {
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM comPekerjaan "
                    + "WHERE comPekerjaanId = " + id + ";";

            System.out.println(sql);

            ComPekerjaan comPekerjaan = null;

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                comPekerjaan = new ComPekerjaan();

                comPekerjaan.setComPekerjaanId(resultSet.getInt("comPekerjaanId"));

//                Category category;
//                CategoryDao categoryDao = ConnectionMySQL.getCategoryDao();
//                category = categoryDao.selectCategotyById(resultSet.getInt("id_cat"));

//                comPekerjaan.setCategory(category);
                comPekerjaan.setComPekerjaanName(resultSet.getString("comPekerjaanName"));
                comPekerjaan.setComPekerjaanDesc(resultSet.getString("comPekerjaanDesc"));

            }
            connection.setAutoCommit(false);
            connection.commit();
            return comPekerjaan;
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
