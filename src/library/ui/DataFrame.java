package library.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import library.data.AuthorDataAccess;
import library.data.BookDataAccess;
import library.data.PublisherDataAccess;
import library.pojo.Author;
import library.pojo.Book;
import library.pojo.LibraryEntity;
import library.pojo.Publisher;

public class DataFrame {

    private JFrame main_frame;
    private int frameType; // 0:Author, 1:Publisher, 2:Book
    private JFrame frame;
    private JLabel id_label;
    private JTextField search_textField;
    private JButton add_button;
    private JButton edit_button;
    private JButton delete_button;
    private JButton clear_button;
    private JButton find_button;
    private JButton showAll_button;
    private JButton back_button;
    private JScrollPane data_scrollPane;
    private JTable data_table;
    private DefaultTableModel model;

    private String frame_title[] = {"Authors", "Publishers", "Books"};
    private String  columns [][] = new String[][]{
            {"Author ID", "Author Name", "Author Biography"},
            {"Publisher ID","Publisher Name"},
            {"Book ID","ISBN","Title","Number of pages","Category","Language","Publication Year","Author ID","Publisher ID"}
    };
    private Object dataAccessObjects[] = {new AuthorDataAccess(), new PublisherDataAccess(), new BookDataAccess()};

    /**
     * Create the application.
     */
    public DataFrame(JFrame main_frame, int frameType) {
        this.main_frame = main_frame;
        this.frameType = frameType;
        initialize();
        frame.setVisible(true);
    }




    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

        frame = new JFrame(frame_title[frameType]);
        frame.setBounds(100, 100, 600, 340);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        id_label = new JLabel(frame_title[frameType].substring(0, frame_title[frameType].length() - 1) + " ID");
        id_label.setBounds(23, 25, 83, 30);
        frame.add(id_label);

        search_textField = new JTextField();
        search_textField.setBounds(108, 22, 86, 30);
        frame.add(search_textField);
        search_textField.setColumns(10);


        find_button = new JButton("Find");
        find_button.setBounds(206, 21, 83, 30);
        frame.add(find_button);

        showAll_button = new JButton("show all");
        showAll_button.setBounds(300, 21, 266, 30);
        frame.add(showAll_button);

        // Add Button
        add_button = new JButton("Add");
        add_button.setBounds(311, 88, 89, 23);

        // Edit Buttorn
        edit_button = new JButton("Edit");
        edit_button.setBounds(311, 147, 89, 23);

        // Delete Button
        delete_button = new JButton("Delete");
        delete_button.setBounds(311, 216, 89, 23);

        // ScrollPane and Table
        data_table = new JTable();
        data_table.setDefaultEditor(Object.class, null);
        data_table.setBounds(34, 235, 300, 300);

        data_scrollPane = new JScrollPane();
        data_scrollPane.setBounds(23, 73, 272, 177);
        data_scrollPane.setViewportView(data_table);

        clear_button = new JButton("Clear");
        clear_button.setBounds(311, 182, 89, 23);


        JPanel p = new JPanel(new GridLayout(1, 2, 10, 0));
        p.setBounds(23, 73, 544, 177);
        p.add(data_scrollPane);

        JPanel p1 = new JPanel(new GridLayout(4, 1, 0, 15));
        p1.add(add_button);
        p1.add(edit_button);
        p1.add(clear_button);
        p1.add(delete_button);
        p.add(p1);

        frame.add(p);

        back_button = new JButton("Back to Home");
        back_button.setBounds(23, 260, 544,30);
        frame.add(back_button);


        //Actions

