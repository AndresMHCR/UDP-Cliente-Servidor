package ec.edu.epn.redes.mesaje.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ServerUDP {
	private static int PORT=9091;

	public static void main(String[] args) throws IOException {
		
		DatagramSocket serverSocket =new DatagramSocket(PORT);
		System.err.println("Server listening on port "+PORT+" in UDP connection\n");
		
		try{
			while(true){
				//receive packet
				byte bufferReceiver[]=new byte[128];
				DatagramPacket receivePacket =new DatagramPacket(bufferReceiver, bufferReceiver.length);
				serverSocket.receive(receivePacket);
				InetAddress clientAddress =receivePacket.getAddress();
				int clientPort=receivePacket.getPort();
				System.out.println("Client port: "+clientPort+"\n");
				
				//Send packet
				String msg="Mensaje de Andres Huertas";
				byte bufferSend[] = msg.getBytes();
				DatagramPacket sendPacket = new DatagramPacket(bufferSend, bufferSend.length,clientAddress,clientPort);
				serverSocket.send(sendPacket);
				
			}
		}
		finally{
			serverSocket.close();
		}

	}

}
