package CityPlanner.Views;

import CityPlanner.Model.Activity;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * Created by tito on 15/05/16.
 */
public class ResultTableModel extends AbstractTableModel {
    String[] columnNames = {"Activité",
            "Description",
            "Adresse",
            "Durée",
            "Prix",
            "Ouverture",
            "Accepter"};
    private ArrayList<Activity> activities;

    public ResultTableModel(ArrayList<Activity> activities) {
        this.activities = activities;
    }

    @Override
    public int getRowCount() {
        return activities.size();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Class getColumnClass(int c) {
        if(c==6) {
            return Boolean.class;
        } else {
            return String.class;
        }
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = "??";
        Activity a = activities.get(rowIndex);
        switch (columnIndex) {
            case 0:
                value = a.getName();
                break;
            case 1:
                value = a.getDescription();
                break;
            case 2:
                value = a.getAddress();
                break;
            case 3:
                value = a.getDuration();
                break;
            case 4:
                value = a.getPrice();
                break;
            case 5:
                value = a.getOpen();
                break;
            case 6:
                value = a.getSelected();
                break;
        }
        return value;

    }

    public boolean isCellEditable(int row, int col) {
        if (col == 6) {
            return true;
        } else {
            return false;
        }
    }

    public void setValueAt(Object value, int row, int col) {
        if(col == 6) {
            Activity a = activities.get(row);
            a.setSelected((Boolean) value);
        }
        fireTableCellUpdated(row, col);
    }
}