/*
Project 3
Peyton Leggoe 
*/
import java.util.*;
import java.io.*;
import java.time.*;

public class project3 {
	static boolean idCheck(String id)
	{
		if(id.length() != 6)
		{
			return false;
		}
		
		for(int i = 0; i < 2; i++)
		{
			if(Character.isLetter(id.charAt(i)) != true)
			{
				return false;
			}
		}
		
		for(int i = 2; i < 6; i++)
		{
			if(Character.isDigit(id.charAt(i)) != true)
			{
				return false;
			}
		}
		return true;
	}
	
	//returns true if same id is already found, false if not
	static boolean idSearch(String id, person[] list, int pos)
	{
		for(int i = 0; i < pos; i++)
		{
			if(id.equals(list[i].getId()))
			{
				System.out.println("\tPerson already exists please enter a new ID");
				return true;
			}
		}
		return false; 
	}
	public static void main (String []args)
	{
		
		Scanner myScan = new Scanner(System.in);
		char selection;
		int caseNum;
		boolean exitCon = true;
		int pos = 0;
		String junk;
		String name;
		String id;
		double gpa;
		int creditHours;
		String department;
		String rank;
		char letterStatus;
		
		
		person[] list = new person[100];
		
		do
		{
			System.out.println("Choose one of the options:\n");
			
			System.out.println("1- Enter the information of the faculty member");
			System.out.println("2- Enter the information of a student");
			System.out.println("3- Print tuition invoice");
			System.out.println("4- Print faculty information");
			System.out.println("5- Enter the information of the staff member");
			System.out.println("6- Print the information of the staff member");
			System.out.println("7- Exit Program");
			
			System.out.printf("\n\n\t Enter your selection: ");
			selection = myScan.nextLine().charAt(0);
			caseNum = (int) selection;
		
			//All cases are using the ASCII vales of the prospective numbers in order
			//to avoid errors thrown when an invalid option was selected
			switch(caseNum)
			{
			//Case 1 Enter information of the faculty
			//Will check if a faculty member exists already and ask if information should be updated
			case 49:
				
				boolean exit = true;
				
				System.out.println("\nEnter faculty info: ");
				System.out.printf("\n\tName of the faculty: ");
				name = myScan.nextLine();
				
				do
				{
					exit = false;
					System.out.printf("\n\n\tID: ");
					id = myScan.nextLine();
					try
					{
						if(idCheck(id) == false)
						{
							throw new idException("");
						}
					}
					catch(idException e)
					{
						System.out.println(e);
						exit = true;
					}
					if(idSearch(id, list, pos) == true)
					{
						exit = true;
					}
					
				}while(exit);
				
				exit = true;
				do
				{
					System.out.printf("\n\n\tRank: ");
					rank = myScan.nextLine();
					if(rank.toLowerCase().equals("professor") || rank.toLowerCase().equals("adjunct"))
					{
						rank = rank.toLowerCase();
						exit = false;
					}
					else
					{
						System.out.printf("\n\t\"%s\" is Invalid!", rank);
					}
				}while(exit);
				
				exit = true;
				do
				{
					System.out.printf("\n\n\tDepartment: ");
					department = myScan.nextLine();
					
					if(department.toLowerCase().equals("mathematics") || department.toLowerCase().equals("engineering") || department.toLowerCase().equals("english"))
					{
						department = department.toLowerCase();
						exit = false;
					}
					else
					{
						System.out.printf("\n\t\"%s\" is Invalid!", department);
					}
				}while(exit);
				
				list[pos] = new faculty(name, id, department, rank);
				pos++;
				System.out.println("\nFaculty member added!");
				break;
				
				//Case 2 Enter information of a student
			case 50:	
				
				System.out.println("Enter the student info:\n");
				System.out.printf("\n\tName of Student: ");
				name = myScan.nextLine();
				do
				{
					exit = false;
					System.out.printf("\n\n\tID: ");
					id = myScan.nextLine();
					try
					{
						if(idCheck(id) == false)
						{
							throw new idException("");
						}
					}
					catch(idException e)
					{
						System.out.println(e);
						exit = true;
					}
					if(idSearch(id, list, pos) == true)
					{
						exit = true;
					}
					
				}while(exit);
				
				System.out.printf("\n\n\tGpa: ");
				gpa = myScan.nextDouble();
				System.out.printf("\n\n\tCredit hours: ");
				creditHours = myScan.nextInt();
				list[pos] = new student(name, id, gpa, creditHours);
				System.out.println("\nStudent added!");
				pos++;
				junk = myScan.nextLine();
				break;
				
				//Case 3 Print tuition invoice
				//Checks to see if student information has been entered then grants option for user to choose which student
				//to have their tuition displayed
			case 51:
				String findStu;
				student newStudent;
				boolean foundStudent = false;
				System.out.print("\n\tEnter the student's id: ");
				findStu = myScan.nextLine();	
				for(int i = 0; i < pos; i++)
				{
					if(list[i].getId().equals(findStu))
					{
						newStudent = (student) list[i];
						newStudent.calTuition();
						foundStudent = true;
						break;
					}
				}
				if(foundStudent == false)
				{
					System.out.println("\nNo student matched!");
				}
				break;
				
				//Case 4 Print faculty information
			case 52:
				String findFac;
				faculty newFac;
				boolean foundFaculty = false;
				System.out.print("\n\tEnter the faculty's id: ");
				findFac = myScan.nextLine();			
				for(int i = 0; i < pos; i++)
				{
					if(list[i].getId().equals(findFac))
					{
						newFac = (faculty) list[i];
						newFac.print();
						foundFaculty = true;
						break;
					}
				}
				if(foundFaculty == false)
				{
					System.out.println("\nNo Faculty matched!");
				}
				break;
				
				//Case 5 Enter the information of the staff member
				//Checks to see if a staff member already exists and will offer to update the information
			case 53:
				System.out.printf("\n\n\nName of the staff member: ");
				name = myScan.nextLine();
				
				do
				{
					exit = false;
					System.out.printf("\n\n\tID: ");
					id = myScan.nextLine();
					try
					{
						if(idCheck(id) == false)
						{
							throw new idException("");
						}
					}
					catch(idException e)
					{
						System.out.println(e);
						exit = true;
					}
					if(idSearch(id, list, pos) == true)
					{
						exit = true;
					}
					
				}while(exit);
				
				exit = true;
				do
				{
					System.out.printf("\n\nDepartment: ");
					department = myScan.nextLine();
					
					if(department.toLowerCase().equals("mathematics") || department.toLowerCase().equals("engineering") || department.toLowerCase().equals("english") ||department.toLowerCase().equals("sciences"))
					{
						department = department.toLowerCase();
						exit = false;
					}
					else
					{
						System.out.printf("\n\t\"%s\" is Invalid!", department);
					}
					
				}while(exit);
				exit = true;
				
				do
				{
					System.out.printf("\n\nStatus, Enter P for Part Time or Enter F for Full Time: ");
					letterStatus = myScan.nextLine().charAt(0);
					if(letterStatus == 'F' || letterStatus == 'f' || letterStatus == 'P' || letterStatus == 'p')
					{
						exit = false;
					}
					else
					{
						System.out.printf("\n\t\"%c\" is Invalid!", letterStatus);
					}
				}while (exit);
				
				list[pos] = new staff(name, id, department, letterStatus);
				pos++;
				
				System.out.println("\nStaff member added!");
				break;
				
				//Case 6 Print the information of the staff member
				//Checks to see if a staff member exists first and if so then it prints the information of the staff member
			case 54:
				String findStaff;
				staff newStaff;
				boolean foundStaff = false;
				System.out.print("\n\tEnter the staff's id: ");
				findStaff = myScan.nextLine();			
				for(int i = 0; i < pos; i++)
				{
					if(list[i].getId().equals(findStaff))
					{
						newStaff = (staff) list[i];
						newStaff.print();
						foundStaff = true;
						break;
					}
				}
				if(foundStaff == false)
				{
					System.out.println("\nNo staff member matched!");
				}
				break;
				
				//Case 7 Exit Program
			case 55:
				char answer;
				System.out.println("\n\n\tWould you like to create a report? (Y/N): ");
				answer = myScan.nextLine().charAt(0);
				if(answer == 'Y' || answer == 'y')
				{
					LocalDate date = LocalDate.now();
					 try {
					      FileWriter myWriter = new FileWriter("report.txt");
					      myWriter.write("\tReport created on " + date);
					      myWriter.write("\n\t***********************");
					      myWriter.write("\n\nFaculty Members\n-------------------------\n");
					      int howMany = 1;
					      
					      for(int i = 0; i < pos; i++)
					      {
					    	  if(list[i].getType() == 'f')
					    	  {
					    		  myWriter.write("\t" + howMany + "." + list[i].getName() + "\n\tID: " + list[i].getId() + "\n\t" + list[i].getRank() + "," + list[i].getDepartment());
					    		  howMany++;
					    	  }
					      }
					      
					      myWriter.write("\n\nStaff Members\n-------------------------\n");
					      howMany = 1;
					      
					      for(int i = 0; i < pos; i++)
					      {
					    	  if(list[i].getType() == 'c')
					    	  {
					    		  myWriter.write("\t" + howMany + "." + list[i].getName() + "\n\tID: " + list[i].getId() + "\n\t" + list[i].getDepartment() + "," + list[i].getStatus());
					    		  howMany++;
					    	  }
					      }
					      
					      myWriter.write("\n\nStudents\n-------------------------\n");
					      howMany = 1;
					      
					      for(int i = 0; i < pos; i++)
					      {
					    	  if(list[i].getType() == 's')
					    	  {
					    		  myWriter.write("\t" + howMany + "." + list[i].getName() + "\n\tID: " + list[i].getId() + "\n\tGPA" + list[i].getGpa() + "\n\tCreditHours: " + list[i].getCreditHours());
					    		  howMany++;
					    	  }
					      }
					      
					      
					    
					      myWriter.close();
					      System.out.println("Report created and saved in your hard drive!");
					    } catch (IOException e) {
					      System.out.println("An error occurred.");
					      e.printStackTrace();
					    }
				}
				System.out.println("Goodbye!");
				exitCon = false;
				break;
			default:
				System.out.println("\n\nInvalid entry- please try again\n\n");
			}
			
		}
		while(exitCon);
		
		
	}
}

