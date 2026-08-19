class test {

    public static void main(String[] args) {
//        int a = 1534236469;
//        int aa = Integer.MAX_VALUE;
//        int b = -2147483648;
//        System.out.println(214748364 < 964632435);
//        int x = -3;
//        x *= -1;

//        int c = 'Z';
//        System.out.println(c);

        String s = "void main() {\n  int i,";
        s = s.replaceAll("\\b\\w+\\b", "~");
        System.out.println(s);
    }

}
