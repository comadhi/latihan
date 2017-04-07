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
public class ComGolonganDarahDelete implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        StaffPmi staffPmi = (StaffPmi) session.getAttribute("loginasStaffPmi");
        String msg = "Failed to delete comGolonganDarah";
        List<ComGolonganDarah> comGolonganDarahs = new ArrayList<>();

        if (staffPmi != null) {
            try {
                ComGolonganDarahDao comGolonganDarahDAO = ConnectionMySQL.getComGolonganDarahDao();

                try {

                    String idStr = request.getParameter("id");
                    if (idStr != null) {
                        Integer idComGolonganDarah = Integer.parseInt(idStr);
                        System.out.println("idcomGolonganDarah delete : " + idComGolonganDarah);
                        ComGolonganDarah comGolonganDarah = comGolonganDarahDAO.getComGolonganDarahById(idComGolonganDarah);

                        comGolonganDarahDAO.delete(idComGolonganDarah);
                        msg = "comGolonganDarah has been delete";

                        try {
                            comGolonganDarahs = comGolonganDarahDAO.selectAll();
                        } catch (Exception ex) {
                        }

                        msg = "ComGolonganDarah has been saved";
                        request.setAttribute("comGolonganDarahs", comGolonganDarahs);

                    }

                } catch (Exception ex) {
                    msg = "Failed to delete comGolonganDarah " + ex.getMessage();
//                    Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
                }

            } catch (SQLException ex) {
                msg = "Failed to delete comGolonganDarah " + ex.getMessage();
//                Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            return "index.jsp";
        }

        request.setAttribute("msg", msg);
        return "comGolonganDarahList.jsp";

    }

}
