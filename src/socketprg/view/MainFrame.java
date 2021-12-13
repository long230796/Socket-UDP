package socketprg.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import socketprg.view.bai1.Bai1;
import socketprg.viewbai2.Bai2;

import java.awt.event.ActionListener;
import java.net.DatagramSocket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public MainFrame(DatagramSocket dgsock) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBaiThucHanh = new JLabel("BAI THUC HANH BUOI 2");
		lblBaiThucHanh.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblBaiThucHanh.setHorizontalAlignment(SwingConstants.CENTER);
		lblBaiThucHanh.setBounds(12, 0, 426, 44);
		contentPane.add(lblBaiThucHanh);
		
		JButton btnBai1 = new JButton("Bai 1");
		btnBai1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Bai1 bai1 = new Bai1(dgsock);
					bai1.setVisible(true);
				} catch (UnknownHostException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnBai1.setBounds(53, 95, 117, 25);
		contentPane.add(btnBai1);
		
		JButton btnBai2 = new JButton("Bai 2");
		btnBai2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Bai2 bai2 = new Bai2(dgsock);
					bai2.setVisible(true);
				} catch (UnknownHostException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnBai2.setBounds(263, 95, 117, 25);
		contentPane.add(btnBai2);
	}

}
