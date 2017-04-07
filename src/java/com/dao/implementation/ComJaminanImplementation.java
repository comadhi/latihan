/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.implementation;

import com.dao.implementation.*;
import com.dao.ComJaminanDao;
import com.entity.ComJaminan;
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
public class ComJaminanImplementation implements ComJaminanDao {

    private Connection connection;

    public ComJaminanImplementation(Connection pConnection) {
        this.connection = pConnection;
    }

    @Override
    public void insert(ComJaminan pComJaminan) {
        PreparedStatement statement;
        statement = null;
        try {
            connection.setAutoCommit(false);

            String sql = "INSERT INTO comJaminan SET comJaminanName = ?, comJaminanDesc = ?;";

            statement = connection.prepareStatement(sql);

            statement.setString(1, pComJaminan.getComJaminanName());
            statement.setString(2, pComJaminan.getComJaminanDesc());

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
                Logger.getLogger(ComJaminanImplementation.class.getName()).log(Level.SEVERE, null, ex);
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
    public void update(ComJaminan pComJaminan) {
        
        PreparedStatement statement;
        statement = null;
        try {
            connection.setAutoCommit(false);

            String sql = "UPDATE comJaminan SET comJaminanName = ?, comJaminanDesc = ? WHERE comJaminanId = ?;";

            statement = connection.prepareStatement(sql);

            statement.setString(1, pComJaminan.getComJaminanName());
            statement.setString(2, pComJaminan.getComJaminanDesc());
            statement.setInt(3, pComJaminan.getComJaminanId());

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
                Logger.getLogger(ComJaminanImplementation.class.getName()).log(Level.SEVERE, null, ex);
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
    public void delete(Integer pComJaminanId) {
        PreparedStatement statement;
        statement = null;
        try {
            connection.setAutoCommit(false);

            String sql = "DELETE FROM comJaminan WHERE comJaminanId = ?";

            statement = connection.prepareStatement(sql);
            statement.setInt(1, pComJaminanId);

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
                Logger.getLogger(ComJaminanImplementation.class.getName()).log(Level.SEVERE, null, ex);
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
    public List<ComJaminan> selectAll() throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        List<ComJaminan> list;
        try {
            list = new ArrayList<>();
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM comJaminan;";
            System.out.println(sql);

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ComJaminan dokter = new ComJaminan();

                dokter.setComJaminanId(resultSet.getInt("comJaminanId"));
                dokter.setComJaminanName(resultSet.getString("comJaminanName"));
                dokter.setComJaminanDesc(resultSet.getString("comJaminanDesc"));

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
    
    public Integer getNoOfRecords() throws Exception {

        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        Integer count = 0;
        try {
            connection.setAutoCommit(false);

            String sql = "SELECT COUNT(*) FROM comJaminan;";
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
    public ComJaminan getComJaminanById(Integer id) throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        try {
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM comJaminan "
                    + "WHERE comJaminanId = " + id + ";";

            System.out.println(sql);

            ComJaminan comJaminan = null;

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                comJaminan = new ComJaminan();

                comJaminan.setComJaminanId(resultSet.getInt("comJaminanId"));

//                Category category;
//                CategoryDao categoryDao = ConnectionMySQL.getCategoryDao();
//                category = categoryDao.selectCategotyById(resultSet.getInt("id_cat"));

//                comJaminan.setCategory(category);
                comJaminan.setComJaminanName(resultSet.getString("comJaminanName"));
                comJaminan.setComJaminanDesc(resultSet.getString("comJaminanDesc"));

            }
            connection.setAutoCommit(false);
            connection.commit();
            return comJaminan;
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
