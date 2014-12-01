package vida;
/* Ocean.java */

/**
 * The Ocean class defines an object that models an ocean full of sharks and
 * fish. Descriptions of the methods you must implement appear below. They
 * include a constructor of the form
 *
 * public Ocean(int i, int j, int starveTime);
 *
 * that creates an empty ocean having width i and height j, in which sharks
 * starve after starveTime timesteps.
 *
 * See the README file accompanying this project for additional details.
 */
public class Ocean {

    /**
     * Do not rename these constants. WARNING: if you change the numbers, you
     * will need to recompile Test4.java. Failure to do so will give you a very
     * hard-to-find bug.
     */
    public final static int EMPTY = 0;
    public final static int SHARK = 1;
    public final static int FISH = 2;

    /**
     * Define any variables associated with an Ocean object here. These
     * variables MUST be private.
     */
    private Animal[][] ocean;
    private int width;
    private int height;
    private int starveTime;

    /**
     * The following methods are required for Part I.
     */
    /**
     * Ocean() is a constructor that creates an empty ocean having width i and
     * height j, in which sharks starve after starveTime timesteps.
     *
     * @param i is the width of the ocean.
     * @param j is the height of the ocean.
     * @param starveTime is the number of timesteps sharks survive without food.
     */
    public Ocean(int i, int j, int starveTime) {
        ocean = new Animal[i][j];
        for (int x = 0; x < i; x++) {
            for (int y = 0; y < j; y++) {
                ocean[x][y] = new Animal();
            }
        }
        width = i;
        height = j;
        this.starveTime = starveTime;
    }

    /**
     * width() returns the width of an Ocean object.
     *
     * @return the width of the ocean.
     */
    public int width() {
        return width;
    }

    /**
     * height() returns the height of an Ocean object.
     *
     * @return the height of the ocean.
     */
    public int height() {
        return height;
    }

    /**
     * starveTime() returns the number of timesteps sharks survive without food.
     *
     * @return the number of timesteps sharks survive without food.
     */
    public int starveTime() {
        return starveTime;
    }

    /**
     * addFish() places a fish in cell (x, y) if the cell is empty. If the cell
     * is already occupied, leave the cell as it is.
     *
     * @param x is the x-coordinate of the cell to place a fish in.
     * @param y is the y-coordinate of the cell to place a fish in.
     */
    public void addFish(int x, int y) {
        ocean[x][y].setTipo(2);
    }

    /**
     * addShark() (with two parameters) places a newborn shark in cell (x, y) if
     * the cell is empty. A "newborn" shark is equivalent to a shark that has
     * just eaten. If the cell is already occupied, leave the cell as it is.
     *
     * @param x is the x-coordinate of the cell to place a shark in.
     * @param y is the y-coordinate of the cell to place a shark in.
     */
    public void addShark(int x, int y) {
        ocean[x][y].setTipo(1);
        ocean[x][y].setFome(starveTime);
    }

    /**
     * cellContents() returns EMPTY if cell (x, y) is empty, FISH if it contains
     * a fish, and SHARK if it contains a shark.
     *
     * @param x is the x-coordinate of the cell whose contents are queried.
     * @param y is the y-coordinate of the cell whose contents are queried.
     */
    public int cellContents(int x, int y) {
        return ocean[x][y].getTipo();
    }
    
