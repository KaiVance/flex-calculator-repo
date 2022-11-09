import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.JComboBox;
import java.text.DecimalFormat;
import java.awt.event.*;

class Flex extends JFrame{
    
    private JPanel dateJPanel;
    
    private JLabel startJLabel;
    private JLabel endJLabel;
    private JLabel moneyJLabel;
    
    private JButton calculateJButton;
    
    private JTextArea calculateJTextArea;
    
    private JTextField moneyJTextField;
    
    private JComboBox startJComboBox;
    private JComboBox endJComboBox;
    
    private JComboBox startDayJComboBox;
    private JComboBox endDayJComboBox;
    
    private final String[] MONTHS = {"1","2", "3", "4", "5", "6", 
                                "7", "8", "9", "10", "11", "12"};
    
    final String[] DAYS = {"1","2","3","4","5","6","7","8","9","10",
                            "11","12","13","14","15","16","17",
                            "18","19","20","21","22","23","24",
                            "25","26","27","28","29","30","31"};
    
    public Flex(){
        createUserInterface();
    }
    
    private void createUserInterface(){
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        
        dateJPanel = new JPanel();
        dateJPanel.setLayout(null);
        dateJPanel.setBorder(new TitledBorder("Flex Calculator Input"));
        dateJPanel.setBounds(20,6,460,200);
        contentPane.add(dateJPanel);
        
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
        
        moneyJTextField = new JTextField();
        moneyJTextField.setBounds(350,50,80,21);
        moneyJTextField.setHorizontalAlignment(JTextField.LEFT);
        dateJPanel.add(moneyJTextField);
        
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
        
        calculateJTextArea = new JTextArea();
        calculateJTextArea.setBounds(150,210,200,50);
        calculateJTextArea.setEditable(false);
        contentPane.add(calculateJTextArea);
        
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
        
        setTitle("Flex Calculator");
        setSize(500,300);
        setVisible(true);
    }
    
    private void calculateJButtonPerformed(ActionEvent event){
        calculateJTextArea.setText("");
        
        double week;
        double spendWeek;
        double spendDay;
        
        Double money = Double.parseDouble(moneyJTextField.getText());
        
        int startMonth = Integer.parseInt(startJComboBox.getSelectedItem().toString());
        int endMonth = Integer.parseInt(endJComboBox.getSelectedItem().toString());
        
        int startDay = Integer.parseInt(startDayJComboBox.getSelectedItem().toString());
        int endDay = Integer.parseInt(endDayJComboBox.getSelectedItem().toString());
        
        DecimalFormat currency = new DecimalFormat("$0.00");
        
        int count = 0;
        
        for (int i = startMonth; i <= endMonth; i++) {
            
            if(startMonth==endMonth){
                for (int j = startDay; j <= endDay; j++) {
                    count++;
                }
                break;
            }
            
            switch(i){
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    for (int j = startDay; j <= 31; j++) {
                        count++;
                    }
                    break;
                
                case 4:
                case 6:
                case 9:
                case 11:
                    for (int j = startDay; j <= 30; j++) {
                        count++;
                    }
                    break;
                    
                case 2:
                    for (int j = startDay; j <= 28; j++) {
                        count++;
                    }
                    break;
                
            }//end switch
            startDay = 1;
            
        }//end for loop
        
        week = count/7.0;
        
        if (week < 1) 
            spendWeek = money/1;
        else
            spendWeek = money/week;
        
        spendDay = money/count;
        
        calculateJTextArea.append(" Weekly Budget: "+currency.format(spendWeek)+"\n Daily Budget: "+currency.format(spendDay));
        
        
    }
    
}

public class FlexCalculator {

    public static void main(String[] args) {
        Flex fc = new Flex();
        fc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}