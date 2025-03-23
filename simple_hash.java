package Nonlinear;

// 简单哈希函数
public class simple_hash <E> {
    // 加法哈希函数
    public int addHash(E key) {
        long hash = 0;
        final int MODULUS = 1000000007;
        // 循环迭代字符串的每个字符，并将加法结果与MODULUS取模
        for (int i = 0; i < key.toString().length(); i++) {
            // 将当前字符与哈希值进行加法运算
            hash = (hash + key.toString().charAt(i)) % MODULUS;
        }
        return (int) hash;
    }

    // 乘法哈希函数
    public int mulHash(E key) {
        long hash = 0;
        final int MODULUS = 1000000007;
        // 循环迭代字符串的每个字符，并将乘法结果与MODULUS取模
        for (int i = 0; i < key.toString().length(); i++) {
            // 将当前字符与哈希值进行乘法运算
            hash = (hash * 31 + key.toString().charAt(i)) % MODULUS;
        }
        return (int) hash;
    }

    // 异或哈希函数
    public int xorHash(E key) {
        long hash = 0;
        final int MODULUS = 1000000007;
        // 循环迭代字符串的每个字符
        for (int i = 0; i < key.toString().length(); i++) {
            // 将当前字符与哈希值进行异或运算
            hash ^= key.toString().charAt(i);
        }
        // 返回值对MODULUS按位与运算
        return (int) hash & MODULUS;
    }

    // 旋转哈希函数
    public int rotHash(E key) {
        long hash = 0;
        final int MODULUS = 1000000007;
        // 循环迭代字符串的每个字符
        for (int i = 0; i < key.toString().length(); i++) {
            // 将当前字符与哈希值进行旋转运算
            // 旋转操作通过左移和右移实现
            // 左移四位是将哈希值向左移动四位，相当于将低四位清零
            // 右移28位是将哈希值向右移动28位，相当于将高四位清零
            hash = (hash << 4) ^ (hash >> 28) ^ key.toString().charAt(i) % MODULUS;
        }
        return (int) hash;
    }
    // 选用1000000007作为哈希函数的MODULUS，保证哈希值在0到MODULUS之间
    // 是因为MODULUS是一个大质数，所以哈希值在0到MODULUS之间可以尽可能均匀分布
    // 避免聚集现象
    // 上述哈希函数都比较脆弱，远达不到哈希算法的设计目标，只用于了解，我在实现数据结构时不会使用自己写的哈希函数
}
