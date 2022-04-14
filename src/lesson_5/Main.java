package lesson_5;

public class Main {
    
    static final int size = 10000000;
    static final int h = size / 2;

    public static void main(String[] args) {

        float[] arr1 = new float[size];
        float[] arr2 = new float[size];

        method1(arr1);
        method2(arr2);

    }

    private static void method1(float[] arr) {
        for ( int i = 0; i < size; i++){
            arr[i] = 1;
        }

        long a = System.currentTimeMillis();

        for (int i = 0; i < size; i++){
            arr[i] = calculate(arr[i], i);
        }
        System.out.println(System.currentTimeMillis() - a);
    }
    private static void method2(float[] arr) {
        for ( int i = 0; i < size; i++){
            arr[i] = 1;
        }
        long a = System.currentTimeMillis();

        float[] a1 = new float[h];
        float[] a2 = new float[h];

        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < h; i++){
                a1[i] = calculate(a1[i], i);

        }
    });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < h; i++) {
                a2[i] = calculate(a2[i], i);
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);

        System.out.println(System.currentTimeMillis() - a);
    }

    private static float calculate(float v, int i) {
        return (float) (v * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
    }
}


