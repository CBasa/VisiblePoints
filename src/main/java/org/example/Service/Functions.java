package org.example.Service;

import org.example.Model.Point;

import java.util.ArrayList;
import java.util.Optional;

public class Functions {

    public ArrayList<Point> visiblePoints(int point, int degrees, int maxDirection) throws Exception {
        if (degrees > 180) {
            throw new Exception("Value of degrees cannot be greater than 180");
        }

        ArrayList<Point> dataset = dataset();
        ArrayList<Point> visiblePoints = new ArrayList<>();

        // 1. Set given point as origin point (0,0)
        Point origin;
        Optional<Point> pointFromDataset = dataset.stream()
                .filter(p -> p.getNumber()==point)
                .findFirst();

        if (pointFromDataset.isPresent()) {
            origin = pointFromDataset.get();
            dataset.removeIf(p -> p.getNumber()==point);
        } else {
            throw new Exception("Point provided does not exist in dataset");
        }

        dataset.stream().forEach(p -> {
            // 2. Adjust co-ordinates of point from dataset according to new origin point e.g. minus given point co-ordinates from dataset point co-ordinates
            double x = (p.getX() - origin.getX());
            double y = (p.getY() - origin.getY());

            // 3. Convert to the adjusted dataset point to polar co-ordinates, where r is the distance from origin and theta is the angle in radians
            double r = Math.hypot(x,y);

            // 4. Based on the direction of the given point, calculate the range of theta valid for the visible cone based on the given degrees and maximumDirection
            if (r <= maxDirection) {

                //if degrees is 180 then visible cone is effectively a circle and any point within radius is added to visiblePoints arraylist
                if (degrees == 180){
                    visiblePoints.add(p);
                } else {

                    double minTheta;
                    double maxTheta;
                    double theta = Math.atan2(y, x);

                    // 5. If both r and theta for the given dataset point fall within the calculated range of the visible cone, then add to visiblePoints arraylist
                    switch (origin.getDirection()) {
                        case NORTH:
                            minTheta = Math.toRadians(90 - degrees);
                            maxTheta = Math.toRadians(90 + degrees);
                            if (minTheta <= theta && theta <= maxTheta) {
                                visiblePoints.add(p);
                            }
                            break;
                        case EAST:
                            minTheta = Math.toRadians(degrees);
                            maxTheta = -Math.toRadians(degrees);
                            if (minTheta <= theta && theta <= maxTheta) {
                                visiblePoints.add(p);
                            }

                            break;
                        case SOUTH:
                            minTheta = -Math.toRadians(90 - degrees);
                            maxTheta = -Math.toRadians(90 + degrees);
                            if (minTheta <= theta && theta <= maxTheta) {
                                visiblePoints.add(p);
                            }
                            break;
                        case WEST:
                            minTheta = Math.toRadians(180 - degrees);
                            maxTheta = Math.PI;
                            double minTheta2 = -Math.toRadians(180 - degrees);
                            double maxTheta2 = -Math.PI;
                            if ((minTheta <= theta && theta <= maxTheta) || (minTheta2 <= theta && theta <= maxTheta2)) {
                                visiblePoints.add(p);
                            }
                            break;
                    }
                }
            }
        });
        visiblePoints.stream().forEach(System.out::println);
        return visiblePoints;

    }

    private ArrayList<Point> dataset() {
        ArrayList<Point> dataset = new ArrayList<>();
        dataset.add(new Point(28,42,1, Point.Direction.NORTH));
        dataset.add(new Point(27,46,2, Point.Direction.EAST));
        dataset.add(new Point(16,22,3, Point.Direction.SOUTH));
        dataset.add(new Point(40,50,4, Point.Direction.WEST));
        dataset.add(new Point(8,6,5, Point.Direction.NORTH));
        dataset.add(new Point(6,19,6, Point.Direction.EAST));
        dataset.add(new Point(28,5,7, Point.Direction.SOUTH));
        dataset.add(new Point(39,36,8, Point.Direction.WEST));
        dataset.add(new Point(12,34,9, Point.Direction.NORTH));
        dataset.add(new Point(36,20,10, Point.Direction.EAST));
        dataset.add(new Point(22,47,11, Point.Direction.SOUTH));
        dataset.add(new Point(33,19,12, Point.Direction.WEST));
        dataset.add(new Point(41,18,13, Point.Direction.NORTH));
        dataset.add(new Point(41,34,14, Point.Direction.EAST));
        dataset.add(new Point(14,29,15, Point.Direction.SOUTH));
        dataset.add(new Point(6,49,16, Point.Direction.WEST));
        dataset.add(new Point(46,50,17, Point.Direction.NORTH));
        dataset.add(new Point(17,40,18, Point.Direction.EAST));
        dataset.add(new Point(28,26,19, Point.Direction.SOUTH));
        dataset.add(new Point(2,12,20, Point.Direction.WEST));
        return dataset;
    }
}
