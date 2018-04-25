package experimental;

import java.net.*;
import javax.swing.*;
import java.awt.*;

public class Messager {

	public static int serverPort = 998;
	public static int clientPort = 999;
	public static int buffer_size = 1024;
	public static DatagramSocket ds;
	public static byte buffer[] = new byte[buffer_size];
	static boolean isServer = true;
	
	public static void main(String args[]) throws Exception {
		System.out.print("Messager started");
		if(isServer) {
			System.out.print(" as server");
			ds = new DatagramSocket(serverPort);
			TheServer();
		} else {
			System.out.print(" as client");
			ds = new DatagramSocket(clientPort);
			TheClient();
		}
	}
	
	public static void TheClient() throws Exception {
		
		JFrame frame = new JFrame("Client");
		frame.setSize(300, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(new FlowLayout());
		
		JLabel label = new JLabel("...");
		frame.add(label);
		
		frame.setVisible(true);
		
		while(true) {
			DatagramPacket p = new DatagramPacket(buffer, buffer.length);
			ds.receive(p);
			label.setText(new String(p.getData(), 0, p.getLength()));
			SwingUtilities.updateComponentTreeUI(frame);
		}
	}
	
	public static void TheServer() throws Exception {
		int pos = 0;
		while(true) {
			int c = System.in.read();
			switch(c) {
				case -1:
					System.out.println("Server stop");
					ds.close();
					return;
				case '\r':
					break;
				case '\n':
					ds.send(new DatagramPacket(buffer, pos, InetAddress.getLocalHost(), clientPort));
					pos = 0;
					break;
				default:
					buffer[pos++] = (byte) c;
			}
		}
	}
}
