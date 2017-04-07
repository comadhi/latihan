package action;

import dao.ScreeningDao;
import entity.Screening;
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
public class ScreeningList implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {

        List<Screening> screenings = new ArrayList<>();

        HttpSession session = request.getSession(true);
        StaffPmi staffPmi = (StaffPmi) session.getAttribute("loginasStaffPmi");
//
        if (staffPmi != null) {
            try {

                ScreeningDao screeningDao = ConnectionMySQL.getScreeningDao();

                try {
                    screenings = screeningDao.selectAll();
                } catch (Exception ex) {
                }
                
                request.setAttribute("screenings", screenings);

                return "screeningList.jsp";

            } catch (SQLException ex) {
            }
        } else {
            return "index.jsp";
        }

        return "screeningList.jsp";
    }

}
