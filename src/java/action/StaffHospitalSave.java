package action;

import dao.StaffHospitalDao;
import entity.Hospital;
import entity.StaffHospital;
//import entity.Category;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import service.ConnectionMySQL;

/**
 *
 * @author mazipan
 */
public class StaffHospitalSave implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {

        int pages = 1;
        int recordsPerPage = 10;
        int noOfRecordsAdmin = 0;
        int noOfPagesAdmin = 0;

        List<StaffHospital> staffHospitals = new ArrayList<>();
        HttpSession session = request.getSession(true);
        Hospital administrator = (Hospital) session.getAttribute("loginashospital");
        String msg;

        if (administrator != null) {
            try {
                StaffHospitalDao staffHospitalDAO = ConnectionMySQL.getStaffHospitalDao();
                try {
                    String id = request.getParameter("id");

                    String sStaffHospitalName = request.getParameter("staffHospitalName");
                    String sStaffHospitalAddress = request.getParameter("staffHospitalAddress");
                    String sStaffHospitalTelp = request.getParameter("staffHospitalTelp");

                    StaffHospital staffHospital = new StaffHospital();

                    staffHospital.setStaffHospitalName(sStaffHospitalName);
                    staffHospital.setStaffHospitalAddress(sStaffHospitalAddress);
                    staffHospital.setStaffHospitalTelp(sStaffHospitalTelp);

                    if (id == null) {
                        System.out.println("insert new staffHospital");
                        staffHospitalDAO.insert(staffHospital);
                    } else {
                        System.out.println("update new staffHospital");
                        staffHospital.setStaffHospitalId(Integer.parseInt(id));
                        staffHospitalDAO.update(staffHospital);
                    }

                    try {
                        staffHospitals = staffHospitalDAO.selectAll();

                        noOfRecordsAdmin = staffHospitalDAO.getNoOfRecords();
                        noOfPagesAdmin = (int) Math.ceil(noOfRecordsAdmin * 1.0 / recordsPerPage);
                    } catch (Exception ex) {
//                    Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    msg = "StaffHospital has been saved";

//                    request.setAttribute("noOfPagesAdmin", noOfPagesAdmin);
//                    request.setAttribute("noOfPages", noOfPages);
                    request.setAttribute("staffHospitals", staffHospitals);

                } catch (Exception ex) {
                    msg = "Failed to save staffHospital " + ex.getMessage();
                }

            } catch (SQLException ex) {
                msg = "Failed to save staffHospital " + ex.getMessage();
            }
        } else {
            return "index.jsp";
        }

        request.setAttribute("msg", msg);
        return "staffHospitalList.jsp";

    }

}
