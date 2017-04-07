package action;

import com.dao.ComGolonganDarahDao;
import com.entity.ComGolonganDarah;
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
public class ComGolonganDarahSave implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {

        int pages = 1;
        int recordsPerPage = 10;
        int noOfRecordsAdmin = 0;
        int noOfPagesAdmin = 0;

        List<ComGolonganDarah> comGolonganDarahs = new ArrayList<>();
        HttpSession session = request.getSession(true);
        StaffPmi staffPmi = (StaffPmi) session.getAttribute("loginasStaffPmi");
        String msg;

        if (staffPmi != null) {
            try {
                ComGolonganDarahDao comGolonganDarahDAO = ConnectionMySQL.getComGolonganDarahDao();
                try {
                    String id = request.getParameter("id");

                    String sComGolonganDarahName = request.getParameter("comGolonganDarahName");
                    String sComGolonganDarahDesc = request.getParameter("comGolonganDarahDesc");

                    ComGolonganDarah comGolonganDarah = new ComGolonganDarah();

                    comGolonganDarah.setComGolonganDarahName(sComGolonganDarahName);
                    comGolonganDarah.setComGolonganDarahDesc(sComGolonganDarahDesc);

                    if (id == null) {
                        System.out.println("insert new Golongan Darah");
                        comGolonganDarahDAO.insert(comGolonganDarah);
                    } else {
                        System.out.println("update new Golongan Darah");
                        comGolonganDarah.setComGolonganDarahId(Integer.parseInt(id));
                        comGolonganDarahDAO.update(comGolonganDarah);
                    }

                    try {
                        comGolonganDarahs = comGolonganDarahDAO.selectAll();

                        noOfRecordsAdmin = comGolonganDarahDAO.getNoOfRecords();
                        noOfPagesAdmin = (int) Math.ceil(noOfRecordsAdmin * 1.0 / recordsPerPage);
                    } catch (Exception ex) {
//                    Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    msg = "comGolonganDarah has been saved";

//                    request.setAttribute("noOfPagesAdmin", noOfPagesAdmin);
//                    request.setAttribute("noOfPages", noOfPages);
                    request.setAttribute("comGolonganDarahs", comGolonganDarahs);

                } catch (Exception ex) {
                    msg = "Failed to save ComGolonganDarah " + ex.getMessage();
                }

            } catch (SQLException ex) {
                msg = "Failed to save ComGolonganDarah " + ex.getMessage();
            }
        } else {
            return "index.jsp";
        }

        request.setAttribute("msg", msg);
        return "comGolonganDarahList.jsp";

    }

}
