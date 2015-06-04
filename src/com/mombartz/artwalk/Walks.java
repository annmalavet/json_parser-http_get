package com.mombartz.artwalk;

public class Walks {
	  private String image;
	    private String walk;
	    private String name;
	    private String description;
	    private String slug; 
	    
	    public Walks(String image, String walk, String name, String description, String slug){
	        this.image = image;
	        this.walk = walk;
	        this.name = name;
	        this.description = description;
	        this.slug = slug;
	    }
	    public String getImage(){
	        return this.image;
	    }

	    public String getName(){
	        return this.name;
	    }

	    public String getDescription(){
	        return this.description;
	    }

	    public String getSlug(){
	        return this.slug;
	    }

}
