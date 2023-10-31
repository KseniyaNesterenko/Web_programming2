package com.example.web_2.servlets;

import points.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.ChronoUnit.MINUTES;

@WebServlet(name = "area-check-servlet", value = "/area-check-servlet")
public class AreaCheckServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter printWriter = response.getWriter();
        long timer = System.nanoTime();
        try {
            float x = Float.parseFloat(request.getParameter("x-value"));
            float y = Float.parseFloat(request.getParameter("y-value"));
            float r = Float.parseFloat(request.getParameter("r-value"));

            log("X: " + x);
            log("Y: " + y);
            log("R: " + r);

            String status = isHit(x, y, r);
            int timezone = Integer.parseInt(request.getParameter("timezone"));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            String currentTime = formatter.format(LocalDateTime.now().plus(timezone, MINUTES));
            long scriptTime = (System.nanoTime() - timer) / 1000;

            Object collection = getServletContext().getAttribute("points");
            if (collection == null){
                collection = new PointsCollectionManager();
            }
            PointsCollectionManager pointsCollection = (PointsCollectionManager) collection;
            Point newPoint = new Point(x, y, r, currentTime, scriptTime, status);
            pointsCollection.add(newPoint);
            log("Shot successfully added");

            getServletContext().setAttribute("points", pointsCollection);

            String responseBody = newPoint.toJSON();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            printWriter.write(responseBody);
            printWriter.flush();
        } catch (NumberFormatException ignored) {

        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter printWriter = response.getWriter();
        printWriter.write("This method is not supported");
        printWriter.flush();
    }




    private String isHit(double x, double y, double r) {
        if (isFirstZone(x, y, r) && isSecondZone(x, y, r) && isThirdZone(x, y, r)){
            return "попадание!";
        }
        return "промах!";
    }

    //1 четверть
    private boolean isFirstZone(double x, double y, double r) {
        return ((x >= 0) && (y >= 0));
    }

    //3 четверть
    private boolean isSecondZone(double x, double y, double r) {
        return ((((x*x) + (y*y)) <= (r*r)) && (x >= -r) && (y <= 0));
    }

    //4 четверть
    private boolean isThirdZone(double x, double y, double r) {
        return ((x >= 0) && (y <= (-r/2)) && (x <= r) && (y <= 0));
    }
}