class idException extends Exception
{
	String s;
	public idException(String s)
	{
		this.s = s;
	}
	
	public String toString()
	{
		return ("\tInvalid ID format. Must be LetterLetterDigitDigitDigitDigit");
	}
}

abstract class person
{
	//variables
	private String fullName;
	private String id;
	
	//constructors
	public person(String name, String newID)
	{
		fullName = name;
		id = newID;
	}
	public person()
	{
		fullName = "";
		id = "";
	}
	
	//getters and setters
	public String getName()
	{
		return fullName;
	}
	public String getId()
	{
		return id;
	}
	public void setName(String fullName)
	{
		this.fullName = fullName;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	
	public char getType()
	{
		return 'x';
	}
	
	public String getDepartment()
	{
		return "department";
	}
	
	public String getRank()
	{
		return "rank";
	}
	
	public String getStatus()
	{
		return "status";
	}
	
	public double getGpa()
	{
		return 0.0;
	}
	
	public int getCreditHours()
	{
		return 0;
	}
}

class student extends person
{
	char type = 's';
	double gpa;
	private int creditHours;
	
	public student()
	{
		super("", "");
		gpa = 0;
		creditHours = 0;
	}
	
	public student(String name, String newId, double newGpa, int newCreditHours)
	{
		super(name, newId);
		gpa = newGpa;
		creditHours = newCreditHours;
	}
	
