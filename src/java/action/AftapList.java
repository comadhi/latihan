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
public class AftapList implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {

        List<Aftap> aftaps = new ArrayList<>();

        HttpSession session = request.getSession(true);
        StaffPmi staffPmi = (StaffPmi) session.getAttribute("loginasStaffPmi");
//
        if (staffPmi != null) {
            try {

                AftapDao aftapDao = ConnectionMySQL.getAftapDao();

                try {
                    aftaps = aftapDao.selectAll();
                } catch (Exception ex) {
                }
                
                request.setAttribute("aftaps", aftaps);

                return "aftapList.jsp";

            } catch (SQLException ex) {
            }
        } else {
            return "index.jsp";
        }

        return "aftapList.jsp";
    }

}
