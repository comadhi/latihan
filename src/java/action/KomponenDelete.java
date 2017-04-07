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
public class KomponenDelete implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        StaffPmi staffPmi = (StaffPmi) session.getAttribute("loginasStaffPmi");
        String msg = "Failed to delete screening";
        List<Screening> screenings = new ArrayList<>();
        String message = null;
        String action = null;

        if (staffPmi != null) {
            try {
                ScreeningDao screeningDAO = ConnectionMySQL.getScreeningDao();

                try {

                    String idStr = request.getParameter("id");
                    if (idStr != null) {
                        Integer idScreening = Integer.parseInt(idStr);
                        System.out.println("idscreening delete : " + idScreening);
                        Screening screening = screeningDAO.getScreeningById(idScreening);

                        screeningDAO.delete(idScreening);
                        msg = "screening has been delete";
                        message = "success";
                        action = "delete";

                        try {
                            screenings = screeningDAO.selectAll();
                        } catch (Exception ex) {
                        }

                        msg = "Screening has been saved";
                        message = "success";
                        action = "delete";
                        request.setAttribute("message", message);
                        request.setAttribute("action", action);
                        request.setAttribute("screenings", screenings);

                    }

                } catch (Exception ex) {
                    msg = "Failed to delete screening " + ex.getMessage();
//                    Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
                    message = "failed";
                }

            } catch (SQLException ex) {
                msg = "Failed to delete screening " + ex.getMessage();
//                Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
                message = "failed";
            }
        } else {
            return "index.jsp";
        }

        request.setAttribute("message", message);
        request.setAttribute("action", action);
        request.setAttribute("msg", msg);
        return "screeningList.jsp";

    }

}
