package bbst;
import com.sun.webkit.Timer;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.Clock;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static bbst.bbst.nil;

public class bbst {
    static int ip[][];
    
    static node nil=new node(1,2,3);
    static node root=nil;

 
  public static void createTree(File f) throws FileNotFoundException, IOException
    {
     
        String content;
        FileInputStream fis = new FileInputStream(f);
                int a=-1;
                   DataInputStream in = new DataInputStream(fis);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        int j=Integer.parseInt(br.readLine());
                while ((content=br.readLine())!=null) 
                {
                    
                    String b[]= content.split(" ");
       
                    insert(new node(Integer.parseInt(b[0]),Integer.parseInt(b[1])));
   a=a+1;
                }
     
    }

 public static int delete(node z)  
 {
          if(z==nil)
              return 0;
        
        else 
          {
            node x;
            node y = z;
            int yoc = y.color;

            if (z.left == nil) {
                x = z.right;
                transplant(z, z.right);
            } else if (z.right == nil) {
                x = z.left;
                transplant(z, z.left);
            } else {
                y = tree_min(z.right);
                yoc = y.color;
                x = y.right;
                if (y.parent == z)
                    x.parent = y;
                else {
                    transplant(y, y.right);
                    y.right = z.right;
                    y.right.parent = y;
                }
                transplant(z, y);
                y.left = z.left;
                y.left.parent = y;
                y.color = z.color;
            }
            if (yoc == 0)
                Delete_Fix(x);
            return 0;
        }
 }
 
 public static node tree_min(node x)
 {

     while(x.left!=nil)
     {
         x=x.left;
     }
     return x;
 }
 public static void transplant(node u, node v)
 {

     if(u.parent==nil)
         root=v;
     else if(u==u.parent.left)
         u.parent.left=v;
     else
         u.parent.right=v;
     
     v.parent=u.parent;
 }
 
 public static void Delete_Fix(node x)
 {
    while(x!=root && x.color == 0){ 
            if(x == x.parent.left){
                node w = x.parent.right;
                if(w.color == 1){
                    w.color = 0;
                    x.parent.color = 1;
                    Left_Rotate(x.parent);
                    w = x.parent.right;
                }
                if(w.left.color == 0 && w.right.color == 0){
                    w.color = 1;
                    x = x.parent;
                    continue;
                }
                else if(w.right.color == 0){
                    w.left.color = 0;
                    w.color = 1;
                    Right_Rotate(w);
                    w = x.parent.right;
                }
                if(w.right.color == 1){
                    w.color = x.parent.color;
                    x.parent.color = 0;
                    w.right.color = 0;
                    Left_Rotate(x.parent);
                    x = root;
                }
            }else{
                node w = x.parent.left;
                if(w.color == 1){
                    w.color = 0;
                    x.parent.color = 1;
                    Right_Rotate(x.parent);
                    w = x.parent.left;
                }
                if(w.right.color == 0 && w.left.color == 0){
                    w.color = 1;
                    x = x.parent;
                    continue;
                }
                else if(w.left.color == 0){
                    w.right.color = 0;
                    w.color = 1;
                    Left_Rotate(w);
                    w = x.parent.left;
                }
                if(w.left.color == 1){
                    w.color = x.parent.color;
                    x.parent.color = 0;
                    w.left.color = 0;
                    Right_Rotate(x.parent);
                    x = root;
                }
            }
        }
        x.color = 0; 
 }
static int k=0;

public static void printTree(node a)
{
 
  if(a!=nil)
     
  {
    
      System.out.println("ID:  "+a.ID);
      System.out.println("Color: "+a.color);
     // System.out.println("Parent ID: "+a.parent.ID);
      System.out.println("Left Child ID: "+a.left.ID);
      
      
      System.out.println("Right Child ID: "+a.right.ID);
      
      System.out.println();
      System.out.println();
      
      
      printTree(a.left);
      
      printTree(a.right);
     
  }

}





