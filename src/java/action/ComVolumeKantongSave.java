package action;

import com.dao.ComVolumeKantongDao;
import com.entity.ComVolumeKantong;
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
public class ComVolumeKantongSave implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {

        int pages = 1;
        int recordsPerPage = 10;
        int noOfRecordsAdmin = 0;
        int noOfPagesAdmin = 0;

        List<ComVolumeKantong> comVolumeKantongs = new ArrayList<>();
        HttpSession session = request.getSession(true);
        StaffPmi staffPmi = (StaffPmi) session.getAttribute("loginasStaffPmi");
        String msg;

        if (staffPmi != null) {
            try {
                ComVolumeKantongDao comVolumeKantongDAO = ConnectionMySQL.getComVolumeKantongDao();
                try {
                    String id = request.getParameter("id");

                    String sComVolumeKantongName = request.getParameter("comVolumeKantongName");
                    String sComVolumeKantongDesc = request.getParameter("comVolumeKantongDesc");

                    ComVolumeKantong comVolumeKantong = new ComVolumeKantong();

                    comVolumeKantong.setComVolumeKantongName(sComVolumeKantongName);
                    comVolumeKantong.setComVolumeKantongDesc(sComVolumeKantongDesc);

                    if (id == null) {
                        System.out.println("insert new Golongan Darah");
                        comVolumeKantongDAO.insert(comVolumeKantong);
                    } else {
                        System.out.println("update new Golongan Darah");
                        comVolumeKantong.setComVolumeKantongId(Integer.parseInt(id));
                        comVolumeKantongDAO.update(comVolumeKantong);
                    }

                    try {
                        comVolumeKantongs = comVolumeKantongDAO.selectAll();

                        noOfRecordsAdmin = comVolumeKantongDAO.getNoOfRecords();
                        noOfPagesAdmin = (int) Math.ceil(noOfRecordsAdmin * 1.0 / recordsPerPage);
                    } catch (Exception ex) {
//                    Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    msg = "comVolumeKantong has been saved";

//                    request.setAttribute("noOfPagesAdmin", noOfPagesAdmin);
//                    request.setAttribute("noOfPages", noOfPages);
                    request.setAttribute("comVolumeKantongs", comVolumeKantongs);

                } catch (Exception ex) {
                    msg = "Failed to save ComVolumeKantong " + ex.getMessage();
                }

            } catch (SQLException ex) {
                msg = "Failed to save ComVolumeKantong " + ex.getMessage();
            }
        } else {
            return "index.jsp";
        }

        request.setAttribute("msg", msg);
        return "comVolumeKantongList.jsp";

    }

}
