package seven.tools.chance;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Random;
/******************************************************************************
 * File: AliasMethod.java
 * Author: Keith Schwarz (htiek@cs.stanford.edu)
 *
 * An implementation of the alias method implemented using Vose's algorithm.
 * The alias method allows for efficient sampling of random values from a
 * discrete probability distribution (i.e. rolling a loaded die) in O(1) time
 * each after O(n) preprocessing time.
 *
 * For a complete writeup on the alias method, including the intuition and
 * important proofs, please see the article "Darts, Dice, and Coins: Smpling
 * from a Discrete Distribution" at
 *
 *                 http://www.keithschwarz.com/darts-dice-coins/
 */
public class AliasChance {
	private final double[] probability;
	private final int[] alias;
	private final int length;
	private final Random rand;
	
	public AliasChance(List<Double> prob){
		this(prob,new Random());
	}
	
	public AliasChance(List<Double> prob,Random rand){
		/* Begin by doing basic structural checks on the inputs. */
        if (prob == null || rand == null)
            throw new NullPointerException();
        if (prob.size() == 0)
            throw new IllegalArgumentException("Probability vector must be nonempty.");
        
        this.rand = rand;
        this.length = prob.size();
        this.probability = new double[length];
        this.alias = new int[length];       
        
        double[] probtemp = new double[length];
        Deque<Integer> small = new ArrayDeque<Integer>();
        Deque<Integer> large = new ArrayDeque<Integer>();
        
        /* divide elements into 2 groups by probability */
        for(int i=0;i<length;i++){
        	probtemp[i] = prob.get(i)*length;  /* initial probtemp */
        	if(probtemp[i]<1.0)
        		small.add(i);
        	else
        		large.add(i);
        }
        
        while(!small.isEmpty() && !large.isEmpty()){
        	int less = small.pop();
        	int more = large.pop();
        	probability[less] = probtemp[less];
        	alias[less] = more;
        	probtemp[more] = probtemp[more]-(1.0-probability[less]);
        	if(probtemp[more]<1.0)
        		small.add(more);
        	else
        		large.add(more);
        }
        /* At this point, everything is in one list, which means that the
         * remaining probabilities should all be 1/n.  Based on this, set them
         * appropriately.
         */
        while (!small.isEmpty())
            probability[small.pop()] = 1.0;
        while (!large.isEmpty())
            probability[large.pop()] = 1.0;       
	}
	
	/**
     * Samples a value from the underlying distribution.
     *
     */
    public int next() {
        /* Generate a fair die roll to determine which column to inspect. */
        int column = rand.nextInt(length);

        /* Generate a biased coin toss to determine which option to pick. */
        boolean coinToss = rand.nextDouble() < probability[column];

        /* Based on the outcome, return either the column or its alias. */
        return coinToss? column : alias[column];
    }
    
    public static void main(String[] argv){
    	List<Double> prob = new ArrayList<Double>();
    	prob.add(0.1);prob.add(0.2);prob.add(0.3);prob.add(0.01);prob.add(0.39);prob.add(0.00);   	
    	int[] cnt = new int[prob.size()];
    	AliasChance am = new AliasChance(prob);

//    	System.out.println(am.next());//返回的整形 为相关的中奖类型
    	
    	
    	for(int i=0;i<10000;i++){
    		cnt[am.next()]++;
    	}
    	for(int i=0;i<cnt.length;i++)
    	System.out.println("概率类型"+i + " " + cnt[i]);
    }
}