package sow.exp;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;

public class ExpensesAnalyzerUI extends JFrame {

	private JPanel contentPane;
	private JLabel lblStatus;
	private JLabel lblSourceFile;
	private JLabel lblDesinationFile;
	private JButton btnOrganize;
	private boolean path1, path2;
	private final JButton btnSoChooser = new JButton("");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExpensesAnalyzerUI frame = new ExpensesAnalyzerUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ExpensesAnalyzerUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 922, 311);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblStatus = new JLabel("Status:");
		lblStatus.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 30));
		lblStatus.setBounds(158, 175, 206, 33);
		contentPane.add(lblStatus);

		lblSourceFile = new JLabel("Source File Path :");
		lblSourceFile.setFont(new Font("Calibri", Font.BOLD, 30));
		lblSourceFile.setBounds(14, 28, 216, 49);
		contentPane.add(lblSourceFile);

		lblDesinationFile = new JLabel("Dest File Path :");
		lblDesinationFile.setFont(new Font("Calibri", Font.BOLD, 30));
		lblDesinationFile.setHorizontalAlignment(SwingConstants.CENTER);
		lblDesinationFile.setBounds(37, 105, 193, 46);
		contentPane.add(lblDesinationFile);

		JLabel lblSelectThePath = new JLabel(
				"Select the path of the source file");
		lblSelectThePath.setFont(new Font("Calibri", Font.PLAIN, 35));
		lblSelectThePath.setBackground(Color.LIGHT_GRAY);
		lblSelectThePath.setBounds(237, 28, 526, 49);
		contentPane.add(lblSelectThePath);

		JLabel lblDefaultPath = new JLabel("C:\\Users\\"
				+ System.getProperty("user.name") + "\\Desktop");
		lblDefaultPath.setFont(new Font("Calibri", Font.PLAIN, 35));
		lblDefaultPath.setBounds(237, 105, 526, 46);
		contentPane.add(lblDefaultPath);

		JButton btnSettings = new JButton("");
		btnSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSettings.setIcon(new ImageIcon("C:\\My Work\\java32\\workspace\\ExpensesAnalyzer\\lib\\settings.jpg"));
		btnSettings.setBounds(47, 163, 70, 51);
		contentPane.add(btnSettings);

		btnOrganize = new JButton("");
		btnOrganize.setIcon(new ImageIcon("C:\\My Work\\java32\\workspace\\ExpensesAnalyzer\\lib\\organize.jpg"));
		btnOrganize.setBounds(563, 163, 200, 50);
		btnOrganize.setEnabled(true);
		btnOrganize.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				File f1 = new File(lblSelectThePath.getText());
				File f2 = new File(lblDefaultPath.getText());
				if (f1.getParentFile().exists() && f2.exists()) {
					lblStatus.setText("Status:Reading");
					Readexpenses r = new Readexpenses();

					r.analizeFile(f1);
					lblStatus.setText("Status:Writing");
					boolean status = r.writeFile(lblDefaultPath.getText());
					if (status)
						lblStatus.setText("Status:Done");
					else
						lblStatus.setText("Status: Some Error Occured");

				} else {
					JOptionPane.showMessageDialog(null, "alert", "alert",
							JOptionPane.ERROR_MESSAGE);
				}
				/*
				 * lblStatus.setText("Status:Reading");
				 * r.analizeFile(fcs.getSelectedFile());
				 * lblStatus.setText("Status:Writing");
				 * r.writeFile(fcd.getSelectedFile().getAbsolutePath());
				 * lblStatus.setText("Status:Done");
				 */
			}
		});
		contentPane.add(btnOrganize);
		btnSoChooser.setFont(new Font("Calibri", Font.PLAIN, 28));
		btnSoChooser.setVerticalAlignment(SwingConstants.TOP);
		btnSoChooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File("C:\\Users\\"+ System.getProperty("user.name") + "\\Desktop"));
				int returnVal = chooser.showOpenDialog(contentPane);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					lblSelectThePath.setText(chooser.getSelectedFile()
							.getAbsolutePath());
				}
			}
		});
		btnSoChooser.setBounds(770, 28, 60, 50);
		btnSoChooser.setIcon(new ImageIcon("C:\\My Work\\java32\\workspace\\ExpensesAnalyzer\\lib\\filechooser.jpg"));
		contentPane.add(btnSoChooser);
		BufferedImage img=null;
		JButton btnDesChooser = new JButton("");
		btnDesChooser.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File(lblDefaultPath.getText()));
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = chooser.showOpenDialog(contentPane);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					lblDefaultPath.setText(chooser.getSelectedFile()
							.getAbsolutePath());
				}
			}

		});
		btnDesChooser.setBounds(770, 105, 60, 50);
		btnDesChooser.setHorizontalAlignment(SwingConstants.CENTER);
		btnDesChooser.setIcon(new ImageIcon("C:\\My Work\\java32\\workspace\\ExpensesAnalyzer\\lib\\filechooser.jpg"));
		contentPane.add(btnDesChooser);

	}
}

class Readexpenses {
	String places[] = { "BLEKING", "GALGAMARKEN", "Frukthuset", "CITY GROSS",
			"WILLYS L", "WILLYS K", "MCDONALD S", "RED LIGHT", "ATM" };
	String line, lis[] = new String[places.length], others;
	boolean flag;
	Float total[] = new Float[places.length], t;

	void analizeFile(File f) {
		System.out.println("in reading");
		for (int k = 0; k < places.length; k++)
			total[k] = 0f;
		try {
			int i = 0;
			BufferedReader br = new BufferedReader(new FileReader(f));
			while ((line = br.readLine()) != null) {
				flag = true;
				for (i = 0; i < places.length && flag; i++) {
					if (line.contains(places[i])) {
						flag = false;
						lis[i] = lis[i] + "\n" + line;
						String[] values = line.split("\t");
						total[i] = total[i]
								+ Float.parseFloat(values[4].replaceAll("\"|,",
										""));
					}

				}
			}

		} catch (Exception e) {
		}

	}

	boolean writeFile(String path) {
		System.out.println("in writing");
		int i = 0;
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(path
					+ "\\expenses.csv"));
			while (i < lis.length) {
				if (lis[i] != null) {
					lis[i] = lis[i].replaceAll("\t", ",");
					bw.write(places[i] + " : \n " + lis[i] + "\n total :"
							+ total[i] + "\n");
				}
				i++;
			}
			bw.close();

		} catch (IOException e) {
			return false;// TODO Auto-generated catch block
		}
		return true;

	}
}