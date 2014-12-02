package vida;

/* RunLengthEncoding.java */

/**
 *  The RunLengthEncoding class defines an object that run-length encodes an
 *  Ocean object.  Descriptions of the methods you must implement appear below.
 *  They include constructors of the form
 *
 *      public RunLengthEncoding(int i, int j, int starveTime);
 *      public RunLengthEncoding(int i, int j, int starveTime,
 *                               int[] runTypes, int[] runLengths) {
 *      public RunLengthEncoding(Ocean ocean) {
 *
 *  that create a run-length encoding of an Ocean having width i and height j,
 *  in which sharks starve after starveTime timesteps.
 *
 *  The first constructor creates a run-length encoding of an Ocean in which
 *  every cell is empty.  The second constructor creates a run-length encoding
 *  for which the runs are provided as parameters.  The third constructor
 *  converts an Ocean object into a run-length encoding of that object.
 *
 *  See the README file accompanying this project for additional details.
 */

public class RunLengthEncoding {

  /**
   *  Define any variables associated with a RunLengthEncoding object here.
   *  These variables MUST be private.
   */
        DoubleLinkedList<TypeAndSizeAndStarve> runLength;
	int width;
	int height;
	int starveTime;
	DoubleLinkedList<TypeAndSizeAndStarve>.DoubleLinkedListIterator iter;

  /**
   *  The following methods are required for Part II.
   */

  /**
   *  RunLengthEncoding() (with three parameters) is a constructor that creates
   *  a run-length encoding of an empty ocean having width i and height j,
   *  in which sharks starve after starveTime timesteps.
   *  @param i is the width of the ocean.
   *  @param j is the height of the ocean.
   *  @param starveTime is the number of timesteps sharks survive without food.
   */

  public RunLengthEncoding(int i, int j, int starveTime) {
    // Your solution here.
        this.starveTime = starveTime;
        width = i;
	height = j;
	int k = i*j;
	runLength = new DoubleLinkedList<TypeAndSizeAndStarve>(); 
	TypeAndSizeAndStarve empty = new TypeAndSizeAndStarve(0, k);
	runLength.add(empty);
	restartRuns();
      
  }

  /**
   *  RunLengthEncoding() (with five parameters) is a constructor that creates
   *  a run-length encoding of an ocean having width i and height j, in which
   *  sharks starve after starveTime timesteps.  The runs of the run-length
   *  encoding are taken from two input arrays.  Run i has length runLengths[i]
   *  and species runTypes[i].
   *  @param i is the width of the ocean.
   *  @param j is the height of the ocean.
   *  @param starveTime is the number of timesteps sharks survive without food.
   *  @param runTypes is an array that represents the species represented by
   *         each run.  Each element of runTypes is Ocean.EMPTY, Ocean.FISH,
   *         or Ocean.SHARK.  Any run of sharks is treated as a run of newborn
   *         sharks (which are equivalent to sharks that have just eaten).
   *  @param runLengths is an array that represents the length of each run.
   *         The sum of all elements of the runLengths array should be i * j.
   */

  public RunLengthEncoding(int i, int j, int starveTime, int[] runTypes, int[] runLengths) {
    // Your solution here.
      this.starveTime = starveTime;
		width = i;
		height = j;
		runLength = new DoubleLinkedList<TypeAndSizeAndStarve>(); 
		for (int x=0; x<runTypes.length; x++)
			runLength.add(new TypeAndSizeAndStarve(runTypes[x], runLengths[x]));
		restartRuns();
	}
  

  /**
   *  restartRuns() and nextRun() are two methods that work together to return
   *  all the runs in the run-length encoding, one by one.  Each time
   *  nextRun() is invoked, it returns a different run (represented as a
   *  TypeAndSize object), until every run has been returned.  The first time
   *  nextRun() is invoked, it returns the first run in the encoding, which
   *  contains cell (0, 0).  After every run has been returned, nextRun()
   *  returns null, which lets the calling program know that there are no more
   *  runs in the encoding.
   *
   *  The restartRuns() method resets the enumeration, so that nextRun() will
   *  once again enumerate all the runs as if nextRun() were being invoked for
   *  the first time.
   *
   *  (Note:  Don't worry about what might happen if nextRun() is interleaved
   *  with addFish() or addShark(); it won't happen.)
   */

  /**
   *  restartRuns() resets the enumeration as described above, so that
   *  nextRun() will enumerate all the runs from the beginning.
   */

  public void restartRuns() {
    iter = (DoubleLinkedList<TypeAndSizeAndStarve>.DoubleLinkedListIterator) runLength.iterator();
  }

  /**
   *  nextRun() returns the next run in the enumeration, as described above.
   *  If the runs have been exhausted, it returns null.  The return value is
   *  a TypeAndSize object, which is nothing more than a way to return two
   *  integers at once.
   *  @return the next run in the enumeration, represented by a TypeAndSize
   *          object.
   */

  public TypeAndSize nextRun() {
    // Replace the following line with your solution.
    if(!iter.hasNext())
			return null;
		
		TypeAndSizeAndStarve temp = iter.next();
		TypeAndSize result = new TypeAndSize(temp.getType(), temp.getSize());
		return result;
  }

  /**
   *  toOcean() converts a run-length encoding of an ocean into an Ocean
   *  object.  You will need to implement the three-parameter addShark method
   *  in the Ocean class for this method's use.
   *  @return the Ocean represented by a run-length encoding.
   */

