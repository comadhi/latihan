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
public class ComJaminanDelete implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        StaffPmi staffPmi = (StaffPmi) session.getAttribute("loginasStaffPmi");
        String msg = "Failed to delete comJaminan";
        List<ComJaminan> comJaminans = new ArrayList<>();

        if (staffPmi != null) {
            try {
                ComJaminanDao comJaminanDAO = ConnectionMySQL.getComJaminanDao();

                try {

                    String idStr = request.getParameter("id");
                    if (idStr != null) {
                        Integer idComJaminan = Integer.parseInt(idStr);
                        System.out.println("idcomJaminan delete : " + idComJaminan);
                        ComJaminan comJaminan = comJaminanDAO.getComJaminanById(idComJaminan);

                        comJaminanDAO.delete(idComJaminan);
                        msg = "comJaminan has been delete";

                        try {
                            comJaminans = comJaminanDAO.selectAll();
                        } catch (Exception ex) {
                        }

                        msg = "ComJaminan has been saved";
                        request.setAttribute("comJaminans", comJaminans);

                    }

                } catch (Exception ex) {
                    msg = "Failed to delete comJaminan " + ex.getMessage();
//                    Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
                }

            } catch (SQLException ex) {
                msg = "Failed to delete comJaminan " + ex.getMessage();
//                Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            return "index.jsp";
        }

        request.setAttribute("msg", msg);
        return "comJaminanList.jsp";

    }

}
