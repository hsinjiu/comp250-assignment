package assignment3;

public class Building {

	OneBuilding data;
	Building older;
	Building same;
	Building younger;
	
	public Building(OneBuilding data){
		this.data = data;
		this.older = null;
		this.same = null;
		this.younger = null;
	}
	
	public String toString(){
		String result = this.data.toString() + "\n";
		if (this.older != null){
			result += "older than " + this.data.toString() + " :\n";
			result += this.older.toString();
		}
		if (this.same != null){
			result += "same age as " + this.data.toString() + " :\n";
			result += this.same.toString();
		}
		if (this.younger != null){
			result += "younger than " + this.data.toString() + " :\n";
			result += this.younger.toString();
		}
		return result;
	}
	
	public Building addBuilding (OneBuilding b){
		// ADD YOUR CODE HERE
        // ADD YOUR CODE HERE
        //b1.addbuilding(ob3)
		//compare building age 
		Building add=new Building(b);
		if(b == null ){
			return this;
		}
		if (this.data.yearOfConstruction == b.yearOfConstruction){ //same age 
            if(this.data.height >= b.height) {
            	if(this.same ==null) {
            		this.same =add;
            	}
            	else {
            		this.same.addBuilding(b);
            	}
            }
            else {
            	OneBuilding swit = this.data;
            	this.data = add.data;
            	this.addBuilding(swit);
            }
        }
		else if(this.data.yearOfConstruction> b.yearOfConstruction ){ //b older 
            if(this.older == null){
                this.older = add; 
            }
            else{
				this.older.addBuilding(b);
            } 
        }
        else if(this.data.yearOfConstruction < b.yearOfConstruction){ //b younger 
            if(this.younger == null){
                this.younger = add; 
            }
            else{
				this.younger.addBuilding(b);
            } 
        }
		return this; // DON'T FORGET TO MODIFY THE RETURN IF NEEDS BE

		
	}
	
	public Building addBuildings (Building b){
		// ADD YOUR CODE HERE
		if(b == null) {
			return this;
		}
		//follow the text 
		this.addBuilding(b.data);
		if(b.older != null) {
			this.addBuildings(b.older);
		}
		if(b.same != null) {
			this.addBuildings(b.same);
		}
		if(b.younger != null ) {
			this.addBuildings(b.younger);
		}
		return this; 
	}

	
	
	public boolean checking(OneBuilding b) { //chekc is this and b is equal 
		if(this.data.yearOfConstruction == b.yearOfConstruction && this.data.costForRepair == b.costForRepair
		&& this.data.height == b.height && this.data.yearForRepair == b.yearForRepair
		&& this.data.name == b.name ) { //individual check
			return true;
		}
		else {
			return false; 
		}
	}
	
	
	public Building removeBuilding (OneBuilding b){

		if(this.checking(b) == true ) { //matches 
			if(this.same!=null) {   //same remove 
				Building top = this.same; 
				Building reOlder = this.older;
				Building reYounger =this.younger;
				top.addBuildings(reOlder);
				top.addBuildings(reYounger);
				return top;
			}
			else if(this.older!=null){   //older remove 
				Building top = this.older;
				Building reYounger = this.younger;
				top.addBuildings(reYounger);
				return top;
			}
			else {   //younger remove 
				return this.younger;
			}
		}
		else { //loop 
			if(this.data.yearOfConstruction < b.yearOfConstruction) {
				
				if(this.younger!=null) {//
					
					this.younger=this.younger.removeBuilding(b);
				}
			}
			else if(this.data.yearOfConstruction == b.yearOfConstruction) {
				
				if(this.same!=null) {
					this.same=this.same.removeBuilding(b);
				}
			}
			else if(this.data.yearOfConstruction > b.yearOfConstruction) {
				
				if(this.older!=null) {
					this.older=this.older.removeBuilding(b);
				}
			}
		}
		return this;
	}
	

	
	public int oldest(){
		// ADD YOUR CODE HERE
		
		Building temp = this;
		while(temp.older != null) {
				temp = this.older;
			}
			
			
			return temp.data.yearOfConstruction;


	}
	
	
	/*
	final String name;
	final int yearOfConstruction;
	final int height;
	final int yearForRepair;
	final int costForRepair;*/
	//helper method to compare  the two b uilding which is higher 
	public OneBuilding compare(OneBuilding b1, OneBuilding b2 ) {
			if (b1 != null && b2 != null) {
				if (b1.height >= b2.height) {
					return b1;
				} else {
					return b2;
				}
			} 
			else if (b1 != null && b2 == null) {
				
				return b1;
			}		
			else if (b1 == null && b2 != null) {
				
				return b2;
			}
			else{ //the null case 
				return null;
			} 
		}

		

	public int highest(){
		// ADD YOUR CODE HERE
		int temp = this.data.height; 
	
		if(this.older != null) {
			temp = Math.max(temp,  this.older.highest() ); //the max comparing method 
		}
		if(this.same != null) {
			temp = Math.max(temp,  this.same.highest() );
			
		}
		if(this.younger != null) {
			temp = Math.max(temp,  this.younger.highest() );
			
		}
		return temp; // DON'T FORGET TO MODIFY THE RETURN IF NEEDS BE 

	}
    	//helper method for highestFromYear


	
	public OneBuilding highestFromYear (int year){
		// ADD YOUR CODE HERE
     
		OneBuilding temp =null;
		if (this.data.yearOfConstruction == year) {
			temp = this.data;
		} else {
			if(this.older != null) {
				temp  = compare(temp , this.older.highestFromYear(year)); 
			}
			if(this.same != null) {
				temp  = compare(temp , this.same.highestFromYear(year));
				
			}
			if(this.younger != null) {
				temp  = compare(temp , this.younger.highestFromYear(year));
				
			}

			 // DON'T FORGET TO MODIFY THE RETURN IF NEEDS BE
		}
		return temp; 
	}
	
	/*
	final String name;
	final int yearOfConstruction;
	final int height;
	final int yearForRepair;
	final int costForRepair;*/
	
	public int numberFromYears (int yearMin, int yearMax){
		// ADD YOUR CODE HERE
        int numbuilding = 0; 
			if(this.data.yearOfConstruction>=yearMin) {
			if(this.data.yearOfConstruction<=yearMax) {
				numbuilding ++;
			}
		}

		if(this.older!=null) {
			numbuilding += this.older.numberFromYears(yearMin, yearMax);
		}

		if(this.same!=null) {
			numbuilding += this.same.numberFromYears(yearMin, yearMax);
		}
		if(this.younger!=null) {
			numbuilding += this.younger.numberFromYears(yearMin, yearMax);
		}
		return numbuilding; // DON'T FORGET TO MODIFY THE RETURN IF NEEDS BE
	}
	
	public int[] costPlanning (int nbYears){
		// ADD YOUR CODE HERE
		int[] cost = new int[nbYears];
		int now =2018;
		for(int i=0;i<nbYears;i++) {
			if(this.data.yearForRepair == ( now + i )) {
				cost[i] += this.data.costForRepair;
			}
		}		//recursion
		if(this.older != null) {
			for(int j=0 ; j<nbYears ; j++) {
				cost[j] += this.older.costPlanning(nbYears)[j];
			}
		}
		if(this.same != null) {
			for(int j=0 ; j < nbYears ; j++) {
				cost[j] += this.same.costPlanning(nbYears)[j];
			}
		}

		if(this.younger != null) {
			for(int j=0  ; j<nbYears ; j++) {
				cost[j] += this.younger.costPlanning(nbYears)[j];
			}
		}
		return cost;

	}
	
}
