package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.model.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemDAO extends CrudDAO<ItemDTO>{

    ArrayList<String> loadAllItemCodes() throws SQLException, ClassNotFoundException;
    ItemDTO findItem(String code) throws SQLException, ClassNotFoundException;
    ItemDTO getItemDetails(String newItemCode) throws SQLException, ClassNotFoundException;
}
