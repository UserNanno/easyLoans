package model.ordenamiento;

import java.util.ArrayList;

public class Quicksort {

    public void quickSort(ArrayList<Integer> array, int low, int high) {
        if (low < high) {
            int partitionIndex = partition(array, low, high);

            quickSort(array, low, partitionIndex - 1);
            quickSort(array, partitionIndex + 1, high);
        }
    }

    private int partition(ArrayList<Integer> array, int low, int high) {
        int pivot = array.get(high);
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (array.get(j) <= pivot) {
                i++;
                int temp = array.get(i);
                array.set(i, array.get(j));
                array.set(j, temp);
            }
        }

        int temp = array.get(i + 1);
        array.set(i + 1, array.get(high));
        array.set(high, temp);

        return i + 1;
    }

}
