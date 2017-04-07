package action;

import com.dao.ComPekerjaanDao;
import com.entity.ComPekerjaan;
import dao.DocterDao;
import dao.OrderDao;
import dao.PatientDao;
import entity.Docter;
import entity.Order;
import entity.Hospital;
import entity.Patient;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import report.ReportLoader;
import service.ConnectionMySQL;

/**
 *
 * @author mazipan
 */
public class OrderPrint implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Hospital hospital = (Hospital) session.getAttribute("loginashospital");
        Order order = null;
        int page = 1;
        int recordsPerPage = 10;
        int noOfRecordsAdmin = 0;
        int noOfPagesAdmin = 0;
        
        ReportLoader report = new ReportLoader();
        List<Patient> patients = new ArrayList<>();
        List<Docter> docterList = new ArrayList<>();
        List<Order> orders = new ArrayList<>();

        if (hospital != null) {
            try {
                OrderDao orderDAO = ConnectionMySQL.getOrderDao();
                PatientDao patientDAO = ConnectionMySQL.getPatientDao();
                DocterDao docterDAO = ConnectionMySQL.getDocterDao();

                patients = patientDAO.selectAll();
                try {

                    String idStr = request.getParameter("id");
                    if (idStr != null) {
//                        report.print(idStr, (Connection) ConnectionMySQL.getOrderDao());

                        orderDAO.print(idStr);
//                        orderDAO.print2(Integer.parseInt(idStr));
//                        Integer idBook = Integer.parseInt(idStr);
//                        System.out.println("idorder edit : " + idBook);

//                        order = orderDAO.getOrderById(idBook);
                    }

                    String pageStr = request.getParameter("page");
                    if (pageStr != null) {
                        page = Integer.parseInt(pageStr);
                    }
                    request.setAttribute("currentPage", page);

                    //books = bookDAO.selectAll();
                    orders = orderDAO.selectAllWithLimit((page - 1) * recordsPerPage, recordsPerPage);
                    System.out.println("orders " + orders.size());

                    int noOfRecords = orderDAO.getNoOfRecords();
                    int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

                    request.setAttribute("noOfPages", noOfPages);
                    request.setAttribute("currentPage", page);

                } catch (Exception ex) {
//                    Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
                }

            } catch (SQLException ex) {
//                Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(OrderPrint.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            return "index.jsp";
        }

        request.setAttribute("patiens", patients);
        request.setAttribute("orders", orders);

        return "orderList.jsp";

    }

}
