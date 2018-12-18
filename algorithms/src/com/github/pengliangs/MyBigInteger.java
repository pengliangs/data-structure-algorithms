package com.github.pengliangs;

/**
 * 大整数的操作
 *
 * @author pengliang on 2018-12-18 09:07
 */
public class MyBigInteger {


    public static void main(String[] args) {
        System.out.println(getBigNumberSum("98", "980"));
    }

    /**
     * 大整数求和
     *
     * @param numA
     * @param numB
     * @return
     */
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

    private static int[] splitStep(String str, int step) {
        int len = str.length();
        int elemASize = (len + step - 1) / step;
        int[] numbers = new int[elemASize];
        for (int i = 0; i < elemASize; i++) {
            int nextStep = step * (i + 1);
            if (nextStep > len) {
                numbers[i] = Integer.parseInt(str.substring(i * step));
            } else {
                numbers[i] = Integer.parseInt(str.substring(i * step, nextStep));
            }
        }
        return numbers;
    }
}
