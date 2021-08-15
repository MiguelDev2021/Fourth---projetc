/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miguel.com.view;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import miguel.com.model.vo.ComprasDeLiderVo;

/**
 *
 * @author Janus
 */
public class TableModelLideres extends AbstractTableModel {
     private List<ComprasDeLiderVo> listaLideres;

    public TableModelLideres(List<ComprasDeLiderVo> listaLideres) {
        this.listaLideres = listaLideres;
    }
     
    
    @Override
    public int getRowCount() {
      return listaLideres.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public String getColumnName(int column) {
         if(column == 0){
           return "Nombre Lider";
       }else if(column == 1){
           return "Total Gastado";
       }
        
        return super.getColumnName(column); //To change body of generated methods, choose Tools | Templates.
    }
    

    @Override
    public Object getValueAt(int row, int column) {
       ComprasDeLiderVo lider = listaLideres.get(row);
       
       if(column == 0){
           return lider.getLider();
       }else if(column == 1){
           return lider.getValor();
       }
       return null;
    }
    
}
