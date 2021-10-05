package com.ty.project2.hibernate1;

import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * @author TEJASHWINI
 *
 */

public class HibernateAssignment {

	/**
	 * @param args 
	 * start of main(String[] args) method
	 */
	public static void main(String[] args) {
		homePage();
	}//end of main(String[] args) method

	/**
	 * start of verifyRollNo(int rollno);
	 * @param rollno accepts rollno and checks whether the table contains that rollno 
	 * @return true if rollno exists else return false
	 */
	public static boolean verifyRollNo(int rollno)
	{
		EntityManagerFactory factory=null;
		EntityManager manager=null;
		StudentTable student1=null;
		try {
			factory=Persistence.createEntityManagerFactory("emp");
			manager=factory.createEntityManager();
			student1=manager.find(StudentTable.class,rollno);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(manager!=null)
				manager.close();
			if(factory!=null)
				factory.close();
			if(student1==null)
				return false;
			else {
				return true;
			}
		}
	}//end of verifyRollNo(int rollno);

	/**
	 * start of homepage()
	 * in this method we perform operations like 
	 * 1.fetching all the data from table,
	 * 2.fetching only a particular data based on id,
	 * 3.update data based on rollno,
	 * 4.delete the data based on rollno entered
	 */
	public static void homePage()
	{
		Scanner sc=new Scanner(System.in);
		StudentTable student1=new StudentTable();
		EntityManagerFactory factory=null;
		EntityManager manager=null;
		EntityTransaction transaction=null;
		boolean key=true;
		while(key)
		{
			System.out.println(" 1.Press 1 to see all the details \n 2.Press 2 to see any particular data \n 3.Press 3 to update data\n 4.Press 4 to delete data \n 5.to exit");
			System.out.println("Enter your Choice");
			int choice =sc.nextInt();
			switch (choice) {
			case 1://to fetch all the data from the table
				try {
					factory=Persistence.createEntityManagerFactory("emp");
					manager=factory.createEntityManager();
					transaction=manager.getTransaction();
					transaction.begin();
					String findAll="from StudentTable";
					Query query=manager.createQuery(findAll);
					List<StudentTable> list=query.getResultList();
					System.out.println(list);
					System.out.println("----------------------");
					for(StudentTable student:list)
					{
						System.out.println(student);
					}

					transaction.commit();
				}
				catch (Exception e) {
					e.printStackTrace();
					if(transaction!=null)
						transaction.rollback();

				}
				finally {
					if(manager!=null)
						manager.close();
					if(factory!=null)
						factory.close();
				}
				break;
			case 2://to fetch particular data from table based on rollno entered
				System.out.println("pls enter the rollno");
				int rollno2=sc.nextInt();
				if(verifyRollNo(rollno2))
				{
					try {
						factory=Persistence.createEntityManagerFactory("emp");
						manager=factory.createEntityManager();
						String findById=" from StudentTable where rollno=:rollno";
						Query query=manager.createQuery(findById);
						query.setParameter("rollno",rollno2);
						StudentTable student3=(StudentTable)query.getSingleResult();
						System.out.println(student3);
					}
					catch (Exception e) {
						e.printStackTrace();
						if(transaction!=null)
							transaction.rollback();

					}
					finally {
						if(manager!=null)
							manager.close();
						if(factory!=null)
							factory.close();
					}
				}
				else {
					throw new RollNoNotFoundException("Entered rollno doesn't exist, please enter a valid rollno");
				}
				break;
			case 3://to update particular data based on rollno entered
				System.out.println("enter the id you wish to update ");
				int rollno=sc.nextInt();
				if(verifyRollNo(rollno))
				{
					System.out.println("Do you want to update the name(yes/no)....?");
					String choice0=sc.next();
					if(choice0.equals("yes"))
					{
						try {
							factory=Persistence.createEntityManagerFactory("emp");
							manager=factory.createEntityManager();
							transaction=manager.getTransaction();
							transaction.begin();
							System.out.println("pls enter the name to be updated");
							String name=sc.next();
							String update="update StudentTable set name=:name where rollno=:rollno";
							Query query=manager.createQuery(update);
							query.setParameter("rollno",rollno);
							query.setParameter("name",name);
							int result=query.executeUpdate();
							transaction.commit();
						}
						catch (Exception e) {
							e.printStackTrace();
							if(transaction!=null)
								transaction.rollback();
						}
						finally {
							if(manager!=null)
								manager.close();
							if(factory!=null)
								factory.close();
						}
					}
					System.out.println("Do you want to update the phno(yes/no)....?");
					String choice1=sc.next();
					if(choice1.equals("yes"))
					{
						try {
							factory=Persistence.createEntityManagerFactory("emp");
							manager=factory.createEntityManager();
							transaction=manager.getTransaction();
							transaction.begin();
							System.out.println("pls enter the phno to be updated");
							long phno=sc.nextLong();
							String update="update StudentTable set phno=:phno where rollno=:rollno";
							Query query=manager.createQuery(update);
							query.setParameter("rollno",rollno);
							query.setParameter("phno",phno);
							int result=query.executeUpdate();

							transaction.commit();
						}
						catch (Exception e) {
							e.printStackTrace();
							if(transaction!=null)
								transaction.rollback();
						}
						finally {
							if(manager!=null)
								manager.close();
							if(factory!=null)
								factory.close();
						}
					}
				}
				else {
					throw new RollNoNotFoundException("Entered rollno doesn't exist, please enter a valid rollno");
				}
				break;
			case 4://to delete particular data based on rollno entered
				System.out.println("enter the rollno whose data you wish to delete ");
				int rollno4=sc.nextInt();
				if(verifyRollNo(rollno4))
				{
					try {
						factory=Persistence.createEntityManagerFactory("emp");
						manager=factory.createEntityManager();
						transaction=manager.getTransaction();
						transaction.begin();
						String DeleteById="delete from StudentTable where rollno=:rollno";
						Query query=manager.createQuery(DeleteById);
						query.setParameter("rollno",rollno4);
						int result=query.executeUpdate();
						System.out.println("deleted successfully......!");
						transaction.commit();
					}
					catch (Exception e) {
						e.printStackTrace();
						if(transaction!=null)
							transaction.rollback();
					}
					finally {
						if(manager!=null)
							manager.close();
						if(factory!=null)
							factory.close();
					}
				}
				else {
					throw new RollNoNotFoundException("Entered rollno doesn't exist, please enter a valid rollno");
				}
				break;
			case 5:key=false;
			break;
			default:System.out.println("please enter valid data...!!");
			}
		}
	}

}
