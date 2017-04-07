package action;

import com.entity.ComGolonganDarah;
import dao.PatientDao;
import entity.Hospital;
import entity.Patient;
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
public class PatientSave implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {

        int pages = 1;
        int recordsPerPage = 10;
        int noOfRecordsAdmin = 0;
        int noOfPagesAdmin = 0;

        List<Patient> patients = new ArrayList<>();
        HttpSession session = request.getSession(true);
        Hospital administrator = (Hospital) session.getAttribute("loginashospital");
        String msg;

        if (administrator != null) {
            try {
                PatientDao patientDAO = ConnectionMySQL.getPatientDao();
                try {
                    String id = request.getParameter("id");

                    String sPatientNo = request.getParameter("patientNo");
                    String sPatientName = request.getParameter("patientName");
                    String sPatientAddress = request.getParameter("patientAddress");
                    String pPendonorGolonganDarah = request.getParameter("patientGolonganDarah");

                    Patient patient = new Patient();
                    
                    ComGolonganDarah oComGolonganDarah = new ComGolonganDarah();
                    oComGolonganDarah.setComGolonganDarahId(Integer.parseInt(pPendonorGolonganDarah));

                    patient.setComGolonganDarah(oComGolonganDarah);
                    patient.setPatientNo(sPatientNo);
                    patient.setPatientName(sPatientName);
                    patient.setPatientAddress(sPatientAddress);

                    if (id == null) {
                        System.out.println("insert new patient");
                        patientDAO.insert(patient);
                    } else {
                        System.out.println("update new patient");
                        patient.setPatientId(Integer.parseInt(id));
                        patientDAO.update(patient);
                    }

                    try {
                        patients = patientDAO.selectAll();

                        noOfRecordsAdmin = patientDAO.getNoOfRecords();
                        noOfPagesAdmin = (int) Math.ceil(noOfRecordsAdmin * 1.0 / recordsPerPage);
                    } catch (Exception ex) {
//                    Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    msg = "Patient has been saved";

//                    request.setAttribute("noOfPagesAdmin", noOfPagesAdmin);
//                    request.setAttribute("noOfPages", noOfPages);
                    request.setAttribute("patients", patients);

                } catch (Exception ex) {
                    msg = "Failed to save patient " + ex.getMessage();
                }

            } catch (SQLException ex) {
                msg = "Failed to save patient " + ex.getMessage();
            }
        } else {
            return "index.jsp";
        }

        request.setAttribute("msg", msg);
        return "patientList.jsp";

    }

}
