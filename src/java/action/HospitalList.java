package action;

import dao.HospitalDao;
import entity.Hospital;
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
public class HospitalList implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {

        int pages = 1;
        int recordsPerPage = 10;
        int noOfRecords = 0;
        int noOfPages = 0;
        int noOfRecordsAdmin = 0;
        int noOfPagesAdmin = 0;

        List<Hospital> hospitals = new ArrayList<>();

        HttpSession session = request.getSession(true);
        Hospital hospital = (Hospital) session.getAttribute("loginashospital");

        if (hospital != null) {
            try {

                HospitalDao hospitalDao = ConnectionMySQL.getHospitalDao();
//                String pageStr = request.getParameter("page");
//                if (pageStr != null) {
//                    pages = Integer.parseInt(pageStr);
//                }
//                request.setAttribute("currentPage", pages);

                try {
                    //members = memberDao.selectAll();
                    //administrators = administratorDao.selectAll();

                    hospitals = hospitalDao.selectAll();

//                    noOfRecordsAdmin = hospitalDao.getNoOfRecords();
//                    noOfPagesAdmin = (int) Math.ceil(noOfRecordsAdmin * 1.0 / recordsPerPage);

                } catch (Exception ex) {
//                    Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
                }

//                request.setAttribute("noOfPagesAdmin", noOfPagesAdmin);
//                request.setAttribute("noOfPages", noOfPages);
                request.setAttribute("hospitals", hospitals);

                return "hospitalList.jsp";

            } catch (SQLException ex) {
//                Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            return "index.jsp";
        }

        return "hospitalList.jsp";
    }

}