 public static node insert(node x) throws NullPointerException{
     
    node y = root;
        if (root == nil) {
            root = x;
            x.color = 0;
            x.parent = nil;
            x.left=nil;
            x.right=nil;
        } else {
            x.color = 1;
            while (true) {
                if (x.ID < y.ID) {
                    if (y.left == nil) {
                        y.left = x;
                        x.parent = y;
                        break;
                    } else {
                        y = y.left;
                    }
                } else if (x.ID >= y.ID) {
                    if (y.right == nil) {
                        y.right = x;
                        x.parent = y;
                        
                        break;
                    } else {
                        y = y.right;
                    }
                }
            
           
        }
             
 } Insert_Fix(x); 
        return x;
 }
 

    
    public static void Insert_Fix(node x) {
       while (x.parent.color == 1) {
            node y = nil;
            if (x.parent == x.parent.parent.left) {
                y = x.parent.parent.right;

                if (y != nil && y.color == 1) {
                    x.parent.color = 0;
                    y.color = 0;
                    x.parent.parent.color = 1;
                    x = x.parent.parent;
                    
                } 
                else if (x == x.parent.right) {
                   
                    x = x.parent;
                    Left_Rotate(x);
                } 
                x.parent.color = 0;
                x.parent.parent.color = 1;
               
                
                Right_Rotate(x.parent.parent);
            } else {
                y = x.parent.parent.left;
                 if (y != nil && y.color == 1) {
                    x.parent.color = 0;
                    y.color = 0;
                    x.parent.parent.color = 1;
                    x = x.parent.parent;
                    continue;
                }
                if (x == x.parent.left) {
                    
                    x = x.parent;
                    Right_Rotate(x);
                }
                x.parent.color = 0;
                x.parent.parent.color = 1;
                
                
                Left_Rotate(x.parent.parent);
            }
        }
        root.color = 0;
    }
    
    

   public static void Left_Rotate(node x) {
        if (x.parent != nil) {
            if (x == x.parent.left) {
                x.parent.left = x.right;
            } else {
                x.parent.right = x.right;
            }
            x.right.parent = x.parent;
            x.parent = x.right;
            if (x.right.left != nil) {
                x.right.left.parent = x;
            }
            x.right = x.right.left;
            x.parent.left = x;
        } else {
            node right = root.right;
            root.right = right.left;
            right.left.parent = root;
            root.parent = right;
            right.left = root;
            right.parent = nil;
            root = right;
        }
    }

    public static void Right_Rotate(node x) {
        
        if (x.parent != nil) {
            if (x == x.parent.left) {
                x.parent.left = x.left;
            } else {
                x.parent.right = x.left;
            }

 x.left.parent = x.parent;
            x.parent = x.left;
            if (x.left.right != nil) {
              x.left.right.parent = x;
            }
            x.left = x.left.right;
            x.parent.right = x;
        }
        else 
        {
            node left = root.left;
            root.left = root.left.right;
            left.right.parent = root;
            root.parent = left;
            left.right = root;
            left.parent = nil;
            root = left;
        }
    }

    
    
 public static int Increase(int ID, int m)
 {
     node a=Find_Node(root,ID);
     if(a==nil)
     {
         
         a=insert(new node(ID,m));
     }
     else
     {
         a.count=a.count+m;
     }
         System.out.println("Count After Insertion           :  "+a.count);
     
     return 0;
 }
 public static int Reduce(int ID, int m)
 {
     node a=Find_Node(root,ID);
     if(a==nil)
     {
         System.out.println(0);
     }
     else
         a.count=a.count-m;
     
     if(a.count<=0)
     {
         delete(a);     
         
     }
     
     node c=Find_Node(root,ID);
     if(c==nil)
     {
         System.out.println("Count:     "+0);
     }
     else
         System.out.println("Count:     "+c.count);
     return 0;
 }
 
 public static node Find_Node(node a, int ID) throws NullPointerException
 {
    node x=a;
   if(x.ID==ID)
         return x;
   else
   {
 if(x.ID<ID)
 {
     x=x.right;
     
 }
 else if(x.ID>ID)
 {
     x=x.left;
  }
   }
  
   if(x!=nil)
 x=Find_Node(x,ID);
     
     
   
 
return x;	
 }
 
