package com.lab_3;

import java.io.Serializable;

public class Responce implements Serializable {
	
	 Responce(int responceCode,int result) {
		 this.responceCode=responceCode;
		 this.result = result;
		
	}

	public int responceCode;
	public int result;
}