    private Integer[] vizinhos(int x, int y) {
        Integer[] vizinhanca = new Integer[2];
        int fishs = 0;
        int shark = 0;
        // ------------- Para tirar informacao da tabela
        // -- Canto superior esquerdo 1
        if (cellContents(((x - 1) + width) % width, ((y - 1) + height) % height) == 2) {
            fishs++;
        }
        if (cellContents(((x - 1) + width) % width, ((y - 1) + height) % height) == 1) {
            shark++;
        }
        // -- Superior, para cima 2
        if (cellContents(x % width, ((y - 1) + height) % height) == 2) {
            fishs++;
        }
        if (cellContents(x % width, ((y - 1) + height) % height) == 1) {
            shark++;
        }
        // -- Superior Direito 3
        if (cellContents((x + 1) % width, ((y - 1) + height) % height) == 2) {
            fishs++;
        }
        if (cellContents((x + 1) % width, ((y - 1) + height) % height) == 1) {
            shark++;
        }
        // --  Direito 4
        if (cellContents((x + 1) % width, y % height) == 2) {
            fishs++;
        }
        if (cellContents((x + 1) % width, y % height) == 1) {
            shark++;
        }
        // -- Inf Direito 5
        if (cellContents((x + 1) % width, (y + 1) % height) == 2) {
            fishs++;
        }
        if (cellContents((x + 1) % width, (y + 1) % height) == 1) {
            shark++;
        }
        // -- Baixo 6
        if (cellContents(x % width, (y + 1) % height) == 2) {
            fishs++;
        }
        if (cellContents(x % width, (y + 1) % height) == 1) {
            shark++;
        }
        // -- Inf Esquerdo 7
        if (cellContents(((x - 1) + width) % width, (y + 1) % height) == 2) {
            fishs++;
        }
        if (cellContents(((x - 1) + width) % width, (y + 1) % height) == 1) {
            shark++;
        }
        // --  Esquerdo 8
        if (cellContents(((x - 1) + width) % width, y % height) == 2) {
            fishs++;
        }
        if (cellContents(((x - 1) + width) % width, y % height) == 1) {
            shark++;
        }
        vizinhanca[0] = fishs;
        vizinhanca[1] = shark;
        return vizinhanca;
    }
    
    
    /**
     * timeStep() performs a simulation timestep as described in README.
     *
     * @return an ocean representing the elapse of one timestep.
     */
    public Ocean timeStep() {
        Ocean newOcean = new Ocean(width, height,starveTime); //novo oceano
        
        for(int i=0; i<width;i++){
            for(int j=0; j<height;j++){
                Integer[] contem= vizinhos(i,j);
                
                //Se Celula está Vazio  
                    if (ocean[i][j].getTipo() == EMPTY) {
                        if (contem[0] >= 2 && contem[1] <= 1){ // Condiçao 7 - Se está vazio e ha +2p e -1t nasce 1 peixe
                        
                            newOcean.addFish(i, j);
                        }
                        if (contem[0] >= 2 && contem[1] >= 2) {
                            newOcean.addShark(i, j); // Condicao 8 - Se está vazio e ha +2p e +2t nasce 1 tubarao	
                        }
                    }
                    //Se Celula contem Tubarao
                    if (ocean[i][j].getTipo() == SHARK) {
                        int tempFeed;
                        if (contem[0] == 0) { // Condicao 2 - Se é tubarao e nao tem peixes a volta passa fome
                            tempFeed = ocean[i][j].getFome();
                            if (tempFeed > 1) {
                                newOcean.addShark(i, j, tempFeed - 1);
                            }
                        }
                        if (contem[0] >= 1) // Condicao 1 - Se é tubarao e tem +1p, tubarao saceado
                        {
                            newOcean.addShark(i, j);
                        }
                    }
                    //Se Celula contem Peixe
                    if (ocean[i][j].getTipo() == FISH){
                        if (contem[0] == 0){ // Condicao 3 - Se é peixe e não ha tubaroes a volta fica igual
                            newOcean.addFish(i, j);
                        }
                        if (contem[1] >= 2){
                            newOcean.addShark(i, j); // Condicao 5 - Se é peixe e ha +2 tubaroes, nasce tubarao no sitio do peixe
                        }
                    }
                
                
            }
        }
        return newOcean;
    }

    /**
     * The following method is required for Part II.
     */
    /**
     * addShark() (with three parameters) places a shark in cell (x, y) if the
     * cell is empty. The shark's hunger is represented by the third parameter.
     * If the cell is already occupied, leave the cell as it is. You will need
     * this method to help convert run-length encodings to Oceans.
     *
     * @param x is the x-coordinate of the cell to place a shark in.
     * @param y is the y-coordinate of the cell to place a shark in.
     * @param feeding is an integer that indicates the shark's hunger. You may
     * encode it any way you want; for instance, "feeding" may be the last
     * timestep the shark was fed, or the amount of time that has passed since
     * the shark was last fed, or the amount of time left before the shark will
     * starve. It's up to you, but be consistent.
     */
    public void addShark(int x, int y, int feeding) {
        // Your solution here.
    }

    /**
     * The following method is required for Part III.
     */
    /**
     * sharkFeeding() returns an integer that indicates the hunger of the shark
     * in cell (x, y), using the same "feeding" representation as the parameter
     * to addShark() described above. If cell (x, y) does not contain a shark,
     * then its return value is undefined--that is, anything you want. Normally,
     * this method should not be called if cell (x, y) does not contain a shark.
     * You will need this method to help convert Oceans to run-length encodings.
     *
     * @param x is the x-coordinate of the cell whose contents are queried.
     * @param y is the y-coordinate of the cell whose contents are queried.
     */
    public int sharkFeeding(int x, int y) {
        // Replace the following line with your solution.
        return 0;
    }

}
