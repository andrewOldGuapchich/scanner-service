package com.andrew.scannerservice.dao;

import com.andrew.scannerservice.connection.PostgreSQLConnection;
import com.andrew.scannerservice.model.dtos.product.ProductInformDto;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDAO {
    private final Connection connection = PostgreSQLConnection.getConnection();

    public String getNameProduct(long productId) throws SQLException {
        String query = "select p.description from product p where id = ?";
        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, productId);
            ResultSet set = statement.executeQuery();
            if(set.next())
                return set.getString(1);
        } catch (SQLException sqlException){
            throw new SQLException();
        }
        return null;
    }

    public List<ProductInformDto> getProductList(long productId) throws SQLException {
        String query = "select bp.id_box, bp.count from box_product bp\n" +
                "left join product p on p.id = bp.id_product\n" +
                "left join box b on b.id = bp.id_box\n" +
                "where bp.id_product = ?";
        List<ProductInformDto> resultList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, productId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                resultList.add(new ProductInformDto(
                                resultSet.getLong(1),
                                resultSet.getInt(2)
                        ));
            }
        } catch (SQLException sqlException){
            throw new SQLException();
        }
        return resultList;
    }
}
