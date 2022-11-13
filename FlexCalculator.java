import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.text.DecimalFormat;
import java.awt.event.*;

class Flex extends JFrame{
    
    //panel for user input and calculate button
    private JPanel dateJPanel;
    
    //labels for start date, end date, and money
    private JLabel startJLabel;
    private JLabel endJLabel;
    private JLabel moneyJLabel;
    
    //calculate button
    private JButton calculateJButton;
    
    //text area where budget will be displayed
    private JTextArea calculateJTextArea;
    
    //text field for user money
    private JTextField moneyJTextField;
    
    //dropdown for months
    private JComboBox startJComboBox;
    private JComboBox endJComboBox;
    
    //dropdown for days
    private JComboBox startDayJComboBox;
    private JComboBox endDayJComboBox;

    private final String[] MONTHS = {"Jan","Feb", "Mar", "April", "May", "June", 
                                "July", "Aug", "Sep", "Oct", "Nov", "Dec"};
    
    private final String[] DAYS = {"1","2","3","4","5","6","7","8","9","10",
                            "11","12","13","14","15","16","17",
                            "18","19","20","21","22","23","24",
                            "25","26","27","28","29","30","31"};
    
    public Flex(){
        createUserInterface();
    }
    
    private void createUserInterface(){

        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        
        //user input panel
        dateJPanel = new JPanel();
        dateJPanel.setLayout(null);
        dateJPanel.setBorder(new TitledBorder("Flex Calculator Input"));
        dateJPanel.setBounds(20,6,460,200);
        contentPane.add(dateJPanel);
        
        //labels
        startJLabel = new JLabel();
        startJLabel.setBounds(20,50,80,21);
        startJLabel.setText("Start Date:");
        dateJPanel.add(startJLabel);
        
        endJLabel = new JLabel();
        endJLabel.setBounds(20,120,80,21);
        endJLabel.setText("End Date:");
        dateJPanel.add(endJLabel);
        
        moneyJLabel = new JLabel();
        moneyJLabel.setBounds(300,50,80,21);
        moneyJLabel.setText("Money:");
        dateJPanel.add(moneyJLabel);
        
        //money text field
        moneyJTextField = new JTextField();
        moneyJTextField.setBounds(350,50,80,21);
        moneyJTextField.setHorizontalAlignment(JTextField.LEFT);
        dateJPanel.add(moneyJTextField);
        
        //button
        calculateJButton = new JButton();
        calculateJButton.setBounds(300,100,125,50);
        calculateJButton.setText("calculate");
        dateJPanel.add(calculateJButton);
        
        calculateJButton.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent event){
                        calculateJButtonPerformed(event);
                    }
            }
        );
        
        //results text area
        calculateJTextArea = new JTextArea();
        calculateJTextArea.setBounds(150,210,200,50);
        calculateJTextArea.setEditable(false);
        contentPane.add(calculateJTextArea);
        
        //dropdown menus
        startJComboBox = new JComboBox<String>(MONTHS);
        startJComboBox.setBounds(100,50,80,21);
        dateJPanel.add(startJComboBox);
        
        startDayJComboBox = new JComboBox<String>(DAYS);
        startDayJComboBox.setBounds(200,50,80,21);
        dateJPanel.add(startDayJComboBox);

        endJComboBox = new JComboBox<String>(MONTHS);
        endJComboBox.setBounds(100,120,80,21);
        dateJPanel.add(endJComboBox);
        
        endDayJComboBox = new JComboBox<String>(DAYS);
        endDayJComboBox.setBounds(200,120,80,21);
        dateJPanel.add(endDayJComboBox);
        
        //window prefrences
        setTitle("Flex Calculator");
        setSize(500,300);
        setVisible(true);
    }
    
    private void calculateJButtonPerformed(ActionEvent event){
        calculateJTextArea.setText("");
        
        double week;
        double spendWeek;
        double spendDay;
        
        //getting info from user
        Double money = Double.parseDouble(moneyJTextField.getText());

        //index of selected month
        int startIndex = startJComboBox.getSelectedIndex() + 1;
        int endIndex = endJComboBox.getSelectedIndex() + 1;

        //make thing to store origional index

        //index of selected day
        int startDayIndex = startDayJComboBox.getSelectedIndex() + 1;
        int endDayIndex = endDayJComboBox.getSelectedIndex() + 1;
        
        //output format
        DecimalFormat currency = new DecimalFormat("$0.00");
        
        int count = 0;

        //instead of changing starting index back to 1, extend end index and have it loop back to jan
        if(startIndex > endIndex){
            int extendEnd = (MONTHS.length + 1) - startIndex + endIndex;
            endIndex = startIndex + extendEnd;
        }
        
        //goes from start month until end month
        for (int i = startIndex; i <= endIndex; i++) {
            
            //if i is greater than 12, loop back to 1 and subtract end index to keep the same dist
            if(i > 12){
                endIndex = endIndex - i;
                i = 1;
            }

            //if months are the same, go until end day
            if(i==endIndex){
                for (int j = startDayIndex; j <= endDayIndex; j++) {
                    count++;
                }
                break;
            }
            
            //switch based on month
            switch(i){
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    for (int j = startDayIndex; j <= 31; j++) {
                        count++;
                    }
                    break;
                
                case 4:
                case 6:
                case 9:
                case 11:
                    for (int j = startDayIndex; j <= 30; j++) {
                        count++;
                    }
                    break;
                    
                case 2:
                    for (int j = startDayIndex; j <= 28; j++) {
                        count++;
                    }
                    break;
                
            }//end switch

            //sets day back to 0 when entering new month
            startDayIndex = 1;
            
        }//end for loop

        week = count/7.0;
        
        //weekly budget
        //if days do not add to a week, just divide by 1
        if (week < 1) 
            spendWeek = money/1;
        else
            spendWeek = money/week;
        
        //daily budget
        spendDay = money/count;
        
        //output
        calculateJTextArea.append(" Weekly Budget: "+currency.format(spendWeek)+"\n Daily Budget: "+currency.format(spendDay));
        
        
    }
    
}

public class FlexCalculator {

    public static void main(String[] args){
        Flex fc = new Flex();
        fc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //ImageIcon img = new ImageIcon("/Users/kaivance/Documents/GitHub/flex-calculator-repo/resources/flexunator.png");
        ImageIcon img = new ImageIcon("resources/flexunator.png");
        fc.setIconImage(img.getImage());
    }
    
}