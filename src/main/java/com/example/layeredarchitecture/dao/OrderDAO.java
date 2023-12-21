package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.model.OrderDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDAO {
    String generateNewId() throws SQLException, ClassNotFoundException;
    public boolean existOrder(String orderId) throws SQLException, ClassNotFoundException;
    boolean saveOrder(OrderDTO orderdto) throws SQLException, ClassNotFoundException;

}
