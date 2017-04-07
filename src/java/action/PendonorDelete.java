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
public class PendonorDelete implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        StaffPmi staffPmi = (StaffPmi) session.getAttribute("loginasStaffPmi");
        String msg = "Failed to delete pendonor";
        List<Pendonor> pendonors = new ArrayList<>();
        String message = null;
        String action = null;

        if (staffPmi != null) {
            try {
                PendonorDao pendonorDAO = ConnectionMySQL.getPendonorDao();

                try {

                    String idStr = request.getParameter("id");
                    if (idStr != null) {
                        Integer idPendonor = Integer.parseInt(idStr);
                        System.out.println("idpendonor delete : " + idPendonor);
                        Pendonor pendonor = pendonorDAO.getPendonorById(idPendonor);

                        pendonorDAO.delete(idPendonor);
                        msg = "pendonor has been delete";
                        message = "success";
                        action = "delete";

                        try {
                            pendonors = pendonorDAO.selectAll();
                        } catch (Exception ex) {
                        }

                        msg = "Pendonor has been saved";
                        message = "success";
                        action = "delete";
                        request.setAttribute("message", message);
                        request.setAttribute("action", action);
                        request.setAttribute("pendonors", pendonors);

                    }

                } catch (Exception ex) {
                    msg = "Failed to delete pendonor " + ex.getMessage();
//                    Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
                    message = "failed";
                }

            } catch (SQLException ex) {
                msg = "Failed to delete pendonor " + ex.getMessage();
//                Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
                message = "failed";
            }
        } else {
            return "index.jsp";
        }

        request.setAttribute("message", message);
        request.setAttribute("action", action);
        request.setAttribute("msg", msg);
        return "pendonorList.jsp";

    }

}
