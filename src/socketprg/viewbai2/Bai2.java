package socketprg.viewbai2;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Bai2 extends JFrame {

	private JPanel contentPane;
	private JTextField hovaten;
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
	 */
	public Bai2(DatagramSocket dgsock) throws UnknownHostException {
		this.dgsock = dgsock;
		this.request = new byte[256];
		this.ip = InetAddress.getByName("localhost");
		
		prepareFrame();
		listener();
		
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
		
		JLabel lblChuanHoaTen = new JLabel("CHUAN HOA TEN THANH EMAIL");
		lblChuanHoaTen.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblChuanHoaTen.setHorizontalAlignment(SwingConstants.CENTER);
		lblChuanHoaTen.setBounds(12, 0, 426, 43);
		contentPane.add(lblChuanHoaTen);
		
		JLabel lblNhapHoVa = new JLabel("Nhap ho va ten");
		lblNhapHoVa.setBounds(23, 77, 130, 43);
		contentPane.add(lblNhapHoVa);
		
		hovaten = new JTextField();
		hovaten.setBounds(155, 83, 251, 31);
		contentPane.add(hovaten);
		hovaten.setColumns(10);
		
		JButton btnNhap = new JButton("Nhap");
		btnNhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = hovaten.getText();
				String chuoi = "2_" + name;
				
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
		btnNhap.setBounds(169, 170, 117, 25);
		contentPane.add(btnNhap);
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
