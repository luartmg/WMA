package DataStructures.grafopack;

import DataStructures.linkedlistpack.List;

public class Vertex <T> implements Serializable
{
	final static String TYPE = "Vertex";
	
	static int _VertexCant;
	
	int _Serial;
	String _ID;
	
	/**
	 * 
	 */
	List< Vertex<T> > _ListaEntradas;
	
	
	/**
	 * 
	 */
	List< Edge<T> > _ListaAristas;
	
	
	/**
	 * 
	 */
	List< Vertex<T> > _ListaSalidas;
	
	@Override
	public String getID()
	{
		return this._ID;
	}

	@Override
	public int getSerial()
	{
		return this._Serial;
	}
	
	@Override
	public String getType()
	{
		return Vertex.TYPE;
	}
	
	void conectInput(Vertex<T> pVertex)
	{
		this._ListaEntradas.add(pVertex);
	}
	
	void conectOutput(Vertex<T> pVertex)
	{
		this._ListaSalidas.add(pVertex);
	}
	
	void disconectInput( Vertex<T> pVertex )
	{
		this._ListaEntradas.remove( pVertex );
	}
	
	void disconectOutput( Vertex<T> pVertex )
	{
		this._ListaSalidas.remove( pVertex );
	}
}