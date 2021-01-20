public class Body {
    /** instance variable for body     */
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
 
    /** constructor */
    public Body(double xP, double yP, double xV,
              double yV, double m, String img) {
    xxPos = xP;
    yyPos = yP;
    xxVel = xV;
    yyVel = yV;
    mass = m;
    imgFileName = img;
    }
    /** Copy constructor */
    public Body(Body b) {
    
    xxPos = b.xxPos;
    yyPos = b.yyPos;
    xxVel = b.xxVel;
    yyVel = b.yyVel;
    mass = b.mass;
    imgFileName = b.imgFileName;
    }
    /** methods */
    public double calcDistance(Body b) {
    double dx = xxPos - b.xxPos;
    double dy = yyPos - b.yyPos;
    return Math.sqrt(dx * dx + dy * dy);
    }


    public double calcForceExertedBy(Body b) {
    double G = 6.67e-11 ; 
    if (this.equals(b)) {
        return 0;
    }
    return G * mass * b.mass / Math.pow(calcDistance(b), 2);
    }

    public double calcForceExertedByX(Body b) {
    double dx = b.xxPos - xxPos;
    return calcForceExertedBy(b) * dx / calcDistance(b);  
    
    }

    public double calcForceExertedByY(Body b) {
    double dy = b.yyPos - yyPos;
    return calcForceExertedBy(b) * dy / calcDistance(b);  
    
    }

    public double calcNetForceExertedByX(Body[] bodys) {
    double netForce = 0;
    for (Body b: bodys) {
        if (! this.equals(b)) {
            netForce += calcForceExertedByX(b);
        }
    }
    return netForce;
    }

    public double calcNetForceExertedByY(Body[] bodys) {
    double netForce = 0;
    for (Body b: bodys) {
        if (! this.equals(b)) {
            netForce += calcForceExertedByY(b);
        }
    }
    return netForce;
    }

    public void update(double time, double Fx, double Fy) {
    double ax = Fx / mass;
    double ay = Fy / mass;
    xxVel += ax * time;
    yyVel += ay * time;
    xxPos += xxVel * time;
    yyPos += yyVel * time;
    }
    
    public void draw() {
    StdDraw.picture(xxPos, yyPos,"./images/" + imgFileName);
    

    }
}
