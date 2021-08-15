/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miguel.com.view;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import miguel.com.model.vo.ProyectoBancovo;

/**
 *
 * @author Janus
 */
public class TableModelBanco extends AbstractTableModel {
    private List<ProyectoBancovo> listaProyectoBanco;

    public TableModelBanco(List<ProyectoBancovo> listaProyectoBanco) {
        this.listaProyectoBanco = listaProyectoBanco;
    }
    

    @Override
    public int getRowCount() {
       return listaProyectoBanco.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public String getColumnName(int column) {
         switch (column){
          case 0:
            return  "Numero de Proyecto";
          case 1:
            return  "Constructora";
          case 2:
            return  "Cuidad";
          case 3:
            return  "Clasificacion";
          case 4:
            return "Estrato";
          case 5:
              return "Lider";
              
      }
        return super.getColumnName(column); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    @Override
    public Object getValueAt(int row, int column) {
      ProyectoBancovo proyecto =  listaProyectoBanco.get(row);
      
      switch (column){
          case 0:
            return  proyecto.getId();
          case 1:
            return  proyecto.getConstructora();
          case 2:
            return  proyecto.getCuidad();
          case 3:
            return  proyecto.getClasificacion();
          case 4:
            return proyecto.getEstrato();
          case 5:
              return proyecto.getLider();
              
      }
              return null;
    }
    
}
