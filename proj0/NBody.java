public class NBody{
    public static double readRadius(String path) {
        In in = new In(path);
        in.readInt();
        return in.readDouble();        
    }
    public static Body[] readBodies(String path) {
        In in = new In(path);
        int N = in.readInt();
        in.readDouble();
        Body[] bodies = new Body[N];
        for(int i = 0; i < N; i++) {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            bodies[i] = new Body(xP, yP, xV, yV, m, img);
            
        }
        return bodies;
    }
    
    public static void main(String[] args) {
        double T = Double. parseDouble(args[0]);
        double dt = Double. parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Body[] bodies = readBodies(filename);

        /** Set the scale to match the radius of universe */
        StdDraw.setXscale(-radius, radius);
        StdDraw.setYscale(-radius, radius);
        StdDraw.picture(0,0,"images/starfield.jpg", 2*radius, 2*radius); 
        for (Body b: bodies) {
            b.draw();
        } 
        StdDraw.enableDoubleBuffering();
        double time = 0;
        double[] XForce = new double [bodies.length];
        double[] YForce = new double [bodies.length];
        while (time < T ) {
           for (int i = 0; i < bodies.length; i++) {
               XForce[i] = bodies[i].calcNetForceExertedByX(bodies);
               YForce[i] = bodies[i].calcNetForceExertedByY(bodies);
           }
           for (int i = 0; i < bodies.length; i++) {
               bodies[i].update(dt, XForce[i], YForce[i]);
           } 
           StdDraw.picture(0,0,"images/starfield.jpg", 2*radius, 2*radius); 
           for (Body b: bodies) {
               b.draw();

           } 
           StdDraw.show();
           StdDraw.pause(10);
           time += dt;
                
        }
    }








}
