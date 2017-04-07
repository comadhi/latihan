package action;

import com.dao.ComGolonganDarahDao;
import dao.PendonorDao;
import com.dao.ComPekerjaanDao;
import com.entity.ComGolonganDarah;
import com.entity.ComPekerjaan;
import entity.Pendonor;
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
public class PendonorEdit implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        StaffPmi staffPmi = (StaffPmi) session.getAttribute("loginasStaffPmi");
        Pendonor pendonor = null;
        List<ComPekerjaan> pekerjaanList = new ArrayList<>();
        List<ComGolonganDarah> comGolonganDarahList = new ArrayList<>();

        if (staffPmi != null) {
            try {
                PendonorDao pendonorDAO = ConnectionMySQL.getPendonorDao();
                ComPekerjaanDao pekerjaanDAO = ConnectionMySQL.getComPekerjaanDao();
                ComGolonganDarahDao comGolonganDarahDao = ConnectionMySQL.getComGolonganDarahDao();
                
                try {

                    String idStr = request.getParameter("id");
                    if (idStr != null) {
                        Integer idBook = Integer.parseInt(idStr);
                        System.out.println("idpendonor edit : " + idBook);

                        pendonor = pendonorDAO.getPendonorById(idBook);
                    }
                    
                    pekerjaanList = pekerjaanDAO.selectAll();
                    comGolonganDarahList = comGolonganDarahDao.selectAll();
                } catch (Exception ex) {
//                    Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
                }

            } catch (SQLException ex) {
//                Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            return "index.jsp";
        }

        request.setAttribute("pendonorforedit", pendonor);
        request.setAttribute("pekerjaans", pekerjaanList);
        request.setAttribute("golonganDarahs", comGolonganDarahList);

        return "pendonorEdit.jsp";

    }

}