 public static int Count(int ID)
 {
     node a=Find_Node(root,ID);
     if(a==nil)
     {
         System.out.println("Count : 0");
     }
     else
         System.out.println("Count  :"+a.count);
     
     
     return 0;
 }
 
public static node Next(int k)
{
    node n=Find_Node(root, k);
    
    if(n.right!=nil)
    {
        n = n.right;
        while (n.left != nil)
            n=n.left;
        return n;
    }

    while(n.parent!=nil)
    {
        if(n.parent.left == n)
            return n.parent;
        n = n.parent;
    }
    return new node(0,0);

}
 
public static node Previous(int k)
{
    node n=Find_Node(root, k);
    if(n.left!=nil)
    {
        n = n.left;
        while (n.right != nil)
            n=n.right;
        return n;
    }

    while(n.parent!=nil)
    {
        if(n.parent.right == n)
            return n.parent;
        n = n.parent;
    }
    return new node(0,0);

}
static int count=0;
 public static int inRange(node root,int k1,int k2){
		if(root==nil)
	        return count;
	    if(root.ID >= k1)
	    	inRange(root.left, k1, k2);
	    if(root.ID >=k1 && root.ID <=k2)	        
	    count=count+root.count;
            
	    if(root.ID < k2)
	    	inRange(root.right,k1,k2);
	    return count;
    }
    
   
 public static void main(String args[]) throws FileNotFoundException, IOException 
    {
        nil.parent=nil;
        nil.left=nil;
        nil.right=nil;
        
        count=0;
      
 String f = args[0];
       
  File file=new File(f);
createTree(file);
  
      
        


        boolean quit = false;
        System.out.println("INPUT FORMAT");
        System.out.println("increase id m");
        System.out.println("reduce id m");
        System.out.println("count id");
        System.out.println("inrange id m");
        System.out.println("next id");
        System.out.println("previous id");
        System.out.println("to exit //");
	BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
	while(!quit)
        {
            count=0;
	    String command = sin.readLine();
	    String[] com = command.split(" ");
	    if (com[0].equals("increase"))
            {
		int id = Integer.parseInt(com[1]);
		int count = Integer.parseInt(com[2]);
		Increase(id, count);
		
	    }
	    else if (com[0].equals("reduce"))
            {
		int id = Integer.parseInt(com[1]);
		int count = Integer.parseInt(com[2]);
		node delnode = new node(id, count);
		Reduce(id,count);
		
	    }
	    else if (com[0].equals("count"))
            {
		int id = Integer.parseInt(com[1]);
		Count(id);
	    }
	    else if (com[0].equals("inrange"))
         {
		int id1 = Integer.parseInt(com[1]);
		int id2 = Integer.parseInt(com[2]);
		int c=inRange(root,id1, id2);
                System.out.println("Range Sum:       "    +        c);
	    }
	    else if (com[0].equals("next"))
            {
		int id = Integer.parseInt(com[1]);
		              node n=Next(id);
                              System.out.println("ID:   "+n.ID+"      Count:    "+n.count);
	    }
	    else if (com[0].equals("previous"))
            {
		int id = Integer.parseInt(com[1]);
		node n = Previous(id);
		System.out.println("ID:   "+n.ID+"      Count:    "+n.count);

	    }
	    else if (com[0].equals("//"))
            {
		quit = true;
	    }
	}   
        
    }
    
    
}

class node
{
    
    
    int count;
    int ID;
        node left=nil;
    node right=nil;
    int color;
    node parent=nil;
    /*
    
    */
    
    node(int id, int cnt)
    {
        ID=id;
        count=cnt;
                color=1;
    }
    node(int id, int cnt, int color)
    {
        ID=-99;
        count=-99;
                color=1;
    }
    node()
    {
        ID=0;
        count=0;
                color=1;
    }
}   