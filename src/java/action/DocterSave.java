package action;

import dao.DocterDao;
import entity.Hospital;
import entity.Docter;
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
public class DocterSave implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {

        int pages = 1;
        int page = 1;
        int recordsPerPage = 10;
        int noOfRecordsAdmin = 0;
        int noOfPagesAdmin = 0;

        List<Docter> docters = new ArrayList<>();
        HttpSession session = request.getSession(true);
        Hospital administrator = (Hospital) session.getAttribute("loginashospital");
        String msg;

        if (administrator != null) {
            try {
                DocterDao docterDAO = ConnectionMySQL.getDocterDao();
                try {
                    String id = request.getParameter("id");

                    String sDocterName = request.getParameter("docterName");
                    String sDocterAddress = request.getParameter("docterAddress");
                    String sDocterTelp = request.getParameter("docterTelp");

                    Docter docter = new Docter();

                    docter.setDocterName(sDocterName);
                    docter.setDocterAddress(sDocterAddress);
                    docter.setDocterTelp(sDocterTelp);

                    if (id == null) {
                        System.out.println("insert new docter");
                        docterDAO.insert(docter);
                    } else {
                        System.out.println("update new docter");
                        docter.setDocterId(Integer.parseInt(id));
                        docterDAO.update(docter);
                    }

                    try {
//                        docters = docterDAO.selectAll();
//
//                        noOfRecordsAdmin = docterDAO.getNoOfRecords();
//                        noOfPagesAdmin = (int) Math.ceil(noOfRecordsAdmin * 1.0 / recordsPerPage);

                        docters = docterDAO.selectAllWithLimit((page - 1) * recordsPerPage, recordsPerPage);
                        System.out.println("docters " + docters.size());

                        int noOfRecords = docterDAO.getNoOfRecords();
                        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

                        request.setAttribute("noOfPages", noOfPages);
                        request.setAttribute("currentPage", page);
                    } catch (Exception ex) {
//                    Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    msg = "Docter has been saved";

//                    request.setAttribute("noOfPagesAdmin", noOfPagesAdmin);
//                    request.setAttribute("noOfPages", noOfPages);
                    request.setAttribute("docters", docters);
                    return "docterList.jsp";

                } catch (Exception ex) {
                    msg = "Failed to save docter " + ex.getMessage();
                }

            } catch (SQLException ex) {
                msg = "Failed to save docter " + ex.getMessage();
            }
        } else {
            return "index.jsp";
        }

        request.setAttribute("msg", msg);
        return "docterList.jsp";

    }

}
