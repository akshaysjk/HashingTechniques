/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author ayush
 */
public class Hashing {

    /**
     * @param args the command line arguments
     */
    static int linearCollisionCount=0;
    static int quadraticCollisionCount=0;
    static int rehashCollisionCount=0;
    public static List <Integer> randomList;
    public static List <Integer> keyList= new ArrayList<Integer>();
    public static int hashtable[];
    public static void main(String[] args) {
        // TODO code application logic here
        
         
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the number of elements");
        int n = s.nextInt();
        List<Integer> temp = new ArrayList<Integer>();
        for(int i=0;i<n*10;i++)
        {
            temp.add(i);
        }
        Collections.shuffle(temp);
        System.out.println("Enter the loadFactor to Calculate Time");
        double lf= s.nextDouble();
        
        
        int keyArray[];
        
        
        hashtable = new int[n];
        keyArray = new int[n];
       
      
        for(int i=0;i<n*10;i++)
        {
            keyList.add(i);
            
        }

          Collections.shuffle(keyList);
        for(int i=0;i<n;i++)
        {
           
            keyArray[i] = keyList.get(i);
        }
       
        System.out.println("Key Array");
        
        for(int i=0;i<n;i++)
        {
            System.out.print(keyArray[i]+" ");
        }
        System.out.println();
        long startTime=0,endTime=0;
        System.out.println("-----------------------------------------------------------------------");
        initializeHashtable(hashtable);
       
        System.out.println("Linear Probing");
            startTime = System.nanoTime();
            modifiedStore(keyArray,hashtable,1);
            endTime = System.nanoTime();
           
            System.out.println("Time taken for Storing Keys in Hash Table with load factor "+lf+" is "+(endTime-startTime));
            
        
        //System.out.println("Time taken for Storing Keys in Hash Table "+(endTime-startTime));
        System.out.println("Linear Collision Count "+linearCollisionCount);
        System.out.println("Hash Table");
        for(int i=0;i<hashtable.length;i++)
        {
            System.out.print(hashtable[i]+" ");
        }
        System.out.println();
        startTime = System.nanoTime();
        hashSearch(15, hashtable);
        endTime = System.nanoTime();
        System.out.println("Time taken for Searching Key in Hash Table "+(endTime-startTime));
        
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("Quadratic Probing");
        initializeHashtable(hashtable);
        
            
            startTime = System.nanoTime();
            store(keyArray,hashtable,2,lf);
            endTime = System.nanoTime();
           
            System.out.println("Time taken for Storing Keys in Hash Table with load factor "+lf+" is "+(endTime-startTime));
            
           
        System.out.println("Quadratic Collision Count "+quadraticCollisionCount);
        System.out.println("Hash Table");
        for(int i=0;i<n;i++)
        {
            System.out.print(hashtable[i]+" ");
        }
        System.out.println();
        startTime = System.nanoTime();
        hashSearch(15, hashtable);
        endTime = System.nanoTime();
        System.out.println("Time taken for Searching Key in Hash Table "+(endTime-startTime));
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("Double Hashing Probing");
        initializeHashtable(hashtable);   
            startTime = System.nanoTime();
            store(keyArray,hashtable,3,lf);
            endTime = System.nanoTime();
           
            System.out.println("Time taken for Storing Keys in Hash Table with load factor "+lf+" is "+(endTime-startTime));
            
        
        System.out.println("Doublehash Collision Count "+rehashCollisionCount);
        System.out.println("Hash Table");
        for(int i=0;i<n;i++)
        {
            System.out.print(hashtable[i]+" ");
        }
        System.out.println();
        startTime = System.nanoTime();
        hashSearch(15, hashtable);
        endTime = System.nanoTime();
        System.out.println("Time taken for Searching Key in Hash Table "+(endTime-startTime));
        
        
        
       
    }
    
    public static void initializeHashtable(int hashtable[])
    {
         for(int i=0;i<hashtable.length;i++)
        {
            hashtable[i] = -1;
        }
    }
    
    public static void store(int keyArray[],int ht[],int choice,double lf)
    {
        int count=0,index=0;
        int temp[]= new int[ht.length];
        double loadFactor = (count*100.0)/(ht.length*100.0);
        for(int i=0;i<keyArray.length;i++)
        {
        while(loadFactor<=lf)
            {   
                
                index = hashFunction(keyArray[i],ht);
                if(ht[index]!=-1)
                    index = collisionProbing(index,ht,choice);
                if(index==-1)
                {
                    System.out.println("HashTable is full...");
                    return;
                }
                ht[index] = keyArray[i];
                count++;
                if(i<keyArray.length)
                {                     
                    i++;
                }
                if(i>=keyArray.length)
                    break;  
            }
            loadFactor = (count*100.0)/(ht.length*100.0);
            if(loadFactor>lf)
            {
                temp = ht;
                ht = Arrays.copyOf(ht,ht.length*2);
                initializeHashtable(ht);
                store(keyArray,ht,choice,lf);
            }
        }
    }   
            
