public class Point3 // extends Object
{
   //====== Fields

   private int x;
   private int y;

   //====== Methods (vardai prasideda mažąja raide)

   public void println () {
      System.out.println("(" + x + "," + y + ")");
   }

   public void clear() {x = 0; y = 0;}

   public void move(int dx, int dy)
   {
      int xLocal = x + dx; x = xLocal;  // Two steps
      y += dy;
   }

   public double length() { return Math.sqrt (x*x + y*y); }

   public double lengthTo (Point3 p) {
      // 1 version
         return Math.sqrt((x - p.x) * (x-p.x) + (y - p.y) * (y-p.y));

      // 2 version
         /*Point3 pTmp = new Point3();
         pTmp.x = x - p.x; pTmp.y = y - p.y;
         return pTmp.length();*/
   }

   // Get/set methods
   public int getX () {return x;}
   public void setX (int xx){x = xx;}

   // Testing Point3
   public static void main(String[] args)  {
      Point3 p1  = new Point3();  p1.println(); // (0,0)
      p1.move(3,4); p1.println(); // (3,4)

      System.out.println(p1.lengthTo(new Point3())); // 5.0

      Point3 p2 = p1;  p2.println(); // (3,4)    // (1,2)
      p2.clear(); p1.println();      // (0,0)
   }
}
