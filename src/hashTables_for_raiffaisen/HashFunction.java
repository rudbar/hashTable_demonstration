package hashTables_for_raiffaisen;

import java.util.Arrays;


//��� ������� - ��� ���� �� �������� ������, �������
//���� ����������� ��� �������� ������ � ������� ������,
//�� BigO notation ����� �������, ��� ��� ����� ������� ������
//������, ������� �������������� �� 0(1),�.�. �����������
//�����������, ������� ������, ������� ����� ��������,���
//�������� �������������� ������ � ����� ���������� ��� Word,
//��� ����������� ����������� �������������� �������������� ������
//��������� ��� ��������, ������� ��� ������������� ��� 
//���� �� �������� ������.


public class HashFunction {

	String[] theArray;
	int arraySize;
	int itemsInArray = 0;

	public static void main(String[] args) {

		HashFunction theFunc = new HashFunction(30);

		//����� ���������� 30 ���������, ����� ��������
		//��� �������� �������� (collisions)

		String[] elementsToAdd2 = { "100", "510", "170", "214", "268", "398",
				"235", "802", "900", "723", "699", "1", "16", "999", "890",
				"725", "998", "978", "988", "990", "989", "984", "320", "321",
				"400", "415", "450", "50", "660", "624" };

		theFunc.hashFunction2(elementsToAdd2, theFunc.theArray);

		//�������� �������� 660 � ��� �������

		theFunc.findKey("660");

		theFunc.displayTheStack();

	}

	//������� ���-�������, ������� �������� �������� � ��� �� ������, 
	//������� ������������� �� ��������

	public void hashFunction1(String[] stringsForArray, String[] theArray) {

		for (int n = 0; n < stringsForArray.length; n++) {

			String newElementVal = stringsForArray[n];

			theArray[Integer.parseInt(newElementVal)] = newElementVal;

		}

	}
	
	// collisions, but not so big that we waste memory
	
	//������ �����������, ��� �� ������ ������� �������� �� 0 �� 999
	//�� ����� ������ ��������� ������ �� 1000 ���������
	
	//���� �� �������� ������� ��� ����� � ������ �� 30 ��������� 
	//������������ ������� mod. ���, ��� ����� �������, ���
	//����� ������ �������� � ����������� �� ������� �������
	
	//���� ������� � ���, ����� ������� ������ ���������� 
	//�������, ����� �������� �������� (collisions), �� �� ��������� 
	//�������, ����� ������� ������

	public void hashFunction2(String[] stringsForArray, String[] theArray) {

		for (int n = 0; n < stringsForArray.length; n++) {

			String newElementVal = stringsForArray[n];

			//������� ������ ��� �������� ��������,
			//���� ������

			int arrayIndex = Integer.parseInt(newElementVal) % 29;

			System.out.println("Modulus Index= " + arrayIndex + " for value "
					+ newElementVal);

			//�������� �� �������, ���� �� ������ ��������� �����

			while (theArray[arrayIndex] != "-1") {

				++arrayIndex;

				System.out.println("Collision Try " + arrayIndex + " Instead");

				//���� ���������� �� ����� �������, �� ������������ 
				//� 0 �������

				arrayIndex %= arraySize;

			}

			theArray[arrayIndex] = newElementVal;

		}

	}

	//���������� ��������, ���������� � ��� �������

	public String findKey(String key) {

		//������� �����
		int arrayIndexHash = Integer.parseInt(key) % 29;

		while (theArray[arrayIndexHash] != "-1") {

			if (theArray[arrayIndexHash] == key) {

				//����� ���� � ���������� ���
				System.out.println(key + " was found in index "
						+ arrayIndexHash);

				return theArray[arrayIndexHash];

			}

			//��������� � ��������� ������

			++arrayIndexHash;

			//���� ������� �� ����� �������, �� ������������ 
			//� 0 �������

			arrayIndexHash %= arraySize;

		}

		//���� �� ������� ����� ����

		return null;

	}

	HashFunction(int size) {

		arraySize = size;

		theArray = new String[size];

		Arrays.fill(theArray, "-1");

	}

	public void displayTheStack() {

		int increment = 0;

		for (int m = 0; m < 3; m++) {

			increment += 10;

			for (int n = 0; n < 71; n++)
				System.out.print("-");

			System.out.println();

			for (int n = increment - 10; n < increment; n++) {

				System.out.format("| %3s " + " ", n);

			}

			System.out.println("|");

			for (int n = 0; n < 71; n++)
				System.out.print("-");

			System.out.println();

			for (int n = increment - 10; n < increment; n++) {

				if (theArray[n].equals("-1"))
					System.out.print("|      ");

				else
					System.out
							.print(String.format("| %3s " + " ", theArray[n]));

			}

			System.out.println("|");

			for (int n = 0; n < 71; n++)
				System.out.print("-");

			System.out.println();

		}

	}

}
