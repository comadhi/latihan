/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.implementation;

import dao.StaffPmiDao;
import entity.StaffPmi;
import entity.StaffPmi;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class StaffPmiImplementation implements StaffPmiDao {

    private Connection connection;

    public StaffPmiImplementation(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void Insert(StaffPmi staffPmi) throws Exception {
        PreparedStatement statement;
        statement = null;
        try {
            connection.setAutoCommit(false);

            String sql = "INSERT INTO staffPmi SET id_staffPmi = null, nik = ?, nama = ?, alamat = ?, telp = ?, bagian = ?, lokasi = ?, ext = ?, password = ? ;";

            statement = connection.prepareStatement(sql);
            statement.setString(2, staffPmi.getStaffPmiName());
            statement.setString(3, staffPmi.getStaffPmiAddress());
            statement.setString(4, staffPmi.getStaffPmiTelp());
            statement.setString(8, staffPmi.getStaffPmiPassword());

            System.out.println(statement.toString());
            statement.executeUpdate();
            connection.commit();
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
        }
    }

    @Override
    public void Update(StaffPmi staffPmi) throws Exception {
        PreparedStatement statement;
        statement = null;
        try {
            connection.setAutoCommit(false);

            String sql = "UPDATE staffPmi SET nik = ?, nama = ?, alamat = ?, telp = ?, bagian = ?, lokasi = ?, ext = ?, password = ? WHERE id_staffPmi = ?;";

            statement = connection.prepareStatement(sql);
            statement.setString(2, staffPmi.getStaffPmiName());
            statement.setString(3, staffPmi.getStaffPmiAddress());
            statement.setString(4, staffPmi.getStaffPmiTelp());
            statement.setString(8, staffPmi.getStaffPmiPassword());
            statement.setInt(9, staffPmi.getStaffPmiId());

            System.out.println(statement.toString());
            statement.executeUpdate();
            connection.commit();
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
        }

    }

    @Override
    public void Delete(Integer idStaffPmi) throws Exception {
        PreparedStatement statement;
        statement = null;
        try {
            connection.setAutoCommit(false);

            String sql = "DELETE FROM staffPmi WHERE id_staffPmi = ?";

            statement = connection.prepareStatement(sql);
            statement.setInt(1, idStaffPmi);

            System.out.println(statement.toString());
            statement.executeUpdate();
            connection.commit();
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
        }

    }

    @Override
    public List<StaffPmi> selectAll() throws Exception {
        PreparedStatement statement;
//        CallableStatement callableStatement;
        
        ResultSet resultSet;
        statement = null;
//        callableStatement = null;
        resultSet = null;
        List<StaffPmi> list;
        try {
            list = new ArrayList<>();
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM staffPmi;";
            System.out.println(sql);

            statement = connection.prepareStatement(sql);
//            callableStatement = connection.prepareCall(sql);
            resultSet = statement.executeQuery();
//            resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                StaffPmi staffPmi = new StaffPmi();

                staffPmi.setStaffPmiId(resultSet.getInt("staffPmiId"));
                staffPmi.setStaffPmiName(resultSet.getString("staffPmiName"));
                staffPmi.setStaffPmiAddress(resultSet.getString("staffPmiAddress"));
                staffPmi.setStaffPmiTelp(resultSet.getString("staffPmiTelp"));

                list.add(staffPmi);
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
    public StaffPmi getStaffPmiById(Integer idStaffPmi) throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        try {
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM staffPmi where id_staffPmi = " + idStaffPmi + " ;";
            System.out.println(sql);

            StaffPmi staffPmi = null;

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                staffPmi = new StaffPmi();

                staffPmi.setStaffPmiId(resultSet.getInt("staffPmiId"));
                staffPmi.setStaffPmiName(resultSet.getString("staffPmiName"));
                staffPmi.setStaffPmiAddress(resultSet.getString("staffPmiAddress"));
                staffPmi.setStaffPmiTelp(resultSet.getString("staffPmiTelp"));
                //staffPmi.setStaffPmiPassword(AES.decrypt(resultSet.getString("password")));

            }

            connection.setAutoCommit(false);
            connection.commit();
            return staffPmi;
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
    public StaffPmi getStaffPmiByNameAndPass(String Name, String Password) throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;

        try {
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM staffPmi where staffPmiUsername = '" + Name + "' and staffPmiPassword = '" + Password + "' ;";
            System.out.println(sql);

            StaffPmi staffPmi = null;

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                staffPmi = new StaffPmi();

                staffPmi.setStaffPmiId(resultSet.getInt("staffPmiId"));
                staffPmi.setStaffPmiName(resultSet.getString("staffPmiName"));
                staffPmi.setStaffPmiAddress(resultSet.getString("staffPmiAddress"));
                staffPmi.setStaffPmiTelp(resultSet.getString("staffPmiTelp"));
                staffPmi.setStaffPmiUsername(resultSet.getString("staffPmiUsername"));
                staffPmi.setStaffPmiPassword(resultSet.getString("staffPmiPassword"));

            }

            connection.setAutoCommit(false);
            connection.commit();
            return staffPmi;
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
    public List<StaffPmi> selectAllWithLimit(int offset, int noOfRecords) throws Exception {

        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        List<StaffPmi> list;
        try {
            list = new ArrayList<>();
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM staffPmi LIMIT " + offset + "," + noOfRecords + ";";
            System.out.println(sql);

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                StaffPmi staffPmi = new StaffPmi();

                staffPmi.setStaffPmiId(resultSet.getInt("staffPmiId"));
                staffPmi.setStaffPmiName(resultSet.getString("staffPmiName"));
                staffPmi.setStaffPmiAddress(resultSet.getString("staffPmiAddress"));
                staffPmi.setStaffPmiTelp(resultSet.getString("staffPmiTelp"));
                staffPmi.setStaffPmiUsername(resultSet.getString("staffPmiUsername"));
                staffPmi.setStaffPmiPassword(resultSet.getString("staffPmiPassword"));
                
                //staffPmi.setStaffPmiPassword(AES.decrypt(resultSet.getString("password")));

                list.add(staffPmi);
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
    public Integer getNoOfRecords() throws Exception {

        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        Integer count = 0;
        try {
            connection.setAutoCommit(false);

            String sql = "SELECT COUNT(*) FROM staffPmi;";
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

}
