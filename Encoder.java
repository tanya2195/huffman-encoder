import java.util.*;
import java.io.*;

 class Node {
    public int data;
    public int frequency;
    public Node left;
    public Node right;

public Node(){
    this.data=-1;
    this.frequency=-1;
    this.left = null;
    this.right = null;
}
               }






 class FourWayHeap 
{
    Node head = null;
    
    ArrayList<Node> Heap = new ArrayList<Node>();  



    public FourWayHeap()
    {
       Heap.add(new Node());
       Heap.add(new Node());
       Heap.add(new Node());





    }
    
    
    public void insert(Node node)
    {
        Heap.add(node);
        heapifyUp(Heap.size()-1);
    }
    
    public void heapifyDown(int f)
    {
        if(f < Heap.size())
        {
           int child;
           int min = f;
           
           for(int i = 0; i < 4; i++)
           {
               child = 4*(f-3) + i+3;
               if(child >= Heap.size())
                   break;
               if(Heap.get(child).frequency <= Heap.get(min).frequency)
                   min = child;
           }         
               if(min != f)
               {  
                   Node minValue = Heap.get(min);
                   Node parentValue = Heap.get(f);
                   Heap.set(min, parentValue);
                   Heap.set(f,minValue);
                   heapifyDown(min);
               }
            }
         }
    
    public void heapifyUp(int child)
    {
        int parent = ((child-4)/4) + 3;
        
        Node parentNode = Heap.get(parent);
        Node childNode =Heap.get(child);
        
        if(parent > 3)
        {
            if(parentNode.frequency >= childNode.frequency)
            {
                Heap.set(parent, childNode);         
                Heap.set(child,parentNode);
                heapifyUp(parent);
            }
        }
    }
        public Node removeMin()
    {
        if(Heap.size()>4){
        Node p= Heap.get(3);
        Heap.set(3, Heap.get(Heap.size()-1));
        Heap.remove(Heap.size()-1);
        heapifyDown(3);
        return p;}
        else{
			if(Heap.size()==4){
            Node p= Heap.get(3);
           Heap.remove(3);
            return p;
			}else{
				return null;
			}

            
        }
    
   } 
}


























class Encoder{



static void buildCodeTable(Node root, String code, PrintWriter of)
 {
   
   if(root.left!=null || root.right!=null)
   {
	 String rightchild = code + "1";
     String leftchild = code + "0";
     
     if(root.left!=null){
       //If root's left is not null go to left for building code table
       buildCodeTable(root.left, leftchild, of);
     }
     if(root.right!=null){
       //If root's left is not null go to left for building code table
       buildCodeTable(root.right, rightchild, of);
     }
   }
   else
   {
     //Print data to code_table.txt
     of.println(root.data+" "+code);
	 of.flush();
   }
 }
	public static void main(String[] args){

try{
            String filename=args[0];
            HashMap<Integer, Integer> ftable = new HashMap<>();
            FileReader inputFile = new FileReader(filename); 
BufferedReader bufferReader = new BufferedReader(inputFile);
		String l;
			while ((l = bufferReader.readLine()) != null){
               
               if(l.length()!=0){
                int temp = Integer.parseInt(l);
                if(ftable.get(temp)==null){
                    ftable.put(temp,1);
                }else{
                    int count = ftable.get(temp);
                    count++;
                    ftable.put(temp,count);
                }
                }
            }
            Iterator iter = ftable.entrySet().iterator();
            FileWriter of= new FileWriter("freq.txt");
            BufferedWriter br= new BufferedWriter(of);
			
	 while (iter.hasNext()) {
				 Map.Entry pair = (Map.Entry)iter.next();
				   int d1 = Integer.parseInt(pair.getKey().toString());
				  int d2 = Integer.parseInt(pair.getValue().toString());
						
                        br.write(d1+" "+d2);
                        br.write('\n');
			  }
                    br.close();
            }catch(Exception e){
			e.printStackTrace();
			System.out.println("Error in reading file" + e.getMessage());  
			                    
		}



try{   
          FourWayHeap fh = new FourWayHeap();
               
        FileReader i = new FileReader("freq.txt"); 
	    BufferedReader br = new BufferedReader(i);
        String line;
        while ((line = br.readLine()) != null){
                
                String[] arr = line.split(" ");
               Node node= new Node();
                node.frequency=Integer.parseInt(arr[1]);
                node.data=Integer.parseInt(arr[0]);

                fh.insert(node);
           }


Node p = new Node();
		Node q=new Node();

                   Node last=new Node();
while(((p=fh.removeMin())!=null)&&((q=fh.removeMin())!=null))
{
Node c=new Node();
c.frequency=p.frequency+q.frequency;
c.left=p;
c.right=q;
fh.insert(c);
last=c;





  }
             
  String oFile = "code_table.txt";
     PrintWriter outFile = new PrintWriter(new FileOutputStream(new File(oFile)));
	 buildCodeTable(last,"",outFile);
	
 int arr[]=new int[1000];
 int top = 0;


}
 catch(Exception e)
        {
			e.printStackTrace();
            }



		try{
		HashMap<Integer,String> hcodes = new HashMap<>();
		FileReader inputFile = new FileReader("code_table.txt"); 
	    BufferedReader bufferReader = new BufferedReader(inputFile);
        String line="";
		while ((line = bufferReader.readLine()) != null){
			if(line.length()!=0){
			String[] arr = line.split(" ");
			int a1 =Integer.parseInt(arr[0]);
			String a2 =arr[1];
			hcodes.put(a1,a2);
			}
		}
String a=args[0];
		FileReader inputFile1 = new FileReader(a); 
	    BufferedReader bufferReader1 = new BufferedReader(inputFile1);
		BitSet bitbuff = new BitSet();
        line="";
		int counter = 0;
		while ((line = bufferReader1.readLine()) != null){
			if(line.length()!=0){
			int key = Integer.parseInt(line);
			if(hcodes.get(key)!=null){
				char[] c = hcodes.get(key).toCharArray();
				for(char i : c){
					if(i=='1')
						bitbuff.set(counter);
					else
						bitbuff.clear(counter);
					counter++;
				}
			}
			}
		}
		System.out.println(bitbuff.length());
		byte[] bytearr = bitbuff.toByteArray();
		FileOutputStream fout=new FileOutputStream("encoded.bin");    
		fout.write(bytearr);    
		fout.close();    
		System.out.println("success...");    
		}catch(Exception e){
			e.printStackTrace();
        }
	}
}
