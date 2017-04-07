package action;

import dao.PendonorDao;
import entity.Pendonor;
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
public class PendonorList implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {

        List<Pendonor> pendonors = new ArrayList<>();

        HttpSession session = request.getSession(true);
        StaffPmi staffPmi = (StaffPmi) session.getAttribute("loginasStaffPmi");
//
        if (staffPmi != null) {
            try {

                PendonorDao pendonorDao = ConnectionMySQL.getPendonorDao();

                try {
                    pendonors = pendonorDao.selectAll();
                } catch (Exception ex) {
                }
                
                request.setAttribute("pendonors", pendonors);

                return "pendonorList.jsp";

            } catch (SQLException ex) {
            }
        } else {
            return "index.jsp";
        }

        return "pendonorList.jsp";
    }

}
