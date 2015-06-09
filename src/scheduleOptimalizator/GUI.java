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
	private JTextField textFieldminpercentofclasses;
	private JTextField textFieldclassesfilledfactor;
	private JTextField textFieldTarget_Penalty_absend;
	private JTextField textFieldTarget_Penalty_clashes;
	private JTextField textFieldAddBusytime;
	private JTextField textFieldOverloadClass;
	private JTextField textFieldstudentexchangenb;
	private JTextField textFieldinterstudentchange;
	private JTextField textFieldmaxnumofmutations;
	
	GUI(){
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panelParameters = new JPanel();
		getContentPane().add(panelParameters);
		panelParameters.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		Box verticalBoxTargetParamters = Box.createVerticalBox();
		panelParameters.add(verticalBoxTargetParamters);
		
		JLabel labelTargetParameters = new JLabel("Target fun. params");
		verticalBoxTargetParamters.add(labelTargetParameters);
		
		Box horizontalBox_6 = Box.createHorizontalBox();
		verticalBoxTargetParamters.add(horizontalBox_6);
		
		JLabel lblClassOverload = new JLabel("Class Overload");
		horizontalBox_6.add(lblClassOverload);
		
		textFieldOverloadClass = new JTextField();
		
		textFieldOverloadClass.setColumns(2);
		horizontalBox_6.add(textFieldOverloadClass);
		
		Box horizontalBox_3 = Box.createHorizontalBox();
		verticalBoxTargetParamters.add(horizontalBox_3);
		
		JLabel labelTarget_penalty_absent = new JLabel("target penalty absent");
		horizontalBox_3.add(labelTarget_penalty_absent);
		
		textFieldTarget_Penalty_absend = new JTextField();
		
		textFieldTarget_Penalty_absend.setColumns(2);
		horizontalBox_3.add(textFieldTarget_Penalty_absend);
		
		Box horizontalBox_4 = Box.createHorizontalBox();
		verticalBoxTargetParamters.add(horizontalBox_4);
		
		JLabel lblTargetpenaltyclashes = new JLabel("penalty clashes");
		horizontalBox_4.add(lblTargetpenaltyclashes);
		
		textFieldTarget_Penalty_clashes = new JTextField();
		
		textFieldTarget_Penalty_clashes.setColumns(2);
		horizontalBox_4.add(textFieldTarget_Penalty_clashes);
		
		Box horizontalBox_5 = Box.createHorizontalBox();
		verticalBoxTargetParamters.add(horizontalBox_5);
		
		JLabel lblAddBusytime = new JLabel("add busytime");
		horizontalBox_5.add(lblAddBusytime);
		
		textFieldAddBusytime = new JTextField();
		
		textFieldAddBusytime.setColumns(2);
		horizontalBox_5.add(textFieldAddBusytime);
		
		Box verticalBoxGenerateParameters = Box.createVerticalBox();
		panelParameters.add(verticalBoxGenerateParameters);
		
		JLabel generateParamsLabel = new JLabel("Generate Parameters");
		verticalBoxGenerateParameters.add(generateParamsLabel);
		
		Box horizontalBox_1 = Box.createHorizontalBox();
		verticalBoxGenerateParameters.add(horizontalBox_1);
		
		JLabel lblMinpercentofclasselabel = new JLabel("min % of generated classes");
		horizontalBox_1.add(lblMinpercentofclasselabel);
		
		textFieldminpercentofclasses = new JTextField();
		textFieldminpercentofclasses.setColumns(2);
		horizontalBox_1.add(textFieldminpercentofclasses);
		
		Box horizontalBox_2 = Box.createHorizontalBox();
		verticalBoxGenerateParameters.add(horizontalBox_2);
		
		JLabel classesfilledfactorLabel = new JLabel("avg % of generated classes");
		horizontalBox_2.add(classesfilledfactorLabel);
		
		textFieldclassesfilledfactor = new JTextField();
		textFieldclassesfilledfactor.setColumns(2);
		horizontalBox_2.add(textFieldclassesfilledfactor);
		
		Box verticalBoxPopulationParameters = Box.createVerticalBox();
		
		JLabel populationParamsLabel = new JLabel("Population Parameters");
		verticalBoxPopulationParameters.add(populationParamsLabel);
		
		Box horizontalBoxPopulationSize = Box.createHorizontalBox();
		verticalBoxPopulationParameters.add(horizontalBoxPopulationSize);
		
		JLabel lblPopulationSize = new JLabel("Population size");
		horizontalBoxPopulationSize.add(lblPopulationSize);
		
		textFieldPopulationSize = new JTextField();
		horizontalBoxPopulationSize.add(textFieldPopulationSize);
		textFieldPopulationSize.setColumns(2);
		
		Box horizontalBoxPopulationElite = Box.createHorizontalBox();
		verticalBoxPopulationParameters.add(horizontalBoxPopulationElite);
		
		JLabel lblPopulationElite = new JLabel("Population elite");
		horizontalBoxPopulationElite.add(lblPopulationElite);
		
		textFieldPopulationElite = new JTextField();
		textFieldPopulationElite.setColumns(2);
		horizontalBoxPopulationElite.add(textFieldPopulationElite);
		
		Box horizontalBoxMutationLevel = Box.createHorizontalBox();
		verticalBoxPopulationParameters.add(horizontalBoxMutationLevel);
		
		JLabel lblMutationLevel = new JLabel("mutation level");
		horizontalBoxMutationLevel.add(lblMutationLevel);
		
		textFieldMutationLevel = new JTextField();
		textFieldMutationLevel.setColumns(2);
		horizontalBoxMutationLevel.add(textFieldMutationLevel);
		
		Box horizontalBoxCrossLevel = Box.createHorizontalBox();
		verticalBoxPopulationParameters.add(horizontalBoxCrossLevel);
		
		JLabel lblCrossLevel = new JLabel("Cross level");
		horizontalBoxCrossLevel.add(lblCrossLevel);
		
		textFieldCrossLevel = new JTextField();
		textFieldCrossLevel.setColumns(2);
		horizontalBoxCrossLevel.add(textFieldCrossLevel);
		
		Box horizontalBoxMaxIterations = Box.createHorizontalBox();
		verticalBoxPopulationParameters.add(horizontalBoxMaxIterations);
		
		JLabel lblMaxIterations = new JLabel("Max Iterations");
		horizontalBoxMaxIterations.add(lblMaxIterations);
		
		textFieldMaxIterations = new JTextField();
		textFieldMaxIterations.setColumns(2);
		horizontalBoxMaxIterations.add(textFieldMaxIterations);
		
		Box horizontalBox = Box.createHorizontalBox();
		verticalBoxPopulationParameters.add(horizontalBox);
		panelParameters.add(verticalBoxPopulationParameters);
		
		Box verticalBox = Box.createVerticalBox();
		panelParameters.add(verticalBox);
		
		JLabel lblCrossParams = new JLabel("Cross params");
		verticalBox.add(lblCrossParams);
		
		Box horizontalBox_7 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_7);
		
		JLabel lblStudentexchangenb = new JLabel("interstudentchange");
		horizontalBox_7.add(lblStudentexchangenb);
		
		textFieldstudentexchangenb = new JTextField();
		
		textFieldstudentexchangenb.setColumns(2);
		horizontalBox_7.add(textFieldstudentexchangenb);
		
		Box horizontalBox_8 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_8);
		
		JLabel lblInterstudentchange = new JLabel("intrastudentchange");
		horizontalBox_8.add(lblInterstudentchange);
		
		textFieldinterstudentchange = new JTextField();
		
		textFieldinterstudentchange.setColumns(2);
		horizontalBox_8.add(textFieldinterstudentchange);
		
		Box verticalBox_1 = Box.createVerticalBox();
		panelParameters.add(verticalBox_1);
		
		JLabel lblMutationParams = new JLabel("Mutation params");
		verticalBox_1.add(lblMutationParams);
		
		Box horizontalBox_9 = Box.createHorizontalBox();
		verticalBox_1.add(horizontalBox_9);
		
		JLabel lblMaxnumofmutations = new JLabel("maxnumofmutations");
		horizontalBox_9.add(lblMaxnumofmutations);
		
		textFieldmaxnumofmutations = new JTextField();
		
		textFieldmaxnumofmutations.setColumns(2);
		horizontalBox_9.add(textFieldmaxnumofmutations);
		
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
		    	  int minpercentofclasses = Integer.parseInt(textFieldminpercentofclasses.getText());
		    	  int classesfilledfactor = Integer.parseInt(textFieldclassesfilledfactor.getText());
		    	  int target_penalty_absent = Integer.parseInt(textFieldTarget_Penalty_absend.getText());
		    	  int target_penalty_clashes = Integer.parseInt(textFieldTarget_Penalty_clashes.getText());
		    	  int target_add_busytime = Integer.parseInt(textFieldAddBusytime.getText());
		    	  int target_overload_classes = Integer.parseInt(textFieldOverloadClass.getText());
		    	  int studentexchangenb = Integer.parseInt(textFieldstudentexchangenb.getText());
		    	  int interstudentchange = Integer.parseInt(textFieldinterstudentchange.getText());
		    	  int maxnbofmutation = Integer.parseInt(textFieldmaxnumofmutations.getText());
		    	  ProgramStarter programStarter = new ProgramStarter(
														    		 textFieldClassesPath.getText(), 
														    		 textFieldStudentsPath.getText(),
														     		 textFieldOutputPath.getText(), 
														    	     size, 
														    		 elite, 
														    		 mutationLevel, 
														    		 crossLevel,
														    		 maxIterations,
														    		 minpercentofclasses,
														    		 classesfilledfactor,
														    		 target_penalty_absent,
														    		 target_penalty_clashes,
														    		 target_add_busytime,
														    		 target_overload_classes,
														    		 studentexchangenb,
														    		 interstudentchange,
														    		 maxnbofmutation);
		    	  programStarter.run();
		      }
		    });
		
		setDefault();
	}

	private void setDefault(){
		textFieldClassesPath.setText("C:\\Users\\Adam Lach\\Desktop\\ftp\\classes_easy.csv");
		textFieldStudentsPath.setText("C:\\Users\\Adam Lach\\Desktop\\ftp\\students_easy.csv");
		textFieldOutputPath.setText("C:\\Users\\Adam Lach\\Desktop\\ftp");
		textFieldPopulationSize.setText("600");
		textFieldPopulationElite.setText("50");
		textFieldCrossLevel.setText("80");
		textFieldMutationLevel.setText("80");
		textFieldMaxIterations.setText("400");
		textFieldminpercentofclasses.setText("70");
		textFieldclassesfilledfactor.setText("95");
		textFieldmaxnumofmutations.setText("15");
		textFieldOverloadClass.setText("50");
		textFieldTarget_Penalty_absend.setText("5000");
		textFieldTarget_Penalty_clashes.setText("5000");
		textFieldAddBusytime.setText("2");
		textFieldstudentexchangenb.setText("50");
		textFieldinterstudentchange.setText("50");
	}
}
