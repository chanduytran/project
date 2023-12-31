/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Public;

import dao.OrderDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author son22
 */
public class SuccessfulCheckoutController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    try (PrintWriter out = response.getWriter()) {
        String id_raw = request.getParameter("vnp_OrderInfo");

        if (id_raw != null) {
            int id = Integer.parseInt(id_raw);

            if (id == -1) {
                request.setAttribute("notification", "Bạn đã chọn phương thức thanh toán khi nhận hàng.");
                request.getRequestDispatcher("/home").forward(request, response);
            } else {
                OrderDao od = new OrderDao();
                od.updateStatusOrder(id, 2);
                request.setAttribute("notification", "Thanh toán bằng VNPAY thành công.");
                request.getRequestDispatcher("/home").forward(request, response);
            }
        }
    } catch (NumberFormatException e) {
        // Xử lý lỗi nếu việc chuyển đổi "id_raw" thành số gây ra ngoại lệ NumberFormatException.
        // Bạn có thể thêm xử lý tại đây nếu cần thiết.
    } catch (Exception e) {
        // Xử lý các lỗi khác nếu có.
        // Bạn có thể thêm xử lý tại đây nếu cần thiết.
    }
}

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
