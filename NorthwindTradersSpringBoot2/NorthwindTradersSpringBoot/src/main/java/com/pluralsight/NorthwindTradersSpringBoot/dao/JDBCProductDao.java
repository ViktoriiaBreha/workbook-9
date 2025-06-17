package com.pluralsight.NorthwindTradersSpringBoot.dao;

import com.pluralsight.NorthwindTradersSpringBoot.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JDBCProductDao implements ProductDao{
    private List<Product> products;
    private DataSource dataSource;

    @Autowired
    public JDBCProductDao (DataSource dataSource){
        this.products = new ArrayList<>();
        this.dataSource = dataSource;
    }

    @Override
    public List<Product> getAll(){
        this.products.clear();
        String query = "SELECT ProductId, ProductName, CategoryId, UnitPrice FROM Products";
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rows = statement.executeQuery();
            while(rows.next()){
                this.products.add(new Product(
                        rows.getInt(1),
                        rows.getString(2),
                        rows.getString(3),
                        rows.getDouble(4)));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return this.products;
    }
    @Override
    public void add(Product product) {
       String query = "INSERT INTO Products (ProductId, ProductName, CategoryId, UnitPrice) VALUES (?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, product.getProductId());
            preparedStatement.setString(2,product.getName());
            preparedStatement.setString(3, product.getCategory());
            preparedStatement.setDouble(4,product.getPrice());

            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void delete(int productId) {
        String query = "DELETE FROM Products WHERE ProductId = ?";
        try(Connection conn = dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(query)){
            preparedStatement.setInt(1, productId);
            preparedStatement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Product updatedProd) {
        String sql = "UPDATE Products SET ProductName = ?, CategoryId = ?, UnitPrice = ? WHERE ProductId = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, updatedProd.getName());
            stmt.setString(2, updatedProd.getCategory());
            stmt.setDouble(3, updatedProd.getPrice());
            stmt.setInt(4, updatedProd.getProductId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product findById(int productId) {
        String sql = "SELECT ProductId, ProductName, CategoryId, UnitPrice FROM Products WHERE ProductId = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Product(
                        rs.getInt("ProductId"),
                        rs.getString("ProductName"),
                        rs.getString("CategoryId"),
                        rs.getDouble("UnitPrice")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding product", e);
        }

        return null;
    }
}
