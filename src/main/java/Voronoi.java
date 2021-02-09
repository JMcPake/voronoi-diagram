import java.awt.Color;

public class Voronoi {

    public static double pointDist(Point2D point1, Point2D point2) {

        int xPoint = Math.abs(point1.getX()-point2.getX());
        int yPoint = Math.abs(point1.getY()-point2.getY());

        return Math.sqrt((yPoint*yPoint)+(xPoint*xPoint));
    }

    public static int findClosestPoint(Point2D point, Point2D[] sites) {
        double min = pointDist(sites[0],point);
        int index = 0;
        for (int i=1; i<sites.length; i++) {
            if (!(pointDist(sites[i],point) == 0)) {

                if (pointDist(sites[i],point)<min) {
                    min = pointDist(sites[i], point);
                    index = i;
                }

            }
        }
        return index;
    }

    public static int[][] buildVoronoiMap(Point2D[] sites, int width, int height) {

        int [] [] indexmap = new int [width] [height];
        for (int i=0; i<width; i++){
            for (int j=0; j<height; j++){
                Point2D point = new Point2D(i,j);
                indexmap[i][j]= findClosestPoint(point,sites);
            }
        }
        return indexmap;
    }

    public static void plotVoronoiMap(Point2D[] sites, Color[] colors, int[][] indexMap) {
        int width = indexMap.length;
        int height = indexMap[0].length;

        StdDraw.setCanvasSize(width, height);
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);
        /*
        StdDraw.setPenColor() to pick the colour of the pixel,
        and StdDraw.point(i, j) to draw a pixel at coordinates (i, j).
        Once you have plotted all the points, set the pen color to Color.BLACK
        and use StdDraw.filledCircle()
         */


        for (int i=0; i<height; i++) {
            for (int j = 0; j < width; j++) {
                StdDraw.setPenColor(colors[indexMap[i][j]]);
                StdDraw.point(i,j);
            }
        }

        for (Point2D site : sites) {
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.filledCircle(site.getX(), site.getY(), 3.0);
        }



    }

    public static int randInt(int max, int min){
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    public static void main(String[] args) {
        int width = 200;
        int height = 200;

        int nSites = 5;
        Point2D[] sites = new Point2D[nSites];
        sites[0] = new Point2D(randInt(width,0),randInt(height,0));
        sites[1] = new Point2D(randInt(width,0),randInt(height,0));
        sites[2] = new Point2D(randInt(width,0),randInt(height,0));
        sites[3] = new Point2D(randInt(width,0),randInt(height,0));
        sites[4] = new Point2D(randInt(width,0),randInt(height,0));

        Color[] colors = new Color[nSites];
        colors[0] = Color.BLUE;
        colors[1] = Color.GREEN;
        colors[2] = Color.YELLOW;
        colors[3] = Color.ORANGE;
        colors[4] = Color.MAGENTA;

        int[][] indexmap = buildVoronoiMap(sites, width, height);
        plotVoronoiMap(sites, colors, indexmap);

    }

}