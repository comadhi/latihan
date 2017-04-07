package action;

import com.dao.ComKomponenDarahDao;
import com.entity.ComKomponenDarah;
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
public class ComKomponenDarahSave implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {

        int pages = 1;
        int recordsPerPage = 10;
        int noOfRecordsAdmin = 0;
        int noOfPagesAdmin = 0;

        List<ComKomponenDarah> comKomponenDarahs = new ArrayList<>();
        HttpSession session = request.getSession(true);
        StaffPmi staffPmi = (StaffPmi) session.getAttribute("loginasStaffPmi");
        String msg;

        if (staffPmi != null) {
            try {
                ComKomponenDarahDao comKomponenDarahDAO = ConnectionMySQL.getComKomponenDarahDao();
                try {
                    String id = request.getParameter("id");

                    String sComKomponenDarahName = request.getParameter("comKomponenDarahName");
                    String sComKomponenDarahDesc = request.getParameter("comKomponenDarahDesc");

                    ComKomponenDarah comKomponenDarah = new ComKomponenDarah();

                    comKomponenDarah.setComKomponenDarahName(sComKomponenDarahName);
                    comKomponenDarah.setComKomponenDarahDesc(sComKomponenDarahDesc);

                    if (id == null) {
                        System.out.println("insert new Golongan Darah");
                        comKomponenDarahDAO.insert(comKomponenDarah);
                    } else {
                        System.out.println("update new Golongan Darah");
                        comKomponenDarah.setComKomponenDarahId(Integer.parseInt(id));
                        comKomponenDarahDAO.update(comKomponenDarah);
                    }

                    try {
                        comKomponenDarahs = comKomponenDarahDAO.selectAll();

                        noOfRecordsAdmin = comKomponenDarahDAO.getNoOfRecords();
                        noOfPagesAdmin = (int) Math.ceil(noOfRecordsAdmin * 1.0 / recordsPerPage);
                    } catch (Exception ex) {
//                    Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    msg = "comKomponenDarah has been saved";

//                    request.setAttribute("noOfPagesAdmin", noOfPagesAdmin);
//                    request.setAttribute("noOfPages", noOfPages);
                    request.setAttribute("comKomponenDarahs", comKomponenDarahs);

                } catch (Exception ex) {
                    msg = "Failed to save ComKomponenDarah " + ex.getMessage();
                }

            } catch (SQLException ex) {
                msg = "Failed to save ComKomponenDarah " + ex.getMessage();
            }
        } else {
            return "index.jsp";
        }

        request.setAttribute("msg", msg);
        return "comKomponenDarahList.jsp";

    }

}
