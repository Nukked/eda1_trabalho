package vida;


public class TypeAndSizeAndStarve extends TypeAndSize{
	
	int starve;
	
	public TypeAndSizeAndStarve(int species, int runLength){super(species, runLength);}
	
	public TypeAndSizeAndStarve(int species, int runLength, int starve){
		super(species, runLength);
		
		if (starve < 1) {
			System.out.println("TypeAndSize Error:  Starve must be at least 1.");
			System.exit(1);
	    }
		this.starve = starve;
	}
	
	public int getType(){return super.type;}	
	public void setType(int typeS){super.type = typeS;}
	public int getSize(){return super.size;}
	public void setSize(int sizeS){super.size = sizeS;}
	public int getStarve(){return starve;}
	public void setStarve(int starveS){starve = starveS;}


}
