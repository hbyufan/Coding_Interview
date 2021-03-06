/**
 * 
 * 【剑指Offer】	面试题5 ： 替换空格
 * 【题目描述】	请实现一个函数，把字符串中的每个空格替换成"%20"，
 * 例如“We are happy.”，则输出“We%20are%20happy.”。
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.examples;

public class E05_ReplaceSpaces {

	// 利用Java类库
	public String replaceSpace(StringBuffer str) {
		// 找到空格出现的地方
		while (str.indexOf(" ") != -1) {
			int indexSpace = str.indexOf(" ");
			str.replace(indexSpace, indexSpace + 1, "%20");
		}

		return str.toString();
	}

	/*
	 * 时间复杂度O(n)算法
	 * length为字符数组的总容量
	 * 异常返回-1，否则返回替换后字符数组长度
	 */
	public int replaceSpace(char[] str, int length) {

		// 有效性检查
		if (str == null || length <= 0) {
			return -1;
		}

		// 统计字符数组中的空白字符数
		int originalLength = 0;
		int numberOfBlank = 0;

		for (int i = 0; str[i] != '\0';) {
			originalLength++;
			if (str[i++] == ' ')
				numberOfBlank++;
		}
		// 计算替换空格后的长度
		int newLength = originalLength + 2 * numberOfBlank;
		if (newLength > length)
			return -1;

		int index1st = originalLength;
		int index2nd = newLength;

		// 替换空格
		while (index1st >= 0 && index2nd > index1st) {
			if (str[index1st] == ' ') {
				str[index2nd--] = '0';
				str[index2nd--] = '2';
				str[index2nd--] = '%';
			} else {
				str[index2nd--] = str[index1st];
			}
			index1st--;
		}
		return newLength;
	}

	// ====================测试代码====================
	private void test(String testName, String str, String expect) {
		System.out.println(testName);
		char[] chars = toChars(str);
		int ret = replaceSpace(chars, 100);
		if (ret >= 0) {
			System.out.printf("替换空格：Result:%s \t Expect:%s\n\n", new String(chars).substring(0, ret),
					expect);
		} else
			System.out.printf("替换空格：Result:%s\t Expect:%s\n\n", null, null);
	}

	private char[] toChars(String str) {

		if (str != null) {
			char[] result = new char[100];
			int i = 0;
			for (char c : str.toCharArray()) {
				result[i++] = c;
			}
			return result;
		} else
			return null;

	}

	public static void main(String[] args) {

		E05_ReplaceSpaces exam = new E05_ReplaceSpaces();

		// 空格在句子中间
		exam.test("=====Test1=====", "hello world", "hello%20world");

		// 空格在句子开头
		exam.test("=====Test2=====", " helloworld", "%20helloworld");

		// 空格在句子末尾
		exam.test("=====Test3=====", "helloworld ", "helloworld%20");

		// 连续有两个空格
		exam.test("=====Test4=====", "hello  world", "hello%20%20world");

		// 传入null
		exam.test("=====Test5=====", null, null);

		// 传入内容为空的字符串
		exam.test("=====Test6=====", "", "");

		// 传入内容为一个空格的字符串
		exam.test("=====Test7=====", " ", "%20");

		// 传入的字符串没有空格
		exam.test("=====Test8=====", "helloworld", "helloworld");

		// 传入的字符串全是空格
		exam.test("=====Test9=====", "   ", "%20%20%20");
	}

}
