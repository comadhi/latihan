package action;

import com.entity.ComPekerjaan;
import dao.PeriksaDao;
import entity.Pendonor;
import entity.Periksa;
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
public class PeriksaSave implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {

        int pages = 1;
        int recordsPerPage = 10;
        int noOfRecordsAdmin = 0;
        int noOfPagesAdmin = 0;

        List<Periksa> periksas = new ArrayList<>();
        HttpSession session = request.getSession(true);
        StaffPmi staffPmi = (StaffPmi) session.getAttribute("loginasStaffPmi");
        String msg;
        String message;
        String action = null;

        if (staffPmi != null) {
            try {
                PeriksaDao periksaDAO = ConnectionMySQL.getPeriksaDao();
                try {
                    String id = request.getParameter("id");

                    String sPendonorId = request.getParameter("pendonorId");
                    String sPeriksaTanggal = request.getParameter("periksaTanggal");
                    String sPeriksaTensi = request.getParameter("periksaTensi");
                    String sPeriksaSuhu = request.getParameter("periksaSuhu");
                    String pPeriksaBeratBadan = request.getParameter("periksaBeratBadan");
                    String pPeriksaRiwayatMedis = request.getParameter("periksaRiwayatMedis");
                    String pPeriksaKeputusan = request.getParameter("periksaKeputusan");
                    String pPeriksaAlasan = request.getParameter("periksaAlasan");

                    Periksa oPeriksa = new Periksa();

                    Pendonor oPendonor = new Pendonor();

                    oPendonor.setPendonorId(Integer.parseInt(sPendonorId));

                    oPeriksa.setPendonor(oPendonor);
                    oPeriksa.setPeriksaTanggal(sPeriksaTanggal);
                    oPeriksa.setPeriksaTensi(sPeriksaTensi);
                    oPeriksa.setPeriksaSuhu(sPeriksaSuhu);
                    oPeriksa.setPeriksaBeratBadan(pPeriksaBeratBadan);
                    oPeriksa.setPeriksaRiwayatMedis(pPeriksaRiwayatMedis);
                    oPeriksa.setPeriksaKeputusan(pPeriksaKeputusan);
                    oPeriksa.setPeriksaAlasan(pPeriksaAlasan);

                    if (id == null) {
                        System.out.println("insert new periksa");
                        periksaDAO.insert(oPeriksa);
                        action = "simpan";
                    } else {
                        System.out.println("update new Periksa");
                        oPeriksa.setPeriksaId(Integer.parseInt(id));
                        periksaDAO.update(oPeriksa);
                        action = "simpan";
                    }

                    try {
                        periksas = periksaDAO.selectAll();
                    } catch (Exception ex) {
                    }

                    msg = "periksa has been saved";
                    message = "success";
                    request.setAttribute("message", message);
                    request.setAttribute("action", action);
                    request.setAttribute("periksas", periksas);

                } catch (Exception ex) {
                    msg = "failed to save Periksa " + ex.getMessage();
                    message = "failed";
                }
            } catch (SQLException ex) {
                msg = "Failed to save Periksa " + ex.getMessage();
                message = "failed";
            }
        } else {
            return "index.jsp";
        }

        request.setAttribute("msg", msg);
        request.setAttribute("message", message);
        request.setAttribute("action", action);
        return "periksaList.jsp";

    }

}
