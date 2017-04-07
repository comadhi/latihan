package action;

import com.dao.ComKomponenDarahDao;
import com.entity.ComKomponenDarah;
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
public class ComKomponenDarahDelete implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        StaffPmi staffPmi = (StaffPmi) session.getAttribute("loginasStaffPmi");
        String msg = "Failed to delete comKomponenDarah";
        List<ComKomponenDarah> comKomponenDarahs = new ArrayList<>();

        if (staffPmi != null) {
            try {
                ComKomponenDarahDao comKomponenDarahDAO = ConnectionMySQL.getComKomponenDarahDao();

                try {

                    String idStr = request.getParameter("id");
                    if (idStr != null) {
                        Integer idComKomponenDarah = Integer.parseInt(idStr);
                        System.out.println("idcomKomponenDarah delete : " + idComKomponenDarah);
                        ComKomponenDarah comKomponenDarah = comKomponenDarahDAO.getComKomponenDarahById(idComKomponenDarah);

                        comKomponenDarahDAO.delete(idComKomponenDarah);
                        msg = "comKomponenDarah has been delete";

                        try {
                            comKomponenDarahs = comKomponenDarahDAO.selectAll();
                        } catch (Exception ex) {
                        }

                        msg = "ComKomponenDarah has been saved";
                        request.setAttribute("comKomponenDarahs", comKomponenDarahs);

                    }

                } catch (Exception ex) {
                    msg = "Failed to delete comKomponenDarah " + ex.getMessage();
//                    Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
                }

            } catch (SQLException ex) {
                msg = "Failed to delete comKomponenDarah " + ex.getMessage();
//                Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            return "index.jsp";
        }

        request.setAttribute("msg", msg);
        return "comKomponenDarahList.jsp";

    }

}
