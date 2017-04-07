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
public class PeriksaList implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {

        List<Periksa> periksas = new ArrayList<>();

        HttpSession session = request.getSession(true);
        StaffPmi staffPmi = (StaffPmi) session.getAttribute("loginasStaffPmi");
//
        if (staffPmi != null) {
            try {

                PeriksaDao periksaDao = ConnectionMySQL.getPeriksaDao();

                try {
                    periksas = periksaDao.selectAll();
                } catch (Exception ex) {
                }
                
                request.setAttribute("periksas", periksas);

                return "periksaList.jsp";

            } catch (SQLException ex) {
            }
        } else {
            return "index.jsp";
        }

        return "periksaList.jsp";
    }

}
