package action;

import dao.PeriksaDao;
import entity.Periksa;
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
public class PeriksaDelete implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        StaffPmi staffPmi = (StaffPmi) session.getAttribute("loginasStaffPmi");
        String msg = "Failed to delete periksa";
        List<Periksa> periksas = new ArrayList<>();
        String message = null;
        String action = null;

        if (staffPmi != null) {
            try {
                PeriksaDao periksaDAO = ConnectionMySQL.getPeriksaDao();

                try {

                    String idStr = request.getParameter("id");
                    if (idStr != null) {
                        Integer idPeriksa = Integer.parseInt(idStr);
                        System.out.println("idperiksa delete : " + idPeriksa);
                        Periksa periksa = periksaDAO.getPeriksaById(idPeriksa);

                        periksaDAO.delete(idPeriksa);
                        msg = "periksa has been delete";
                        message = "success";
                        action = "delete";

                        try {
                            periksas = periksaDAO.selectAll();
                        } catch (Exception ex) {
                        }

                        msg = "Periksa has been saved";
                        message = "success";
                        action = "delete";
                        request.setAttribute("message", message);
                        request.setAttribute("action", action);
                        request.setAttribute("periksas", periksas);

                    }

                } catch (Exception ex) {
                    msg = "Failed to delete periksa " + ex.getMessage();
//                    Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
                    message = "failed";
                }

            } catch (SQLException ex) {
                msg = "Failed to delete periksa " + ex.getMessage();
//                Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
                message = "failed";
            }
        } else {
            return "index.jsp";
        }

        request.setAttribute("message", message);
        request.setAttribute("action", action);
        request.setAttribute("msg", msg);
        return "periksaList.jsp";

    }

}
