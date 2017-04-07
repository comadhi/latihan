package action;

import com.dao.ComVolumeKantongDao;
import com.entity.ComVolumeKantong;
import dao.AftapDao;
import entity.Aftap;
import entity.Pendonor;
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
public class AftapSave implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {

        int pages = 1;
        int recordsPerPage = 10;
        int noOfRecordsAdmin = 0;
        int noOfPagesAdmin = 0;

        List<Aftap> aftaps = new ArrayList<>();
        HttpSession session = request.getSession(true);
        StaffPmi staffPmi = (StaffPmi) session.getAttribute("loginasStaffPmi");
        Date date;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<ComVolumeKantong> volumekantongs = new ArrayList<>();
        String msg;
        String message;
        String action = null;

        if (staffPmi != null) {
            try {
                AftapDao aftapDAO = ConnectionMySQL.getAftapDao();
                ComVolumeKantongDao comVolumeKantongDAO = ConnectionMySQL.getComVolumeKantongDao();
                try {
                    String id = request.getParameter("id");

                    String sPendonorId = request.getParameter("pendonorId");
                    String sAftapNoKantong = request.getParameter("aftapNoKantong");
                    String sAftapTanggal = request.getParameter("aftapTanggal");
                    String sAftapStatusAmbil = request.getParameter("aftapStatusAmbil");
                    String pAftapReaksi = request.getParameter("aftapReaksi");
                    String pAftapStatusDonor = request.getParameter("aftapStatusDonor");
                    String pAftapVolumeKantong = request.getParameter("aftapVolumeKantong");

                    Aftap oAftap = new Aftap();

                    ComVolumeKantong oComVolumeKantong = new ComVolumeKantong();
                    oComVolumeKantong.setComVolumeKantongId(Integer.parseInt(pAftapVolumeKantong));
                    volumekantongs = comVolumeKantongDAO.selectAll();

                    Pendonor oPendonor = new Pendonor();

                    oPendonor.setPendonorId(Integer.parseInt(sPendonorId));

                    oAftap.setPendonor(oPendonor);
                    oAftap.setAftapNoKantong(sAftapNoKantong);
                    oAftap.setAftapTanggal(sAftapTanggal);
                    oAftap.setAftapStatusAmbil(sAftapStatusAmbil);
                    oAftap.setAftapReaksi(pAftapReaksi);
                    oAftap.setAftapStatusDonor(pAftapStatusDonor);
                    oAftap.setComVolumeKantong(oComVolumeKantong);

                    boolean existBagNumber = aftapDAO.isExistByBagNumber(sAftapNoKantong);
                    if (!"".equals(sAftapNoKantong)) {
                        if (id == null && !existBagNumber) {
                            System.out.println("insert new aftap");
                            aftapDAO.insert(oAftap);
                            action = "simpan";
                        } else {
                            message = "Duplicate Data";
                            request.setAttribute("message", message);
                            request.setAttribute("volumekantongs", volumekantongs);
                            return "aftapEdit.jsp";
                        }
                    } else {
                        
                        message = "Please insert no bag";
                        request.setAttribute("message", message);
                        request.setAttribute("volumekantongs", volumekantongs);
                        return "aftapEdit.jsp";
                    }

                    if (id != null) {
                        System.out.println("update new Aftap");
                        oAftap.setAftapId(Integer.parseInt(id));
                        aftapDAO.update(oAftap);
                        action = "simpan";
                    }

                    try {
                        aftaps = aftapDAO.selectAll();
                    } catch (Exception ex) {
                    }

                    msg = "aftap has been saved";
                    message = "success";
                    request.setAttribute("message", message);
                    request.setAttribute("action", action);
                    request.setAttribute("aftaps", aftaps);

                } catch (Exception ex) {
                    msg = "failed to save Aftap " + ex.getMessage();
                    message = "failed";
                }
            } catch (SQLException ex) {
                msg = "Failed to save Aftap " + ex.getMessage();
                message = "failed";
            }
        } else {
            return "index.jsp";
        }

        request.setAttribute("msg", msg);
        request.setAttribute("message", message);
        request.setAttribute("action", action);
        return "aftapList.jsp";

    }

}
