package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.CustomerDTO;
import com.example.layeredarchitecture.view.tdm.CustomerTM;
import javafx.scene.control.TableView;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Customer");
        ArrayList<CustomerDTO> customerDTOS = new ArrayList<>();
        while(rst.next()) {
            CustomerDTO customerDTO = new CustomerDTO(rst.getString(
                    "id"), rst.getString("name"), rst.getString("address"));
            customerDTOS.add(customerDTO);
        }
        return customerDTOS;
    }

    @Override
    public boolean save(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Customer (id,name, address) VALUES (?,?,?)", customerDTO.getId(), customerDTO.getName(), customerDTO.getAddress());
    }

    @Override
    public boolean update(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Customer SET name=?, address=? WHERE id=?",customerDTO.getName(),customerDTO.getAddress(),customerDTO.getId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Customer WHERE id=?",id);
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT id FROM Customer WHERE id=?");
        pstm.setString(1, id);
        return pstm.executeQuery().next();
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT id FROM Customer ORDER BY id DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("id");
            int newCustomerId = Integer.parseInt(id.replace("C00-", "")) + 1;
            return String.format("C00-%03d", newCustomerId);
        } else {
            return "C00-001";
        }
    }

    @Override
    public String getLastCustomerId(TableView<CustomerTM> tblCustomers) {
        List<CustomerTM> tempCustomersList = new ArrayList<>(tblCustomers.getItems());
        Collections.sort(tempCustomersList);
        return tempCustomersList.get(tempCustomersList.size() - 1).getId();
    }

    @Override
    public ArrayList<String> loadCustomersIds() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Customer");
        ArrayList<String> customerIds = new ArrayList<>();
        while (resultSet.next()) {
            customerIds.add(resultSet.getString("id"));
        }
        return customerIds;
    }

    @Override
    public String getCustomerName(String newValue) throws SQLException, ClassNotFoundException {
        ResultSet resultSet= SQLUtil.execute("SELECT * FROM Customer WHERE id=?",newValue);
        String customerName = null;
        while (resultSet.next()) {
            customerName = resultSet.getString("name");
        }
        return customerName;
    }
}
