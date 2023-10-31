package points;

//import jakarta.servlet.http.HttpSession;
import javax.servlet.http.HttpSession;

import java.util.LinkedList;
import java.util.List;

public class PointsCollectionManager {
    private List<Point> collection;

    public PointsCollectionManager() {
        collection = new LinkedList<>();
    }

    public void add(Point point) {
        collection.add(point);
    }

    public void clear() {
        collection.clear();
    }

    public List<Point> getCollection() {
        return collection;
    }

    public Point getLast() {
        return collection.get(collection.size() - 1);
    }

    public boolean isEmpty() {
        return collection.isEmpty();
    }

    public static PointsCollectionManager getPointsCollection(HttpSession session) {
        PointsCollectionManager pointsCollection = (PointsCollectionManager) session.getAttribute("points");
        if (pointsCollection == null) {
            pointsCollection = new PointsCollectionManager();
            session.setAttribute("points", pointsCollection);
        }
        return pointsCollection;
    }

    public static void setPointsCollection(HttpSession session, PointsCollectionManager pointsCollection) {
        session.setAttribute("points", pointsCollection);
    }
}