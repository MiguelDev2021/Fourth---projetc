/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miguel.com.view;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import miguel.com.controller.ReportesController;
import miguel.com.model.vo.PagadoPorProyectoVo;

/**
 *
 * @author Janus
 */
public class TableModelPagadoProyecto extends AbstractTableModel {
    private List<PagadoPorProyectoVo> listaPagadoporProyecto;

    public TableModelPagadoProyecto(List<PagadoPorProyectoVo> listaPagadoporProyecto) {
        this.listaPagadoporProyecto = listaPagadoporProyecto;
    }
    
    
    
    
    
    
    @Override
    public int getRowCount() {
        return listaPagadoporProyecto.size();
    }

    @Override
    public int getColumnCount() {
       return 2;
    }

    @Override
    public String getColumnName(int column) {
        if(column == 0){
        return "Numero de Proyecto";
       }else if(column == 1){
           return "Valor";
       
       }
        
        return super.getColumnName(column); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Object getValueAt(int row, int column) {
       PagadoPorProyectoVo proyecto =  listaPagadoporProyecto.get(row);
       if(column == 0){
        return proyecto.getId();
       }else if(column == 1){
           return proyecto.getValor();
       
       }
       
       return null;
    }
    
    
    
}
