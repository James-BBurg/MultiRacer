package com.multiracer.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.multiracer.entity.Heat;
import com.multiracer.entity.HeatElement;
import com.multiracer.entity.HeatNode;
import com.multiracer.entity.Race;
import com.multiracer.entity.Racer;

public class RoundRobin {

	int [] working;
	
	public List<Heat> getRoundRobinHeats(Race race) {
		List<Heat> result = new ArrayList<Heat>();
		Random rnd = new Random();		
		rnd.setSeed(System.currentTimeMillis());

		int pivot;
		int n = race.getRacers().size();
		int m = n + (n%2);
		
		// initialize temporary array
		working = new int[m];
		for(int i = 0; i < m; i++)
			working[i] = i;
		
		pivot = rnd.nextInt(m) + 1;			// Select pivot value.
		
		for(int i = 0; i < (n - 1); i++) {
			nextRound(pivot);
			result.add(buildHeat(race));
		}
		return result;
	}

	/**
	 * given a working array, where n/2
	 * @param race
	 * @return
	 */
	private Heat buildHeat(Race race) {
		Heat result = new Heat();
		result.setRaceId(race.getId());
		int len = working.length;
		int max = len / 2;
		int p, s;
		Racer one;
		Racer two;
		HeatNode node;
		HeatElement e1, e2;
		
		for(int i = 0; i < max; i++) {  // RoundRobin has n/2 sub races per heat
			p = i;
			s = p + max;
			one = race.getRacers().get(p);
			two = race.getRacers().get(s);
			
			// build heatNode
			node = new HeatNode(result.getId());
			result.addHeatNode(node);
			
			// build heatElement for each racer
			e1 = new HeatElement(one.getId(), node.getId());
			e1.setLane(1);
			e1.setResult(0);
			e2 = new HeatElement(two.getId(), node.getId());
			e2.setLane(2);
			e2.setResult(0);

			node.addHeatElement(e1);
			node.addHeatElement(e2);
		}
		return result;
	}
	
	private void nextRound(int pivot) {
		int [] round = new int[working.length];
		
		round[pivot] = working[pivot];
		int d = working.length;
		int j;
		for(int i = 0; i < d; i++) {
			if(i != pivot) {
				j = i - 1;
				if(j == pivot)
					j--;
				if(j < 0) 
					j = d - 1;
				round[i] = round[j];
			}
		}
		working = round;
	}
}
