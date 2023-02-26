import java.util.Arrays;

/*
 * Code that draws a 2-D GRID of black and white squares.
 * for example:
 * Grid2D grid = new Grid2D(7,8,50);
 * will draw a grid with 7 rows and 8 columns of cell size 50 (pixels)
 *
 *
 * The code includes a MouseListener to show how you might handle mouse clicks
 * when a user clicks somewhere on the grid.
 *
 * The code also includes an example of shading in cells for possible maze solving programs.
 * (this can be commented out or removed)
 *
 * @author Joe Polacco
 * @author Tanisha Gupta
 */
public class Grid2D
{

    private int rows, cols, cellSize;
    private static boolean mousePressed = false;

    public Grid2D(int rows, int cols, int cellSize)
    {

        this.rows = rows;
        this.cols = cols;
        this.cellSize = cellSize;
        StdDraw.setCanvasSize(cols*cellSize, rows*cellSize);

    }

    public int getHeight(){
        return rows*cellSize;
    }

    public int getWidth(){
        return cols*cellSize;
    }

    public void drawGrid(){
        double deltax = 1.0/cols;
        double deltay = 1.0/rows;
        StdDraw.setPenRadius(0.0005);

        for (int c = 1; c < cols; c++){
            StdDraw.line(c*deltax,0.0,c*deltax,1.0);
        }

        for (int r = 1; r < rows; r++){
            StdDraw.line(0.0,r*deltay,1.0,r*deltay);
        }
    }

    public void mazify(int[][] cells){
        for (int r = 0; r < cells.length; r++)
            for (int c = 0; c < cells[r].length; c++){
                if (cells[r][c] == 0)
                    shadeInCell(r,c);
            }
    }

    public void shadeInCell(int row, int col){
        double deltax = 1.0/cols;
        double deltay = 1.0/rows;
        double[] x = {col*deltax,col*deltax, (col+1)*deltax, (col+1)*deltax};
        double[] y = {1-row*deltay,1-(row+1)*deltay, 1-(row+1)*deltay,1-row*deltay};
        StdDraw.filledPolygon(x,y);
    }

    public static void myArraysprint(int[][] grid) {
        int i = 0;
        for (i =0; i < grid.length; i++) {
            System.out.println(Arrays.toString(grid[i]));
        }
    }

    //
    public static void main(String[] args){
        Grid2D grid = new Grid2D(10,10,50);
        grid.drawGrid();

        int[][] gridVis = new int[10][10];
        for(int i = 0; i < gridVis.length; i++) {
            for(int j = 0; j < gridVis.length; j++) {
                gridVis[i][j] = (int)(Math.random()*(100-1+1)+1) % 2;
            }
        }

        System.out.println("Input grid:");
        myArraysprint(gridVis);
        grid.mazify(gridVis);

        int randInt1 = (int)(Math.random()*(gridVis.length-1));
        int randInt2 = (int)(Math.random()*(gridVis.length-1));
        System.out.println("Randomly chosen cell: " + randInt1 + ", " + randInt2);
        DepthFirstSearch dfs = new DepthFirstSearch(gridVis, randInt1, randInt2);

        System.out.println("Output Grid:");
        myArraysprint(dfs.grid());

        StdDraw.pause(60000);

        grid.mazify(dfs.grid());
    }

    /* OUTPUT:
    Input grid:
    [1, 0, 1, 0, 1]
    [0, 1, 1, 1, 1]
    [1, 1, 1, 1, 1]
    [1, 1, 1, 1, 1]
    [1, 1, 1, 1, 0]
    Randomly chosen cell: 2, 3
    Output Grid:
    [1, 0, 0, 0, 0]
    [0, 0, 0, 0, 0]
    [0, 0, 0, 0, 0]
    [0, 0, 0, 0, 0]
    [0, 0, 0, 0, 0]

    ==========
    Input grid:
    [1, 0, 1, 1, 0]
    [0, 0, 1, 0, 0]
    [0, 1, 1, 0, 1]
    [0, 0, 1, 0, 1]
    [1, 1, 0, 1, 1]
    Randomly chosen cell: 1, 2
    Output Grid:
    [1, 0, 0, 0, 0]
    [0, 0, 0, 0, 0]
    [0, 0, 0, 0, 1]
    [0, 0, 0, 0, 1]
    [1, 1, 0, 1, 1]

    ======================

    Input grid:
    [0, 0, 0, 1, 1, 0, 1, 0, 0, 1]
    [1, 1, 0, 1, 0, 0, 1, 1, 1, 1]
    [1, 0, 1, 0, 1, 0, 0, 1, 1, 1]
    [0, 0, 1, 0, 1, 0, 1, 1, 0, 1]
    [0, 0, 1, 1, 0, 0, 1, 1, 1, 0]
    [0, 1, 1, 1, 0, 1, 0, 1, 0, 0]
    [0, 1, 1, 0, 1, 1, 1, 1, 0, 0]
    [1, 0, 1, 1, 0, 1, 1, 0, 1, 1]
    [0, 0, 0, 1, 1, 0, 1, 1, 0, 1]
    [0, 1, 0, 0, 0, 0, 1, 1, 0, 0]
    Randomly chosen cell: 2, 0
    Output Grid:
    [0, 0, 0, 1, 1, 0, 1, 0, 0, 1]
    [0, 0, 0, 1, 0, 0, 1, 1, 1, 1]
    [0, 0, 1, 0, 1, 0, 0, 1, 1, 1]
    [0, 0, 1, 0, 1, 0, 1, 1, 0, 1]
    [0, 0, 1, 1, 0, 0, 1, 1, 1, 0]
    [0, 1, 1, 1, 0, 1, 0, 1, 0, 0]
    [0, 1, 1, 0, 1, 1, 1, 1, 0, 0]
    [1, 0, 1, 1, 0, 1, 1, 0, 1, 1]
    [0, 0, 0, 1, 1, 0, 1, 1, 0, 1]
    [0, 1, 0, 0, 0, 0, 1, 1, 0, 0]


     */

}