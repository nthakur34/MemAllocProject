package manage;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * @author navthakur
 *
 * @param <T>
 */
public class QuickSort<T extends Comparable<? super T>> {

    /**
     * Arraylist holding free blocks.
     */
    private ArrayList<T> freeList = new ArrayList<T>();
    
    /**
     * Constructor to set up arraylist of blocks.
     * @param blockList List to sort and defrag.
     * @param comparator for starting address of Memblocks
     */
    public QuickSort(ArrayList<T> blockList, Comparator<? super T> comparator) {
        
        if (blockList == null || blockList.size() < 1) {
            return;
        }
        this.freeList = blockList;
        this.quickSort(0, blockList.size() - 1, comparator);
    }
    
    /**
     * quick sorts all values.
     * @param lowerIndex lower index to compare to pivot
     * @param higherIndex higher index to compare to pivot
     * @param comparator for starting address of Memblocks
     */
    private void quickSort(int lowerIndex, int higherIndex, Comparator<?
            super T> comparator) {
        int i = lowerIndex;
        int j = higherIndex;
        T pivot = this.freeList.get(lowerIndex + (higherIndex 
                - lowerIndex) / 2);
        
        while (i <= j) {
            while (comparator.compare(this.freeList.get(i), pivot) < 0) {
               // System.out.println("stuckOrNO?");
                i++;
            }
            while (comparator.compare(this.freeList.get(j), pivot) > 0) {
                j--;
            }
            if (i <= j) {
                T temp = this.freeList.get(i);
                this.freeList.set(i, this.freeList.get(j));
                this.freeList.set(j, temp);
                i++;
                j--;
            }
        }
        
        if (lowerIndex < j) {
            this.quickSort(lowerIndex, j, comparator);
        }
        if (i < higherIndex) {
            this.quickSort(i, higherIndex, comparator);
        }   
    }
    
    /**
     * @return  the arraylist
     */
    public ArrayList<T> getList() {
        return this.freeList;   
    }
}
