package sql;

import java.text.ParseException;

public class Lane 
{
	String name; // Lane's name
	float occupancy; // Q
	float intensity; // p
	float speed; // V
	float distance; // d
	String date; // date
	
	// Parse into new Lane object
	public Lane(String[] str) throws ParseException
	{
		name = str[1];
		occupancy = Float.parseFloat(str[3].replace(',', '.'));
		distance = Float.parseFloat(str[14].replace(',', '.'));
		if(this.distance != 0 && this.occupancy != 0)
		{
			
			speed = Float.parseFloat(str[5].replace(',', '.'));	
			intensity = 1/occupancy; 
		}
		else
		{
			intensity = 0;
			speed = 0;
		}
		date = str[16] +' '+ str[17];	
	}

	// return the InsertQuery for this Lane
	public String getInsertQuery()
	{
		return "(" + 
				"'" + this.name 		+ "'" + "," + 
				"'" + this.occupancy 	+ "'" + "," + 
				"'" + this.intensity	+ "'" + "," + 
				"'" + this.speed 		+ "'" + "," +
				"'" + this.distance		+ "'" + "," +				
				"'" + this.date         + "'" +")";
	}
}
