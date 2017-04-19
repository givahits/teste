package br.bilac.planetas;

import com.sun.opengl.util.Animator;
import com.sun.opengl.util.GLUT;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLException;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

/**
 * Planetas.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel)
 * <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class Planetas implements GLEventListener {

    private float rotateSistemaSolar = 0.0f;
    private float rotateMercurio = 0.0f;
    private float rotateVenus = 0.0f;
    private float rotateTerra = 0.0f;
    private float rotateMarte = 0.0f;
    private float rotateJupter = 0.0f;
    private float rotateSaturno = 0.0f;
    private float rotateUrano = 0.0f;
    private float rotateNeptuno = 0.0f;
    private float rotatePlutao = 0.00f;
    private float rotateLua = 0.00f;
    private float velocidade = 0.0005f;
    private GLU glu = new GLU();
    private GLUquadric quadObj;

    public static void main(String[] args) {
        Frame frame = new Frame("SISTEMA SOLAR");
        GLCanvas canvas = new GLCanvas();

        canvas.addGLEventListener(new Planetas());
        frame.add(canvas);
        frame.setSize(1280, 720);
        final Animator animator = new Animator(canvas);
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                // Run this on another thread than the AWT event queue to
                // make sure the call to Animator.stop() completes before
                // exiting
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });
        // Center frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        animator.start();
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        // Use debug pipeline
        //drawable.setGL(new DebugGL(drawable.getGL()));

        GL gl = drawable.getGL();
        gl.glShadeModel(GL.GL_SMOOTH); // try setting this to GL_FLAT and see what happens.
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glClearDepth(1.0f);
        gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glDepthFunc(GL.GL_LEQUAL);
        gl.glHint(GL.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);

    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL gl = drawable.getGL();

        if (height <= 0) { // avoid a divide by zero error!

            height = 1;
        }
        final float h = (float) width / (float) height;

        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        final float fh = 0.5f;
        final float fw = fh * h;

        gl.glFrustum(-fw, fw, -fh, fh, 0.8f, 1000.0f);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        rotateSistemaSolar += 0.05f;
        rotateMercurio += (0.15f + velocidade);
        rotateVenus += (0.08f + velocidade);
        rotateTerra += (0.001f + velocidade);
        rotateMarte += (0.0009f + velocidade);
        rotateJupter += (-0.015f + velocidade);
        rotateSaturno += (0.025f + velocidade);
        rotateUrano += (-0.0001f + velocidade);
        rotateNeptuno += (-0.0003f + velocidade);
        rotatePlutao += (-0.00222f + velocidade);
        rotateLua += 0.3;

        GL gl = drawable.getGL();
        GLUT glut = new GLUT();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();

        float[] sol = {2.5f, 2.0f, 0.0f, 2.5f};
        float[] posicao = {200.0f, 300.0f, 100.0f, 0.0f};
        gl.glLightfv(gl.GL_LIGHT0, gl.GL_AMBIENT, sol, 0);
        //POSIÇÃO DA ILUMINAÇÃO
        gl.glLightfv(gl.GL_LIGHT0, gl.GL_POSITION, posicao, 1);
        gl.glEnable(gl.GL_LIGHT0);
        gl.glEnable(gl.GL_LIGHTING);
        gl.glTranslatef(0.0f, 0.0f, -25.0f);
        gl.glRotatef(rotateSistemaSolar, 0.0f, 1.0f, 0.0f);
        glut.glutSolidSphere(3.0, 50, 20);
        gl.glPopMatrix();

        gl.glPushMatrix();
        float[] merCurio = {1.0f, 0.0f, 0.0f};
        gl.glLightfv(gl.GL_LIGHT0, gl.GL_AMBIENT, merCurio, 0);
        gl.glTranslatef(0.0f, 0.0f, 0.0f);
        gl.glRotatef(rotateMercurio, 0.0f, 1.0f, 0.0f);
        gl.glTranslatef(-0.3f, -1.0f, 3.0f);
        gl.glRotatef(rotateMercurio, 0.0f, 1.0f, 0.0f);
        glut.glutWireSphere(0.18f, 20, 20);
        gl.glPopMatrix();

        gl.glPushMatrix();
        float[] veNus = {1.85f, 0.107f, 0.047f};
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_AMBIENT, veNus, 0);
        gl.glTranslatef(0.0f, 0.0f, 0.0f);
        gl.glRotatef(rotateVenus, 0.0f, 1.0f, 0.0f);
        gl.glTranslatef(0.6f, 0.05f, 3.5f);
        gl.glRotatef(rotateVenus, 0.0f, 1.0f, 0.0f);
        glut.glutSolidSphere(0.37, 20, 20);
        gl.glPopMatrix();

        gl.glPushMatrix();
        float[] teRra = {0.5f, 0.5f, 4.0f};
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_AMBIENT, teRra, 0);
        gl.glTranslatef(0.0f, 0.0f, 0.0f);
        gl.glRotatef(rotateTerra, 0.0f, 1.0f, 0.0f);
        gl.glTranslatef(-3.0f, -1.3f, 4.5f);
        gl.glRotatef(rotateTerra, 0.0f, 1.0f, 0.0f);
        glut.glutSolidSphere(0.47, 20, 20);
        gl.glPopMatrix();

        gl.glPushMatrix();
        float[] lua = {1.0f, 0.5f, 1.0f};
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_AMBIENT, lua, 0);
        gl.glTranslatef(0.0f, 0.0f, 0.0f);
        gl.glRotatef(rotateTerra, 0.0f, 1.0f, 0.0f);
        gl.glTranslatef(-3.0f, -1.3f, 4.5f);
        gl.glRotatef(this.rotateLua, 0.0f, 1.0f, 0.0f);
        gl.glTranslatef(-1.5f, 0.0f, 0.2f);
        gl.glRotatef(this.rotateLua, 0.0f, 1.0f, 0.0f);
        glut.glutSolidSphere(0.1, 20, 20);
        gl.glPopMatrix();

        gl.glPushMatrix();
        float[] maRte = {1.5f, 0.5f, 0.147f};
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_AMBIENT, maRte, 0);
        gl.glTranslatef(0.0f, 0.0f, 0.0f);
        gl.glRotatef(rotateMarte, 0.0f, 1.0f, 0.0f);
        gl.glTranslatef(-2.0f, -1.0f, 7.0f);
        gl.glRotatef(rotateMarte, 0.0f, 1.0f, 0.0f);
        glut.glutSolidSphere(0.22, 20, 20);
        gl.glPopMatrix();

        gl.glPushMatrix();
        float[] juPter = {0.212f, 0.228f, 0.544f};
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_AMBIENT, juPter, 0);
        gl.glTranslatef(0.0f, 0.0f, 0.0f);
        gl.glRotatef(rotateJupter, 0.0f, 1.0f, 0.0f);
        gl.glTranslatef(8.0f, -1.0f, 4.0f);
        gl.glRotatef(rotateJupter, 0.0f, 1.0f, 0.0f);
        glut.glutSolidSphere(1.0f, 20, 20);
        gl.glPopMatrix();

        gl.glPushMatrix();
        float[] satuRno = {0.3f, 0.6f, 0.3f};
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_AMBIENT, satuRno, 0);
        gl.glTranslatef(0.0f, 0.0f, 0.0f);
        gl.glRotatef(rotateSaturno, 0.0f, 1.0f, 0.0f);
        gl.glTranslatef(-10.0f, -1.0f, 5.5f);
        gl.glRotatef(rotateSaturno, 0.0f, 1.0f, 0.0f);
        glut.glutSolidSphere(0.8f, 20, 20);
        gl.glPopMatrix();

        gl.glPushMatrix();
        float[] urAno = {1.0f, 2.0f, 255.0f};
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_AMBIENT, urAno, 0);
        gl.glTranslatef(0.0f, 0.0f, 0.0f);
        gl.glRotatef(rotateUrano, 0.0f, 1.0f, 0.0f);
        gl.glTranslatef(10.0f, -1.0f, 10.5f);
        gl.glRotatef(rotateUrano, 0.0f, 1.0f, 0.0f);
        glut.glutSolidSphere(0.5f, 20, 20);
        gl.glPopMatrix();

        gl.glPushMatrix();
        float[] nepTuno = {0.5f, 2.5f, 4.0f};
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_AMBIENT, nepTuno, 0);
        gl.glTranslatef(0.0f, 0.0f, 0.0f);
        gl.glRotatef(rotateNeptuno, 0.0f, 1.0f, 0.0f);
        gl.glTranslatef(-12.0f, -1.0f, -7.5f);
        gl.glRotatef(rotateNeptuno, 0.0f, 1.0f, 0.0f);
        glut.glutSolidSphere(0.4f, 20, 20);
        gl.glPopMatrix();

        gl.glPushMatrix();
        float[] pluTao = {0.0f, 2.0f, 2.0f};
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_AMBIENT, pluTao, 0);
        gl.glTranslatef(0.0f, 0.0f, 0.0f);
        gl.glRotatef(rotatePlutao, 0.0f, 1.0f, 0.0f);
        gl.glTranslatef(12.0f, -1.0f, -11.7f);
        gl.glRotatef(rotatePlutao, 0.0f, 1.0f, 0.0f);
        glut.glutSolidSphere(0.3f, 20, 20);
        gl.glPopMatrix();

        gl.glFlush();

    }

    public void glutSolidSphere(double radius, int slices, int stacks) {
        quadObjInit(glu);
        glu.gluQuadricDrawStyle(quadObj, GLU.GLU_FILL);
        glu.gluQuadricNormals(quadObj, GLU.GLU_SMOOTH);
        /* If we ever changed/used the texture or orientation state
       of quadObj, we'd need to change it to the defaults here
       with gluQuadricTexture and/or gluQuadricOrientation. */
        glu.gluSphere(quadObj, radius, slices, stacks);
    }

    private void quadObjInit(GLU glu) {
        if (quadObj == null) {
            quadObj = glu.gluNewQuadric();
        }
        if (quadObj == null) {
            throw new GLException("Out of memory");
        }
    }

    @Override
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
}
