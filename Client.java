package com.lab_3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;


public class Client {
	public static void main(String[] args) {
		try {
			byte[] sendData = new byte[1024];
			byte[] receiveData = new byte[1024];
			DatagramSocket clientSocket = new DatagramSocket();
			InetAddress IPAddress = InetAddress.getByName("localhost");
			//System.out.println("Type Msg: ");
			//BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
			//String sentence = inFromUser.readLine();
			//sendData = sentence.getBytes();
			
			
			Request request = new Request();
			Scanner input = new Scanner(System.in);		
			System.out.println("Enter operand 1 \n");
			request.op1 = input.nextInt();
			System.out.println("Enter operand 2 \n");
			request.op2 = input.nextInt();
			System.out.println("Enter operation \n");
			request.operand = input.next().charAt(0);
			
			
			
			sendData = Util.convertToBytes(request);
			
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
			clientSocket.send(sendPacket);
			
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			clientSocket.receive(receivePacket);
			
			//Getting data from server
			
			Responce res = (Responce) Util.convertFromBytes(receivePacket.getData());
				
			//String modifiedSentence = new String(receivePacket.getData());
			System.out.println("FROM SERVER:" + res.result);
			clientSocket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}