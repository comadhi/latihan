/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.implementation;

import dao.StaffHospitalDao;
import entity.Docter;
import entity.StaffHospital;
import entity.StaffHospital;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author heri
 */
public class StaffHospitalImplementation implements StaffHospitalDao {

    private Connection connection;

    public StaffHospitalImplementation(Connection pConnection) {
        this.connection = pConnection;
    }

    @Override
    public void insert(StaffHospital pStaffHospital) {
        PreparedStatement statement;
        statement = null;
        try {
            connection.setAutoCommit(false);

            String sql = "INSERT INTO staffHospital SET staffHospitalName = ?, staffHospitalAddress = ?, staffHospitalTelp=?;";

            statement = connection.prepareStatement(sql);

            statement.setString(1, pStaffHospital.getStaffHospitalName());
            statement.setString(2, pStaffHospital.getStaffHospitalAddress());
            statement.setString(3, pStaffHospital.getStaffHospitalTelp());

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
                Logger.getLogger(StaffHospitalImplementation.class.getName()).log(Level.SEVERE, null, ex);
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
    public void update(StaffHospital pStaffHospital) {
        PreparedStatement statement;
        statement = null;
        try {
            connection.setAutoCommit(false);

            String sql = "UPDATE staffHospital SET staffHospitalName = ?, staffHospitalAddress = ?, staffHospitalTelp = ? WHERE staffHospitalId = ?;";

            statement = connection.prepareStatement(sql);

            statement.setString(1, pStaffHospital.getStaffHospitalName());
            statement.setString(2, pStaffHospital.getStaffHospitalAddress());
            statement.setString(3, pStaffHospital.getStaffHospitalTelp());
            statement.setInt(4, pStaffHospital.getStaffHospitalId());

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
                Logger.getLogger(StaffHospitalImplementation.class.getName()).log(Level.SEVERE, null, ex);
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
    public void delete(Integer pStaffHospitalId) {
        PreparedStatement statement;
        statement = null;
        try {
            connection.setAutoCommit(false);

            String sql = "DELETE FROM staffHospital WHERE staffHospitalId = ?";

            statement = connection.prepareStatement(sql);
            statement.setInt(1, pStaffHospitalId);

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
                Logger.getLogger(DocterImplementation.class.getName()).log(Level.SEVERE, null, ex);
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
    public List<StaffHospital> selectAll() throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        List<StaffHospital> list;
        try {
            list = new ArrayList<>();
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM staffHospital;";
            System.out.println(sql);

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                StaffHospital staffHospital = new StaffHospital();

                staffHospital.setStaffHospitalId(resultSet.getInt("staffHospitalId"));
                staffHospital.setStaffHospitalName(resultSet.getString("staffHospitalName"));
                staffHospital.setStaffHospitalAddress(resultSet.getString("staffHospitalAddress"));

                list.add(staffHospital);
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
    public List<StaffHospital> selectAllWithLimit(int offset, int noOfRecords) throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        List<StaffHospital> list;
        try {
            list = new ArrayList<>();
            connection.setAutoCommit(false);

            String sql = "SELECT * \n"
                    + "FROM staffHospital ORDER BY staffHospitalId LIMIT " + offset + "," + noOfRecords + ";";

            System.out.println(sql);

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                StaffHospital staffHospital = new StaffHospital();

                staffHospital.setStaffHospitalId(resultSet.getInt("staffHospitalId"));
                staffHospital.setStaffHospitalName(resultSet.getString("staffHospitalName"));
                staffHospital.setStaffHospitalAddress(resultSet.getString("staffHospitalAddress"));

                list.add(staffHospital);
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

            String sql = "SELECT COUNT(*) FROM staffHospital;";
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
    public StaffHospital getStaffHospitalById(Integer id) throws Exception {
        
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        try {
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM staffHospital "
                    + "WHERE staffHospitalId = " + id + ";";

            System.out.println(sql);

            StaffHospital staffHospital = null;

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                staffHospital = new StaffHospital();

                staffHospital.setStaffHospitalId(resultSet.getInt("staffHospitalId"));

//                Category category;
//                CategoryDao categoryDao = ConnectionMySQL.getCategoryDao();
//                category = categoryDao.selectCategotyById(resultSet.getInt("id_cat"));

//                staffHospital.setCategory(category);
                staffHospital.setStaffHospitalName(resultSet.getString("staffHospitalName"));
                staffHospital.setStaffHospitalAddress(resultSet.getString("staffHospitalAddress"));
                staffHospital.setStaffHospitalTelp(resultSet.getString("staffHospitalTelp"));

            }
            connection.setAutoCommit(false);
            connection.commit();
            return staffHospital;
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
