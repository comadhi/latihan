package action;

import com.dao.ComJaminanDao;
import com.entity.ComJaminan;
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
public class ComJaminanEdit implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        StaffPmi staffPmi = (StaffPmi) session.getAttribute("loginasStaffPmi");
        ComJaminan comJaminan = null;
//        List<Category> categoryList = new ArrayList<>();

        if (staffPmi != null) {
            try {
                ComJaminanDao comJaminanDAO = ConnectionMySQL.getComJaminanDao();
                try {

                    String idStr = request.getParameter("id");
                    if (idStr != null) {
                        Integer idBook = Integer.parseInt(idStr);
                        System.out.println("idcomJaminan edit : " + idBook);

                        comJaminan = comJaminanDAO.getComJaminanById(idBook);
                    }
                } catch (Exception ex) {
//                    Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
                }

            } catch (SQLException ex) {
//                Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            return "index.jsp";
        }

        request.setAttribute("comJaminanforedit", comJaminan);

        return "comJaminanEdit.jsp";

    }

}
