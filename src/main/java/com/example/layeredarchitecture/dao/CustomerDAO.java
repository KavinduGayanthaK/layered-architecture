package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.model.CustomerDTO;
import com.example.layeredarchitecture.view.tdm.CustomerTM;
import javafx.scene.control.TableView;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDAO extends CrudDAO<CustomerDTO> {

    String getLastCustomerId(TableView<CustomerTM> tblCustomers);
    ArrayList<String> loadCustomersIds() throws SQLException, ClassNotFoundException;
    String getCustomerName(String newValue) throws SQLException, ClassNotFoundException;
}
