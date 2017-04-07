package action;

import dao.DocterDao;
import entity.Hospital;
import entity.Docter;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class DocterDelete implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Hospital hospital = (Hospital) session.getAttribute("loginashospital");
        String msg = "Failed to delete docter";
        List<Docter> docters = new ArrayList<>();

        if (hospital != null) {
            try {
                DocterDao docterDAO = ConnectionMySQL.getDocterDao();

                try {

                    String idStr = request.getParameter("id");
                    if (idStr != null) {
                        Integer idDocter = Integer.parseInt(idStr);
                        System.out.println("iddocter delete : " + idDocter);
                        Docter docter = docterDAO.getDocterById(idDocter);

                        docterDAO.delete(idDocter);
                        msg = "docter has been delete";

                        try {
                            docters = docterDAO.selectAll();
                        } catch (Exception ex) {
                        }

                        msg = "Docter has been saved";
                        request.setAttribute("docters", docters);

                    }

                } catch (Exception ex) {
                    msg = "Failed to delete docter " + ex.getMessage();
//                    Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
                }

            } catch (SQLException ex) {
                msg = "Failed to delete docter " + ex.getMessage();
//                Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            return "index.jsp";
        }

        request.setAttribute("msg", msg);
        return "docterList.jsp";

    }

}
