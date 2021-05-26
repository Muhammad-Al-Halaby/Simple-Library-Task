package library.ui;

import library.data.AuthorDataAccess;
import library.data.BookDataAccess;
import library.data.PublisherDataAccess;
import library.pojo.Author;
import library.pojo.Book;
import library.pojo.Publisher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AddEditDialog extends JDialog {

    private int operationType; // 0:Add, 1:Update
    private int dialogType; // 0:Author, 1:Publisher, 2:Book
    private Object rowData[];
    private JDialog dialog;
    private JFrame dataFrame;
    private DataFrame dataFrameRef;
    private JButton submit_button;
    private JTextField textFields[];

    private String operation_types[] = {"Add", "Edit"};
    private String dialog_types[] = {"Author", "Publisher", "Book"};

    private String  columns [][] = new String[][]{
            {"Author ID", "Author Name", "Author Biography"},
            {"Publisher ID","Publisher Name"},
            {"Book ID","ISBN","Title","Number of pages","Category","Language","Publication Year","Author ID","Publisher ID"}
    };

    AddEditDialog(JFrame dataFrame, int operationType, int dialogType, Object rowData[], DataFrame dataFrameRef) {
        this.dataFrame = dataFrame;
        this.operationType = operationType;
        this.dialogType = dialogType;
        this.rowData = rowData;
        this.dataFrameRef = dataFrameRef;

        initialize();

        dialog.setVisible(true);
    }

    private void initialize() {
        dialog = new JDialog();
        dialog.setTitle(operation_types[operationType] + " " + dialog_types[dialogType]);
        dialog.setBounds(100, 100, 300, 300);
        dialog.setResizable(false);
        dialog.setLayout(new GridLayout(2, 1));
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);


        int fieldsCnt = columns[dialogType].length;
        JPanel panel = new JPanel(new GridLayout(fieldsCnt, 2));
        textFields = new JTextField[fieldsCnt];

        for(int i = 0;i < fieldsCnt;i++){
            panel.add(new JLabel(columns[dialogType][i]));
            String data = (rowData != null ? rowData[i].toString() : "");
            textFields[i] = new JTextField(data);
            panel.add(textFields[i]);
        }

        //Disable ID TextField at updates
        if(operationType == 1) {
            textFields[0].setEnabled(false);
        }

        submit_button = new JButton(operation_types[operationType]);

        dialog.setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));
        dialog.add(panel);
        dialog.add(submit_button);


        //Actions
        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                dataFrame.setEnabled(true);
            }
        });

        submit_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submit();
                dataFrameRef.updateTable();
                dialog.dispose();
                dataFrame.setEnabled(true);
            }
        });
    }


    void submit(){
        String data[] = getDataFromTextFields();
        if(dialogType == 0) {
            AuthorDataAccess obj = new AuthorDataAccess();
            Author author = new Author(Integer.parseInt(data[0]), data[1], data[2]);
            if(operationType == 0)
                obj.insert(author);
            else
                obj.update(Integer.parseInt(data[0]), author);
        }
        else if(dialogType == 1){
            PublisherDataAccess obj = new PublisherDataAccess();
            Publisher publisher = new Publisher(Integer.parseInt(data[0]), data[1]);
            if(operationType == 0)
                obj.insert(publisher);
            else
                obj.update(Integer.parseInt(data[0]), publisher);
        }
        else if(dialogType == 2){
            BookDataAccess obj = new BookDataAccess();
            Book book = new Book(Integer.parseInt(data[0]), data[1], data[2], Integer.parseInt(data[3]), data[4], data[5], Integer.parseInt(data[6]), Integer.parseInt(data[7]), Integer.parseInt(data[8]));
            if(operationType == 0)
                obj.insert(book);
            else
                obj.update(Integer.parseInt(data[0]), book);
        }
    }


    String[] getDataFromTextFields(){
        String data[] = new String[textFields.length];
        int i = 0;
        for(JTextField textField : textFields){
            data[i] = textField.getText();
            i++;
        }
        return data;
    }
}