    public static void modifiedStore(int keyArray[],int ht[],int choice)
    {
        int count=0,index=0;
        int temp[]= new int[ht.length];
        int tempKey[] = new int[keyArray.length];
        double loadFactor = (count*100.0)/(ht.length*100.0);
        for(int i=0;i<keyArray.length;i++)
        {
        while(loadFactor<=0.5)
            {   
                
                index = hashFunction(keyArray[i],ht);
                if(ht[index]!=-1)
                    index = collisionProbing(index,ht,choice);
                if(index==-1)
                {
                    System.out.println("HashTable is full...");
                    return;
                }
                ht[index] = keyArray[i];
                count++;
                if(i<keyArray.length)
                {                     
                    i++;
                }
                if(i>=keyArray.length)
                    break;  
                loadFactor = (count*100.0)/(ht.length*100.0);
            }
            
            if(loadFactor>0.5)
            {
                temp = ht;
                hashtable = Arrays.copyOf(hashtable,hashtable.length*3);
                int k=0;
                int temp1 = 0;
                for(int j=i;j<keyArray.length;j++)
                {
                     temp1++;
                }
                tempKey = new int[temp1];
                for(int j=i;j<keyArray.length;j++)
                {
                     tempKey[k++]=keyArray[j];
                }
                initializeHashtable(hashtable);
                modifiedInsertionStore(tempKey,hashtable,choice,temp);
                break;
            }
        }
          
    }
    
    public static void modifiedInsertionStore(int newKey[],int ht[],int choice,int oldKey[])
    {
        int index=0,oldKeyIndex=0,newKeyIndex=0;
         
        new Thread()
          {
              
              public void run()
              {
                  
                   for(int i=0;i<oldKey.length;i++)
                    {
                        if(oldKey[i]!=-1)
                        keyHash(oldKey[i],ht,choice);
                    }
              }
          }.start();
          
          new Thread()
          {
              
              public void run()
              {
                  
                   for(int j=0;j<newKey.length;j++)
                    {
                        if(newKey[j]!=-1)
                        keyHash(newKey[j],ht,choice);
                    }
              }
          }.start();
                
           
        
    }
    
    public static void keyHash(int key,int ht[],int choice)
    {
        int index=0;
        index = hashFunction(key,ht);
                if(ht[index]!=-1)
                    index = collisionProbing(index,ht,choice);
                if(index==-1)
                {
                    System.out.println("HashTable is full...");
                    return;
                }
                ht[index] = key;
        
    }
    
    public static int hashFunction(int key,int ht[])
    {
        int index;
        index = key%ht.length;
        if(index == ht.length)
            return 0;
        else
            return index;
    }
    
    public static int collisionProbing(int index,int ht[],int choice)
    {
        if(choice==1)
        { 
            index = linearProbing(index, ht);
            return index;
        }
        else if(choice==2)
        {
            index = quadraticProbing(index, ht);
            return index;
        }
        else if(choice==3)
        {
            index = rehashing(index, ht);
            return index;
        }
            
       return -1; 
    }
    
    public static int linearProbing(int index,int ht[])
    {
        
        int startIndex = index;
        while(ht[index]!=-1)
        {
            linearCollisionCount++;
            index++;
            if(index==ht.length)
                index=0;
            if(index== startIndex)
                return -1;
        }
        return index;
    }
    
    
    public static int quadraticProbing(int index,int ht[])
    {
        
        int startIndex = index;
        int i=1;
        do
        {
            quadraticCollisionCount++;
            index = (index+i*i)%ht.length;
            i++;
            
            
        }while(index!=startIndex);
        if(index==startIndex)
        {
            return -1;
        }
        return index;
    }
    
    public static int rehashing(int key,int ht[])
    {
        List<Integer> primeNumberList = new ArrayList<>();
        for (int i = 2; i < ht.length; i++) {
            boolean isPrimeNumber = true;

            // check to see if the number is prime
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    isPrimeNumber = false;
                    break; // exit the inner for loop
                }
            }

            // print the number if prime
            if (isPrimeNumber) {
                primeNumberList.add(i);
            }
        }
       
        int index;
        int m=primeNumberList.size()-1;
        do
        {
          index = key%primeNumberList.get(m);
          if(ht[index]!=-1)
          {    
              rehashCollisionCount++;
              m--;
              if(m!=-1)
              index = primeNumberList.get(m)-(key%primeNumberList.get(m));
              if(ht[index]!=-1)
              { 
                  rehashCollisionCount++;
                  m--;
              }
              else   
              return index;
          }
          else
          {
              return index;
          }
            
        }while(m<=-1);
        
        if(m<=-1)
            return -1;
        else
            return index;
        
    }
    
    
    
    public static void hashSearch(int key,int ht[])
    {
        int index;
        index= hashFunction(key, ht);
        int startIndex = index;
        if(ht[index]==-1)
            System.out.println("Key :"+key+" is not present");
        else
        {
        while(ht[index]!=key)
        {
            index++;
            
            if(index==ht.length)
                index=0;
            
            if(index==startIndex)
            {
                System.out.println("Key :"+key+" is not present");
                return;
            }
            
        }
        if(ht[index]==key)
            System.out.println("Key :"+key+" is present at position "+index);
        }
        
            
    }
    
    
}