	public char getType()
	{
		return type;
	}
	
	public String toString()
	{
		return getName() + " " + getId() + " " + gpa + " " + creditHours;
	}
	
	public double getGpa()
	{
		return gpa;
	}
	
	public int getCreditHours()
	{
		return creditHours;
	}
	
	
	public void calTuition()
	{
		double tuition;
		double perHour = 236.45;
		int adminFee = 52;
		double discount = 0;
		
		tuition = (creditHours * perHour) + adminFee;
		
		if(gpa >= 3.85)
		{
			discount = tuition * 0.15;
			tuition -= discount;
		}
		
		System.out.printf("\n\nHere is the tuition invoice for %s:", getName());
		System.out.println("\n\t---------------------------------------------------------------------------");
		System.out.printf("\n\t%s\t%5s", getName(), getId());
		System.out.printf("\n\n\tCredit hours: %d ($236.45/credit hour)", creditHours);
		System.out.printf("\n\n\tFees: $%d", adminFee);
		System.out.printf("\n\n\n\tTotal payment (after discount): $%.2f \t($%.2f discount applied)", tuition, discount);
		System.out.println("\n\t---------------------------------------------------------------------------\n\n\n");
	}
}

class employee extends person
{
	private String department;
	
	
	public employee()
	{
		super("", "");
		department = "";
	}
	public employee(String department, String name, String id)
	{
		super(name, id);
		this.department = department;
	}
	public void setDepartment(String department)
	{
		this.department = department;
	}
	public String getDepartment()
	{
		return department;
	}
}


class faculty extends employee
{
	private String rank;
	char type = 'f';
	
	public faculty()
	{
		super();
		rank = "";
	}
	
	public faculty(String name, String id, String department, String rank)
	{
		super(department, name, id);
		this.rank = rank;
	}
	
	public void print()
	{
		setDepartment(getDepartment().substring(0,1).toUpperCase() + getDepartment().substring(1));
		rank = rank.substring(0,1).toUpperCase() + rank.substring(1);
		System.out.println("\n\n\n\n---------------------------------------------------------------------------");
		System.out.printf("\n%s\t%5s", getName(), getId());
		System.out.printf("\n\n%s Department, %s", getDepartment(), rank);
		System.out.printf("\n\n---------------------------------------------------------------------------\n\n");
	}
	
	public char getType()
	{
		return type;
	}
	
	public String getRank()
	{
		return rank;
	}
}

class staff extends employee
{
	private String status;
	char type = 'c';
	
	public staff()
	{
		super();
		status = "";
	}
	
	public staff(String name, String newId, String newDepartment, char newStatus)
	{
		super(newDepartment, name, newId);
		if(newStatus == 'f' || newStatus == 'F')
		{
			status = "Full Time";
		}
		else if(newStatus == 'p' || newStatus == 'P')
		{
			status = "Part Time";
		}
	}
	public void print()
	{
		System.out.println("\n\n\n---------------------------------------------------------------------------");
		System.out.printf("\n\n%s \t%5s", getName(), getId());
		System.out.printf("\n\n%s Department, %s", getDepartment().substring(0,1).toUpperCase() + getDepartment().substring(1), status);
		System.out.println("\n\n---------------------------------------------------------------------------\n\n");
	}
	
	public String getStatus()
	{
		return status;
	}
	
	public char getType()
	{
		return type;
	}
}