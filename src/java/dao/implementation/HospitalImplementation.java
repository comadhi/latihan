/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.implementation;

import dao.HospitalDao;
import entity.Hospital;
import entity.Hospital;
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
public class HospitalImplementation implements HospitalDao {

    private Connection connection;

    public HospitalImplementation(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void Insert(Hospital hospital) throws Exception {
        PreparedStatement statement;
        statement = null;
        try {
            connection.setAutoCommit(false);

            String sql = "INSERT INTO hospital SET id_hospital = null, nik = ?, nama = ?, alamat = ?, telp = ?, bagian = ?, lokasi = ?, ext = ?, password = ? ;";

            statement = connection.prepareStatement(sql);
            statement.setString(2, hospital.getHospitalName());
            statement.setString(3, hospital.getHospitalAlamat());
            statement.setString(4, hospital.getHospitalTelp());
            statement.setString(6, hospital.getHospitalLokasi());
            statement.setString(8, hospital.getHospitalPassword());

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
    public void Update(Hospital hospital) throws Exception {
        PreparedStatement statement;
        statement = null;
        try {
            connection.setAutoCommit(false);

            String sql = "UPDATE hospital SET nik = ?, nama = ?, alamat = ?, telp = ?, bagian = ?, lokasi = ?, ext = ?, password = ? WHERE id_hospital = ?;";

            statement = connection.prepareStatement(sql);
            statement.setString(2, hospital.getHospitalName());
            statement.setString(3, hospital.getHospitalAlamat());
            statement.setString(4, hospital.getHospitalTelp());
            statement.setString(6, hospital.getHospitalLokasi());
            statement.setString(8, hospital.getHospitalPassword());
            statement.setInt(9, hospital.getHospitalId());

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
    public void Delete(Integer idHospital) throws Exception {
        PreparedStatement statement;
        statement = null;
        try {
            connection.setAutoCommit(false);

            String sql = "DELETE FROM hospital WHERE id_hospital = ?";

            statement = connection.prepareStatement(sql);
            statement.setInt(1, idHospital);

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
    public List<Hospital> selectAll() throws Exception {
        PreparedStatement statement;
//        CallableStatement callableStatement;
        
        ResultSet resultSet;
        statement = null;
//        callableStatement = null;
        resultSet = null;
        List<Hospital> list;
        try {
            list = new ArrayList<>();
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM hospital;";
            System.out.println(sql);

            statement = connection.prepareStatement(sql);
//            callableStatement = connection.prepareCall(sql);
            resultSet = statement.executeQuery();
//            resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                Hospital hospital = new Hospital();

                hospital.setHospitalId(resultSet.getInt("hospitalId"));
                hospital.setHospitalName(resultSet.getString("hospitalName"));
                hospital.setHospitalAlamat(resultSet.getString("hospitalAlamat"));
                hospital.setHospitalTelp(resultSet.getString("hospitalTelp"));
                hospital.setHospitalLokasi(resultSet.getString("hospitalLokasi"));
                //hospital.setHospitalPassword(AES.decrypt(resultSet.getString("password")));

                list.add(hospital);
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
    public Hospital selectHospitalById(Integer pHospitalId) throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        try {
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM hospital where hospitalId = " + pHospitalId + " ;";
            System.out.println(sql);

            Hospital oHospital = null;

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                oHospital = new Hospital();

                oHospital.setHospitalId(resultSet.getInt("hospitalId"));
                oHospital.setHospitalName(resultSet.getString("hospitalName"));

            }

            connection.setAutoCommit(false);
            connection.commit();
            return oHospital;
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

    @Override
    public Hospital getHospitalById(Integer idHospital) throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        try {
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM hospital where id_hospital = " + idHospital + " ;";
            System.out.println(sql);

            Hospital hospital = null;

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                hospital = new Hospital();

                hospital.setHospitalId(resultSet.getInt("hospitalId"));
                hospital.setHospitalName(resultSet.getString("hospitalName"));
                hospital.setHospitalAlamat(resultSet.getString("hospitalAlamat"));
                hospital.setHospitalTelp(resultSet.getString("hospitalTelp"));
                hospital.setHospitalLokasi(resultSet.getString("hospitalLokasi"));
                //hospital.setHospitalPassword(AES.decrypt(resultSet.getString("password")));

            }

            connection.setAutoCommit(false);
            connection.commit();
            return hospital;
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
    public Hospital getHospitalByNameAndPass(String Name, String Password) throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;

        try {
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM hospital where hospitalUsername = '" + Name + "' and hospitalPassword = '" + Password + "' ;";
            System.out.println(sql);

            Hospital hospital = null;

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                hospital = new Hospital();

                hospital.setHospitalId(resultSet.getInt("hospitalId"));
                hospital.setHospitalName(resultSet.getString("hospitalName"));
                hospital.setHospitalAlamat(resultSet.getString("hospitalAlamat"));
                hospital.setHospitalTelp(resultSet.getString("hospitalTelp"));
                hospital.setHospitalLokasi(resultSet.getString("hospitalLokasi"));
                hospital.setHospitalUsername(resultSet.getString("hospitalUsername"));
                hospital.setHospitalPassword(resultSet.getString("hospitalPassword"));

            }

            connection.setAutoCommit(false);
            connection.commit();
            return hospital;
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
    public List<Hospital> selectAllWithLimit(int offset, int noOfRecords) throws Exception {

        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        List<Hospital> list;
        try {
            list = new ArrayList<>();
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM hospital LIMIT " + offset + "," + noOfRecords + ";";
            System.out.println(sql);

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Hospital hospital = new Hospital();

                hospital.setHospitalId(resultSet.getInt("hospitalId"));
                hospital.setHospitalName(resultSet.getString("hospitalName"));
                hospital.setHospitalAlamat(resultSet.getString("hospitalAlamat"));
                hospital.setHospitalTelp(resultSet.getString("hospitalTelp"));
                hospital.setHospitalLokasi(resultSet.getString("hospitalLokasi"));
                hospital.setHospitalUsername(resultSet.getString("hospitalUsername"));
                hospital.setHospitalPassword(resultSet.getString("hospitalPassword"));
                
                //hospital.setHospitalPassword(AES.decrypt(resultSet.getString("password")));

                list.add(hospital);
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

            String sql = "SELECT COUNT(*) FROM hospital;";
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
