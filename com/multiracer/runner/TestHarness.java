package com.multiracer.runner;

import com.multiracer.lanes.Lanes4;

public class TestHarness {

	/**
	 * @param args
	 */
	public static void main(String[] args) {


		for(int[][] heat : Lanes4.lane24) {
			for(int[] lanes : heat) {
				for(int idx : lanes) {
					System.out.print(String.format("%4d", idx));
				}				
				log("");
			}
		}

	}

	private static void log(String msg) {
		System.out.println(msg);
	}
}
