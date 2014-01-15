package outils;

import java.io.Serializable;

@SuppressWarnings("serial")
public class HistoryMessage implements Serializable
{
	int indiceDepart;
	String result;
	boolean nextResult;
	
	public HistoryMessage() 
	{
		indiceDepart = 0;
	}
	/**
	 * 
	 * @return indiceDepart
	 */
	public int getIndiceDepart() 
	{
		return indiceDepart;
	}
	/**
	 * 
	 * @param indiceDepart
	 */
	public void setIndiceDepart(int indiceDepart) 
	{
		this.indiceDepart = indiceDepart;
	}
	/**
	 * 
	 * @return result
	 */
	public String getResult() 
	{
		return result;
	}
	/**
	 * 
	 * @param result
	 */
	public void setResult(String result) 
	{
		this.result = result;
	}
	/**
	 * 
	 * @return nextResult
	 */
	public boolean isNextResult() 
	{
		return nextResult;
	}
	/**
	 * 
	 * @param nextResult
	 */
	public void setNextResult(boolean nextResult) 
	{
		this.nextResult = nextResult;
	}
}