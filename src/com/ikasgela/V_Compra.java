package com.ikasgela;

import javax.swing.*;
import java.awt.*;

public class V_Compra {
    private JPanel panel1;
    private JButton add_Button;
    private JButton edit_Button;
    private JButton delete_Button;
    private JTextField producto_textField;
    private JTextField unidades_textField;
    private JList<Item> compra_Vlist;

    private final DefaultListModel<Item> modelo = new DefaultListModel<>();

    public V_Compra() {
        add_Button.addActionListener(e -> {
            if (!producto_textField.getText().equals("")) {
                int indice = compra_Vlist.getSelectedIndex();
                Item actual = null;
                if (unidades_textField.getText().equals("")) {
                    actual = new Item(producto_textField.getText(), 0);
                } else {
                    try {
                        int unidades = Integer.parseInt(unidades_textField.getText());
                        actual = new Item(producto_textField.getText(), unidades);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Tipo de dato ingresado en las " +
                                        "unidades es incorrecto intente nuevamente.", "Error"
                                , JOptionPane.ERROR_MESSAGE);
                    }
                }

                if (indice == -1) {
                    modelo.addElement(actual);
                } else {
                    modelo.add(indice, actual);
                }
                compra_Vlist.setModel(modelo);
                producto_textField.setText("");
                unidades_textField.setText("");
            } else JOptionPane.showMessageDialog(null, "Campo de Producto vacio.", "Error"
                    , JOptionPane.ERROR_MESSAGE);
        });

        edit_Button.addActionListener(e -> {
            int indice = compra_Vlist.getSelectedIndex();
            if (indice != -1) {
                if (producto_textField.getText().equals("") && unidades_textField.getText().equals("")) {
                    Item editing = modelo.get(indice);
                    modelo.remove(indice);
                    producto_textField.setText(editing.getProducto());
                    if (editing.getUnidades() == 0) unidades_textField.setText("");
                    else unidades_textField.setText(String.valueOf(editing.getUnidades()));

                } else JOptionPane.showMessageDialog(null, "Campos ocupados por edicion.", "Error"
                        , JOptionPane.ERROR_MESSAGE);

            } else JOptionPane.showMessageDialog(null, "Debes seleccionar un producto a editar.", "Error"
                    , JOptionPane.ERROR_MESSAGE);

        });

        delete_Button.addActionListener(e -> {
            int indice = compra_Vlist.getSelectedIndex();
            if (indice != -1) modelo.remove(indice);
            else JOptionPane.showMessageDialog(null, "Debes seleccionar un producto a eliminar."
                    , "Error", JOptionPane.ERROR_MESSAGE);
        });
    }

    public static void main(String[] args) {
        try {
            // Set System L&F
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException e) {
            // handle exception
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        JFrame frame = new JFrame("V_Compra");
        frame.setContentPane(new V_Compra().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;
        frame.setLocation(x, y);
    }
}
