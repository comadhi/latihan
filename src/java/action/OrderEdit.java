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
public class OrderEdit implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Hospital hospital = (Hospital) session.getAttribute("loginashospital");
        Order order = null;
        List<Patient> patientList = new ArrayList<>();
        List<Docter> docterList = new ArrayList<>();

        if (hospital != null) {
            try {
                OrderDao orderDAO = ConnectionMySQL.getOrderDao();
                PatientDao patientDAO = ConnectionMySQL.getPatientDao();
                DocterDao docterDAO = ConnectionMySQL.getDocterDao();

                try {

                    String idStr = request.getParameter("id");
                    if (idStr != null) {
                        Integer idBook = Integer.parseInt(idStr);
                        System.out.println("idorder edit : " + idBook);

                        order = orderDAO.getOrderById(idBook);
                    }

                    
                    patientList = patientDAO.selectAll();
                    docterList = docterDAO.selectAll();

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
        request.setAttribute("patients", patientList);
        request.setAttribute("docters", docterList);

        return "orderEdit.jsp";

    }

}
