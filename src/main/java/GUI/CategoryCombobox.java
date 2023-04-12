/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Entities.Category;
import Entities.Vegetable;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author ASUS
 */
public class CategoryCombobox extends DefaultListCellRenderer {
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        if (value instanceof Category) {
            Category categoryName = (Category) value;
            setText(categoryName.getName());
        }
        return this;
    }
}
