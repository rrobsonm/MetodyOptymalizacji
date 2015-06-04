package scheduleOptimalizator;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Box;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class GUI extends JFrame {
	private JTextField textFieldPopulationSize;
	private JTextField textFieldPopulationElite;
	private JTextField textFieldMutationLevel;
	private JTextField textFieldCrossLevel;
	private JTextField textFieldMaxIterations;
	private JTextField textFieldStudentsPath;
	private JTextField textFieldOutputPath;
	private JTextField textFieldClassesPath;
	
	GUI(){
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panelParameters = new JPanel();
		getContentPane().add(panelParameters);
		panelParameters.setLayout(new BoxLayout(panelParameters, BoxLayout.X_AXIS));
		
		Box verticalBoxParameters = Box.createVerticalBox();
		
		Box horizontalBoxPopulationSize = Box.createHorizontalBox();
		verticalBoxParameters.add(horizontalBoxPopulationSize);
		
		JLabel lblPopulationSize = new JLabel("Population size");
		horizontalBoxPopulationSize.add(lblPopulationSize);
		
		textFieldPopulationSize = new JTextField();
		horizontalBoxPopulationSize.add(textFieldPopulationSize);
		textFieldPopulationSize.setColumns(2);
		
		Box horizontalBoxPopulationElite = Box.createHorizontalBox();
		verticalBoxParameters.add(horizontalBoxPopulationElite);
		
		JLabel lblPopulationElite = new JLabel("Population elite");
		horizontalBoxPopulationElite.add(lblPopulationElite);
		
		textFieldPopulationElite = new JTextField();
		textFieldPopulationElite.setColumns(2);
		horizontalBoxPopulationElite.add(textFieldPopulationElite);
		
		Box horizontalBoxMutationLevel = Box.createHorizontalBox();
		verticalBoxParameters.add(horizontalBoxMutationLevel);
		
		JLabel lblMutationLevel = new JLabel("mutation level");
		horizontalBoxMutationLevel.add(lblMutationLevel);
		
		textFieldMutationLevel = new JTextField();
		textFieldMutationLevel.setColumns(2);
		horizontalBoxMutationLevel.add(textFieldMutationLevel);
		
		Box horizontalBoxCrossLevel = Box.createHorizontalBox();
		verticalBoxParameters.add(horizontalBoxCrossLevel);
		
		JLabel lblCrossLevel = new JLabel("Cross level");
		horizontalBoxCrossLevel.add(lblCrossLevel);
		
		textFieldCrossLevel = new JTextField();
		textFieldCrossLevel.setColumns(2);
		horizontalBoxCrossLevel.add(textFieldCrossLevel);
		
		Box horizontalBoxMaxIterations = Box.createHorizontalBox();
		verticalBoxParameters.add(horizontalBoxMaxIterations);
		
		JLabel lblMaxIterations = new JLabel("Max Iterations");
		horizontalBoxMaxIterations.add(lblMaxIterations);
		
		textFieldMaxIterations = new JTextField();
		textFieldMaxIterations.setColumns(2);
		horizontalBoxMaxIterations.add(textFieldMaxIterations);
		
		Box horizontalBox = Box.createHorizontalBox();
		verticalBoxParameters.add(horizontalBox);
		panelParameters.add(verticalBoxParameters);
		
		JPanel panelPaths = new JPanel();
		getContentPane().add(panelPaths);
		panelPaths.setLayout(new CardLayout(0, 0));
		
		Box verticalBoxPaths = Box.createVerticalBox();
		panelPaths.add(verticalBoxPaths, "name_777971694481600");
		
		Box horizontalBoxStudentsPath = Box.createHorizontalBox();
		verticalBoxPaths.add(horizontalBoxStudentsPath);
		
		JLabel lblStudentsPath = new JLabel("Students file path");
		horizontalBoxStudentsPath.add(lblStudentsPath);
		
		textFieldStudentsPath = new JTextField();
		horizontalBoxStudentsPath.add(textFieldStudentsPath);
		textFieldStudentsPath.setColumns(30);
		
		JButton btnBrowseStudentPath = new JButton("browse");
		horizontalBoxStudentsPath.add(btnBrowseStudentPath);
		btnBrowseStudentPath.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent ae) {
		        JFileChooser fileChooser = new JFileChooser();
		        
		        int returnValue = fileChooser.showOpenDialog(null);
		        if (returnValue == JFileChooser.APPROVE_OPTION) {
		          textFieldStudentsPath.setText(fileChooser.getSelectedFile().getAbsolutePath());
		        }
		      }
		    });
		
		Box horizontalBoxOutputPath = Box.createHorizontalBox();
		verticalBoxPaths.add(horizontalBoxOutputPath);
		
		JLabel lblOutputPath = new JLabel("Output file path");
		horizontalBoxOutputPath.add(lblOutputPath);
		
		textFieldOutputPath = new JTextField();
		horizontalBoxOutputPath.add(textFieldOutputPath);
		textFieldOutputPath.setColumns(30);
		
		JButton btnBrowseOutputPath = new JButton("browse");
		horizontalBoxOutputPath.add(btnBrowseOutputPath);
		btnBrowseOutputPath.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent ae) {
		        JFileChooser fileChooser = new JFileChooser();
		        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		        int returnValue = fileChooser.showOpenDialog(null);
		        if (returnValue == JFileChooser.APPROVE_OPTION) {
		        	File file = fileChooser.getSelectedFile();
		            if(file.isDirectory()) {
		                fileChooser.setCurrentDirectory(file);
		                fileChooser.rescanCurrentDirectory();
		            }
		            else {
		                fileChooser.approveSelection();
		            }
		          textFieldOutputPath.setText(fileChooser.getCurrentDirectory().getAbsolutePath());
		        }
		      }
		    });
		
		Box horizontalBoxClassesPath = Box.createHorizontalBox();
		verticalBoxPaths.add(horizontalBoxClassesPath);
		
		JLabel lblClassesPath = new JLabel("Classes file path");
		horizontalBoxClassesPath.add(lblClassesPath);
		
		textFieldClassesPath = new JTextField();
		horizontalBoxClassesPath.add(textFieldClassesPath);
		textFieldClassesPath.setColumns(30);
		
		JButton btnBrowseClassesPath = new JButton("browse");
		horizontalBoxClassesPath.add(btnBrowseClassesPath);	
		btnBrowseClassesPath.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent ae) {
		        JFileChooser fileChooser = new JFileChooser();
		        int returnValue = fileChooser.showOpenDialog(null);
		        if (returnValue == JFileChooser.APPROVE_OPTION) {
		          textFieldClassesPath.setText(fileChooser.getSelectedFile().getAbsolutePath());
		        }
		      }
		    });
		
		JPanel panelStart = new JPanel();
		getContentPane().add(panelStart);
		
		Box horizontalBoxStart = Box.createHorizontalBox();
		panelStart.add(horizontalBoxStart);
		
		JButton btnStart = new JButton("START");
		horizontalBoxStart.add(btnStart);
		btnStart.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent ae) {
		    	  int size = Integer.parseInt(textFieldPopulationSize.getText());
		    	  int elite = Integer.parseInt(textFieldPopulationElite.getText());
		    	  int mutationLevel = Integer.parseInt(textFieldMutationLevel.getText());
		    	  int crossLevel = Integer.parseInt(textFieldCrossLevel.getText());
		    	  int maxIterations = Integer.parseInt(textFieldMaxIterations.getText());
		    	  ProgramStarter programStarter = new ProgramStarter(textFieldClassesPath.getText(), textFieldStudentsPath.getText(),textFieldOutputPath.getText(), size, elite, mutationLevel, crossLevel, maxIterations);
		    	  programStarter.run();
		      }
		    });
		
		textFieldClassesPath.setText("C:\\Users\\Adam Lach\\LunaWorkspace\\scheduleOptimalizator\\res\\classes.csv");
		textFieldStudentsPath.setText("C:\\Users\\Adam Lach\\LunaWorkspace\\scheduleOptimalizator\\res\\students.csv");
		
	}

}
