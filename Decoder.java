import java.io.*;
import java.util.*;

class Decoder {
	
	static hufNode root = new hufNode(-1, -1, null, null);
	
	public static void main(String[] args) throws IOException{
String co=args[1];

			File f = new File(co);
			FileReader fr = new FileReader(f);
			       BufferedReader br = new BufferedReader(fr);
			String str="";
			        while((str=br.readLine())!=null && str.length()>0){
				decodertree(str);
			}
                        String en=args[0];
			File encoded = new File(en);String num;
			byte[] bytearr = new byte[(int)encoded.length()];
			FileInputStream p = new FileInputStream(encoded);
			p.read(bytearr);p.close();
			System.setProperty("line.separator", "\n");File of = new File("decoded.txt");
			of.createNewFile();
			BitSet bitset = BitSet.valueOf(bytearr);
			PrintWriter pwrite = new PrintWriter(of);traversal(bitset, pwrite);
	}
	
	static void decodertree(String s){
		String []str = s.split(" ");
		String []path = str[1].split("");
		hufNode head = root;
		
		int i = 0;
		while(i<path.length){	
			if(path[i].equals("0")){
				if(head.left!=null){
					head = head.left;
				}
				else{
					head.left = new hufNode(-1,-1,null,null);head = head.left;
				}
			}
			else{
				if(head.right!=null){
					head = head.right;
				}
				else{
					head.right = new hufNode(-1,-1,null,null);
					head = head.right;}}
			i++;}head.data = Integer.parseInt(str[0]);}
	
	static void traversal(BitSet b, PrintWriter pwr){
		int length = b.length();hufNode currentnode = root;
		int i =0;
		while(i<=length){
			//if bit is 1
			if(b.get(i) == true){
				currentnode = currentnode.right;
				if(currentnode.left==null && currentnode.right==null){
					pwr.println(""+currentnode.data);
					currentnode=root;
				}
			}
			//if bit is 0
			else{
				currentnode = currentnode.left;
				if(currentnode.left==null && currentnode.right==null){
					pwr.println(""+currentnode.data);
					currentnode=root;
				}
			}
			i++;
		}
		pwr.flush();
		pwr.close();
	}
}
class hufNode{
	int data;
	int frequency;
	hufNode left;
	hufNode right;
	
	hufNode(int d, int f, hufNode l, hufNode r){
		this.data = d;
		this.frequency = f;
		this.left = l;
		this.right = r;
	}
}
