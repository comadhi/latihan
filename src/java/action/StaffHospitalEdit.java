package action;

import dao.StaffHospitalDao;
import entity.StaffHospital;
import entity.Hospital;
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
public class StaffHospitalEdit implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Hospital hospital = (Hospital) session.getAttribute("loginashospital");
        StaffHospital staffHospital = null;
//        List<Category> categoryList = new ArrayList<>();

        if (hospital != null) {
            try {
                StaffHospitalDao staffHospitalDAO = ConnectionMySQL.getStaffHospitalDao();
//                CategoryDao categoryDAO = ConnectionMySQL.getCategoryDao();

                try {

                    String idStr = request.getParameter("id");
                    if (idStr != null) {
                        Integer idBook = Integer.parseInt(idStr);
                        System.out.println("idstaffHospital edit : " + idBook);

                        staffHospital = staffHospitalDAO.getStaffHospitalById(idBook);
                    }

//                    categoryList = categoryDAO.selectAll();
//                    System.out.println("categoryList " + categoryList.size());

//                    Date date = new Date();
//                    SimpleDateFormat frm = new SimpleDateFormat("YYYY");
//                    String DateStr = frm.format(date);
//                    Integer DateInt = Integer.parseInt(DateStr) + 1;
//                    List<String> listYear = new ArrayList();
//                    for (int i = 0; i < 20; i++) {
//                        DateInt -= 1;
//                        listYear.add(Integer.toString(DateInt));
//                    }
//                    System.out.println("listYear " + listYear.size());
//                    request.setAttribute("yearforedit", listYear);

                } catch (Exception ex) {
//                    Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
                }

            } catch (SQLException ex) {
//                Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            return "index.jsp";
        }

        request.setAttribute("staffHospitalforedit", staffHospital);
//        request.setAttribute("categoryforedit", categoryList);

        return "staffHospitalEdit.jsp";

    }

}
