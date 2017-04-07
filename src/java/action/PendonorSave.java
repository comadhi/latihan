package action;

import com.entity.ComGolonganDarah;
import com.entity.ComPekerjaan;
import dao.PendonorDao;
import entity.Pendonor;
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
public class PendonorSave implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {

        int pages = 1;
        int recordsPerPage = 10;
        int noOfRecordsAdmin = 0;
        int noOfPagesAdmin = 0;

        List<Pendonor> pendonors = new ArrayList<>();
        HttpSession session = request.getSession(true);
        StaffPmi staffPmi = (StaffPmi) session.getAttribute("loginasStaffPmi");
        String msg;
        String message;
        String action = null;

        if (staffPmi != null) {
            try {
                PendonorDao pendonorDAO = ConnectionMySQL.getPendonorDao();
                try {
                    String id = request.getParameter("id");

                    String sPendonorNo = request.getParameter("pendonorNo");
                    String sPendonorName = request.getParameter("pendonorName");
                    String sPendonorBirthPlace = request.getParameter("pendonorBirthPlace");
                    String pPendonorBirthDate = request.getParameter("pendonorDate");
                    String pPendonorGender = request.getParameter("pendonorGender");
                    String pPendonorAddress = request.getParameter("pendonorAddress");
                    String pPendonorTelp = request.getParameter("pendonorTelp");
                    String pPendonorEmail = request.getParameter("pendonorEmail");
                    String pPendonorPassword = request.getParameter("pendonorPassword");
                    String pPendonorPekerjaan = request.getParameter("pendonorPekerjaan");
                    String pPendonorGolonganDarah = request.getParameter("pendonorGolonganDarah");

                    Pendonor oPendonor = new Pendonor();

                    ComPekerjaan oComPekerjaan = new ComPekerjaan();
                    oComPekerjaan.setComPekerjaanId(Integer.parseInt(pPendonorPekerjaan));
                    
                    ComGolonganDarah oComGolonganDarah = new ComGolonganDarah();
                    oComGolonganDarah.setComGolonganDarahId(Integer.parseInt(pPendonorGolonganDarah));

                    oPendonor.setPendonorNo(sPendonorNo);
                    oPendonor.setPendonorName(sPendonorName);
                    oPendonor.setPendonorBirthPlace(sPendonorBirthPlace);
                    oPendonor.setPendonorBirthDate(pPendonorBirthDate);
                    oPendonor.setPendonorGender(pPendonorGender);
                    oPendonor.setPendonorAddress(pPendonorAddress);
                    oPendonor.setPendonorTelp(pPendonorTelp);
                    oPendonor.setPendonorEmail(pPendonorEmail);
                    oPendonor.setPendonorPassword(pPendonorPassword);
                    oPendonor.setComPekerjaan(oComPekerjaan);
                    oPendonor.setComGolonganDarah(oComGolonganDarah);

                    if (id == null) {
                        System.out.println("insert new pendonor");
                        pendonorDAO.insert(oPendonor);
                        action = "simpan";
                    } else {
                        System.out.println("update new Pendonor");
                        oPendonor.setPendonorId(Integer.parseInt(id));
                        pendonorDAO.update(oPendonor);
                        action = "simpan";
                    }

                    try {
                        pendonors = pendonorDAO.selectAll();
                    } catch (Exception ex) {
                    }

                    msg = "pendonor has been saved";
                    message = "success";
                    request.setAttribute("message", message);
                    request.setAttribute("action", action);
                    request.setAttribute("pendonors", pendonors);

                } catch (Exception ex) {
                    msg = "failed to save Pendonor " + ex.getMessage();
                    message = "failed";
                }
            } catch (SQLException ex) {
                msg = "Failed to save Pendonor " + ex.getMessage();
                message = "failed";
            }
        } else {
            return "index.jsp";
        }

        request.setAttribute("msg", msg);
        request.setAttribute("message", message);
        request.setAttribute("action", action);
        return "pendonorList.jsp";

    }

}
