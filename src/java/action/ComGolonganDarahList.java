package action;

import com.dao.ComGolonganDarahDao;
import com.entity.ComGolonganDarah;
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
public class ComGolonganDarahList implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {

        int pages = 1;
        int recordsPerPage = 10;
        int noOfRecords = 0;
        int noOfPages = 0;
        int noOfRecordsAdmin = 0;
        int noOfPagesAdmin = 0;

        List<ComGolonganDarah> comGolonganDarahs = new ArrayList<>();

        HttpSession session = request.getSession(true);
        StaffPmi staffPmi = (StaffPmi) session.getAttribute("loginasStaffPmi");
//
        if (staffPmi != null) {
            try {

                ComGolonganDarahDao comGolonganDarahDao = ConnectionMySQL.getComGolonganDarahDao();
                String pageStr = request.getParameter("page");
                if (pageStr != null) {
                    pages = Integer.parseInt(pageStr);
                }
                request.setAttribute("currentPage", pages);

                try {
                    //members = memberDao.selectAll();
                    //administrators = administratorDao.selectAll();

                    comGolonganDarahs = comGolonganDarahDao.selectAll();

                    noOfRecordsAdmin = comGolonganDarahDao.getNoOfRecords();
                    noOfPagesAdmin = (int) Math.ceil(noOfRecordsAdmin * 1.0 / recordsPerPage);
                } catch (Exception ex) {
//                    Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
                }

                request.setAttribute("noOfPagesAdmin", noOfPagesAdmin);
                request.setAttribute("noOfPages", noOfPages);
                request.setAttribute("comGolonganDarahs", comGolonganDarahs);

                return "comGolonganDarahList.jsp";

            } catch (SQLException ex) {
//                Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            return "index.jsp";
        }

        return "comGolonganDarahList.jsp";
    }

}
