package com.andrew.scannerservice.dao;

import com.andrew.scannerservice.connection.PostgreSQLConnection;
import com.andrew.scannerservice.model.dtos.box.BoxDto;
import com.andrew.scannerservice.model.dtos.box.BoxInformDto;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BoxDAO {
    private final Connection connection = PostgreSQLConnection.getConnection();

    public List<BoxInformDto> getBoxInformList(long idBox) throws SQLException{
        String query = "select bp.id_product, bp.count, p.description  from box_product bp\n" +
                "left join box b on bp.id_box = b.id\n" +
                "left join product p on bp.id_product = p.id\n" +
                "where id_box = ?";

        List<BoxInformDto> resultList = null;
        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, idBox);
            ResultSet set = statement.executeQuery();
            resultList = new ArrayList<>();
            while (set.next()){
                BoxInformDto boxInformDto = new BoxInformDto(
                        set.getLong(1),
                        set.getInt(2),
                        set.getString(3)
                );

                resultList.add(boxInformDto);
            }
        } catch (SQLException sqlException){
            throw new SQLException();
        }

        return resultList;
    }

    public BoxDto getBoxData(Long idBox) throws SQLException{
        String query = "select * from box " +
                "where id = ?";
        BoxDto box = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, idBox);
            ResultSet result = preparedStatement.executeQuery();
            if(result.next()){
                box = new BoxDto(
                        result.getInt(1),
                        result.getInt(2),
                        result.getInt(3),
                        result.getString(4)
                );
            }
        } catch (SQLException sqlException){
            throw new SQLException();
        }

        return box;
    }

    /** если в ящике столько продуктов сколько насканировано, то перемещаем и из ящика удаляем запись
     *
     */
    public void moveOnFromBox(long idBox, long idProduct, int count){
        String query = "update box_product set count = count - ? where id_box = ? and id_product = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, count);
            preparedStatement.setLong(2, idBox);
            preparedStatement.setLong(3, idProduct);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }

    public void moveOnToBox(long idBox, long idProduct, int count) {
        String query = "update box_product set count = count + ? where id_box = ? and id_product = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, count);
            preparedStatement.setLong(2, idBox);
            preparedStatement.setLong(3, idProduct);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void clearBox(long idBox, long idProduct){
        String query = "delete from box_product where id_box = ? and id_product = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, idBox);
            preparedStatement.setLong(2, idProduct);
            preparedStatement.executeUpdate();
        } catch (SQLException s){
            s.printStackTrace();
        }
    }


    //количество товара в ящике
    public int getCountProductOnBox(long idBox, long idProduct){
        int count = 0;
        String query = "select pb.count from box_product pb where id_box = ?" +
                " and id_product = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, idBox);
            preparedStatement.setLong(2, idProduct);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                count = resultSet.getInt(1);
            }
        } catch (SQLException s){
            s.printStackTrace();
        }

        return count;
    }
}
