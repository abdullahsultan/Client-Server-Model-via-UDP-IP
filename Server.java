package com.lab_3;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class Server {
	public static void main(String[] args) {
		int d = 0;
		try {

			byte[] receiveData = new byte[1024];
			byte[] sendData = new byte[1024];
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			DatagramSocket serverSocket = new DatagramSocket(9876);
			System.out.println("Listening for clients");
			serverSocket.receive(receivePacket);
			//String sentence = new String(receivePacket.getData());
			//System.out.println("RECEIVED: " + sentence);
			
			//if(sentence.contains("Can I send a  request"))
				//System.out.println("Correct message");
			
			Request r=(Request) Util.convertFromBytes(receivePacket.getData());
			
			if (r.operand == '+')
			{
				 d = r.op1 + r.op2;
			}
			else if (r.operand == '-')
			{
				 d = r.op1 - r.op2;
				
			}
			else if (r.operand == '/')
			{
				d = r.op1 / r.op2;				
			}
			else if (r.operand == '*')
			{
				d = r.op1 * r.op2;
				
			}
			
			
			Responce responce = new Responce(1,d);
			
			sendData = Util.convertToBytes(responce);
			
			InetAddress IPAddress = receivePacket.getAddress();
			int port = receivePacket.getPort();
			//String capitalizedSentence = sentence.toUpperCase();
			//sendData = capitalizedSentence.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
			serverSocket.send(sendPacket);
			serverSocket.close();
		} catch (IOException e) {
// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}