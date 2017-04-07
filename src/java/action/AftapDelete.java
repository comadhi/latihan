package action;

import dao.AftapDao;
import entity.Aftap;
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
public class AftapDelete implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        StaffPmi staffPmi = (StaffPmi) session.getAttribute("loginasStaffPmi");
        String msg = "Failed to delete aftap";
        List<Aftap> aftaps = new ArrayList<>();
        String message = null;
        String action = null;

        if (staffPmi != null) {
            try {
                AftapDao aftapDAO = ConnectionMySQL.getAftapDao();

                try {

                    String idStr = request.getParameter("id");
                    if (idStr != null) {
                        Integer idAftap = Integer.parseInt(idStr);
                        System.out.println("idaftap delete : " + idAftap);
                        Aftap aftap = aftapDAO.getAftapById(idAftap);

                        aftapDAO.delete(idAftap);
                        msg = "aftap has been delete";
                        message = "success";
                        action = "delete";

                        try {
                            aftaps = aftapDAO.selectAll();
                        } catch (Exception ex) {
                        }

                        msg = "Aftap has been saved";
                        message = "success";
                        action = "delete";
                        request.setAttribute("message", message);
                        request.setAttribute("action", action);
                        request.setAttribute("aftaps", aftaps);

                    }

                } catch (Exception ex) {
                    msg = "Failed to delete aftap " + ex.getMessage();
//                    Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
                    message = "failed";
                }

            } catch (SQLException ex) {
                msg = "Failed to delete aftap " + ex.getMessage();
//                Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
                message = "failed";
            }
        } else {
            return "index.jsp";
        }

        request.setAttribute("message", message);
        request.setAttribute("action", action);
        request.setAttribute("msg", msg);
        return "aftapList.jsp";

    }

}
