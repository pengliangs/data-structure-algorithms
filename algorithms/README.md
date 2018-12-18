# 大整数相加


由于数据类型 Integer、Long等存储有限当进行大数运算的时候会导致溢出，java提供了BigInteger、BigDecimal操作大数；
自己实现了个简单的大整数相加的方法了，使用string来实现大整数的相加，根据加法的竖列公式进行计算 个位对个位 
十位对十位

如数字：142798532297  和  1427985322

1. 将当前字符串进行反转

2. 从个位依次相加

3. 判断相加结果是否存在进位

4. 保存当前位置相加后的结果

5. 将结果反转过来得到最终的和

```
7 9 2 2 3 5 8 9 7 2 4 1
2 2 3 5 8 9 7 2 4 1 
--------------------------
9 1 6 7 1 5 6 2 2 4 4 1
```

```java
public static String getBigNumberSum(String numA, String numB) {
    if (numA.length() < 1 && numB.length() < 1) {
        throw new NumberFormatException("Zero length BigInteger");
    }
    String numberReg = "[0-9]+";
    if (numA.length() > 0 && !numA.matches(numberReg)) {
        throw new NumberFormatException("For input string: " + "\"" + numA + "\"");
    }
    if (numB.length() > 0 && !numB.matches(numberReg)) {
        throw new NumberFormatException("For input string: " + "\"" + numB + "\"");
    }
    StringBuilder reverseBuilderNumA = new StringBuilder(numA).reverse();
    StringBuilder reverseBuilderNumB = new StringBuilder(numB).reverse();
    final int lenA = reverseBuilderNumA.length();
    final int lenB = reverseBuilderNumB.length();
    final int maxLen = lenA > lenB ? lenA : lenB;
    int carry = 1;
    boolean isCarry = Boolean.FALSE;
    StringBuilder resultBuild = new StringBuilder();
    for (int i = 0; i < maxLen; i++) {
        int valA = i < lenA ? reverseBuilderNumA.charAt(i) - '0' : 0;
        int valB = i < lenB ? reverseBuilderNumB.charAt(i) - '0' : 0;
        int resultVal = valA + valB;
        if (isCarry) {
            resultVal += carry;
        }
        isCarry = resultVal > 9;
        if (i == maxLen - 1 && isCarry) {
            resultBuild.append(resultVal % 10).append(carry);
        } else {
            resultBuild.append(isCarry ? resultVal % 10 : resultVal);
        }
    }
    return resultBuild.reverse().toString();
}
```
