package action;

import dao.OrderDao;
import entity.Order;
import entity.StaffPmi;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class PmiCrossEdit implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        StaffPmi staffPmi = (StaffPmi) session.getAttribute("loginasStaffPmi");
        Order order = null;

        if (staffPmi != null) {
            try {
                OrderDao orderDAO = ConnectionMySQL.getOrderDao();

                try {

                    String idStr = request.getParameter("id");
                    if (idStr != null) {
                        Integer idBook = Integer.parseInt(idStr);
                        System.out.println("idorder edit : " + idBook);

                        order = orderDAO.getOrderById(idBook);
                    }

                    request.setAttribute("orderforedit", order);
                    return "pmiCrossEdit.jsp";
                } catch (Exception ex) {
//                    Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
                }

            } catch (SQLException ex) {
//                Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            return "index.jsp";
        }

        request.setAttribute("orderforedit", order);
        return "pmiCrossEdit.jsp";

    }

}
