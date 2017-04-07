package action;

import dao.StaffHospitalDao;
import entity.Hospital;
import entity.StaffHospital;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import service.ConnectionMySQL;

/**
 *
 * @author mazipan
 */
public class StaffHospitalDelete implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Hospital hospital = (Hospital) session.getAttribute("loginashospital");
        String msg = "Failed to delete staffHospital";
        List<StaffHospital> staffHospitals = new ArrayList<>();

        if (hospital != null) {
            try {
                StaffHospitalDao staffHospitalDAO = ConnectionMySQL.getStaffHospitalDao();

                try {

                    String idStr = request.getParameter("id");
                    if (idStr != null) {
                        Integer idStaffHospital = Integer.parseInt(idStr);
                        System.out.println("idstaffHospital delete : " + idStaffHospital);
                        StaffHospital staffHospital = staffHospitalDAO.getStaffHospitalById(idStaffHospital);

                        staffHospitalDAO.delete(idStaffHospital);
                        msg = "staffHospital has been delete";

                        try {
                            staffHospitals = staffHospitalDAO.selectAll();
                        } catch (Exception ex) {
                        }

                        msg = "StaffHospital has been saved";
                        request.setAttribute("staffHospitals", staffHospitals);

                    }

                } catch (Exception ex) {
                    msg = "Failed to delete staffHospital " + ex.getMessage();
//                    Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
                }

            } catch (SQLException ex) {
                msg = "Failed to delete staffHospital " + ex.getMessage();
//                Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            return "index.jsp";
        }

        request.setAttribute("msg", msg);
        return "staffHospitalList.jsp";

    }

}
