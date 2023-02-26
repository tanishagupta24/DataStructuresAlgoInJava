/*
 * Simple DFS based off of Algorithms by Sedgewick DFS method, but modified for arrays.
 * @author Tanisha Gupta
 */
public class DepthFirstSearch {
    //array to compute DFS
    private int[][] G;
    //To determine whether this is the first entry of the dfs
    int count;

    //constructor
    public DepthFirstSearch(int[][] G, int i, int j) {
        this.G = G;
        count = 0;
        dfs(i, j, G[i][j]);
    }

    // depth first search from G[i][j]
    public void dfs(int i, int j, int zeroOrOne) {
        //check to make sure within bounds of graph
        if(i < 0 || i > G.length-1 || j < 0 || j > G.length-1) {
            return;
        }

        //check to ensure this isn't first run of dfs function
        if (count != 0) {
            //check to see if current cell has same value as original cell did
            if(G[i][j] != zeroOrOne) {
                return;
            }
        }
        //after first run of dfs, update the counter
        count++;

        //if the value of your cell is 0, change it to 1, if the value is 1, change to 0
        if(G[i][j] == 0) {
            G[i][j] = 1;
        } else {
            G[i][j] = 0;
        }

        //check for edge cases and recurse appropriately

        //if on top row
        if(i == 0) {
            //if on top left corner
            if(j == 0) {
                dfs(i+1, j, zeroOrOne);
                dfs( i, j+1, zeroOrOne);
            //if on top right corner
            } else if (j == G[i].length-1) {
                dfs(i, j-1, zeroOrOne);
                dfs(i+1, j, zeroOrOne);
            //else where in top row
            } else {
                dfs(i+1, j, zeroOrOne);
                dfs(i, j+1, zeroOrOne);
                dfs(i, j-1, zeroOrOne);
            }
        //if on last row
        } else if (i == G.length-1) {
            //if on bottom left corner
            if(j == 0) {
                dfs(i-1, j, zeroOrOne);
                dfs(i, j+1, zeroOrOne);
            //if on bottom right corner
            } else if (j == G[i].length-1) {
                dfs(i-1, j, zeroOrOne);
                dfs(i, j-1, zeroOrOne);
            //elsewhere in the bottom row
            } else {
                dfs(i-1, j, zeroOrOne);
                dfs(i, j-1, zeroOrOne);
                dfs(i, j+1, zeroOrOne);
            }
        //if on a middle row
        } else {
            //if on first column
            if(j == 0) {
                dfs(i-1, j, zeroOrOne);
                dfs(i+1, j, zeroOrOne);
                dfs(i, j+1, zeroOrOne);
            //if on last column
            } else if (j == G.length-1) {
                dfs(i-1, j, zeroOrOne);
                dfs(i, j-1, zeroOrOne);
                dfs(i+1, j, zeroOrOne);
            // if somewhere in the middle
            } else {
                dfs(i-1, j, zeroOrOne);
                dfs( i, j-1, zeroOrOne);
                dfs(i+1, j, zeroOrOne);
                dfs(i, j+1, zeroOrOne);
            }
        }
    }

    //return grid
    public int[][] grid() {
        return G;
    }

}
