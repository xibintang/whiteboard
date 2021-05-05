package com.example.project.courseItem;

public class TextComparator {
	
	private String target;
	private String source;
	private int targetLength;
	private int sourceLength;
	
	public TextComparator(String s1, String s2)
	{
		target = s1;
		source = s2;
		targetLength = target.length();
		sourceLength = source.length();
	}
	
	private int LevenshteinDistanceCompute(String target, String source)
	{
		int s = source.length(); 
		int t = target.length(); 
		
		if (s == 0) 
		return t;
		else if (t == 0)
		return s;
		
		int p[] = new int[s+1]; 
		int d[] = new int[s+1]; 
		int _d[]; 
		int i; 
		int j; 
		char t_j; 
		int cost;
		
		for (i = 0; i<=s; i++) {
		 p[i] = i;
		}
		for (j = 1; j<=t; j++) {
			t_j = target.charAt(j-1);
			d[0] = j;
			for (i=1; i<=s; i++) {
				cost = source.charAt(i-1)==t_j ? 0 : 1;
				d[i] = Math.min(Math.min(d[i-1]+1, p[i]+1), p[i-1]+cost); 
			}
		_d = p;
		p = d;
		d = _d;
		} 
		return p[s]; 
	}

	public double compareTexts()
	{
		double LD = LevenshteinDistanceCompute(target, source);
		double TotalSimilarity = (1 - (LD / Math.max(targetLength, sourceLength))) * 100;
	
		return TotalSimilarity;

	}
	
}
