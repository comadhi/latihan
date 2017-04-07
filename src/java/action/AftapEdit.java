package action;

import dao.AftapDao;
import com.dao.ComPekerjaanDao;
import com.dao.ComVolumeKantongDao;
import com.entity.ComPekerjaan;
import com.entity.ComVolumeKantong;
import dao.PendonorDao;
import entity.Aftap;
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
public class AftapEdit implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        StaffPmi staffPmi = (StaffPmi) session.getAttribute("loginasStaffPmi");
        Aftap aftap = null;
        List<ComVolumeKantong> volumekantongs = new ArrayList<>();
        Pendonor pendonor = null;
        String paramPendonorId = request.getParameter("pendonorId");

        if (staffPmi != null) {
            try {
                AftapDao aftapDAO = ConnectionMySQL.getAftapDao();
                ComVolumeKantongDao comVolumeKantongDAO = ConnectionMySQL.getComVolumeKantongDao();
                
                PendonorDao pendonorDao = ConnectionMySQL.getPendonorDao();
                                
                try {

                    String idStr = request.getParameter("id");
                    if (idStr != null) {
                        Integer idBook = Integer.parseInt(idStr);
                        System.out.println("idaftap edit : " + idBook);

                        aftap = aftapDAO.getAftapById(idBook);
                    }
                    
                    pendonor = pendonorDao.selectPendonorById(Integer.parseInt(paramPendonorId));
                    volumekantongs = comVolumeKantongDAO.selectAll();
                } catch (Exception ex) {
//                    Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
                }

            } catch (SQLException ex) {
//                Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            return "index.jsp";
        }

//        request.setAttribute("pendonorId", paramPendonorId);
        request.setAttribute("volumekantongs", volumekantongs);
        request.setAttribute("aftapforedit", aftap);
        request.setAttribute("pendonor", pendonor);

        return "aftapEdit.jsp";

    }

}
