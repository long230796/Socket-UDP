package socketprg.view.bai1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import socketprg.view.bai1.Bai1.ReceiverThread;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Bai1 extends JFrame {

	private JPanel contentPane;
	ReceiverThread receiver;
	
	DatagramSocket dgsock;
	DatagramPacket reqPack;
	DatagramPacket resPack;
	InetAddress ip;
	
	byte request[];
	byte response[];
	int port = 8989;
	private JTextField src;
	private JTextField des;
	private JTextField fileName;

	/**
	 * Create the frame.
	 * @throws UnknownHostException 
	 */
	public Bai1(DatagramSocket dgsock) throws UnknownHostException {
		this.dgsock = dgsock;
		this.request = new byte[256];
		this.ip = InetAddress.getByName("localhost");
		
		listener();
		prepareFrame();
		
	}
	
	private void listener() {
		receiver = new ReceiverThread();
		receiver.start();
	}
	
	private void prepareFrame() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBaiThucHanh = new JLabel("TRUYEN FILE FTP SU DUNG UDP");
		lblBaiThucHanh.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblBaiThucHanh.setHorizontalAlignment(SwingConstants.CENTER);
		lblBaiThucHanh.setBounds(12, 0, 426, 44);
		contentPane.add(lblBaiThucHanh);
		
		JButton btnBai = new JButton("Nhap");
		btnBai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String source = src.getText();
				String destination = des.getText();
				String file = fileName.getText();
				String chuoi = "1_" + source + "-" + destination + "-" + file;
				
				request = String.valueOf(chuoi).getBytes();
				reqPack = new DatagramPacket(request, request.length, ip, port);
				try {
					System.out.println("sending...");
					dgsock.send(reqPack);
					request = new byte[256];
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnBai.setBounds(168, 186, 117, 25);
		contentPane.add(btnBai);
		
		JLabel lblNguon = new JLabel("Nguon");
		lblNguon.setBounds(22, 56, 70, 15);
		contentPane.add(lblNguon);
		
		src = new JTextField();
		src.setBounds(110, 54, 273, 25);
		contentPane.add(src);
		src.setColumns(10);
		
		JLabel lblDich = new JLabel("Dich");
		lblDich.setBounds(22, 96, 70, 15);
		contentPane.add(lblDich);
		
		des = new JTextField();
		des.setBounds(110, 91, 273, 25);
		contentPane.add(des);
		des.setColumns(10);
		
		JLabel lblTenFile = new JLabel("Ten file");
		lblTenFile.setBounds(22, 135, 70, 15);
		contentPane.add(lblTenFile);
		
		fileName = new JTextField();
		fileName.setBounds(110, 133, 273, 25);
		contentPane.add(fileName);
		fileName.setColumns(10);
	}
	
	class ReceiverThread extends Thread {
		public void run() {
			while (!interrupted()) {
				try {
					response = new byte[256];
					resPack = new DatagramPacket(response, response.length);
					dgsock.receive(resPack);
					JOptionPane.showMessageDialog(null, new String(resPack.getData(), 0, resPack.getLength()).trim());
				} catch (Exception e) {}
			}
		}
	}
}
