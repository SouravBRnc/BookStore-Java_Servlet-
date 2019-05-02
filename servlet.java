 
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class Customer{
	String email,name,phno,isbn,bname;
	int id;
	float price,dues;
}

class Message extends JFrame{
	String msg;
	Message(String message){
		msg = message;
		JOptionPane.showMessageDialog(null, msg);
	}
}


class Billing extends JFrame implements ActionListener{
	JLabel nameLabel = new JLabel("Book-Name");
	JTextField bookname = new JTextField(25);
	JLabel cidLabel = new JLabel("Customer-id");
	JTextField cid = new JTextField(12);
	JLabel cnameLabel = new JLabel("Customer-name");
	JTextField cname = new JTextField(20);
	JLabel cemailLabel = new JLabel("Customer-email");
	JTextField cemail = new JTextField(20);
	JLabel dateLabel = new JLabel("date");
	JTextField date = new JTextField(12);
	JLabel priceLabel = new JLabel("Price");
	JTextField price = new JTextField(6);
	JLabel payLabel = new JLabel("Payment Method");
	JComboBox payM;
	JLabel toDateLabel = new JLabel("Issued upto");
	JTextField toDate = new JTextField(12);
	JLabel duesLabel = new JLabel("Previous dues");
	JTextField dues = new JTextField(6);
	JButton buy = new JButton("Confirm Purchase");
	JButton rent = new JButton("Confirm Renting");
	JTextField isbn = new JTextField(12);
	JLabel isbnLabel = new JLabel("Book ISBN");
	
	Billing(Customer c, int id){
		if(id==0) {
			Container cont = getContentPane();
			cont.setLayout(new FlowLayout());
			cont.add(nameLabel);
			cont.add(bookname);
			cont.add(cidLabel);
			cont.add(cid);
			cont.add(cnameLabel);
			cont.add(cname);
			cont.add(dateLabel);
			cont.add(date);
			cont.add(isbnLabel);
			cont.add(isbn);
			cont.add(priceLabel);
			cont.add(price);
			String pay[] = {"Cash","Debit Card", "Credit Card", "UPI"};
			cont.add(payLabel);
			payM = new JComboBox(pay);
			cont.add(payM);
			cont.add(duesLabel);
			cont.add(dues);
			dues.setText(""+c.dues);
			cont.add(buy);
			cid.setText(""+c.id);
			cname.setText(c.name);
			bookname.setText(c.bname);
			Date datenow = new java.util.Date();
			date.setText(""+datenow);
			isbn.setText(c.isbn);
			price.setText(""+c.price);
			buy.addActionListener(this);
		}
		if(id==1) {
			Container cont = getContentPane();
			cont.setLayout(new FlowLayout());
			cont.setLayout(new FlowLayout());
			cont.add(nameLabel);
			cont.add(bookname);
			cont.add(isbnLabel);
			cont.add(isbn);
			cont.add(cidLabel);
			cont.add(cid);
			cont.add(cnameLabel);
			cont.add(cname);
			cont.add(cemailLabel);
			cont.add(cemail);
			cont.add(dateLabel);
			cont.add(date);
			cont.add(toDateLabel);
			cont.add(toDate);
			cont.add(duesLabel);
			cont.add(dues);
			bookname.setText(c.bname);
			dues.setText(""+c.dues);
			cont.add(rent);
			cid.setText(""+c.id);
			cname.setText(c.name);
			cemail.setText(c.email);
			Date datenow = new java.util.Date();
			date.setText(""+datenow);
			Calendar cal = Calendar.getInstance();
			cal.setTime(datenow);
			cal.add(Calendar.DAY_OF_MONTH, 15);
			toDate.setText(""+cal.getTime());
			price.setText(""+c.price);
			isbn.setText(c.isbn);
			rent.addActionListener(this);
		}
	}
	
