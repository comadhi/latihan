package action;

import dao.PeriksaDao;
import com.dao.ComPekerjaanDao;
import com.entity.ComPekerjaan;
import dao.PendonorDao;
import entity.Pendonor;
import entity.Periksa;
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
public class PeriksaEdit implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        StaffPmi staffPmi = (StaffPmi) session.getAttribute("loginasStaffPmi");
        Periksa periksa = null;
        List<ComPekerjaan> pekerjaanList = new ArrayList<>();

        Pendonor pendonor = null;
        String paramPendonorId = request.getParameter("pendonorId");
        if (staffPmi != null) {
            try {
                PeriksaDao periksaDAO = ConnectionMySQL.getPeriksaDao();
                ComPekerjaanDao pekerjaanDAO = ConnectionMySQL.getComPekerjaanDao();
                
                PendonorDao pendonorDao = ConnectionMySQL.getPendonorDao();
                try {

                    String idStr = request.getParameter("id");
                    if (idStr != null) {
                        Integer idBook = Integer.parseInt(idStr);
                        System.out.println("idperiksa edit : " + idBook);

                        periksa = periksaDAO.getPeriksaById(idBook);
                    }
                    
                    pendonor = pendonorDao.selectPendonorById(Integer.parseInt(paramPendonorId));
                    pekerjaanList = pekerjaanDAO.selectAll();
                } catch (Exception ex) {
//                    Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
                }

            } catch (SQLException ex) {
//                Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            return "index.jsp";
        }

        request.setAttribute("periksaforedit", periksa);
        request.setAttribute("pekerjaans", pekerjaanList);
        request.setAttribute("pendonor", pendonor);

        return "periksaEdit.jsp";

    }

}
