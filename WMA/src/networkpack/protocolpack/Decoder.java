package networkpack.protocolpack;

import managerpack.MainManager;

public class Decoder {
	private static Decoder _singletonDecoder = new Decoder();
	
	public Decoder(){}
	
	
	public static void main(String[] args) {
		//_singletonDecoder.Decode("<correspondence%id=lun 11 de nov 12:53%from=192.168.0.2%to=all%type=node%action=create%contains=ardunio@1%details=ID:node@533#domain:192.168.0.2/>");
	}
	public static Decoder getInstance(){
		return _singletonDecoder;
	}

	
	public void Decode(String pMensaje){
		System.out.println("Mensaje a decodificar"+pMensaje);
		String[] msg_recieve=pMensaje.split("%");
		String [] a= msg_verif(msg_recieve);
		if (a!=null){
			if(a[5].equals(Constantes.ACCION_CREAR)){
				System.out.println("noooooooooooooooooooodooooooooo "+a[6].split("#")[0] + " "+ a[6].split("#")[1]);
				MainManager.getInstance().getManager().crearRegionExtrangera(a[6].split("#")[0].replace("ID:", ""), a[6].split("#")[1].replaceFirst("Domain:", ""), null);
			}
		}
		for(int x=0;x<a.length;x++){
			//System.out.println("elemento "+  x + "  " + a[x]  );
		}

	}


	void msg_correct(){

	}


	public String[] msg_verif(String[] toverif){	
		if(toverif.length<7 ||toverif.length>9){
			System.out.println("Mensaje erroneo");
			return null;
		}
		else if (Constantes.CORREPONDENCIA_ETIQUETA.equals(toverif[0].replaceAll("<", ""))){
			
			toverif[0]=Constantes.CORREPONDENCIA_ETIQUETA;
			String tmp = toverif[1];
			if (tmp.contains(Constantes.ATRIBUTO_ID)){
				toverif[1]=tmp.replace(Constantes.ATRIBUTO_ID + "=", "");
				tmp = toverif[2];
				
				if(tmp.contains(Constantes.ATRIBUTO_FROM)){
					toverif[2]=tmp.replace(Constantes.ATRIBUTO_FROM + "=", "");
					tmp = toverif[3];
					if(tmp.contains(Constantes.ATRIBUTO_TO)){
						toverif[3]=tmp.replace(Constantes.ATRIBUTO_TO + "=", "");
						tmp = toverif[4];
						if(tmp.contains(Constantes.ATRIBUTO_TYPE)){
							toverif[4]=tmp.replace(Constantes.ATRIBUTO_TYPE + "=", "");							
							tmp = toverif[5];
							if(tmp.contains(Constantes.ATRIBUTO_ACTION)){
								toverif[5]=tmp.replace(Constantes.ATRIBUTO_ACTION + "=", "");
								tmp = toverif[6];
								if(tmp.contains(Constantes.ATRIBUTO_CONTAINS)){
									toverif[6]=tmp.replace(Constantes.ATRIBUTO_CONTAINS + "=", "");
									tmp = toverif[7];
									if(tmp.contains(Constantes.ATRIBUTO_DETAILS)){
										toverif[7]=tmp.replace(Constantes.ATRIBUTO_DETAILS + "=", "").replaceFirst("/>", "");
										return toverif;
									}
								}			
								else if(tmp.contains(Constantes.ATRIBUTO_DETAILS)){
									
									toverif[6]=tmp.replace(Constantes.ATRIBUTO_DETAILS + "=", "").replaceFirst("/>", "");
									if(toverif.length==9){
										tmp = toverif[7].replace("/>", "");
										if(tmp.contains(Constantes.ATRIBUTO_IDA)){
											toverif[7]=tmp.replace(Constantes.ATRIBUTO_IDA + "=", "");
											tmp = toverif[8];
											if(tmp.contains(Constantes.ATRIBUTO_IDB)){
												toverif[8]=tmp.replace(Constantes.ATRIBUTO_IDB + "=", "");
												return toverif;
											}
										}
									}
									else return toverif;
								}
							}	
						}
					}
				}
			}
		}
		return null;
	}
}

