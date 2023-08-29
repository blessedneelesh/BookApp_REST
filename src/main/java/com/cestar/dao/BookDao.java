package com.cestar.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.cestar.model.Book;


public class BookDao {
	public Connection setUpConnection() {
		Connection con=null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Loaded the embedded driver.");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String user="root";
		String pwd="";
		String url="jdbc:mysql://localhost:3307/book";
		
		try {
			con=DriverManager.getConnection(url, user, pwd);
			System.out.println("successful");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
	}
	
	public ArrayList<Book> SelectAllBook(){
		ArrayList <Book> allBook=new ArrayList<>(); 
		// setup connection by calling the method setUpConnection()
		
				Connection con=setUpConnection();
				
				// Write the sql query needed as a string
				
				String sql="select * from book";
				
				// For select query we use Statement interface.
				Statement stmt;
				
				try {
					stmt = con.createStatement();
					
					ResultSet rs=stmt.executeQuery(sql);
					
					while(rs.next()) {
						Book book_from_rs= new Book(rs.getString("bookId"),rs.getString("name"),rs.getString("author"),rs.getFloat("price"),rs.getString("genre"));
						allBook.add(book_from_rs);
					}
					System.out.println(allBook);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return allBook;
	}
	
	public int insertRec(Book book_from_servlet) {
		System.out.println(book_from_servlet);
		int status=0;
		Connection con=setUpConnection();
		String sql="insert into book (bookId, name,author,price,genre) values(?,?,?,?,?)";
		
		try {
		
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setString(1,book_from_servlet.getBookId());
			pstmt.setString(2, book_from_servlet.getName());
			pstmt.setString(3, book_from_servlet.getAuthor());
			pstmt.setFloat(4, book_from_servlet.getPrice());
			pstmt.setString(5, book_from_servlet.getGenre());
			
			status=pstmt.executeUpdate();
			
			if(status>0) {
				System.out.println("record Inserted!!");
			}else {
				System.out.println("TRY again");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return status;
	}
	
	public Book getBookById(String eid) {
		Book book_from_rs=null;
		Connection con=setUpConnection();
		String sql="select * from book where bookId=?";
		System.out.println(eid);
		try {
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,eid);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()) {
				book_from_rs= new Book(rs.getString("bookId"),rs.getString("name"),rs.getString("author"),rs.getFloat("price"),rs.getString("genre"));
			}
		System.out.println(book_from_rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return book_from_rs;
	}
	
	public int delete(String id) {
		Connection con = setUpConnection();
		int status =0;
		String sql="delete from book where bookId=?";
		
		try {
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.setString(1,id);
			
			status=stmt.executeUpdate();
			
			if(status>0) {
				System.out.println("record deleted!!");
			}else {
				System.out.println("TRY again");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return status;
	}
	
	public int updateBook(String oid, Book book) {
		int status=0;
		Connection con=setUpConnection();
		String sql="update book set bookId=?,name=?,author=?, price=?,genre=? where bookId=?";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, book.getBookId());
			pstmt.setString(2, book.getName());
			pstmt.setString(3, book.getAuthor());
			pstmt.setFloat(4, book.getPrice());
			pstmt.setString(5, book.getGenre());
			pstmt.setString(6, oid);
			
	status=pstmt.executeUpdate();
			
			if(status>0) {
				System.out.println("record Updated!!");
			}else {
				System.out.println("TRY again");
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return status;
	}
}
