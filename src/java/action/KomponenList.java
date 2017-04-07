package action;

import com.dao.ComKomponenDarahDao;
import com.entity.ComKomponenDarah;
import dao.KomponenDao;
import entity.Komponen;
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
public class KomponenList implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {

        List<Komponen> komponens = new ArrayList<>();

        HttpSession session = request.getSession(true);
        StaffPmi staffPmi = (StaffPmi) session.getAttribute("loginasStaffPmi");
//
        if (staffPmi != null) {
            try {

                KomponenDao komponenDao = ConnectionMySQL.getKomponenDao();

                try {
                    komponens = komponenDao.selectAll();
                            
                } catch (Exception ex) {
                }
                
                request.setAttribute("komponens", komponens);

                return "komponenList.jsp";

            } catch (SQLException ex) {
            }
        } else {
            return "index.jsp";
        }

        return "komponenList.jsp";
    }

}
