package action;

import com.dao.ComKomponenDarahDao;
import dao.KomponenDao;
import com.entity.ComKomponenDarah;
import entity.Komponen;
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
public class KomponenEdit implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        StaffPmi staffPmi = (StaffPmi) session.getAttribute("loginasStaffPmi");
        Komponen komponen = null;
        List<ComKomponenDarah> comkomponendarahs = new ArrayList<>();

        if (staffPmi != null) {
            try {
                KomponenDao komponenDAO = ConnectionMySQL.getKomponenDao();
                ComKomponenDarahDao komponenDarahDAO = ConnectionMySQL.getComKomponenDarahDao();
                
                try {

                    String idStr = request.getParameter("id");
                    if (idStr != null) {
                        Integer idBook = Integer.parseInt(idStr);
                        System.out.println("idkomponen edit : " + idBook);

                        komponen = komponenDAO.getKomponenById(idBook);
                    }
                    
                    comkomponendarahs = komponenDarahDAO.selectAll();
                } catch (Exception ex) {
//                    Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
                }

            } catch (SQLException ex) {
//                Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            return "index.jsp";
        }

        request.setAttribute("komponenforedit", komponen);
        request.setAttribute("comkomponendarahs", comkomponendarahs);

        return "komponenEdit.jsp";

    }

}