        find_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try{
                    String txt = search_textField.getText();
                    int id = Integer.parseInt(txt);

                    LibraryEntity entity = getById(id);

                    data_table.setModel(new DefaultTableModel());
                    model = (DefaultTableModel) data_table.getModel();

                    for (Object val : columns[frameType]){
                        model.addColumn(val);
                    }

                    Object rowData[] = entityToArray(entity);

                    model.addRow(rowData);

                } catch(Exception ex) {
                    JOptionPane.showMessageDialog(frame,"ID doesn't exist");
                }
            }
        });



        showAll_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateTable();
            }
        });


        add_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createAddDialog();
            }
        });

            edit_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = getIDInSelectedRow();
                    LibraryEntity entity = getById(id);
                    Object rowData[] = entityToArray(entity);
                    createEditDialog(rowData);
                }
                catch (Exception ex){
                    JOptionPane.showMessageDialog(frame,"You have to select one record first");
                }
            }
        });


        delete_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    int id = getIDInSelectedRow();
                    delete(id);
                    DefaultTableModel model = (DefaultTableModel) data_table.getModel();
                    model.removeRow(data_table.getSelectedRow());
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "You have to select one record first");
                }
            }
        });

        clear_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                ((DefaultTableModel)data_table.getModel()).setRowCount(0);
            }
        });

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                main_frame.setVisible(true);
            }
        });

        frame.addPropertyChangeListener("enabled", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                System.out.println("State changed for " + evt.getPropertyName() + " to " + evt.getNewValue());
            }
        });


        back_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                main_frame.setVisible(true);
            }
        });

    }

    private <T extends LibraryEntity> T getById(int id){
        if(frameType == 0){
            AuthorDataAccess obj = new AuthorDataAccess();
            return (T) obj.getById(id);
        }
        else if(frameType == 1){
            PublisherDataAccess obj = new PublisherDataAccess();
            return (T) obj.getById(id);
        }
        else if(frameType == 2){
            BookDataAccess obj = new BookDataAccess();
            return (T) obj.getById(id);
        }
        return null;
    }

    private <T extends LibraryEntity> ArrayList<T> getAll(){
        if(frameType == 0) {
            AuthorDataAccess obj = new AuthorDataAccess();
            return (ArrayList<T>) obj.getAll();
        }
        else if(frameType == 1){
            PublisherDataAccess obj = new PublisherDataAccess();
            return (ArrayList<T>) obj.getAll();
        }
        else if(frameType == 2){
            BookDataAccess obj = new BookDataAccess();
            return (ArrayList<T>) obj.getAll();
        }
        return null;
    }


    private Object[] entityToArray(LibraryEntity entity) {
        Object data[] = new Object[columns[frameType].length];

        if (frameType == 0) {
            data[0] = ((Author) entity).getAuthor_id();
            data[1] = ((Author) entity).getAuthor_full_name();
            data[2] = ((Author) entity).getAuthor_biography();
        } else if (frameType == 1) {
            data[0] = ((Publisher) entity).getPublisher_id();
            data[1] = ((Publisher) entity).getPublisher_name();
        } else if (frameType == 2) {
            data[0] = ((Book) entity).getBook_id();
            data[1] = ((Book) entity).getBook_isbn();
            data[2] = ((Book) entity).getBook_title();
            data[3] = ((Book) entity).getBook_number_of_pages();
            data[4] = ((Book) entity).getBook_category();
            data[5] = ((Book) entity).getBook_language();
            data[6] = ((Book) entity).getPublication_year();
            data[7] = ((Book) entity).getAuthor_id();
            data[8] = ((Book) entity).getPublisher_id();
        }
        return data;
    }

    private void delete(int id) {
        if (frameType == 0) {
            new AuthorDataAccess().delete(id);
        } else if (frameType == 1) {
            new PublisherDataAccess().delete(id);
        } else if (frameType == 2) {
            new BookDataAccess().delete(id);
        }
    }

    private void createAddDialog(){

        new AddEditDialog(frame, 0, frameType, null, this);
        frame.setEnabled(false);
    }

    private void createEditDialog(Object rowData[]){
        new AddEditDialog(frame,1, frameType, rowData, this);
        frame.setEnabled(false);
    }

    private int getIDInSelectedRow(){
        int row = data_table.getSelectedRow();
        int col = 0;
        int id = Integer.parseInt(data_table.getModel().getValueAt(row, col).toString());
        return id;
    }

    public void updateTable() {
        try {
            ArrayList<LibraryEntity> entities = getAll();
            data_table.setModel(new DefaultTableModel());
            model = (DefaultTableModel) data_table.getModel();

            for (Object val : columns[frameType]) {
                model.addColumn(val);
            }

            Object rowData[];
            for (int i = 0; i < entities.size(); i++) {
                rowData = entityToArray(entities.get(i));
                model.addRow(rowData);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Id doesn't exist");
        }
    }
}
