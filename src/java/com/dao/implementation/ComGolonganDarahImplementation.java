/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.implementation;

import com.dao.implementation.*;
import com.dao.ComGolonganDarahDao;
import com.entity.ComGolonganDarah;
import com.entity.ComGolonganDarah;
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
public class ComGolonganDarahImplementation implements ComGolonganDarahDao {

    private Connection connection;

    public ComGolonganDarahImplementation(Connection pConnection) {
        this.connection = pConnection;
    }

    @Override
    public void insert(ComGolonganDarah pComGolonganDarah) {
        PreparedStatement statement;
        statement = null;
        try {
            connection.setAutoCommit(false);

            String sql = "INSERT INTO comGolonganDarah SET comGolonganDarahName = ?, comGolonganDarahDesc = ?;";

            statement = connection.prepareStatement(sql);

            statement.setString(1, pComGolonganDarah.getComGolonganDarahName());
            statement.setString(2, pComGolonganDarah.getComGolonganDarahDesc());

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
                Logger.getLogger(ComGolonganDarahImplementation.class.getName()).log(Level.SEVERE, null, ex);
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
    public void update(ComGolonganDarah pComGolonganDarah) {
        
        PreparedStatement statement;
        statement = null;
        try {
            connection.setAutoCommit(false);

            String sql = "UPDATE comGolonganDarah SET comGolonganDarahName = ?, comGolonganDarahDesc = ? WHERE comGolonganDarahId = ?;";

            statement = connection.prepareStatement(sql);

            statement.setString(1, pComGolonganDarah.getComGolonganDarahName());
            statement.setString(2, pComGolonganDarah.getComGolonganDarahDesc());
            statement.setInt(3, pComGolonganDarah.getComGolonganDarahId());

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
                Logger.getLogger(ComGolonganDarahImplementation.class.getName()).log(Level.SEVERE, null, ex);
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
    public void delete(Integer pComGolonganDarahId) {
        PreparedStatement statement;
        statement = null;
        try {
            connection.setAutoCommit(false);

            String sql = "DELETE FROM comGolonganDarah WHERE comGolonganDarahId = ?";

            statement = connection.prepareStatement(sql);
            statement.setInt(1, pComGolonganDarahId);

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
                Logger.getLogger(ComGolonganDarahImplementation.class.getName()).log(Level.SEVERE, null, ex);
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
    public List<ComGolonganDarah> selectAll() throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        List<ComGolonganDarah> list;
        try {
            list = new ArrayList<>();
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM comGolonganDarah;";
            System.out.println(sql);

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ComGolonganDarah dokter = new ComGolonganDarah();

                dokter.setComGolonganDarahId(resultSet.getInt("comGolonganDarahId"));
                dokter.setComGolonganDarahName(resultSet.getString("comGolonganDarahName"));
                dokter.setComGolonganDarahDesc(resultSet.getString("comGolonganDarahDesc"));

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
    public ComGolonganDarah selectComGolonganDarahById(Integer pComGolonganDarahId) throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        try {
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM comGolonganDarah where comGolonganDarahId = " + pComGolonganDarahId + " ;";
            System.out.println(sql);

            ComGolonganDarah comGolonganDarah = null;

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                comGolonganDarah = new ComGolonganDarah();

                comGolonganDarah.setComGolonganDarahId(resultSet.getInt("comGolonganDarahId"));
                comGolonganDarah.setComGolonganDarahName(resultSet.getString("comGolonganDarahName"));
                comGolonganDarah.setComGolonganDarahDesc(resultSet.getString("comGolonganDarahDesc"));

            }

            connection.setAutoCommit(false);
            connection.commit();
            return comGolonganDarah;
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

            String sql = "SELECT COUNT(*) FROM comGolonganDarah;";
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
    public ComGolonganDarah getComGolonganDarahById(Integer id) throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        try {
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM comGolonganDarah "
                    + "WHERE comGolonganDarahId = " + id + ";";

            System.out.println(sql);

            ComGolonganDarah comGolonganDarah = null;

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                comGolonganDarah = new ComGolonganDarah();

                comGolonganDarah.setComGolonganDarahId(resultSet.getInt("comGolonganDarahId"));

//                Category category;
//                CategoryDao categoryDao = ConnectionMySQL.getCategoryDao();
//                category = categoryDao.selectCategotyById(resultSet.getInt("id_cat"));

//                comGolonganDarah.setCategory(category);
                comGolonganDarah.setComGolonganDarahName(resultSet.getString("comGolonganDarahName"));
                comGolonganDarah.setComGolonganDarahDesc(resultSet.getString("comGolonganDarahDesc"));

            }
            connection.setAutoCommit(false);
            connection.commit();
            return comGolonganDarah;
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
