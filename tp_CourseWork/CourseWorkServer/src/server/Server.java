package server;

import sql.Lane;
import sql.SqlConnection;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Server 
{
	public static void main(String[] args) throws ParseException, IOException, ClassNotFoundException, SQLException
	{
		// Source log file
		String filename = "D:\\CourseWork\\Logs\\logdet07-11-11.log";
		File logFile = new File(filename);
		if (logFile.exists())
		{
			//Get all lanes information from source file
			ArrayList<Lane> InsertDB = parseLog(logFile);
			//Insert the lanes into database
			new SqlConnection().insert(InsertDB);
		}
		else
		{
			System.out.println("File does not exist");
		}
	}
	
	//Read from file and add to ArrayList new Lane object
	static ArrayList<Lane> parseLog(File file) throws ParseException, FileNotFoundException
	{
		ArrayList<Lane> lanes = new ArrayList<Lane>();
		String temp = null;
		Pattern p = Pattern.compile("\\s+");
		String[] laneInfo = null;
		Scanner scan = new Scanner(file);
		while(scan.hasNext())
		{
			temp = scan.nextLine();
			if (temp.trim().length() != 0)
			{	
				laneInfo = p.split(temp);
				lanes.add(new Lane(laneInfo));
			}
		}
		scan.close();
		return lanes;
	}
}
