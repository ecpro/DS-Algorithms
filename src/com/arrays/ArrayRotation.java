package com.arrays;

public class ArrayRotation {

	public void methodA(Object[] input, int shiftVal) {
		System.out.println("methodA\n");
		Object[] aux = new Object[shiftVal];
		shiftVal = shiftVal % input.length;
		for (int i = 0; i < input.length; i++) {
			if (i < shiftVal) {
				aux[i] = input[i];
			} else {
				input[i - shiftVal] = input[i];
			}
		}

		for (int i = input.length - shiftVal, j = 0; i < input.length; i++, j++) {
			input[i] = aux[j];
		}
	}

	public void methodB(Object[] input, int shiftVal) {
		shiftVal = shiftVal % input.length;
		System.out.println("methodB (rotate 1-by-1) \n");
		for (int i = 0; i < shiftVal; i++) {
			rotateByOne(input);
		}
	}

	public void methodC(Object[] input, int shiftVal) {
		System.out.println("methodC (Juggling Algorithm)");
		// TODO: complete this program
	}

	private void rotateByOne(Object[] input) {
		Object aux = input[0];
		for (int i = 1; i < input.length; i++) {
			input[i - 1] = input[i];
		}
		input[input.length - 1] = aux;
	}

	public static void main(String[] args) {
		ArrayRotation ar = new ArrayRotation();
		Integer[] x = { 1, 2, 3, 4, 5, 6 };
		ar.methodB(x, 18);
		for (int a : x) {
			System.out.print(a + " ");
		}
	}
}
