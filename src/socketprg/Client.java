package socketprg;

import java.net.DatagramSocket;
import java.net.SocketException;

import socketprg.view.MainFrame;

public class Client {

	public static void main(String[] args) throws SocketException {
		DatagramSocket dgsock = new DatagramSocket();
		MainFrame menu = new MainFrame(dgsock);
		menu.setVisible(true);

	}

}
