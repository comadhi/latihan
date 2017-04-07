package action;

import dao.DocterDao;
import entity.Docter;
import entity.Hospital;
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
public class DocterList implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {

        int page = 1;
        int recordsPerPage = 10;
        int noOfRecordsAdmin = 0;
        int noOfPagesAdmin = 0;

        List<Docter> docters = new ArrayList<>();

        HttpSession session = request.getSession(true);
        Hospital hospital = (Hospital) session.getAttribute("loginashospital");
//
        if (hospital != null) {
            try {

                DocterDao docterDao = ConnectionMySQL.getDocterDao();
                try {

                    String pageStr = request.getParameter("page");
                    if (pageStr != null) {
                        page = Integer.parseInt(pageStr);
                    }
                    request.setAttribute("currentPage", page);

                    //books = bookDAO.selectAll();
                    docters = docterDao.selectAllWithLimit((page - 1) * recordsPerPage, recordsPerPage);
                    System.out.println("docters " + docters.size());

                    int noOfRecords = docterDao.getNoOfRecords();
                    int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

                    request.setAttribute("noOfPages", noOfPages);
                    request.setAttribute("currentPage", page);
                } catch (SQLException ex) {

                }

                request.setAttribute("docters", docters);

                return "docterList.jsp";

            } catch (SQLException ex) {
//                Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(DocterList.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            return "index.jsp";
        }

        return "docterList.jsp";
    }

}
