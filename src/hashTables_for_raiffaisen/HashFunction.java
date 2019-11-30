package hashTables_for_raiffaisen;

import java.util.Arrays;


//Хэш таблица - это одна из структур данных, которая
//дает возможность для быстрого поиска и вставки данных,
//по BigO notation можно сказать, что это самый быстрый способ
//поиска, который осуществляется за 0(1),т.е. практически
//моментально, хороший пример, который можно привести,это
//проверка грамматических ошибок в таких программах как Word,
//там практически моментально подсвечиваются грамматические ошибки
//благодаря хэш таблицам, который там испольлзуются как 
//одна из структур данных.


public class HashFunction {

	String[] theArray;
	int arraySize;
	int itemsInArray = 0;

	public static void main(String[] args) {

		HashFunction theFunc = new HashFunction(30);

		//Здесь содержится 30 предметов, чтобы показать
		//как работает коллизии (collisions)

		String[] elementsToAdd2 = { "100", "510", "170", "214", "268", "398",
				"235", "802", "900", "723", "699", "1", "16", "999", "890",
				"725", "998", "978", "988", "990", "989", "984", "320", "321",
				"400", "415", "450", "50", "660", "624" };

		theFunc.hashFunction2(elementsToAdd2, theFunc.theArray);

		//Помещаем значение 660 в хэш таблицу

		theFunc.findKey("660");

		theFunc.displayTheStack();

	}

	//Простая хеш-функция, которая помещает значения в тот же индекс, 
	//который соответствует их значению

	public void hashFunction1(String[] stringsForArray, String[] theArray) {

		for (int n = 0; n < stringsForArray.length; n++) {

			String newElementVal = stringsForArray[n];

			theArray[Integer.parseInt(newElementVal)] = newElementVal;

		}

	}
	
	// collisions, but not so big that we waste memory
	
	//Теперь предположим, что мы должны хранить значения от 0 до 999
	//Не имеет смысла создавать массив из 1000 элементов
	
	//Один из способов вписать эти числа в массив из 30 элементов 
	//использовать функцию mod. Все, что нужно сделать, это
	//взять модуль значения в зависимости от размера массива
	
	//Цель состоит в том, чтобы сделать массив достаточно 
	//большим, чтобы избежать коллизий (collisions), но не настолько 
	//большим, чтобы тратить память

	public void hashFunction2(String[] stringsForArray, String[] theArray) {

		for (int n = 0; n < stringsForArray.length; n++) {

			String newElementVal = stringsForArray[n];

			//Создаем индекс для хранения значения,
			//взяв модуль

			int arrayIndex = Integer.parseInt(newElementVal) % 29;

			System.out.println("Modulus Index= " + arrayIndex + " for value "
					+ newElementVal);

			//Проходим по массиву, пока не найдем свободное место

			while (theArray[arrayIndex] != "-1") {

				++arrayIndex;

				System.out.println("Collision Try " + arrayIndex + " Instead");

				//Если добираемся до конца массива, то возвращаемся 
				//к 0 индексу

				arrayIndex %= arraySize;

			}

			theArray[arrayIndex] = newElementVal;

		}

	}

	//Возвращаем значения, хранящиеся в хэш таблице

	public String findKey(String key) {

		//Находим ключи
		int arrayIndexHash = Integer.parseInt(key) % 29;

		while (theArray[arrayIndexHash] != "-1") {

			if (theArray[arrayIndexHash] == key) {

				//Нашли ключ и возвращаем его
				System.out.println(key + " was found in index "
						+ arrayIndexHash);

				return theArray[arrayIndexHash];

			}

			//Переходим в следующий индекс

			++arrayIndexHash;

			//Если доходим до конца массива, то возвращаемся 
			//к 0 индексу

			arrayIndexHash %= arraySize;

		}

		//Если не удалось найти ключ

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
