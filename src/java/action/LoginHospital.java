package action;

import dao.HospitalDao;
import dao.StaffPmiDao;
import entity.Hospital;
import entity.StaffPmi;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import service.ConnectionMySQL;

/**
 *
 * @author mazipan
 */
public class LoginHospital implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {
        //deklarasi dan inisialisasi variabel userName

        String fileJsp = "index.jsp";
        String pesan = "";

        if (request != null) {

            String userName = request.getParameter("username");
            String password = request.getParameter("password");

            if (userName != null && password != null) {
                if (userName.trim().equals("") && password.trim().equals("")) {
                    pesan = "Username and Password still empty";
                } else if (userName.trim().equals("")) {
                    pesan = "Username still empty";
                } else if (password.trim().equals("")) {
                    pesan = "Password still empty";
                } else {
                    try {
                        HospitalDao hospitalDao = ConnectionMySQL.getHospitalDao();
                        Hospital hospital = hospitalDao.getHospitalByNameAndPass(userName.toUpperCase(), password.toLowerCase());
                                          
                        
                        StaffPmiDao staffPmiDao = ConnectionMySQL.getStaffPmiDao();
                        StaffPmi oStaffPmi = staffPmiDao.getStaffPmiByNameAndPass(userName.toUpperCase(), password.toLowerCase());
                        

                        if (hospital != null) {
                            HttpSession session = request.getSession(true);
                            session.setAttribute("loginashospital", hospital);

                            pesan = "Success Login...";

                            fileJsp = "homeHospital.jsp";
                        } else if (oStaffPmi != null){
                            
                            HttpSession session = request.getSession(true);
                            session.setAttribute("loginasStaffPmi", oStaffPmi);

                            pesan = "Success Login As Staff PMI...";

                            fileJsp = "homeStaffPmi.jsp";
                        } else {
                            pesan = "Wrong Username or Password";
                        }

                    } catch (SQLException e) {
                        pesan = "Error when connecting to database, Please check your MySQL connecion";
                    } catch (Exception e) {
                        pesan = "Error when connecting to database, Please check your MySQL connecion";
                    }
                }
            }
        }

        //set attribut untuk menampilkasn pesan jika 
        //proses login gagal
        request.setAttribute("msg", pesan);

        System.out.println("fileJsp " + fileJsp);
        System.out.println("pesan " + pesan);
        //return fileJsp
        return fileJsp;
    }
}
