package project.pkg1.pkgfor.account;
import java.io.*;
import java.util.*;

class account implements Serializable
{
    String acc_no;
    String name;
    double balance;
    String p_num;
    
    account()
    {
        
    }
    account(String acc,String name,double bal,String p_num)
    {
        acc_no=acc;
        this.name=name;
        balance=bal;   
        this.p_num=p_num;
        
    }
    
    @Override
    public String toString()
    {
        return "1. Account number is -> "+acc_no+
                "\n2. Name of account holder is -> "+name+
                "\n3. Balance in account is -> "+balance+
                "\n4. Phone Number of "+name+" is "+p_num;
    }
}

public class Project1ForAccount 
{


    public static void main(String[] args)
    {
        
        Scanner s=new Scanner(System.in);
        
        account acc= null;
        
        HashMap<String,account> hm=new HashMap<>();
        
        try
        {
        FileInputStream fis=new FileInputStream("D:/programs/java/program file/account project.txt");
        ObjectInputStream ois =new ObjectInputStream(fis);
        
        int count =ois.readInt();
        
        for(int i=0;i<count;i++)
        {
            acc=(account)ois.readObject();
            
            hm.put(acc.acc_no, acc);
        }
        fis.close();
        ois.close();
        }catch(Exception e) {}
        
        try
        {
        
        
        FileOutputStream fos=new FileOutputStream("D:/programs/java/program file/account project.txt");
        ObjectOutputStream oos=new ObjectOutputStream(fos);
        
        try
        {
        String a_num,name,p_num="";
        double balance;
        int choose;
        
        do
        {
            System.out.println("Menu");
            System.out.println("1. Create Account.");
            System.out.println("2. Close Account.");
            System.out.println("3. View Account.");
            System.out.println("4. View all Accounts.");
            System.out.println("5. Balance Enquiry.");
            System.out.println("6. Deposite Amount.");
            System.out.println("7. Withdraw Amount.");
            System.out.println("8. Exit.");           
            System.out.println("Choose one of the above option.");
            
            choose=s.nextInt();
            s.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            
            
            
            switch(choose)
            {
                case 1 : System.out.println("Enter the name of the person.");
                		 name = s.nextLine();
                		 
                		 System.out.println("Allot a specific account number.");
                         a_num= s.nextLine();
                         
                         
                         System.out.println("Enter the first deposite amount.");
                         balance=s.nextDouble();
                         
                         
                         System.out.println("Enter the phone number.");
                         String p=s.next();
                         if(p.matches("[0-9]{10}"))
                         {
                        	 p_num=p;
                         }
                         else
                         {
                        	 System.out.println("Try with the valid phone number");
                        	 break;
                         }
                         
                         acc=new account(a_num,name,balance,p_num);
                         hm.put(a_num, acc);
                         
                         System.out.println("Account is created for "+name);
                         break;
                
                case 2: System.out.println("Enter the account number.");
                        
                        a_num=s.nextLine();
                        acc=hm.get(a_num);
                        hm.remove(a_num);
                        System.out.println("Account of "+acc.name+" is closed.");
                        break;
                        
                case 3: System.out.println("Enter the account number.");
                        a_num=s.nextLine();
                        acc=hm.get(a_num);
                        System.out.println(acc);
                        break;
                           
                case 4:  
                    for(account x: hm.values())
                    {
                    System.out.println(x);
                    System.out.println();
                    }
                    break;
                    
                case 5: System.out.println("Enter the account number.");
                        a_num=s.nextLine();
                        acc =hm.get(a_num);
                        System.out.println("Balance of "+acc.name+" is "+acc.balance);
                        break;
                        
                case 6 : System.out.println("Enter the account number");
                         a_num=s.nextLine();
                         acc=hm.get(a_num);
                         if(acc==null)
                         {
                        	 System.out.println("Account is not found");
                        	 break;
                         }
                         System.out.println("Enter the deposite amount");
                         double d_amt=s.nextDouble();
                         acc.balance= acc.balance +d_amt;
                         hm.put(a_num, acc);
                         System.out.println(d_amt+" is credited and the new balance for this account is "+acc.balance);
                         break;
                
                case 7: System.out.println("Enter the account number");
                        a_num=s.nextLine();
                        acc=hm.get(a_num);
                        if(acc==null)
                        {
                        	System.out.println("Account is not Found");
                        	break;
                        }
                        System.out.println("Enter amount for withdraw");
                        double w_amt=s.nextDouble();
                        acc.balance=acc.balance-w_amt;
                        hm.put(a_num, acc);
                        System.out.println(w_amt+" is debited and remaining balance of this account is "+acc.balance);
                        break;
                        
                case 8: oos.writeInt(hm.size());
                
                        for(account y:hm.values())
                        {
                            oos.writeObject(y);
                        }
                        System.out.println("Exit and data save succesfully");
                        break;
                        
                default: System.out.println("Choose the option from the Menu");
                		break;
            }
            
        }while(choose!=8);
        
        
        }
        catch(Exception e)
        {
        	System.out.println("Check the input and required file and try again");    
        	oos.writeInt(hm.size());
            
            for(account y:hm.values())
            {
                oos.writeObject(y);
            }
        }
        
        oos.flush();
        oos.close();
        fos.close();
        }catch(Exception e) 
        {
        	System.out.println("File not found");        
        }
    }
    
}
