package action;

import dao.AftapDao;
import entity.Aftap;
import dao.ScreeningDao;
import entity.Screening;
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
public class ScreeningSave implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {

        int pages = 1;
        int recordsPerPage = 10;
        int noOfRecordsAdmin = 0;
        int noOfPagesAdmin = 0;

        List<Screening> screenings = new ArrayList<>();
        HttpSession session = request.getSession(true);
        StaffPmi staffPmi = (StaffPmi) session.getAttribute("loginasStaffPmi");
        String msg;
        String message;
        String action = null;

        if (staffPmi != null) {
            try {
                ScreeningDao screeningDAO = ConnectionMySQL.getScreeningDao();
                AftapDao aftapDAO = ConnectionMySQL.getAftapDao();
                try {
                    String id = request.getParameter("id");

                    String sScreeningNo = request.getParameter("screeningNo");
                    String sScreeningTanggal = request.getParameter("screeningTanggal");
                    String pScreeningHbsag = request.getParameter("screeningHbsag");
                    String pScreeningAntiHiv = request.getParameter("screeningAntiHiv");
                    String pScreeningAntiHcv = request.getParameter("screeningAntiHcv");
                    String pScreeningVprl = request.getParameter("screeningVprl");
                    String pScreeningNoKantong = request.getParameter("screeningNoKantong");

                    Screening oScreening = new Screening();

                    Aftap oAftap = new Aftap();                    
                    oAftap = aftapDAO.selectAftapByBagNumber(pScreeningNoKantong);
                    
                    oScreening.setScreeningNo(sScreeningNo);
                    oScreening.setScreeningTanggal(sScreeningTanggal);
                    oScreening.setScreeningHbsag(pScreeningHbsag);
                    oScreening.setScreeningAntiHiv(pScreeningAntiHiv);
                    oScreening.setScreeningAntiHcv(pScreeningAntiHcv);
                    oScreening.setScreeningVprl(pScreeningVprl);
                    oScreening.setAftap(oAftap);

                    if (id == null) {
                        
                        System.out.println("insert new screening");
                        screeningDAO.insert(oScreening);
                        action = "simpan";
                    } else {
                        System.out.println("update new Screening");
                        oScreening.setScreeningId(Integer.parseInt(id));
                        screeningDAO.update(oScreening);
                        action = "simpan";
                    }

                    try {
                        screenings = screeningDAO.selectAll();
                    } catch (Exception ex) {
                    }

                    msg = "screening has been saved";
                    message = "success";
                    request.setAttribute("message", message);
                    request.setAttribute("action", action);
                    request.setAttribute("screenings", screenings);

                } catch (Exception ex) {
                    msg = "failed to save Screening " + ex.getMessage();
                    message = "failed";
                }
            } catch (SQLException ex) {
                msg = "Failed to save Screening " + ex.getMessage();
                message = "failed";
            }
        } else {
            return "index.jsp";
        }

        request.setAttribute("msg", msg);
        request.setAttribute("message", message);
        request.setAttribute("action", action);
        return "screeningList.jsp";

    }

}
