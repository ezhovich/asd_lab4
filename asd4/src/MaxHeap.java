public class MaxHeap {
    private int[] Heap;
    private int size;
    private int maxsize;

    public MaxHeap(int maxsize)
    {
        this.maxsize = maxsize;
        this.size = 0;
        Heap = new int[this.maxsize + 1];
        Heap[0] = Integer.MAX_VALUE;
    }

    // Повертае позицію батька
    private int parent(int pos) { return pos / 2; }

    // Функції для повертання лівого та правого нащадка
    private int leftChild(int pos) { return (2 * pos); }
    private int rightChild(int pos)
    {
        return (2 * pos) + 1;
    }

    // Чи є нода листком
    private boolean isLeaf(int pos)
    {
        if (pos > (size / 2) && pos <= size) {
            return true;
        }
        return false;
    }

    private void swap(int fpos, int spos)
    {
        int tmp;
        tmp = Heap[fpos];
        Heap[fpos] = Heap[spos];
        Heap[spos] = tmp;
    }

    //Функція, що перевіряє валідність купи
    private void maxHeapify(int pos)
    {
        if (isLeaf(pos))
            return;

        if (Heap[pos] < Heap[leftChild(pos)]
                || Heap[pos] < Heap[rightChild(pos)]) {

            if (Heap[leftChild(pos)]
                    > Heap[rightChild(pos)]) {
                swap(pos, leftChild(pos));
                maxHeapify(leftChild(pos));
            }
            else {
                swap(pos, rightChild(pos));
                maxHeapify(rightChild(pos));
            }
        }
    }

    // Вставка елементу
    public void insert(int element)
    {
        Heap[++size] = element;

        int current = size;
        while (Heap[current] > Heap[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    public void print()
    {
        for (int i = 1; i <= size / 2; i++) {
            System.out.print(
                    " PARENT : " + Heap[i]
                            + " LEFT CHILD : " + Heap[2 * i]
                            + " RIGHT CHILD :" + Heap[2 * i + 1]);
            System.out.println();
        }
    }

    // Видалення останнього елементу
    public int extractMax()
    {
        int popped = Heap[1];
        Heap[1] = Heap[size--];
        maxHeapify(1);
        return popped;
    }

    public int getElement(int e){
        for(int i = 0; i < Heap.length; i++){
            if (Heap[i] == e){
                return e;
            }
        }
        return 0;
    }

    public static void main(String[] arg)
    {
        System.out.println("The Max Heap is ");
        MaxHeap maxHeap = new MaxHeap(100000);
        long tStartA = System.nanoTime();
        for(int i = 0; i < 10001; i++){
            maxHeap.insert((int)(Math.random()*100));
        }
        long tEndA = System.nanoTime();
        System.out.println("Insertion time for maxHeap is " + (tEndA-tStartA) + " nanoseconds");

        long tStartB = System.nanoTime();
        System.out.println(maxHeap.getElement(1000000000));
        long tEndB = System.nanoTime();
        System.out.println("Get element time for maxHeap is " + (tEndB-tStartB) + " nanoseconds");

        long tStartC = System.nanoTime();
        System.out.println(maxHeap.extractMax());
        long tEndC = System.nanoTime();
        System.out.println("Delete element time for maxHeap is " + (tEndC-tStartC) + " nanoseconds");

        maxHeap.print();

        maxHeap.extractMax();
    }
}