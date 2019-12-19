import java.util.*;

public class StraightTest {
public static void main(String args[])
  {
      //build hand array
     int[] hand = {2, 3, 5, 6, 7, 8, 9};
     ArrayList<Integer> referenceList = new ArrayList<>(Arrays.asList(14, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14));
     ArrayList<Integer> handList = new ArrayList<Integer>();
      
    //System.out.println("Size of referenceList is " + referenceList.size());

    int highCard = 0;
    int count = 1;

      
      //sort hand
      int n = hand.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (hand[j] > hand[j+1])
                {
                    // swap temp and hand[i]
                    int temp = hand[j];
                    hand[j] = hand[j+1];
                    hand[j+1] = temp;
                }

      for (int index = 0; index < hand.length; index++)
      {
           handList.add(hand[index]);
      }

      if (!handList.contains(14)) {
                 referenceList.set(0, 0);
                 referenceList.set(13, 0);
      }           


      for (int index = 0; index < referenceList.size(); index++)
      {
            if (!handList.contains(referenceList.get(index)))
                 referenceList.set(index, 0);

      }
       System.out.println("Size of referenceList is " + referenceList.size());
       for (int index = 0; index < referenceList.size(); index++)
       {
        System.out.println("refenceList contains " + referenceList.get(index));
       }

       for (int index = 1; index < handList.size(); index++) {
           if (handList.get(index) == handList.get(index - 1) + 1) {
              highCard = handList.get(index);
              count++;
        }
           else
            count = 1;

       }

       if (count < 5)
       {
        highCard = 0;
       }

       
          System.out.println("High Card is " + highCard);
          System.out.println("Count is " + count);
       
               // return highCard;
       
  }
}
