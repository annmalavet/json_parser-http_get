package com.mombartz.artwalk;

public class Sights {
	  private int countOf;
	    private String walk;
	    private String name;
	    private String status;
	    private String slug; 
	    
	    public Sights(int countOf, String walk, String name, String status, String slug){
	        this.countOf = countOf;
	        this.walk = walk;
	        this.name = name;
	        this.status = status;
	        this.slug = slug;
	    }
	    public int getCountOf(){
	        return this.countOf;
	    }

	    public String getName(){
	        return this.name;
	    }

	    public String getStatus(){
	        return this.status;
	    }

	    public String getSlug(){
	        return this.slug;
	    }


}
