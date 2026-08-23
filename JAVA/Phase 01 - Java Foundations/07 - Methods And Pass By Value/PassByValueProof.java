public class PassByValueProof {

    static void modifyPrimitive(int val) {
        val = 9999;
    }

    static void mutateObjectContent(int[] arr) {
        arr[0] = 8888; // modifies shared heap object
    }

    static void reassignObjectReference(int[] arr) {
        arr = new int[]{1111, 2222}; // modifies only the local parameter copy
    }

    public static void main(String[] args) {
        // 1. Primitive proof
        int x = 10;
        modifyPrimitive(x);
        System.out.println("x after modifyPrimitive: " + x); // 10

        // 2. Object content mutation proof
        int[] myArr = {1, 2, 3};
        mutateObjectContent(myArr);
        System.out.println("myArr[0] after mutateObjectContent: " + myArr[0]); // 8888

        // 3. Object reassignment proof
        reassignObjectReference(myArr);
        System.out.println("myArr[0] after reassignObjectReference: " + myArr[0]); // 8888 (unchanged)
    }
}
