package action;

import com.dao.ComKomponenDarahDao;
import com.entity.ComKomponenDarah;
import dao.AftapDao;
import dao.OrderDao;
import entity.Aftap;
import entity.Order;
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
public class PmiCrossList implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {

//        List<Order> crosss = new ArrayList<>();
        List<Aftap> crosss = new ArrayList<>();

        HttpSession session = request.getSession(true);
        StaffPmi staffPmi = (StaffPmi) session.getAttribute("loginasStaffPmi");
//
        if (staffPmi != null) {
            try {

                OrderDao crossDao = ConnectionMySQL.getOrderDao();
                AftapDao aftapDao = ConnectionMySQL.getAftapDao();
                
                

                try {
//                    crosss = crossDao.selectAll();
                    crosss = aftapDao.selectAllForCrossMatch();
                            
                } catch (Exception ex) {
                }
                
                request.setAttribute("crosss", crosss);

                return "pmiCrossList.jsp";

            } catch (SQLException ex) {
            }
        } else {
            return "index.jsp";
        }

        return "pmiCrossList.jsp";
    }

}
