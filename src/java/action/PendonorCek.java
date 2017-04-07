package action;

import dao.AftapDao;
import com.dao.ComPekerjaanDao;
import com.entity.ComPekerjaan;
import dao.PendonorDao;
import dao.PeriksaDao;
import entity.Aftap;
import entity.Pendonor;
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
public class PendonorCek implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        StaffPmi staffPmi = (StaffPmi) session.getAttribute("loginasStaffPmi");
        Aftap oAftap = null;
        Pendonor oPendonor = null;
        List<ComPekerjaan> pekerjaanList = new ArrayList<>();
        String msg = null;
        String message = null;
        String sModule = null;
        String sSetModule = null;

        if (staffPmi != null) {
            try {

                String sPendonorNo = request.getParameter("pendonorId");
                sModule = request.getParameter("module");

                AftapDao aftapDAO = ConnectionMySQL.getAftapDao();
                PeriksaDao aftapDao = ConnectionMySQL.getPeriksaDao();

                ComPekerjaanDao pekerjaanDAO = ConnectionMySQL.getComPekerjaanDao();

                PendonorDao pendonorDAO = ConnectionMySQL.getPendonorDao();

                try {
                    oAftap = aftapDAO.checkPendonorById(sPendonorNo);

                    if (oAftap != null) {
                        msg = "Pendonor sudah waktunya donor.";
                        message = "success";

                        try {
                            if (null != sModule) switch (sModule) {
                                case "periksa":
                                    if (sPendonorNo != null) {
                                        boolean bAftapAvailable = aftapDao.isAvailableDonatorPerToday(sPendonorNo);
                                        
                                        if (bAftapAvailable) {
                                            msg = "Sudah memasukkan data pemeriksaan";
                                            message = "sudahPeriksa";
                                            request.setAttribute("sModule", sModule);
                                            request.setAttribute("msg", msg);
                                            request.setAttribute("message", message);
                                            return "pendonorCek.jsp";
                                        }
                                    }   break;
                                case "aftap":
                                    if (sPendonorNo != null) {
                                        boolean bAftapAvailable = aftapDao.isAvailableDonatorPerToday(sPendonorNo);
                                        
                                        if (!bAftapAvailable) {
                                            msg = "Belum memasukkan data pemeriksaan";
                                            message = "belumPeriksa";
                                            request.setAttribute("sModule", sModule);
                                            request.setAttribute("msg", msg);
                                            request.setAttribute("message", message);
                                            return "pendonorCek.jsp";
                                        }
                                    }   break;
                                default:
                                    request.setAttribute("sModule", sModule);
                                    request.setAttribute("msg", msg);
                                    request.setAttribute("message", message);
                                    request.setAttribute("pendonorforcek", oAftap);
                                    return "pendonorCek.jsp";
                            }

                        } catch (Exception ex) {
                            Logger.getLogger(PendonorCek.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } else if (sPendonorNo != null) {
                        msg = "Pendonor belum waktunya donor!";
                        message = "failed";
                    } else {
                        msg = "";
                    }
                } catch (Exception ex) {
                    Logger.getLogger(PendonorCek.class.getName()).log(Level.SEVERE, null, ex);
                }

            } catch (SQLException ex) {
//                Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            return "index.jsp";
        }

        request.setAttribute("sModule", sModule);
        request.setAttribute("msg", msg);
        request.setAttribute("message", message);
        request.setAttribute("pendonorforcek", oAftap);
//        request.setAttribute("pekerjaans", pekerjaanList);

        return "pendonorCek.jsp";

    }

}
