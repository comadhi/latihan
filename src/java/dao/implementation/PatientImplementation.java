/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.implementation;

import com.dao.ComGolonganDarahDao;
import com.entity.ComGolonganDarah;
import dao.PatientDao;
import entity.Hospital;
import entity.Patient;
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
public class PatientImplementation implements PatientDao {

    private Connection connection;

    public PatientImplementation(Connection pConnection) {
        this.connection = pConnection;
    }

    @Override
    public void insert(Patient pPatient) {
        PreparedStatement statement;
        statement = null;
        try {
            connection.setAutoCommit(false);

            String sql = "INSERT INTO patient SET "
                    + "patientNo = ?, "
                    + "patientName = ?, "
                    + "patientAddress = ?, "
                    + "comGolonganDarahId = ? "
                    + ";";

            statement = connection.prepareStatement(sql);
            
            

            statement.setString(1, pPatient.getPatientNo());
            statement.setString(2, pPatient.getPatientName());
            statement.setString(3, pPatient.getPatientAddress());
            statement.setInt(4, pPatient.getComGolonganDarah().getComGolonganDarahId());

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
                Logger.getLogger(PatientImplementation.class.getName()).log(Level.SEVERE, null, ex);
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
    public void update(Patient pPatient) {
        
        PreparedStatement statement;
        statement = null;
        try {
            connection.setAutoCommit(false);

            String sql = "UPDATE patient SET patientName = ?, patientAddress = ?, patientTelp = ? WHERE patientId = ?;";

            statement = connection.prepareStatement(sql);

            statement.setString(1, pPatient.getPatientNo());
            statement.setString(2, pPatient.getPatientName());
            statement.setString(3, pPatient.getPatientAddress());
            statement.setInt(4, pPatient.getPatientId());

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
                Logger.getLogger(PatientImplementation.class.getName()).log(Level.SEVERE, null, ex);
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
    public void delete(Integer pPatientId) {
        PreparedStatement statement;
        statement = null;
        try {
            connection.setAutoCommit(false);

            String sql = "DELETE FROM patient WHERE patientId = ?";

            statement = connection.prepareStatement(sql);
            statement.setInt(1, pPatientId);

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
                Logger.getLogger(PatientImplementation.class.getName()).log(Level.SEVERE, null, ex);
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
    public List<Patient> selectAll() throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        List<Patient> list;
        try {
            list = new ArrayList<>();
            connection.setAutoCommit(false);

            String sql = "SELECT * "
                    + "FROM patient p INNER JOIN comGolonganDarah cgd ON p.comGolonganDarahId = cgd.comGolonganDarahId"
                    + ";";
            System.out.println(sql);

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Patient patient = new Patient();

                ComGolonganDarah oComGolonganDarah;
                ComGolonganDarahDao comGolonganDarahDao = ConnectionMySQL.getComGolonganDarahDao();
                oComGolonganDarah = comGolonganDarahDao.selectComGolonganDarahById(resultSet.getInt("comGolonganDarahId"));
                
                patient.setComGolonganDarah(oComGolonganDarah);
                patient.setPatientId(resultSet.getInt("patientId"));
                patient.setPatientNo(resultSet.getString("patientNo"));
                patient.setPatientName(resultSet.getString("patientName"));
                patient.setPatientAddress(resultSet.getString("patientAddress"));

                list.add(patient);
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
    public Patient selectPatientById(Integer pPatientId) throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        try {
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM patient where patientId = " + pPatientId + " ;";
            System.out.println(sql);

            Patient oPatient = null;

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                oPatient = new Patient();

                oPatient.setPatientId(resultSet.getInt("patientId"));
                oPatient.setPatientNo(resultSet.getString("patientNo"));
                oPatient.setPatientName(resultSet.getString("patientName"));
                oPatient.setPatientAddress(resultSet.getString("patientAddress"));

            }

            connection.setAutoCommit(false);
            connection.commit();
            return oPatient;
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
    
    public Integer getNoOfRecords() throws Exception {

        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        Integer count = 0;
        try {
            connection.setAutoCommit(false);

            String sql = "SELECT COUNT(*) FROM patient;";
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
    public Patient getPatientById(Integer id) throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        try {
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM patient "
                    + "WHERE patientId = " + id + ";";

            System.out.println(sql);

            Patient patient = null;

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                patient = new Patient();

                patient.setPatientId(resultSet.getInt("patientId"));

//                Category category;
//                CategoryDao categoryDao = ConnectionMySQL.getCategoryDao();
//                category = categoryDao.selectCategotyById(resultSet.getInt("id_cat"));

//                patient.setCategory(category);
                patient.setPatientName(resultSet.getString("patientName"));
                patient.setPatientAddress(resultSet.getString("patientAddress"));
                patient.setPatientNo(resultSet.getString("patientNo"));

            }
            connection.setAutoCommit(false);
            connection.commit();
            return patient;
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
