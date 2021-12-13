package socketprg;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import socketprg.viewbai2.Bai2;

class ReceiverThread extends Thread {
	private DatagramSocket dgsock;
	private byte data[];
	private byte result[];
	DatagramPacket req;
	DatagramPacket res;
	
	public ReceiverThread(DatagramSocket dgsock) {
		this.dgsock = dgsock;
		this.result = new byte[256];
	}
	
	private void sender(String data) {
		try {
			result = String.valueOf(data).getBytes();
			res = new DatagramPacket(result, result.length, req.getAddress(), req.getPort());
			dgsock.send(res);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	private void handler(DatagramPacket req) {
		String data = new String(req.getData());
		String[] baitap = data.split("_");
		
		if (baitap[0].contains("1")) {
			String[] temp = baitap[1].split("-");
			HandlerBai1 bai1 = new HandlerBai1(temp[0], temp[1], temp[2]);
			if (bai1.checkFolderExist()) {
				if (bai1.checkFileExist()) {
					String messageCopy = bai1.copyFile();
					sender(messageCopy);
				} else {
					sender("File khong ton tai");
				}
			} else {
				sender("Folder khong ton tai");
			}
			
			
		} else {
			String name = baitap[1].trim();
			String emailList = HandlerBai2.chuanhoa(name);
			
			sender(emailList);
		}
	}
	
	public void run() {
		while(! interrupted()) {
			try {
				data = new byte[256];
				req = new DatagramPacket(data, data.length);
				dgsock.receive(req);
				handler(req);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}

public class Server {

	public static void main(String[] args) throws IOException {
		DatagramSocket dgSock = new DatagramSocket(8989);
		
		ReceiverThread receiver = new ReceiverThread(dgSock);
		receiver.start();

	}

}