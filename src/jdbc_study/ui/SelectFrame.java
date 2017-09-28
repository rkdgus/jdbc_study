package jdbc_study.ui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class SelectFrame extends JFrame {

	private JPanel contentPane;
	
	
	


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectFrame frame = new SelectFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public SelectFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 274, 113);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 1, 0, 0));
		
		JButton btnEmp = new JButton("사원관리");
		btnEmp.setSize(new Dimension(100, 200));
		contentPane.add(btnEmp);
		
		JButton btnDept = new JButton("부서관리");
		contentPane.add(btnDept);
		
		
		btnEmp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				EmployeeJFrame employeeJFrame = new EmployeeJFrame(true);
				employeeJFrame.setVisible(true);
				
			}
		});
		
		btnDept.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				EmployeeJFrame employeeJFrame = new EmployeeJFrame(false);
				employeeJFrame.setVisible(true);
				
			}
		});

	}

}
