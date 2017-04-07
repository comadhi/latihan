package action;

import dao.OrderDao;
import dao.PatientDao;
import entity.Order;
import entity.Hospital;
import entity.Patient;
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
public class OrderList implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {

        int page = 1;
        int recordsPerPage = 10;
        int noOfRecordsAdmin = 0;
        int noOfPagesAdmin = 0;

        List<Order> orders = new ArrayList<>();
        List<Patient> patients = new ArrayList<>();

        HttpSession session = request.getSession(true);
        Hospital hospital = (Hospital) session.getAttribute("loginashospital");
//
        if (hospital != null) {
            try {

                OrderDao orderDao = ConnectionMySQL.getOrderDao();
                PatientDao patientDao = ConnectionMySQL.getPatientDao();
                
                patients = patientDao.selectAll();
                
                try {

                    String pageStr = request.getParameter("page");
                    if (pageStr != null) {
                        page = Integer.parseInt(pageStr);
                    }
                    request.setAttribute("currentPage", page);

                    //books = bookDAO.selectAll();
                    orders = orderDao.selectAllWithLimit((page - 1) * recordsPerPage, recordsPerPage);
                    System.out.println("orders " + orders.size());

                    int noOfRecords = orderDao.getNoOfRecords();
                    int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

                    request.setAttribute("noOfPages", noOfPages);
                    request.setAttribute("currentPage", page);
                } catch (SQLException ex) {

                }

                request.setAttribute("patiens", patients);
                request.setAttribute("orders", orders);

                return "orderList.jsp";

            } catch (SQLException ex) {
//                Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(OrderList.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            return "index.jsp";
        }

        return "orderList.jsp";
    }

}
