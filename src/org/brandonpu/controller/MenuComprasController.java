/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.brandonpu.controller;

import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.brandonpu.dao.Conexion;
import org.brandonpu.model.Compra;
import org.brandonpu.model.DetalleCompra;
import org.brandonpu.model.Producto;
import org.brandonpu.system.Main;
import org.brandonpu.utils.SuperKinalAlert;

/**
 * FXML Controller class
 *
 * @author Pavilión
 */
public class MenuComprasController implements Initializable {
    private Main stage;
    
    private static Connection conexion = null;
    private static PreparedStatement statement = null;
    private static ResultSet resultSet = null;
    /**
     * Initializes the controller class.
     */
    
    @FXML
    ComboBox cmbCompraId,cmbProductoId;
    @FXML
    Button btnRegresar,btnGuardar,btnEliminar,btnVaciar,btnGuardarDC,btnVaciarDC;
    @FXML
    TableView tblDetalleCompras,tblCompras; 
    @FXML
    TextField tfCompraId,tfTotalCompra,tfFechaCompra,tfDetalleCompraId,tfCantidadCompra;
    @FXML
    TableColumn colDetalleCompraId,colCantidadCompra,colProducto,colCompra,colCompraId,colFechaCompra,colTotalCompra ;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbProductoId.setItems(listarProductos());
        cmbCompraId.setItems(listarCompra());
        cargarDatos();
        cargarDatosDC();
    }    

    @FXML
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnRegresar){
            stage.menuPrincipalView();
        } else if(event.getSource() == btnGuardar){
            if(tfCompraId.getText().equals("")){
                if(!tfTotalCompra.getText().equals("")){
                    agregarCompra();
                    SuperKinalAlert.getInstance().mostrarAlertaInfo(401);
                    //vaciarDatos();
                    cargarDatosDC();
                    cargarDatos();
                    cmbCompraId.setItems(listarCompra());
                }else{
                    SuperKinalAlert.getInstance().mostrarAlertaInfo(400);
                    tfTotalCompra.requestFocus();
                    return;
                }
            } else{
                if(!tfCompraId.getText().equals("")){
                    if(SuperKinalAlert.getInstance().mostrarAlertaConfirmacion(106).get() == ButtonType.OK){
                        if(!tfTotalCompra.getText().equals("")){
                            editarCompra();
                            cargarDatosDC();
                            cargarDatos();
                        } else{
                            SuperKinalAlert.getInstance().mostrarAlertaInfo(400);
                            tfTotalCompra.requestFocus();
                            return;
                        }
                    }
                }
            }
        } else if(event.getSource() == btnGuardarDC){
             if(tfDetalleCompraId.getText().equals("")){
                 if(!tfCantidadCompra.getText().equals("") && cmbCompraId.getValue() != null && cmbProductoId.getValue() != null){
                    agregarDetalleCompra();
                    SuperKinalAlert.getInstance().mostrarAlertaInfo(401);
                    //vaciarDatos();
                    cargarDatos();
                    cargarDatosDC();
                 }else{
                    SuperKinalAlert.getInstance().mostrarAlertaInfo(400);
                    tfCantidadCompra.requestFocus();
                    return;
                 }
            } else{
                /*if(!cmbEmpleadoId.getItems().equals("")){
                    if(SuperKinalAlert.getInstance().mostrarAlertaConfirmacion(106).get() == ButtonType.OK){
                        editarFactura();
                        cargarDatos();
                    }
                }*/
            }
        } else if(event.getSource() == btnVaciar){
            vaciarCompras();
        } else if(event.getSource() == btnVaciarDC){
            vaciarDetalleCompras();
        }
    }
    
    public void cargarDatos(){
        tblCompras.setItems(listarCompra());
        colCompraId.setCellValueFactory(new PropertyValueFactory<Compra, Integer>("compraId"));
        colFechaCompra.setCellValueFactory(new PropertyValueFactory<Compra, String>("fechaCompra"));
        colTotalCompra.setCellValueFactory(new PropertyValueFactory<Compra, Double>("totalCompra"));
        
    }
    
    public void cargarDatosDC(){
        tblDetalleCompras.setItems(listarDetalleCompra());
        colDetalleCompraId.setCellValueFactory(new PropertyValueFactory<DetalleCompra, Integer>("detalleCompraId"));
        colCantidadCompra.setCellValueFactory(new PropertyValueFactory<DetalleCompra, Integer>("cantidadCompra"));
        colProducto.setCellValueFactory(new PropertyValueFactory<DetalleCompra, String>("producto"));
        colCompra.setCellValueFactory(new PropertyValueFactory<DetalleCompra, String>("compra"));
    }
    
    public void vaciarCompras(){
        tfCompraId.clear();
        tfTotalCompra.clear();
        tfFechaCompra.clear();
    }
    
    public void vaciarDetalleCompras(){
        tfDetalleCompraId.clear();
        tfCantidadCompra.clear();
        cmbCompraId.getSelectionModel().clearSelection();
        cmbProductoId.getSelectionModel().clearSelection();
    }
    
    public void cargarDatosEditarC(){
        Compra c = (Compra)tblCompras.getSelectionModel().getSelectedItem();
        if(c != null){
            tfCompraId.setText(Integer.toString(c.getCompraId()));
            tfTotalCompra.setText(String.valueOf(c.getTotalCompra()));
            tfFechaCompra.setText(c.getFechaCompra());
        }
    }
    
    public void cargarDatosEditarDC(){
        DetalleCompra DC = (DetalleCompra)tblDetalleCompras.getSelectionModel().getSelectedItem();
        if(DC != null){
            tfDetalleCompraId.setText(Integer.toString(DC.getDetalleCompraId()));
            tfCantidadCompra.setText(Integer.toString(DC.getCantidadCompra()));
            cmbCompraId.getSelectionModel().select(obtenerIndexCompra());
            cmbProductoId.getSelectionModel().select(obtenerIndexProducto());
        }
    }
    
    public int obtenerIndexProducto(){
        int index = 0;
        for(int i = 0 ; i < cmbProductoId.getItems().size() ; i++){
            String productoCmb = cmbProductoId.getItems().get(i).toString();
            String productoTbl = ((DetalleCompra)tblDetalleCompras.getSelectionModel().getSelectedItem()).getProducto();
            if(productoCmb.equals(productoTbl)){
                index = i;
                break;
            }
        }
        return index;
    }
    
    public int obtenerIndexCompra(){
        int index2 = 0;
        for(int i  = 0 ; i < cmbCompraId.getItems().size() ; i++){
            String compraCmb = cmbCompraId.getItems().get(i).toString();
            String compraTbl = ((DetalleCompra)tblDetalleCompras.getSelectionModel().getSelectedItem()).getCompra();
            if(compraCmb.equals(compraTbl)){
                index2 = i;
                break;
            }
        }
        return index2;
    }
    
    public void agregarCompra(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_agregarCompra(?)";
            statement = conexion.prepareStatement(sql);
            double totalCompra = Double.parseDouble(tfTotalCompra.getText());
            statement.setDouble(1, totalCompra);
            statement.execute();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(resultSet != null){
                    resultSet.close();
                }
                if(statement != null){
                    statement.close();
                }
                if(conexion != null){
                    conexion.close();
                }
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public void agregarDetalleCompra(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_agregarDetalleCompra(?,?,?)";
            statement = conexion.prepareStatement(sql);
            statement.setString(1, tfCantidadCompra.getText());
            statement.setInt(2, ((Producto)cmbProductoId.getSelectionModel().getSelectedItem()).getProductoId());
            statement.setInt(3, ((Compra)cmbCompraId.getSelectionModel().getSelectedItem()).getCompraId());
            statement.execute();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(resultSet != null){
                    resultSet.close();
                }
                if(statement != null){
                    statement.close();
                }
                if(conexion != null){
                    conexion.close();
                }
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }
    
    public ObservableList<DetalleCompra> listarDetalleCompra(){
        ArrayList<DetalleCompra> detalleCompra = new ArrayList<>();
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_listarDetalleCompras()";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                int detalleCompraId = resultSet.getInt("detalleCompraId");
                int cantidadCompra = resultSet.getInt("cantidadCompra");
                String producto = resultSet.getString("producto");
                String compra = resultSet.getString("compra");
                
                detalleCompra.add(new DetalleCompra(detalleCompraId,cantidadCompra,producto,compra));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(resultSet != null){
                    resultSet.close();
                }
                if(statement != null){
                    statement.close();
                }
                if(conexion != null){
                    conexion.close();
                }
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
        
        return FXCollections.observableList(detalleCompra);
    }
   
    public ObservableList<Compra> listarCompra(){
        ArrayList<Compra> compra = new ArrayList<>();
        
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_listarCompras()";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                int compraId = resultSet.getInt("compraId");
                String fechaCompra = resultSet.getString("fechaCompra");
                Double totalCompra = resultSet.getDouble("totalCompra");
                
                compra.add(new Compra(compraId,fechaCompra,totalCompra));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(resultSet != null){
                    resultSet.close();
                }
                if(statement != null){
                    statement.close();
                }
                if(conexion != null){
                    conexion.close();
                }
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
        
        return FXCollections.observableList(compra);
    }
    
    public ObservableList<Producto> listarProductos(){
        ArrayList<Producto> productos = new ArrayList<>();
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_ListarProductos()";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                int productoId = resultSet.getInt("productoId");
                String nombre = resultSet.getString("nombreProducto");
                String descripcion = resultSet.getString("descripcionProducto");
                int stock = resultSet.getInt("cantidadStock");
                double unidad = resultSet.getDouble("precioVentaUnitario");
                double mayor = resultSet.getDouble("precioVentaMayor");
                double compra = resultSet.getDouble("precioCompra");
                Blob imagen = resultSet.getBlob("imagenProducto");
                String distribuidor = resultSet.getString("Distribuidor");
                String categoria = resultSet.getString("Categoria");

                productos.add(new Producto(productoId, nombre, descripcion, stock, unidad, mayor, compra, imagen,distribuidor,categoria));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
            
        }finally{
            try{
                if(resultSet != null){
                    resultSet.close();
                }
                if(statement != null){
                    statement.close();
                }
                if(conexion != null){
                    conexion.close();
                }
            }catch(SQLException e){
            System.out.println(e.getMessage());
            }
        }
        
        return FXCollections.observableList(productos);
    }
    
    public void editarCompra(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_editarCompra(?,?,?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(tfCompraId.getText()));
            statement.setString(2, tfFechaCompra.getText());
            statement.setDouble(3, Double.parseDouble(tfTotalCompra.getText()));
            statement.execute();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(conexion != null){
                conexion.close();
                }
                if(statement != null){
                    statement.close();
                }
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }
    
    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }
    
    
}
