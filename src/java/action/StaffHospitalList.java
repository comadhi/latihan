package action;

import dao.StaffHospitalDao;
import entity.Docter;
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
public class StaffHospitalList implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {

        int page = 1;
        int recordsPerPage = 10;
        int noOfRecordsAdmin = 0;
        int noOfPagesAdmin = 0;

        List<StaffHospital> staffHospitals = new ArrayList<>();

        HttpSession session = request.getSession(true);
        Hospital hospital = (Hospital) session.getAttribute("loginashospital");
//
        if (hospital != null) {
            try {

                StaffHospitalDao staffHospitalDao = ConnectionMySQL.getStaffHospitalDao();
                try {
                    String pageStr = request.getParameter("page");
                    if (pageStr != null) {
                        page = Integer.parseInt(pageStr);
                    }
                    request.setAttribute("currentPage", page);

                    staffHospitals = staffHospitalDao.selectAllWithLimit((page - 1) * recordsPerPage, recordsPerPage);
                    System.out.println("staffHospitals " + staffHospitals.size());

                    int noOfRecords = staffHospitalDao.getNoOfRecords();
                    int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

                    request.setAttribute("noOfPagesAdmin", noOfPagesAdmin);
                    request.setAttribute("noOfPages", noOfPages);

                } catch (SQLException ex) {

                }

                request.setAttribute("staffHospitals", staffHospitals);

                return "staffHospitalList.jsp";

            } catch (SQLException ex) {
//                Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(StaffHospitalList.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            return "index.jsp";
        }

        return "staffHospitalList.jsp";
    }

}
