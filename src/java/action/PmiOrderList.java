package action;

import com.dao.ComKomponenDarahDao;
import com.entity.ComKomponenDarah;
import dao.OrderDao;
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
public class PmiOrderList implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {

        List<Order> orders = new ArrayList<>();

        HttpSession session = request.getSession(true);
        StaffPmi staffPmi = (StaffPmi) session.getAttribute("loginasStaffPmi");
//
        if (staffPmi != null) {
            try {

                OrderDao orderDao = ConnectionMySQL.getOrderDao();

                try {
                    orders = orderDao.selectAll();
                            
                } catch (Exception ex) {
                }
                
                request.setAttribute("orders", orders);

                return "pmiOrderList.jsp";

            } catch (SQLException ex) {
            }
        } else {
            return "index.jsp";
        }

        return "pmiOrderList.jsp";
    }

}
