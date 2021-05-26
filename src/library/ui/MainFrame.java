package library.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame {


    private JFrame frame;
    private JLabel title;
    private JButton book_Button;
    private JButton author_Button;
    private JButton publisher_Button;
    private JPanel title_panel;
    private JPanel buttons_panel;

    public MainFrame() {
        initialize();
        frame.setVisible(true);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

        frame = new JFrame("Main");
        frame.setBounds(100, 100, 500, 200);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(2, 1));


        title_panel = new JPanel();
        frame.add(title_panel);

        title = new JLabel("Welcome to Our Simple Library");
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 30));
        title_panel.add(title);


        buttons_panel = new JPanel();
        frame.add(buttons_panel);


        author_Button = new JButton("Authors");
        publisher_Button = new JButton("Publishers");
        book_Button = new JButton("Books");

        buttons_panel.add(author_Button);
        buttons_panel.add(publisher_Button);
        buttons_panel.add(book_Button);


        //Buttons Actions
        author_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createDataFrame(0);
            }
        });

        publisher_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createDataFrame(1);
            }
        });

        book_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createDataFrame(2);
            }
        });

    }


    void createDataFrame(int frameType){
        new DataFrame(frame, frameType);
        frame.setVisible(false);
    }
}