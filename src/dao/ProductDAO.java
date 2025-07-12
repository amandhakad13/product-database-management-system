package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.DBConnection;
import entity.Product;

public class ProductDAO {

	public int insert(Product p) throws SQLException {
		Connection con = DBConnection.getConnection();
		String query = "INSERT INTO products VALUES (?,?,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setInt(1, p.getId());
		pstmt.setString(2, p.getName());
		pstmt.setFloat(3, p.getPrice());
		pstmt.setInt(4, p.getQuantity());
		pstmt.setString(5, p.getManuDate());
		pstmt.setFloat(6, p.getDiscount());
		int isInsert = pstmt.executeUpdate();
		return isInsert;
	}

	public List<Product> display() throws SQLException {
		Connection con = DBConnection.getConnection();
		String query = "SELECT * FROM products";
		PreparedStatement pstmt = con.prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();
		List<Product> pl = new ArrayList<>();
		while (rs.next()) {
			Product p = new Product();

			p.setId(rs.getInt(1));
			p.setName(rs.getString(2));
			p.setPrice(rs.getFloat(3));
			p.setQuantity(rs.getInt(4));
			p.setManuDate(rs.getString(5));
			p.setDiscount(rs.getFloat(6));

			pl.add(p);
			p = null;
		}
		return pl;
	}

	public int delete(int id) throws SQLException {
		Connection con = DBConnection.getConnection();
		String query = "DELETE FROM products where p_id = ?";
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setInt(1, id);
		int isDelete = pstmt.executeUpdate();
		return isDelete;
	}

	public Product search(int id) throws SQLException {
		Connection con = DBConnection.getConnection();
		String query = "select * from products where p_id = ?";
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		Product p = new Product();
		if (rs.next()) {
			p.setId(rs.getInt(1));
			p.setName(rs.getString(2));
			p.setPrice(rs.getFloat(3));
			p.setQuantity(rs.getInt(4));
			p.setManuDate(rs.getString(5));
			p.setDiscount(rs.getFloat(6));
		} else {
			return null;
		}
		return p;

	}

}
