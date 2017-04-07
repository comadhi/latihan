package action;

import com.dao.ComKomponenDarahDao;
import com.entity.ComKomponenDarah;
import dao.AftapDao;
import entity.Aftap;
import dao.KomponenDao;
import entity.Komponen;
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
public class KomponenSave implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {

        int pages = 1;
        int recordsPerPage = 10;
        int noOfRecordsAdmin = 0;
        int noOfPagesAdmin = 0;

        List<Komponen> komponens = new ArrayList<>();
        HttpSession session = request.getSession(true);
        StaffPmi staffPmi = (StaffPmi) session.getAttribute("loginasStaffPmi");
        String msg;
        String message;
        String action = null;

        if (staffPmi != null) {
            try {
                KomponenDao komponenDAO = ConnectionMySQL.getKomponenDao();
                AftapDao aftapDAO = ConnectionMySQL.getAftapDao();
                ComKomponenDarahDao comKomponenDarahDAO = ConnectionMySQL.getComKomponenDarahDao();
                try {
                    String id = request.getParameter("id");

                    String sKomponenNo = request.getParameter("komponenNo");
                    String sKomponenTanggal = request.getParameter("komponenTanggal");
                    String sKomponenDarahId = request.getParameter("komponenDarah");
                    String pKomponenNoKantong = request.getParameter("komponenNoKantong");

                    Komponen oKomponen = new Komponen();

                    Aftap oAftap = new Aftap();                    
                    oAftap = aftapDAO.selectAftapByBagNumber(pKomponenNoKantong);
                    ComKomponenDarah oComKomponenDarah = new ComKomponenDarah();
                    oComKomponenDarah = comKomponenDarahDAO.getComKomponenDarahById(Integer.parseInt(sKomponenDarahId));
                    
                    oKomponen.setKomponenNo(sKomponenNo);
                    oKomponen.setKomponenTanggal(sKomponenTanggal);
                    oKomponen.setComKomponenDarah(oComKomponenDarah);
                    oKomponen.setAftap(oAftap);

                    if (id == null) {
                        
                        System.out.println("insert new komponen");
                        komponenDAO.insert(oKomponen);
                        action = "simpan";
                    } else {
                        System.out.println("update new Komponen");
                        oKomponen.setKomponenId(Integer.parseInt(id));
                        komponenDAO.update(oKomponen);
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
                    msg = "failed to save Komponen " + ex.getMessage();
                    message = "failed";
                }
            } catch (SQLException ex) {
                msg = "Failed to save Komponen " + ex.getMessage();
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