  public Ocean toOcean() {
    Ocean newOcean = new Ocean(width, height, starveTime);
		int x=0;
		int y=0;
		for(TypeAndSizeAndStarve runs: runLength)
			for(int i = 0; i<runs.getSize(); i++){
				if(y==width){
					x++;
					y=0;
				}
				if(runs.getType() == 1){
					newOcean.addShark(y, x, starveTime);
					y++;
				}
				else if(runs.getType() == 2){
					newOcean.addFish(y, x);
					y++;
				}
				else
					y++;
			}
			return newOcean;
  }

  /**
   *  The following method is required for Part III.
   */

  /**
   *  RunLengthEncoding() (with one parameter) is a constructor that creates
   *  a run-length encoding of an input Ocean.  You will need to implement
   *  the sharkFeeding method in the Ocean class for this constructor's use.
   *  @param sea is the ocean to encode.
   */

  public RunLengthEncoding(Ocean sea) {
    
		width = sea.width();
		height = sea.height();
		int arraySize = width * height;
		Animal[] arrayOcean = new Animal[arraySize];
		int arrayIndex = 0;
		runLength = new DoubleLinkedList<TypeAndSizeAndStarve>();
		for(int i = 0; i < width; i++)
	    	for (int j = 0; j < height; j++){
	    		if(sea.cellContents(i, j) == 1){
	    			arrayOcean[arrayIndex] = new Animal(sea.cellContents(i, j), sea.sharkFeeding(i, j));
	    			arrayIndex++;
	    		}
	    		else{
	    			arrayOcean[arrayIndex] = new Animal(sea.cellContents(i, j));
	    			arrayIndex++;
	    		}
	    	}
		int cont = 1;
		for (int k = 1; k < arraySize; k++){
			if(arrayOcean[k-1].getTipo() == arrayOcean[k].getTipo() && arrayOcean[k-1].getFome() == arrayOcean[k].getFome())
				cont++;
			else
				if(arrayOcean[k-1].getTipo() == 1){
					runLength.add(new TypeAndSizeAndStarve(1, cont, arrayOcean[k-1].getFome()));
					cont = 1;
				}
				else{
					runLength.add(new TypeAndSizeAndStarve(arrayOcean[k-1].getTipo(), cont));	
					cont = 1;
				}
		}
		if(arrayOcean[arraySize-1].getTipo() == 1)
			runLength.add(new TypeAndSizeAndStarve(arrayOcean[arraySize-1].getTipo(), cont, arrayOcean[arraySize-1].getFome()));
		else
			runLength.add(new TypeAndSizeAndStarve(arrayOcean[arraySize-1].getTipo(), cont));
		check();
		restartRuns();
  }

  /**
   *  The following methods are required for Part IV.
   */

  /**
   *  addFish() places a fish in cell (x, y) if the cell is empty.  If the
   *  cell is already occupied, leave the cell as it is.  The final run-length
   *  encoding should be compressed as much as possible; there should not be
   *  two consecutive runs of sharks with the same degree of hunger.
   *  @param x is the x-coordinate of the cell to place a fish in.
   *  @param y is the y-coordinate of the cell to place a fish in.
   */

  public void addFish(int x, int y) {
    // Your solution here, but you should probably leave the following line
    //   at the end.
    check();
  }

  /**
   *  addShark() (with two parameters) places a newborn shark in cell (x, y) if
   *  the cell is empty.  A "newborn" shark is equivalent to a shark that has
   *  just eaten.  If the cell is already occupied, leave the cell as it is.
   *  The final run-length encoding should be compressed as much as possible;
   *  there should not be two consecutive runs of sharks with the same degree
   *  of hunger.
   *  @param x is the x-coordinate of the cell to place a shark in.
   *  @param y is the y-coordinate of the cell to place a shark in.
   */

  public void addShark(int x, int y) {
    // Your solution here, but you should probably leave the following line
    //   at the end.
    check();
  }

  /**
   *  check() walks through the run-length encoding and prints an error message
   *  if two consecutive runs have the same contents, or if the sum of all run
   *  lengths does not equal the number of cells in the ocean.
   */

  public void check() {      
		int cont = 0;
		int prev = -1;
		int feed = -1;
		int flag = 0;
		for (TypeAndSizeAndStarve x: runLength){
			cont += x.getSize();
			if(x.getType() == 1 && prev == 1)
				if(x.getStarve() == feed)
					flag = 1;
			else if(x.getType() == prev && x.getStarve()<0)
					flag = 1;
			prev = x.getType();
			feed = x.getStarve();
		}
		if(flag == 1)
			System.out.println("Dois runs consecutivos têm exatamente o mesmo conteúdo");
		if(cont != width*height){
			System.out.println("A soma do comprimento de todos os Runs na lista não é igual ao número de células do oceano");
		}
  }

  
    public String toString(){
		String res = "";
		for (TypeAndSizeAndStarve x: runLength)
			if(x.getType() == 0)
				res += "| ." + x.getSize() + " |\n";
			else if(x.getType() == 1)
				res += "| S" + x.getStarve() + "," + x.getSize()  + "|\n";
			else if(x.getType() == 2)
				res += "| F" + x.getSize() + " |\n";
		return res;
	}
}
