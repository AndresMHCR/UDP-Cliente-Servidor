package ec.edu.epn.redes.mesaje.udp;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import javax.swing.JOptionPane;

public class ClientUPD {
	private static int SERVER_PORT =9091;
	

	public static void main(String[] args) throws IOException {
		
		String serverAddress= JOptionPane.showInputDialog("Enter IP address of a machine"+"running the service on port "
								+SERVER_PORT+":");
		
		//send packet request
		DatagramSocket clientSocket =new DatagramSocket();
		byte bufferSend[]= serverAddress.getBytes();
		DatagramPacket sendPacket = new DatagramPacket(bufferSend, bufferSend.length, InetAddress.getByName(serverAddress), SERVER_PORT);
		clientSocket.send(sendPacket);
		
		//Receive packet
		byte bufferReceive[]= new byte[128];
		DatagramPacket receivePacket = new DatagramPacket(bufferReceive, bufferReceive.length);
		clientSocket.receive(receivePacket);
		
		//transforms byte to String
		InputStream myImputStream = new ByteArrayInputStream(receivePacket.getData());
		BufferedReader input= new BufferedReader(new InputStreamReader(myImputStream));
		String answer = input.readLine();
		
		//desplega mensaje
		JOptionPane.showMessageDialog(null, answer);
		clientSocket.close();
		System.exit(0);
		
		
	}

}
