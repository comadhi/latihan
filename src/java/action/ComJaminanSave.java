package action;

import com.dao.ComJaminanDao;
import com.entity.ComJaminan;
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
public class ComJaminanSave implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {

        int pages = 1;
        int recordsPerPage = 10;
        int noOfRecordsAdmin = 0;
        int noOfPagesAdmin = 0;

        List<ComJaminan> comJaminans = new ArrayList<>();
        HttpSession session = request.getSession(true);
        StaffPmi staffPmi = (StaffPmi) session.getAttribute("loginasStaffPmi");
        String msg;

        if (staffPmi != null) {
            try {
                ComJaminanDao comJaminanDAO = ConnectionMySQL.getComJaminanDao();
                try {
                    String id = request.getParameter("id");

                    String sComJaminanName = request.getParameter("comJaminanName");
                    String sComJaminanDesc = request.getParameter("comJaminanDesc");

                    ComJaminan comJaminan = new ComJaminan();

                    comJaminan.setComJaminanName(sComJaminanName);
                    comJaminan.setComJaminanDesc(sComJaminanDesc);

                    if (id == null) {
                        System.out.println("insert new Golongan Darah");
                        comJaminanDAO.insert(comJaminan);
                    } else {
                        System.out.println("update new Golongan Darah");
                        comJaminan.setComJaminanId(Integer.parseInt(id));
                        comJaminanDAO.update(comJaminan);
                    }

                    try {
                        comJaminans = comJaminanDAO.selectAll();

                        noOfRecordsAdmin = comJaminanDAO.getNoOfRecords();
                        noOfPagesAdmin = (int) Math.ceil(noOfRecordsAdmin * 1.0 / recordsPerPage);
                    } catch (Exception ex) {
//                    Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    msg = "comJaminan has been saved";

//                    request.setAttribute("noOfPagesAdmin", noOfPagesAdmin);
//                    request.setAttribute("noOfPages", noOfPages);
                    request.setAttribute("comJaminans", comJaminans);

                } catch (Exception ex) {
                    msg = "Failed to save ComJaminan " + ex.getMessage();
                }

            } catch (SQLException ex) {
                msg = "Failed to save ComJaminan " + ex.getMessage();
            }
        } else {
            return "index.jsp";
        }

        request.setAttribute("msg", msg);
        return "comJaminanList.jsp";

    }

}
