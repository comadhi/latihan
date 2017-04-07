package action;

import dao.AftapDao;
import entity.Aftap;
import dao.OrderDao;
import entity.Docter;
import entity.Order;
import entity.Patient;
import entity.StaffPmi;
//import entity.Category;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import service.ConnectionMySQL;

/**
 *
 * @author mazipan
 */
public class PmiOrderSave implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {

        int pages = 1;
        int recordsPerPage = 10;
        int noOfRecordsAdmin = 0;
        int noOfPagesAdmin = 0;

        List<Order> komponens = new ArrayList<>();
        HttpSession session = request.getSession(true);
        StaffPmi staffPmi = (StaffPmi) session.getAttribute("loginasStaffPmi");
        String msg;
        String message;
        String action = null;

        if (staffPmi != null) {
            try {
                OrderDao komponenDAO = ConnectionMySQL.getOrderDao();
                AftapDao aftapDAO = ConnectionMySQL.getAftapDao();
                OrderDao orderDAO = ConnectionMySQL.getOrderDao();
                try {
                    String id = request.getParameter("id");

                    String sOrderNo = request.getParameter("orderNo");
                    String sOrderPatient = request.getParameter("orderPatient");
                    String sOrderDocter = request.getParameter("orderDocter");
                    String sOrderTanggal = request.getParameter("orderTanggal");
                    String sOrderNoKartu = request.getParameter("orderNoKartu");
                    String sOrderHb = request.getParameter("orderHb");
                    String sOrderDiagnosa = request.getParameter("orderDiagnosa");
                    String sOrderKelas = request.getParameter("orderKelas");
                    String sOrderBangsal = request.getParameter("orderBangsal");
                    String sOrderTanggalMasuk = request.getParameter("orderTanggalMasuk");
                    String sOrderTanggalDigunakan = request.getParameter("orderTanggalDigunakan");
                    String sOrderJmlMinta = request.getParameter("orderJmlMinta");

                    Order order = new Order();
                    Patient oPatient = new Patient();
                    Docter oDocter = new Docter();
                    oPatient.setPatientId(Integer.parseInt(sOrderPatient));
                    oDocter.setDocterId(Integer.parseInt(sOrderDocter));

                    order.setOrderNo(sOrderNo);
                    order.setOrderTanggal(sOrderTanggal);
                    order.setOrderNoKartu(sOrderNoKartu);
                    order.setOrderHb(sOrderHb);
                    order.setOrderDiagnosa(sOrderDiagnosa);
                    order.setOrderKelas(sOrderKelas);
                    order.setOrderBangsal(sOrderBangsal);
                    order.setOrderTanggalMasuk(sOrderTanggalMasuk);
                    order.setOrderTanggalDigunakan(sOrderTanggalDigunakan);
                    order.setOrderJmlMinta(sOrderJmlMinta);
                    order.setPatient(oPatient);
                    order.setDocter(oDocter);

                    if (id == null) {
                        
                        System.out.println("insert new komponen");
                        komponenDAO.insert(order);
                        action = "simpan";
                    } else {
                        System.out.println("update new Order");
                        order.setOrderId(Integer.parseInt(id));
                        komponenDAO.update(order);
                        action = "simpan";
                    }

                    try {
                        komponens = komponenDAO.selectAll();
                    } catch (Exception ex) {
                    }

                    msg = "komponen has been saved";
                    message = "success";
                    request.setAttribute("message", message);
                    request.setAttribute("action", action);
                    request.setAttribute("komponens", komponens);

                } catch (Exception ex) {
                    msg = "failed to save Order " + ex.getMessage();
                    message = "failed";
                }
            } catch (SQLException ex) {
                msg = "Failed to save Order " + ex.getMessage();
                message = "failed";
            }
        } else {
            return "index.jsp";
        }

        request.setAttribute("msg", msg);
        request.setAttribute("message", message);
        request.setAttribute("action", action);
        return "komponenList.jsp";

    }

}
