package action;

import dao.ScreeningDao;
import com.dao.ComPekerjaanDao;
import com.entity.ComPekerjaan;
import entity.Screening;
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
public class ScreeningEdit implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        StaffPmi staffPmi = (StaffPmi) session.getAttribute("loginasStaffPmi");
        Screening screening = null;
        List<ComPekerjaan> pekerjaanList = new ArrayList<>();

        if (staffPmi != null) {
            try {
                ScreeningDao screeningDAO = ConnectionMySQL.getScreeningDao();
                ComPekerjaanDao pekerjaanDAO = ConnectionMySQL.getComPekerjaanDao();
                
                try {

                    String idStr = request.getParameter("id");
                    if (idStr != null) {
                        Integer idBook = Integer.parseInt(idStr);
                        System.out.println("idscreening edit : " + idBook);

                        screening = screeningDAO.getScreeningById(idBook);
                    }
                    
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

        request.setAttribute("screeningforedit", screening);
        request.setAttribute("pekerjaans", pekerjaanList);

        return "screeningEdit.jsp";

    }

}
