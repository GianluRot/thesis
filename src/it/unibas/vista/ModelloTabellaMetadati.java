package it.unibas.vista;

import it.unibas.modello.Metadata;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelloTabellaMetadati extends AbstractTableModel{
    
    private List<Metadata> lista;

    public ModelloTabellaMetadati(List<Metadata> lista) {
        this.lista = lista;
    }

    public void setListaMetadati(List<Metadata> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        if (lista == null){
            return 0;
        }
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        Metadata dato = lista.get(i);
        if (i1 == 0){
            return dato.getNome();
        }
        if (i1 == 1){
            return dato.getValore();
        }
        return null;
    }

    @Override
    public String getColumnName(int i1) {
        if (i1 == 0){
            return "Proprietà";
        }
        if (i1 == 1){
            return "Valore";
        }
        return "";
    }
    

    public void aggiornaTabella() {
        this.fireTableDataChanged();
    }

}
