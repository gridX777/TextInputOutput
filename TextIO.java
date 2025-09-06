package ac.za.tut;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TextIO extends JFrame implements ActionListener {
    private JTextArea display = new JTextArea();
    private JButton read = new JButton("Read from files"),
            write = new JButton("Write to file");
    private JTextField nameField = new JTextField(20);
    private JLabel prompt = new JLabel("Filename", JLabel.RIGHT);
    private JPanel commands = new JPanel();
    
    public TextIO()
    {
        super("TextIO Demo");
        read.addActionListener(this);
        write.addActionListener(this);
        
        //below we start adding things to the panel
        commands.setLayout(new GridLayout(2,2,1,1));
        commands.add(prompt);
        commands.add(nameField);
        commands.add(read);
        commands.add(write);
        display.setLineWrap(true);
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add("North", commands);
        this.getContentPane().add(new JScrollPane());
        this.getContentPane().add("Center", display);
    }//Text IO
    
    private void writeTextFile(JTextArea display, String fileName)
    {
        try{
            FileWriter outStream = new FileWriter(fileName);
            outStream.write(display.getText());
            outStream.close();
        }catch(IOException e)
        {
            display.setText("Error"+e.getMessage()+"\n");
            e.printStackTrace();
        }
                
    }
    
    private void readTextFile(JTextArea display, String fileName)
    {
        try{
            BufferedReader inStream = new BufferedReader(new FileReader(fileName));
            String line = inStream.readLine();
            
            while(line != null)
            {
                display.append(line +"\n");
                line = inStream.readLine();
            }
            inStream.close();
        }
        catch(FileNotFoundException e)
        {
            display.setText("Error file: "+fileName+"Not found");
            e.printStackTrace();
        }catch(IOException e)
        {
            display.setText("Error"+e.getMessage()+"\n");
            e.printStackTrace();
        }
        
        
    }

    
    public static void main(String[] args) {
       TextIO tio = new TextIO();
       tio.setSize(400,200);
       tio.setVisible(true);
       tio.addWindowListener(new WindowAdapter()
       {
           public void WindowClosing()
           {
               System.exit(0);
           }
       });
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
       String fileName = nameField.getText();
       
       if(evt.getSource() == read)
       {
           display.setText("");
           readTextFile(display, fileName);
       }else writeTextFile(display, fileName);
    }//action perform
    
}