	Billing(String email, String bname, String isb, float prc, int id){
		if(id==0) {
			Container cont = getContentPane();
			cont.setLayout(new FlowLayout());
			cont.setLayout(new FlowLayout());
			cont.add(nameLabel);
			cont.add(bookname);
			cont.add(isbnLabel);
			cont.add(isbn);
			cont.add(cidLabel);
			cont.add(cid);
			cont.add(cnameLabel);
			cont.add(cname);
			cont.add(cemailLabel);
			cont.add(cemail);
			cont.add(dateLabel);
			cont.add(date);
			cont.add(priceLabel);
			cont.add(price);
			String pay[] = {"Cash","Debit Card", "Credit Card", "UPI"};
			cont.add(payLabel);
			payM = new JComboBox(pay);
			cont.add(payM);
			cont.add(buy);
			cid.setEditable(false);
			cidLabel.disable();
			cemail.setText(email);
			Date datenow = new java.util.Date();
			date.setText(""+datenow);
			price.setText(""+prc);
			isbn.setText(isb);
			bookname.setText(bname);
			buy.addActionListener(this);

		}
		
		if(id==1) {
			Container cont = getContentPane();
			cont.setLayout(new FlowLayout());
			cont.setLayout(new FlowLayout());
			cont.add(nameLabel);
			cont.add(bookname);
			cont.add(isbnLabel);
			cont.add(isbn);
			cont.add(cidLabel);
			cont.add(cid);
			cont.add(cnameLabel);
			cont.add(cname);
			cont.add(cemailLabel);
			cont.add(cemail);
			cont.add(dateLabel);
			cont.add(date);
			cont.add(toDateLabel);
			cont.add(toDate);
			cont.add(rent);
			cid.setEditable(false);
			bookname.setText(bname);
			cidLabel.disable();
			cemail.setText(email);
			Date datenow = new java.util.Date();
			date.setText(""+datenow);
			Calendar cal = Calendar.getInstance();
			cal.setTime(datenow);
			cal.add(Calendar.DAY_OF_MONTH, 15);
			toDate.setText(""+cal.getTime());
			price.setText(""+prc);
			isbn.setText(isb);
			rent.addActionListener(this);
		}		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==buy) {
			try {
				int cust_id=0;
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BookStore","root","root");
				Statement stmt = con.createStatement();
				if(!cid.isEditable()) {
					String ad = JOptionPane.showInputDialog(null, "Enter Customer Address:");
					String p = JOptionPane.showInputDialog(null, "Enter Customer Phone no.:");
					float ag = Float.parseFloat(JOptionPane.showInputDialog(null, "Enter Age:"));	
					String query2 = "insert into Customer(email,name,address,phone,age,dues,no_of_books_rented) values('"+cemail.getText()+"','"+cname.getText()+"','"+ad+"','"+p+"',"+ag+","+0+","+0+");";
					stmt.executeUpdate(query2);
					String query3 = "select * from Customer where email='"+cemail.getText()+"';";
					ResultSet rsx = stmt.executeQuery(query3);
					
					if(rsx.next()) {
						cust_id = rsx.getInt("id");
						String query = "insert into SoldBooks values('"+isbn.getText()+"','"+bookname.getText()+"',"+rsx.getInt("id")+",'"+date.getText()+"','"+payM.getSelectedItem().toString()+"');";
						stmt.executeUpdate(query);
					}
					this.dispose();
					JOptionPane.showMessageDialog(null, "Success!");
					try{
						File f = new File("bought_new_custoemr.txt");
						PrintWriter pw=new PrintWriter(f);
				        pw.println("Thank You for making a purchase at SS Books Store. \n\nYour Information is as follows: \n\nCustomer_ID: "+cust_id+"\n, CustomerName: "+cname.getText()+"\n, BookName: "+bookname.getText()+"\n, ISBN: "+isbn.getText()+"\n, Purchased on: "+date.getText()+"\n, Price: Rs."+price.getText()+"\n\n Thank You!");
				        pw.close();
					} 
					catch(Exception e1) {
						e1.printStackTrace();
					}
				}
				else {
					String query = "insert into SoldBooks values('"+isbn.getText()+"','"+bookname.getText()+"','"+Integer.parseInt(cid.getText())+"','"+date.getText()+"','"+payM.getSelectedItem().toString()+"');";
					stmt.executeUpdate(query);
					this.dispose();
					try{
						File f = new File("bought_old_custoemr.txt");
						PrintWriter pw=new PrintWriter(f);
				        pw.println("Thank You for making a purchase at SS Books Store. \n\nYour Information is as follows: \n\nCustomer_ID: "+cust_id+"\n, CustomerName: "+cname.getText()+"\n, BookName: "+bookname.getText()+"\n, ISBN: "+isbn.getText()+"\n, Purchased on: "+date.getText()+"\n, Price: Rs."+price.getText()+"\n\n Thank You!");
				        pw.close();
					} 
					catch(Exception e1) {
						e1.printStackTrace();
					}
				}
				String newquery = "select * from Books where bookname='"+bookname.getText()+"';";
				ResultSet rs = stmt.executeQuery(newquery);
				if(rs.next()) {
					String last = "update Books set stock="+(rs.getInt("stock")-1)+" where bookname='"+bookname.getText()+"';";
					stmt.executeUpdate(last);
					JOptionPane.showMessageDialog(null, "Success!");
				}
				stmt.close();
				con.close();
				return;
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}
		if(e.getSource()==rent) {
			try {
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BookStore","root","root");
				Statement stmt = con.createStatement();
				if(!cid.isEditable()) {
					int cust_id=0;
					String ad = JOptionPane.showInputDialog(null, "Enter Customer Address:");
					String p = JOptionPane.showInputDialog(null, "Enter Customer Phone no.:");
					float ag = Float.parseFloat(JOptionPane.showInputDialog(null, "Enter Age:"));	
					String query2 = "insert into Customer(email,name,address,phone,age,dues,no_of_books_rented) values('"+cemail.getText()+"','"+cname.getText()+"','"+ad+"','"+p+"',"+ag+","+0+","+1+");";
					stmt.executeUpdate(query2);
					String query3 = "select * from Customer where email='"+cemail.getText()+"';";
					ResultSet rsx = stmt.executeQuery(query3);
					if(rsx.next()) {
						cust_id= rsx.getInt("id");
						String query = "insert into RentedBooks values('"+isbn.getText()+"','"+bookname.getText()+"',"+rsx.getInt("id")+",'"+date.getText()+"','"+toDate.getText()+"','"+Float.parseFloat(price.getText())+"');";
						stmt.executeUpdate(query);
					}
					String newquery = "select * from Books where bookname='"+bookname.getText()+"';";
					ResultSet rs = stmt.executeQuery(newquery);
					if(rs.next()) {
						String last = "update Books set stock="+(rs.getInt("stock")-1)+" where bookname='"+bookname.getText()+"';";
						stmt.executeUpdate(last);
					}
					this.dispose();
					try{
						File f=new File("rent_new_customer.txt");
				        PrintWriter pw=new PrintWriter(f);
				        pw.println("Thank You for making a renting at SS Books Store.\n\n Your Information is as follows: \n\nCustomer_ID: "+cust_id+"\n, CustomerName: "+cname.getText()+"\n, BookName: "+bookname.getText()+"\n, ISBN: "+isbn.getText()+"\n, Rented on: "+date.getText()+"\n, Rented upto: "+toDate.getText()+"\n. Please make sure to return on or before "+toDate.getText()+" to avoid late fines. \n\nThank You!");
				        pw.close();
					} 
					catch(Exception e1) {
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Success!");
				}
				else {
					String query3 = "select * from Customer where email='"+cemail.getText()+"';";
					ResultSet rsx = stmt.executeQuery(query3);
					if(rsx.next()) {
						if(rsx.getInt("no_of_books_rented")<2) {
							int nobr = rsx.getInt("no_of_books_rented");
							String query = "insert into RentedBooks values('"+isbn.getText()+"','"+bookname.getText()+"','"+Integer.parseInt(cid.getText())+"','"+date.getText()+"','"+toDate.getText()+"','"+Float.parseFloat(price.getText())+"');";
							String queryx= "update Customer set no_of_books_rented="+(nobr+1)+" where email='"+cemail.getText()+"';";
							stmt.executeUpdate(query);
							stmt.executeUpdate(queryx);
							String newquery = "select * from Books where bookname='"+bookname.getText()+"';";
							ResultSet rs = stmt.executeQuery(newquery);
							if(rs.next()) {
								String last = "update Books set stock="+(rs.getInt("stock")-1)+" where bookname='"+bookname.getText()+"';";
								stmt.executeUpdate(last);
							}
						}
						else {
							JOptionPane.showMessageDialog(null, "Already issued 2 books!");
							return;
						}
					}
					this.dispose();
					try{
						File f = new File("rent_old_customer.txt");
				        PrintWriter pw=new PrintWriter(f);
				        pw.println("Thank You for making a renting at SS Books Store. \n\nYour Information is as follows: \n\nCustomer_ID: "+cid.getText()+"\n, CustomerName: "+cname.getText()+"\n, BookName: "+bookname.getText()+"\n, ISBN: "+isbn.getText()+"\n, Rented on: "+date.getText()+"\n, Rented upto: "+toDate.getText()+"\n. Please make sure to return on or before "+toDate.getText()+" to avoid late fines. \n\nThank You!");
				        pw.close();
					} 
					catch(Exception e1) {
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Success!");
				}
				stmt.close();
				con.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}

		}		
	}
}


@WebServlet("/BookStoreServlet")
public class BookStoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private int logged_in;
	
       
    public BookStoreServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			System.out.println(".....user is logged_in.....");
			
			if(logged_in==0) {
				RequestDispatcher rd =  request.getRequestDispatcher("index.jsp");
				rd.include(request, response);
				response.getWriter().append("You must be logged in!!!");
				return;
			}
			else {
			//Logout handling
			if(request.getParameter("params").equalsIgnoreCase("logout")) {
				logged_in=0;
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.include(request, response);
				response.getWriter().append("Successfully Logged out!!!");
			}
			
			if(request.getParameter("params").equalsIgnoreCase("home")) {
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				try {
					try {
						Class.forName("com.mysql.jdbc.Driver");
					}
					catch(ClassNotFoundException cnfe) {
						cnfe.printStackTrace();
					}
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BookStore","root","root");
					logged_in=1;
					//HashMap<String, Integer> hm = new HashMap<>();
					RequestDispatcher rd = request.getRequestDispatcher("admin_home.jsp");
					Statement stmt = con.createStatement();
					String q1 = "Select * from Books;";
					ResultSet rs = stmt.executeQuery(q1);
					rd.include(request, response);
					out.append("<table align=\"center\" style=\"margin:'1px solid black'\"><tr><th>BookName</th><th>Stock Left</th></tr>");
					while(rs.next()) {
						//hm.put(rs.getString("bookname"), rs.getInt("stock"));
						out.append("<tr><td>"+rs.getString("bookname")+"</td><td>"+rs.getInt("stock")+"</td></tr>");
					}
					//request.setAttribute("stockDetails", hm);
					out.append("</table>");
				}
				catch(SQLException sqle) {
					sqle.printStackTrace();
				}
			}
			
			if(request.getParameter("params").equalsIgnoreCase("add_book")) {
				RequestDispatcher rd = request.getRequestDispatcher("add_book.jsp");
				rd.include(request, response);
			}
			if(request.getParameter("params").equalsIgnoreCase("rent_book")) {
				RequestDispatcher rd = request.getRequestDispatcher("rent_book.jsp");
				rd.include(request, response);
			}
			if(request.getParameter("params").equalsIgnoreCase("new_transact")) {
				RequestDispatcher rd = request.getRequestDispatcher("new_transact.jsp");
				rd.include(request, response);
			}
			if(request.getParameter("params").equalsIgnoreCase("process_return")) {
				RequestDispatcher rd = request.getRequestDispatcher("process_return.jsp");
				rd.include(request, response);
			}
		}
	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	System.out.println(".....user is logged_in.....");
    	
    	
    	//This is for admin_login_Trial
		if(request.getParameter("admin_login_btn")!=null) {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			String name = request.getParameter("name");
			String pass = request.getParameter("pass");
			try {
				try {
					Class.forName("com.mysql.jdbc.Driver");
				}
				catch(ClassNotFoundException cnfe) {
					cnfe.printStackTrace();
				}
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BookStore","root","root");
				String query = "Select * from Admin where name='"+name+"' and pass='"+pass+"';";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				if(rs.next()) {
					logged_in=1;
					RequestDispatcher rd = request.getRequestDispatcher("admin_home.jsp");
					String q1 = "Select * from Books;";
					rs = stmt.executeQuery(q1);
					rd.include(request, response);
					out.append("<table align=\"center\"><tr><th style=\"border:'1px solid black'\">BookName</th><th>Stock Left</th></tr>");
					while(rs.next()) {
						out.append("<tr><td  style=\"border:'1px solid black'\">"+rs.getString("bookname")+"</td><td>"+rs.getInt("stock")+"</td></tr>");
					}
					out.append("</table>");
				}
				else {
					RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
					rd.include(request, response);
					out.append("<p class=form-group>Wrong Credentials try again!</p>");
				}
			}
			catch(SQLException sqle) {
				sqle.printStackTrace();
			}
		}
		
		//Logout handling
		if(logged_in==0) {
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.include(request, response);
			response.getWriter().append("You are Logged out!!!");
			return;
		}
		else {    

				
		//This is for adding books
		if(request.getParameter("add_book_btn")!=null) {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			String name = request.getParameter("bookname");
			String author = request.getParameter("author");
			String genre = request.getParameter("genre");
			float prc = Float.parseFloat(request.getParameter("price"));
			int stock = Integer.parseInt(request.getParameter("qty"));
			try {
				try {
					Class.forName("com.mysql.jdbc.Driver");
				}
				catch(ClassNotFoundException cnfe) {
					cnfe.printStackTrace();
				}
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BookStore","root","root");
				String query = "insert into Books(bookname,author,genre,price,stock) values('"+name+"','"+author+"','"+genre+"',"+prc+","+stock+");";
				Statement stmt = con.createStatement();
				int x = stmt.executeUpdate(query);
				if(x==0) {
					RequestDispatcher rd = request.getRequestDispatcher("add_book.jsp");
					rd.include(request, response);
					out.append("<p>Something went wrong please try again...</p>");
				}
				else {
					String newQ = "insert into BookId(bookname) values('"+name+"');";
					for(int i=0; i<stock; i++) {
						stmt.executeUpdate(newQ);
					}				
					RequestDispatcher rd = request.getRequestDispatcher("add_book.jsp");
					rd.include(request, response);
					out.append("<p>Updated Successfully!</p>");
				}
			}
			catch(SQLException sqle) {
				sqle.printStackTrace();
			}
		}
		
		//Check customer exists or not and buy
		if(request.getParameter("chk_cust_btn")!=null) {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			String c_email = request.getParameter("email");
			float price;
			String name = request.getParameter("name");
			try {
				try {
					Class.forName("com.mysql.jdbc.Driver");
				}
				catch(ClassNotFoundException cnfe) {
					cnfe.printStackTrace();
				}
				
				String isbn;
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BookStore","root","root");
				String query ="select * from Customer where email='"+c_email+"';";
				String query2 = "select * from Books where bookname='"+name+"';";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query2);
				if(rs.next()) {
					if(rs.getInt("stock")>=1) {
						price = rs.getFloat("price");
						String q = "Select * from BookId where bookname='"+name+"';";
						rs = stmt.executeQuery(q);
						if(rs.next()) {
							int temp = rs.getInt("isbn");
							isbn = ""+temp;
							String qt = "delete from BookId where isbn="+temp+";";
							stmt.executeUpdate(qt);
						}
						else {
							isbn="";
						}
					}
					else {
						isbn="";
						price=0;
						RequestDispatcher rd = request.getRequestDispatcher("new_transact.jsp");
						rd.include(request, response);
						out.append("<p>No more books: "+name+" left in stock</p>");
						return;
					}
				}
				else {
					isbn="";
					price=0;

					
					RequestDispatcher rd = request.getRequestDispatcher("new_transact.jsp");
					rd.include(request, response);
					out.append("<p>No more books: "+name+" left in stock</p>");
					return;
				}
				rs = stmt.executeQuery(query);
				if(rs.next()) {
					Customer c = new Customer();
					c.id=rs.getInt("id");
					c.bname = name;
					c.name=rs.getString("name");
					c.isbn = isbn; 
					c.dues = rs.getFloat("dues");
					c.price = price;
					Billing b = new Billing(c,0);
					b.setSize(300, 300);
					b.setVisible(true);
					b.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				}
				else {
					Billing b = new Billing(c_email, name, isbn, price,  0);
					b.setSize(300, 300);
					b.setVisible(true);
					b.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				}
				RequestDispatcher rd = request.getRequestDispatcher("new_transact.jsp");
				rd.include(request, response);
				//out.append("<p>Successfully recorded purchase!</p>");
			}
			catch(SQLException sqle) {
				sqle.printStackTrace();
			}
		}
		
		//This is to handle renting of books
		if(request.getParameter("chk_cust_rent_btn")!=null) {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			String c_email = request.getParameter("email");
			float price;
			int flag=0;
			String bookname = request.getParameter("name");
			try {
				try {
					Class.forName("com.mysql.jdbc.Driver");
				}
				catch(ClassNotFoundException cnfe) {
					cnfe.printStackTrace();
				}
				String isbn;
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BookStore","root","root");
				String query ="select * from Customer where email='"+c_email+"';";
				String query2 = "select * from Books where bookname='"+bookname+"';";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query2);
				if(rs.next()) {
					if(rs.getInt("stock")>=1) {
						price = rs.getFloat("price");
						String q = "Select * from BookId where bookname='"+bookname+"';";
						rs = stmt.executeQuery(q);
						if(rs.next()) {
							int temp = rs.getInt("isbn");
							isbn = ""+temp;
							String qt = "delete from BookId where isbn="+temp+";";
							stmt.executeUpdate(qt);
						}
						else {
							isbn="";
						}
					}
					else {
						isbn="";
						price=0;


						RequestDispatcher rd = request.getRequestDispatcher("rent_book.jsp");
						rd.include(request, response);
						out.append("<p>No more books: "+bookname+" left in stock</p>");
						return;
					}
				}
				else {
					isbn="";
					price=0;


					RequestDispatcher rd = request.getRequestDispatcher("rent_book.jsp");
					rd.include(request, response);
					out.append("<p>No books titled: "+bookname+" exists</p>");
					return;
				}
				rs = stmt.executeQuery(query);
				if(rs.next()) {
					try{
						Customer c = new Customer();
						c.id=rs.getInt("id");
						c.bname = bookname;
						c.email = c_email;
						c.name=rs.getString("name");
						c.isbn = isbn; 
						c.dues = rs.getFloat("dues");
						c.price = price;
						Billing b = new Billing(c,1);
						b.setSize(300, 300);
						b.setVisible(true);
						b.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					}
					catch(Exception e) {
						e.printStackTrace();
					}
					finally {
						RequestDispatcher rd = request.getRequestDispatcher("rent_book.jsp");
						rd.include(request, response);
						//out.append("<p>Successfully issued and recorded!</p>");
					}
					
				}
				else {
					try {
						Billing b = new Billing(c_email, bookname, isbn, price,  1);
						b.setSize(300, 300);
						b.setVisible(true);
						b.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					}
					catch(Exception e) {
						e.printStackTrace();
					}
					finally {
						RequestDispatcher rd = request.getRequestDispatcher("rent_book.jsp");
						rd.include(request, response);
						//JOptionPane.showMessageDialog(null, "Success!");
						//out.append("<p>Successfully issued and recorded!</p>");
					}	
				}
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}
		
		//This is to handle return of rented books
		if(request.getParameter("process_return_btn")!=null) {
			try {
				try {
					Class.forName("com.mysql.jdbc.Driver");
				}
				catch(ClassNotFoundException cnfe) {
					cnfe.printStackTrace();
				}
				
				String toDate;
				String bname;
				Float dues;
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BookStore","root","root");
				PrintWriter out = response.getWriter();
				int cid = Integer.parseInt(request.getParameter("cid"));
				int isbn = Integer.parseInt(request.getParameter("isbn"));
				Statement stmt = con.createStatement();
				String query = "Select * from RentedBooks where id="+cid+" and isbn="+isbn+";";
				//System.out.print(query);
				int nof;
				ResultSet rs = stmt.executeQuery(query);
				dues=0.0f;
				if(rs.next()) {
					bname = rs.getString("bookname");
					toDate = rs.getString("to_Date");
					String q1 = "select * from Customer where id="+cid+";";
					ResultSet r1 = stmt.executeQuery(q1);
					if(r1.next()) {
						dues = r1.getFloat("dues");
						nof = r1.getInt("no_of_books_rented");
						Date cdate = new Date();
						String cur_date = ""+cdate;
						String to_date = ""+toDate;
						String curDateArr[] = cur_date.split(" ");
						String toDateArr[] = to_date.split(" ");
						String toDay = toDateArr[2];
						String toMonth = toDateArr[1];
						String curDay = curDateArr[2];
						String curMonth = curDateArr[1];
						int day1=Integer.parseInt(toDay);
						int day2=Integer.parseInt(curDay);
						int month1, month2;
						
						//System.out.println("TO: "+toMonth);
						//System.out.println("Curr: "+curMonth);
						
						if(toMonth.equalsIgnoreCase("Jan")) {
							month1=1;
						}
						else if(toMonth.equalsIgnoreCase("Feb")) {
							month1=2;
						}
						else if(toMonth.equalsIgnoreCase("Mar")) {
							month1=3;
						}
						else if(toMonth.equalsIgnoreCase("Apr")) {
							month1=4;
						}
						else if(toMonth.equalsIgnoreCase("May")) {
							month1=5;
						}
						else if(toMonth.equalsIgnoreCase("Jun")) {
							month1=6;
						}
						else if(toMonth.equalsIgnoreCase("Jul")) {
							month1=7;
						}
						else if(toMonth.equalsIgnoreCase("Aug")) {
							month1=8;
						}
						else if(toMonth.equalsIgnoreCase("Sep")) {
							month1=9;
						}
						else if(toMonth.equalsIgnoreCase("Oct")) {
							month1=10;
						}
						else if(toMonth.equalsIgnoreCase("Nov")) {
							month1=11;
						}
						else {
							month1=12;
						}
						
						if(curMonth.equalsIgnoreCase("Jan")) {
							month2=1;
						}
						else if(curMonth.equalsIgnoreCase("Feb")) {
							month2=2;
						}
						else if(curMonth.equalsIgnoreCase("Mar")) {
							month2=3;
						}
						else if(curMonth.equalsIgnoreCase("Apr")) {
							month2=4;
						}
						else if(curMonth.equalsIgnoreCase("May")) {
							month2=5;
						}
						else if(curMonth.equalsIgnoreCase("Jun")) {
							month2=6;
						}
						else if(curMonth.equalsIgnoreCase("Jul")) {
							month2=7;
						}
						else if(curMonth.equalsIgnoreCase("Aug")) {
							month2=8;
						}
						else if(curMonth.equalsIgnoreCase("Sep")) {
							month2=9;
						}
						else if(curMonth.equalsIgnoreCase("Oct")) {
							month2=10;
						}
						else if(curMonth.equalsIgnoreCase("Nov")) {
							month2=11;
						}
						else {
							month2=12;
						}
						
						int duration;
						//System.out.println("to_date: "+day1+"-"+month1);
						//System.out.println("curr_date: "+day2+"-"+month2);
						if(month1!=month2) {
							duration= ((30-day1)+(day2)-((1-(month2-month1))*30));
							System.out.println("duration: "+duration);
							if(duration>0) {
								dues = dues + (float) (duration*2);
							}
							else {	
								dues=0.0f;
							}
						}
						else {
							if(day2<=day1) {
								duration=0;
								dues=0.0f;
							}
							else {
							duration= day2-day1;
							if(duration>0)
								dues = dues + (float) (duration*2);
							}
						}
						
						//System.out.print("Collected dues of Rs."+dues+" for a duration of: "+duration+" days.");
						JOptionPane.showMessageDialog(null, "Collect Rs."+dues+" as fine for "+duration+" days.");
						String q2 = "select * from Books where bookname='"+bname+"';";
						ResultSet r2 = stmt.executeQuery(q2);
						if(r2.next()) {
							String q3 = "update Books set stock="+(r2.getInt("stock")+1)+" where bookname='"+bname+"';";
							stmt.executeUpdate(q3);
						}
						String q4 = "insert into BookId values("+isbn+",'"+bname+"');";
						stmt.executeUpdate(q4);
						String q5 = "Update Customer set dues=0 where id="+cid+";";
						stmt.executeUpdate(q5);
						String q7 =  "update Customer set no_of_books_rented="+(nof-1)+" where id="+cid+";";
						stmt.executeUpdate(q7);
						String q6 = "Delete from RentedBooks where id="+cid+" and isbn="+isbn;
						stmt.executeUpdate(q6);
						
					}
					RequestDispatcher rd = request.getRequestDispatcher("process_return.jsp");
					rd.include(request, response);
					out.println("Succesfully processed!");
					out.append("Collected a fine of Rs."+dues);
				}
				else {
					RequestDispatcher rd = request.getRequestDispatcher("process_return.jsp");
					rd.include(request, response);
					out.append("No such books rented");
				}
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}
		
	}
    }
}
