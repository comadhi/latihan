package action;

import com.dao.ComJaminanDao;
import com.entity.ComJaminan;
import entity.StaffPmi;
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
public class ComJaminanList implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {

        int pages = 1;
        int recordsPerPage = 10;
        int noOfRecords = 0;
        int noOfPages = 0;
        int noOfRecordsAdmin = 0;
        int noOfPagesAdmin = 0;

        List<ComJaminan> comJaminans = new ArrayList<>();

        HttpSession session = request.getSession(true);
        StaffPmi staffPmi = (StaffPmi) session.getAttribute("loginasStaffPmi");
//
        if (staffPmi != null) {
            try {

                ComJaminanDao comJaminanDao = ConnectionMySQL.getComJaminanDao();
                String pageStr = request.getParameter("page");
                if (pageStr != null) {
                    pages = Integer.parseInt(pageStr);
                }
                request.setAttribute("currentPage", pages);

                try {
                    //members = memberDao.selectAll();
                    //administrators = administratorDao.selectAll();

                    comJaminans = comJaminanDao.selectAll();

                    noOfRecordsAdmin = comJaminanDao.getNoOfRecords();
                    noOfPagesAdmin = (int) Math.ceil(noOfRecordsAdmin * 1.0 / recordsPerPage);
                } catch (Exception ex) {
//                    Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
                }

                request.setAttribute("noOfPagesAdmin", noOfPagesAdmin);
                request.setAttribute("noOfPages", noOfPages);
                request.setAttribute("comJaminans", comJaminans);

                return "comJaminanList.jsp";

            } catch (SQLException ex) {
//                Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            return "index.jsp";
        }

        return "comJaminanList.jsp";
    }

}
