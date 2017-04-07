package action;

import dao.OrderDao;
import entity.Docter;
import entity.Hospital;
import entity.Order;
import entity.Patient;
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
public class OrderSave implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {

        int page = 1;
        int recordsPerPage = 10;

        List<Order> orders = new ArrayList<>();
        HttpSession session = request.getSession(true);
        Hospital administrator = (Hospital) session.getAttribute("loginashospital");
        String msg;

        if (administrator != null) {
            try {
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
                    order.setHospital(administrator);
                    order.setPatient(oPatient);
                    order.setDocter(oDocter);

                    if (id == null) {
                        System.out.println("insert new order");
                        orderDAO.insert(order);
                    } else {
                        System.out.println("update new order");
                        order.setOrderId(Integer.parseInt(id));
                        orderDAO.update(order);
                    }

                    try {
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

                    msg = "Order has been saved";

//                    request.setAttribute("noOfPagesAdmin", noOfPagesAdmin);
//                    request.setAttribute("noOfPages", noOfPages);
                    request.setAttribute("orders", orders);

                    return "orderList.jsp";

                } catch (Exception ex) {
                    msg = "Failed to save order " + ex.getMessage();
                }

            } catch (SQLException ex) {
                msg = "Failed to save order " + ex.getMessage();
            }
        } else {
            return "index.jsp";
        }

        request.setAttribute("msg", msg);
        return "orderList.jsp";

    }

}
